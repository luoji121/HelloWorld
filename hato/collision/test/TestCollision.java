package collision.test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import junit.framework.TestCase;
import util.ProxyUtils;
import util.SpatialIndexUtil;

public class TestCollision extends TestCase{
	public static final String GROUP_JOINER = "#";
	SpatialIndexUtil siu = SpatialIndexUtil.getEarthSpatialUtil(14);
	String url = "http://c1:8080/EzDBaaSManager/StreamQASServlet";
	JsonParser jp = new JsonParser();
	DateTimeFormatter dtf = DateTimeFormat.forPattern("yyyyMMddHHmmss");
	int aroundSeconds = 3;
	
	public void testDirectGet() {
		testDirectGet("10005", "20120401110000", "20120401110500");
	}
	
	//由于EzBigData仅支持spatial索引编号开头，后面只能跟主表Rowkey。所以如果需要Rowkey为spatial+时间+gpsid的情况，请使用
	//添加数据处理脚本的方式，添加一个字spatial四叉树索引字段，在处理脚本中计算四叉树索引编号，再利用这个字段组合Rowkey
	public void testDirectGet(String gpsid, String startTime, String stopTime) {
//		String gpsid = "10005";
//		String startTime = "20120401110000";
//		String stopTime = "20120401110500";
		JsonObject joQuery = new JsonObject();
		joQuery.addProperty("hbaseInstance", "HBase01");
		joQuery.addProperty("hbaseTable", "gps0401_spatial");
		joQuery.addProperty("qasType", "KvQueryByStartAndStopRowkey");
		JsonObject joParams = new JsonObject();
		joParams.addProperty("startRowkey", gpsid+GROUP_JOINER+startTime);
		joParams.addProperty("stopRowkey", gpsid+GROUP_JOINER+stopTime);
		joQuery.add("params", joParams);
		String resp = ProxyUtils.httpPost(joQuery.toString(), url);
//		System.out.println(resp);
		
		JsonArray jaResult = jp.parse(resp).getAsJsonObject().get("result").getAsJsonArray();
//		for(JsonElement je:jaResult) {
//			ja
//		}
		
		List<String> resultStringList = new ArrayList<>();
//		Set<String> squareSet = new LinkedHashSet<>();
		jaResult.forEach(e -> {
			JsonObject joCur = e.getAsJsonObject();
			JsonObject curResult = new JsonObject();
			DateTime dt = dtf.parseDateTime(joCur.get("TIME").getAsString());
			String startTimeCur = dtf.print(dt.minusSeconds(aroundSeconds));
			String stopTimeCur = dtf.print(dt.plusSeconds(aroundSeconds));
			
			double xCur = Double.parseDouble(joCur.get("X").getAsString());
			double yCur = Double.parseDouble(joCur.get("Y").getAsString());
			String curSquare = siu.xy2spatialIndex(xCur, yCur);
			List<String> matrix = siu.getMatrix(curSquare);//修改此处，或改为只有curSquare一个的List
			JsonArray curArray = new JsonArray();
			JsonObject curQuery = new JsonObject();
			curQuery.addProperty("hbaseInstance", "HBase01");
			curQuery.addProperty("hbaseTable", "gps0401_spatial2-traceSpatial");
			curQuery.addProperty("qasType", "KvQueryByStartAndStopRowkey");
			matrix.forEach(position -> {
				String startRow = position+GROUP_JOINER+startTimeCur;
				String stopRow = position+GROUP_JOINER+stopTimeCur;
				JsonObject curParams = new JsonObject();
				curParams.addProperty("startRowkey", startRow);
				curParams.addProperty("stopRowkey", stopRow);
				curQuery.add("params", curParams);
				JsonObject curInnerResult = jp.parse(ProxyUtils.httpPost(curQuery.toString(), url)).getAsJsonObject();
				JsonArray curJsonArray = curInnerResult.get("result").getAsJsonArray();
				Iterator<JsonElement> curJsonArrayIter = curJsonArray.iterator();
				while(curJsonArrayIter.hasNext()) {
					JsonObject curJoIter = curJsonArrayIter.next().getAsJsonObject();
					if(!curJoIter.has("GPSID") || gpsid.equals(curJoIter.get("GPSID").getAsString())) {
						curJsonArrayIter.remove();
					}
				}
				curArray.addAll(curJsonArray);
			});
			curResult.add("result", curArray);
			resultStringList.add(curResult.toString());
		});
		
		System.out.println(resultStringList);
	}
	
	
	
}




