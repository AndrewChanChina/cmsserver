import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;


public abstract class AbstractTest {

	public static Map<String,Integer> map = new HashMap<String,Integer>();
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String s ="dgdfgdfhdfh";
		Object[] obj = findChar(s);
		System.out.println(Arrays.toString(obj));
		Set<String> set = map.keySet();
		for(Iterator i = set.iterator();i.hasNext();){
			System.out.println(i.next()+";"+map);
		}
		
	}
	
	public static Object[] findChar(String s){
		char[] c1 = s.toCharArray();
		int max = 0;
		//String maxStr = "";
		Object[] obj = new Object[2];
		for(int i=0;i<s.length();i++){
			String str = s.substring(i, i+1);
			String s1 = s.replaceAll(str, "");
			int num = s.length()-s1.length();
				map.put(str, num);
			if(num>max){
				max = num;
				obj[0] = max;
				obj[1] = str;
			}
		}
		return obj;
	}
}
