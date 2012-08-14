import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;


public class MySqlConnector {
	public static Connection dbConnect(String db_connect_string,
			String db_userid, String db_password)

	{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(db_connect_string, db_userid,
					db_password);
			System.out.println("connected");

		} catch (Exception e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static void main(String[] args) throws SQLException {
		ResultSet stat = null;
		Connection con = new MySqlConnector().dbConnect("jdbc:mysql://localhost:3306/worddb","root",DBConstants.dbPass);
/*			String  string = "SELECT  [ID]       ,[Word]       ,[Meaning]       ,[Usage]       ,[Mnemonic]   FROM [movedb].[dbo].[wordInfo] where ID ="+1+";";
			stat = con.createStatement().executeQuery(string);
			System.out.println(string);
			stat.next();*/

	}
	
}
