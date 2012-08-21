import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Replicator {
	public static void main(String[] args) throws SQLException {
		ResultSet stat = null;
		Connection con = new SqlServer2008Connect().dbConnect("jdbc:jtds:sqlserver://localhost:1433/tempdb","sa","Tibco@2012");
		String  string = "SELECT  [ID]       ,[Word]       ,[Meaning]       ,[Usage]       ,[Mnemonic]   FROM [movedb].[dbo].[wordInfo]";
		stat = con.createStatement().executeQuery(string);
		
		Connection con1 = new MySqlConnector().dbConnect("jdbc:mysql://localhost:3306/worddb","root","");
		
		while(stat.next())
		{
			String insertString = "insert into wordtable values ("+stat.getInt(1)+",'"+processString(stat.getString(2))+"','"+processString(stat.getString(3))+"','"+processString(stat.getString(4))+"','"+processString(stat.getString(5))+"');";
			System.out.println(insertString);
			int stat1 = con1.createStatement().executeUpdate(insertString);
			System.out.println(stat1);
		}
	}
	public static String processString(String input) {
		if (input == null)
			return null;
		if (input.equals(""))
			return "";
		input = input.replace("'", "&#39;");
		input = input.replace(",", "&#44;");
		input = input.replace("\n", "&#10;");
		return input;
	}
}
