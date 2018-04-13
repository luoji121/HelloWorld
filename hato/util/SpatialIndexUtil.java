package util;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class SpatialIndexUtil {
	/*最东端 东经135度2分30秒 黑龙江和乌苏里江交汇处 按135.1算
	最西端 东经73度40分 帕米尔高原乌兹别里山口（乌恰县）按73.6算
	最南端 北纬3度52分 南沙群岛曾母暗沙  按3.8算
	最北端 北纬53度33分 漠河以北黑龙江主航道（漠河县）按53.6算*/
	private double basicX ;//
	private double basicY;
	private double totalLongitude;//经度总长
	private double totalLatitude;//纬度总长
	private double spatialLevel;
	private double basicXDivideLength;
	private double basicYDivideLength;
	
	private SpatialIndexUtil(){
		
	}
	public static SpatialIndexUtil getChinaSpatialUtil(double spatiallevel){//spatiallevel只要不大于原有数据导入时的spatiallevel即可？
		//更低的spatialLevel适用于更大范围？
		SpatialIndexUtil siu = new SpatialIndexUtil();
		siu.basicX=73.6;
		siu.basicY=53.6;
		siu.totalLongitude=61.5;
		siu.totalLatitude=49.8;
		siu.spatialLevel=spatiallevel;
		siu.basicXDivideLength =61.5/ (Math.pow(2,spatiallevel));
		siu.basicYDivideLength = 49.8/ (Math.pow(2,spatiallevel));
		return siu;
		
	}
	
	public static SpatialIndexUtil getEarthSpatialUtil(double spatiallevel){
		SpatialIndexUtil siu = new SpatialIndexUtil();
		siu.basicX=-180.0;
		siu.basicY=90;
		siu.totalLongitude=360;
		siu.totalLatitude=180;
		siu.spatialLevel=spatiallevel;
		siu.basicXDivideLength =360/ (Math.pow(2,spatiallevel));
		siu.basicYDivideLength = 180/ (Math.pow(2,spatiallevel));
		return siu;
	}
	
	public String xy2spatialIndex(double x, double y){
		double offsetX = x-basicX;
		double offsetY = basicY-y;
		boolean isXodd;
		boolean isYodd;
		String resultString="";
		for(int i=1;i<=spatialLevel;i++){
			double dividesXLength = basicXDivideLength*(Math.pow(2,spatialLevel-i));
			double dividesYLength = basicYDivideLength*(Math.pow(2,spatialLevel-i));
			isXodd = isOdd((int) (offsetX/dividesXLength) );
			isYodd = isOdd((int) (offsetY/dividesYLength) );
			if(isXodd && isYodd){
				resultString=resultString.concat("3");
			}
			else if(!isXodd && isYodd){
				resultString=resultString.concat("2");
			}
			else if(isXodd && !isYodd){
				resultString=resultString.concat("1");
			}
			else if(!isXodd && !isYodd){
				resultString=resultString.concat("0");
			}
		}
		return resultString;
		
	}
	
	public boolean isOdd(int i){//按位与判断奇偶
		return (i&1)!=0; 
	}
	
	/**
	 * 获取落入矩形内部的栅格,更少的位数代表更大的方块。如何合并四叉树？无法合并？
	 * 
	 */
	public List<String> getInnerSquares(double leftTopX, double leftTopY, double rightBottomX, double rightBottomY){
		String leftTopSquare = moveRight(moveDown(xy2spatialIndex(leftTopX, leftTopY)));
		String rightTopSquare = moveLeft(moveDown(xy2spatialIndex(rightBottomX, leftTopY)));
		String leftBottomSquare = moveRight(moveUp(xy2spatialIndex(leftTopX, rightBottomY)));
		String rightBottomSquare = moveLeft(moveUp(xy2spatialIndex(rightBottomX, rightBottomY)));
		List<String> lsSquare = new ArrayList<String>();
		String curRowBeginSquare = leftTopSquare;
		String curRowEndSquare = rightTopSquare;
		String curSquare = leftTopSquare;
		while(curRowBeginSquare.compareTo(leftBottomSquare)<=0){
			curSquare = curRowBeginSquare;
			while (curSquare.compareTo(curRowEndSquare)<=0) {
				lsSquare.add(curSquare);
				curSquare = moveRight(curSquare);
			}
			curRowBeginSquare = moveDown(curRowBeginSquare);
			curRowEndSquare = moveDown(curRowEndSquare);
		}
		return lsSquare;
		
	}
	
	/**
	 * 获取与选取矩形相交的栅格
	 */
	public List<String> getCrossSquares(double leftTopX, double leftTopY, double rightBottomX, double rightBottomY){
		String leftTopSquare = xy2spatialIndex(leftTopX, leftTopY);
		String rightTopSquare = xy2spatialIndex(rightBottomX, leftTopY);
		String leftBottomSquare = xy2spatialIndex(leftTopX, rightBottomY);
		String rightBottomSquare = xy2spatialIndex(rightBottomX, rightBottomY);

		Set<String> lsSquare = new HashSet<String>();
		String curSquare = leftTopSquare;
		lsSquare.add(curSquare);
		while(curSquare.compareTo(rightTopSquare)<0){
			curSquare=moveRight(curSquare);
			lsSquare.add(curSquare);
		}
		while(curSquare.compareTo(rightBottomSquare)<0){
			curSquare=moveDown(curSquare);
			lsSquare.add(curSquare);
		}
		while(curSquare.compareTo(leftBottomSquare)>0){
			curSquare=moveLeft(curSquare);
			lsSquare.add(curSquare);
		}
		while(curSquare.compareTo(leftTopSquare)>0){
			curSquare=moveUp(curSquare);
			lsSquare.add(curSquare);
		}
		return new ArrayList<String>(lsSquare);
		
	}
	
public String moveUp(String position){
		StringBuffer sb= new StringBuffer(position);
		int charIndex=sb.length()-1;
		while (true){
			if(sb.charAt(charIndex)=='0')
				sb=sb.replace(charIndex, charIndex+1, "2");
			else if(sb.charAt(charIndex)=='1')
				sb=sb.replace(charIndex, charIndex+1, "3");
			else if(sb.charAt(charIndex)=='2'){
				sb=sb.replace(charIndex, charIndex+1, "0");
				break;
			}
			else if(sb.charAt(charIndex)=='3'){
				sb=sb.replace(charIndex, charIndex+1, "1");
				break;
			}
				charIndex--;	
		}
		return sb.toString();
	}
	
public String moveDown(String position){
	StringBuffer sb= new StringBuffer(position);
	int charIndex=sb.length()-1;
	while (true){
		if(sb.charAt(charIndex)=='2')
			sb=sb.replace(charIndex, charIndex+1, "0");
		else if(sb.charAt(charIndex)=='3')
			sb=sb.replace(charIndex, charIndex+1, "1");
		else if(sb.charAt(charIndex)=='0'){
			sb=sb.replace(charIndex, charIndex+1, "2");
			break;
		}
		else if(sb.charAt(charIndex)=='1'){
			sb=sb.replace(charIndex, charIndex+1, "3");
			break;
		}
			charIndex--;	
	}
	return sb.toString();
	}
public String moveLeft(String position){
	StringBuffer sb= new StringBuffer(position);
	int charIndex=sb.length()-1;
	while (true){
		if(sb.charAt(charIndex)=='0')
			sb=sb.replace(charIndex, charIndex+1, "1");
		else if(sb.charAt(charIndex)=='2')
			sb=sb.replace(charIndex, charIndex+1, "3");
		else if(sb.charAt(charIndex)=='1'){
			sb=sb.replace(charIndex, charIndex+1, "0");
			break;
		}
		else if(sb.charAt(charIndex)=='3'){
			sb=sb.replace(charIndex, charIndex+1, "2");
			break;
		}
			charIndex--;	
	}
	return sb.toString();
}
public String moveRight(String position){
	StringBuffer sb= new StringBuffer(position);
	int charIndex=sb.length()-1;
	while (true){
		if(sb.charAt(charIndex)=='1')
			sb=sb.replace(charIndex, charIndex+1, "0");
		else if(sb.charAt(charIndex)=='3')
			sb=sb.replace(charIndex, charIndex+1, "2");
		else if(sb.charAt(charIndex)=='0'){
			sb=sb.replace(charIndex, charIndex+1, "1");
			break;
		}
		else if(sb.charAt(charIndex)=='2'){
			sb=sb.replace(charIndex, charIndex+1, "3");
			break;
		}
			charIndex--;	
	}
	return sb.toString();
}
/**
 * 获取九宫格
 * @param position
 * @return
 */
public List<String> getMatrix(String position){
	List<String> returnList = new ArrayList<>();
	returnList.add(moveUp(moveLeft(position)));
	returnList.add(moveUp(position));
	returnList.add(moveUp(moveRight(position)));
	returnList.add(moveLeft(position));
	returnList.add(position);
	returnList.add(moveRight(position));
	returnList.add(moveDown(moveLeft(position)));
	returnList.add(moveDown(position));
	returnList.add(moveDown(moveRight(position)));
	return returnList;
}
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {//测效率
		// TODO Auto-generated method stub
		Date bdate = new Date();
		double d = 3.84;
		int a = 0 ;
		SpatialIndexUtil siu =getChinaSpatialUtil(17);
		String s = null;
		List<String> ls = siu.getCrossSquares(118, 40.002, 118.002,40);
//		for (int i=0; i<1; i++){
////			s= siu.xy2spatialIndex(118.673354798, 40.0089056);
////			s = siu.moveRight("1211121332033313");
////			a = "113".compareTo("1121");
//			
//		}
		Date edate = new Date();
		System.out.println(edate.getTime()-bdate.getTime());
		System.out.println(ls.size());
	}

}
