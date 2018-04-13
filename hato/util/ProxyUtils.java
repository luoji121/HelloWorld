package util;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ProxyUtils {
	public static String httpGet(String strUrl) {
		StringBuffer buffer = new StringBuffer();
		try {
			// String getURL="";
			// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编�?
			/*
			 * if(data!=null){ getURL
			 * =strUrl+"?data="+URLEncoder.encode(data,"utf-8"); }else{
			 */
			// getURL =strUrl;
			// }
			URL getUrl = new URL(strUrl);
			// 根据拼凑的URL，打�?��接，URL.openConnection函数会根�?URL的类型，
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			// 进行连接，但是实际上get request要在下一句的 connection.getInputStream()函数中才会真正发�?
			// 服务�?
			connection.connect();
			// 取得输入流，并使用Reader读取
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));

			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
			br.close();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static String httpGetThrowsException(String strUrl) throws IOException {
		StringBuffer buffer = new StringBuffer();
		
			URL getUrl = new URL(strUrl);
			// 根据拼凑的URL，打�?��接，URL.openConnection函数会根�?URL的类型，
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			// 进行连接，但是实际上get request要在下一句的 connection.getInputStream()函数中才会真正发�?
			// 服务�?
			connection.connect();
			// 取得输入流，并使用Reader读取
			BufferedReader br = new BufferedReader(new InputStreamReader(
					connection.getInputStream(), "UTF-8"));

			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
			br.close();
		return buffer.toString();
	}
	
	public static String httpPost(String data, String strUrl) {
		URL u = null;
		HttpURLConnection con = null;
		// System.out.println("send_url:"+strUrl);
		try {
			u = new URL(strUrl);
			con = (HttpURLConnection) u.openConnection();
			con.setRequestMethod("POST");
			con.setDoOutput(true);
			con.setDoInput(true);
			con.setUseCaches(false);
			con.setRequestProperty("Content-Type", "application/json");
			OutputStreamWriter osw = new OutputStreamWriter(
					con.getOutputStream(), "UTF-8");
			// 构建请求参数
			if (data != null) {
				StringBuffer sb = new StringBuffer();
				sb.append(data);
				osw.write(sb.toString());
			}
			osw.flush();
			osw.close();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (con != null) {
				con.disconnect();
			}
		}
		// 读取返回内容
		StringBuffer buffer = new StringBuffer();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(
					con.getInputStream(), "UTF-8"));
			String temp;
			while ((temp = br.readLine()) != null) {
				buffer.append(temp);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return buffer.toString();
	}
	
	public static InputStream httpGetReturnInputStream(String strUrl) {
//		StringBuffer buffer = new StringBuffer();
		InputStream returnStream = null;
		try {
			// String getURL="";
			// 拼凑get请求的URL字串，使用URLEncoder.encode对特殊和不可见字符进行编�?
			/*
			 * if(data!=null){ getURL
			 * =strUrl+"?data="+URLEncoder.encode(data,"utf-8"); }else{
			 */
			// getURL =strUrl;
			// }
			URL getUrl = new URL(strUrl);
			// 根据拼凑的URL，打�?��接，URL.openConnection函数会根�?URL的类型，
			// 返回不同的URLConnection子类的对象，这里URL是一个http，因此实际返回的是HttpURLConnection
			HttpURLConnection connection = (HttpURLConnection) getUrl
					.openConnection();
			// 进行连接，但是实际上get request要在下一句的 connection.getInputStream()函数中才会真正发�?
			// 服务�?
			connection.connect();
			// 取得输入流，并使用Reader读取
//			BufferedReader br = new BufferedReader(new InputStreamReader(
//					connection.getInputStream(), "UTF-8"));
//
//			String temp;
//			while ((temp = br.readLine()) != null) {
//				buffer.append(temp);
//			}
//			br.close();
			returnStream = connection.getInputStream();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return returnStream;
	}
}
