import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class DBTest {

	public static void main(String[] args){
		try {
			Class.forName("org.gjt.mm.mysql.Driver");
			String url ="jdbc:mysql://121.199.28.7/zkh061_db";
			Connection conn = DriverManager.getConnection(url, "zkh061", "p2t5m3f5");
			System.out.println(conn);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
