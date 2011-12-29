import com.smit.util.KeyedDigestMD5;
import com.smit.util.TripleDESHelper;


public class TripleTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		TripleDESHelper helper = new TripleDESHelper("123456789ABCDEFG");
		String s = "G7FlHO4NBXxB0NJmaF6vt3kLEVPhSu+dQa8rPBMp23Ru9J9CjVbn+dPE7QTx7AmaWBAsA35AMyt9" +
				"eFQYTkXe2wXzDQCkkhJjAHNu/kpx9DkUmUb2Ym2l0sW8NGZ0GSLs2UK6CD75hxpEW1ta5BTIZAo1" +
				"uXWnBd3YVn7yIPmK2e6FL0CIhbV4rKbXGMyHXzbA";
		String s1 = "G7FlHO4NBXxB0NJmaF6vt3kLEVPhSu+dQa8rPBMp23QG7nYjYM79BpsO8jZB4+mzlKGXgr9WEsMCtx9UOsIFAGEXzVCra03JGyyIuy6neJeR6XKQw5i4mZaquaumKPnmyaZQiOlrFch4dxO8YuyXHE1UFEVcbj0RKMbxo/qpnOi9VIRPOdTzXU8ZvwwD8Q6f";
		System.out.println(helper.decode(s));
		System.out.println(helper.decode(s1));
		
		String s2 = "NV985000NV88030031105121014450022059a007cb130000120225120225050000000000000000000000000000000000";
		String s3 = "NV985000NV88030031105121014450022059A007CB130000120225120225050000000000000000000000000000000000";
		System.out.println(KeyedDigestMD5.getKeyedDigest(s2,"123456789ABCDEFG"));
		System.out.println(KeyedDigestMD5.getKeyedDigest(s3,"123456789ABCDEFG"));
	}

}
