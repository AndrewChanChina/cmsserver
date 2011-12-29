import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;


public class TestPost {

	public static void main(String[] args) throws Exception{
		
		DefaultHttpClient httpclient = new DefaultHttpClient();
	     HttpPost post = new HttpPost("http://localhost:8080/pring/detailLog.do");
//		 post.addHeader("type", "地方频道");
//		 post.addHeader("channel","BTV卫视");
		 List<NameValuePair> params = new ArrayList<NameValuePair>();
		 params.add(new BasicNameValuePair("checkID", "110915163009527400002059A007CB29"));
		 params.add(new BasicNameValuePair("uploadFile", "<devices><device><name>Record</name><status>Suc</status><note>tell me something more</note></device></devices>"));
		 post.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
		 HttpResponse  response = httpclient.execute(post);
		 System.out.println(response.getStatusLine().getStatusCode());
	}
}
