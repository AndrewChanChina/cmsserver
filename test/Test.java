
public class Test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String a = "124ab";
		int b = Integer.parseInt(a,16);
		System.out.println(b);
		String c = "1a2b3345678d";
		long l1 = Long.parseLong(c, 16);
		System.out.println(l1);
		System.out.println(l1+1);
		String l2 = Long.toHexString(l1+1);
		System.out.println(l2);
		StringBuffer sb = new StringBuffer();
		if(l2.length()<12){
			int length = l2.length();
			for(int i=0;i<12-length;i++){
				l2 = "0"+l2;
			}
		}
		System.out.println(l2);
	}

}
