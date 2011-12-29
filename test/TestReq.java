import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class TestReq {

	public static void main(String[] args) throws Exception{
		URL mUrl = new URL("http://192.168.1.101:8080/pring/test.do");
		HttpURLConnection connection = (HttpURLConnection) mUrl.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		connection.setUseCaches(false);//忽略缓存
		connection.setReadTimeout(30*1000);
		connection.setConnectTimeout(30*1000);
		int responseCode = connection.getResponseCode();
		System.out.println(responseCode);
		
	}
	}
