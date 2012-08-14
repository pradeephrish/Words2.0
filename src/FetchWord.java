

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FetchWord
 */
@WebServlet("/FetchWord")
public class FetchWord extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public FetchWord() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ResultSet stat = null;
		String input = request.getParameter("queryString");
		System.out.println("Input is ="+input); 
		Connection con = new SqlServer2008Connect().dbConnect("jdbc:jtds:sqlserver://localhost:1433","sa","Tibco@2012");
		
		try {
			stat = con.createStatement().executeQuery("SELECT  [ID],[Word] ,[Meaning]  ,[Usage]  ,[Mnemonic]    FROM [movedb].[dbo].[wordInfo] where Word like '"+input+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stat.next();
			int val = (int)((double) stat.getObject(1));
			request.getSession().setAttribute("index",val);
			request.getSession().setAttribute("1",stat.getObject(2));
			request.getSession().setAttribute("2",stat.getObject(3));
			request.getSession().setAttribute("3",processString(clobToString(stat.getObject(4))));
			request.getSession().setAttribute("4",processString(clobToString(stat.getObject(5))));

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	private String clobToString(Object object) {
		Clob clob = (Clob) object;
	    StringBuilder sb = new StringBuilder();
	    try {
	        Reader reader = clob.getCharacterStream();
	        BufferedReader br = new BufferedReader(reader);

	        String line;
	        while(null != (line = br.readLine())) {
	            sb.append(line);
	        }
	        br.close();
	    } catch (SQLException e) {
	        // handle this exception
	    } catch (IOException e) {
	        // handle this exception
	    }
	    return sb.toString();
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
