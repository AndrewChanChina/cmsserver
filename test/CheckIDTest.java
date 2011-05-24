import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.RandomStringUtils;


public class CheckIDTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		//long time = System.currentTimeMillis();
		//String time = format.format(date);
		//System.out.println(time);
		//String checkID=time.substring(2,4)+time.substring(4,time.length());
		
		RandomStringUtils rst = new RandomStringUtils();
		String num = RandomStringUtils.randomNumeric(4);
		//RandomStringUtils.r
		//String str= RandomStringUtils.random(12).toString();
		//System.out.println(str);
		//System.out.println(num);
	//	System.out.println(checkID);
		String str = "r";
		String str1 = "4";
		String str2 = "";
		if(str2.matches("\\d+")){
			System.out.println("hhfhf");
		}
		System.out.println(str.matches("\\d*"));
		System.out.println(str1.matches("\\d*"));
		
		List list = new ArrayList();
		System.out.println(list.get(0));
	}

}

