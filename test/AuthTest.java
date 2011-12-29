import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.smit.util.TripleDESHelper;


public class AuthTest {

	public String isAuth(String encodeStr) throws ParseException{
		TripleDESHelper helper = new TripleDESHelper("123456789ABCDEFG");
		String whiteText = helper.decode(encodeStr);
		String authStr = whiteText.substring(48);
		String bTimeStr = authStr.substring(0, 6);
		String eTimeStr = authStr.substring(6, 12);
		SimpleDateFormat formater = new SimpleDateFormat("yyyyMMdd");
		Date begin_time = formater.parse("20"+bTimeStr);
		Date end_time = formater.parse("20"+eTimeStr);
		int count = Integer.parseInt(authStr.substring(12));
		Date date = new Date();
		if(date.getTime()>begin_time.getTime()&&date.getTime()<end_time.getTime()&&count>0){
			StringBuffer sb = new StringBuffer();
			sb.append(bTimeStr);
			sb.append(eTimeStr);
			sb.append(String.format("%04d", count-1));
			return sb.toString();
		}
		return null;
	}
	public static void main(String[] args) throws ParseException{
		String s = "8d1/aun0EoRGEKLdXKmtMsOH44rJF81WB5d1uUzwZSRZNySg7ojWkXbNY7arxOzsnCbJJYAlYHlaPBvqSR3CHpcmyhA9V5d4lU3tYThn+jk=";
		AuthTest test = new AuthTest();
		String s2 = test.isAuth(s);
		System.out.println(s2);
	}
}
