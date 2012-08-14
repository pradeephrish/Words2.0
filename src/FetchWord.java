

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
		Connection con = new MySqlConnector().dbConnect("jdbc:mysql://localhost:3306/worddb","root",DBConstants.dbPass);
		
		try {
			stat = con.createStatement().executeQuery("SELECT  Id,word ,meaning  ,usages  ,mnemonic    FROM wordtable where word like '"+input+"'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			stat.next();
			int val = Integer.parseInt((stat.getObject(1)).toString());
			request.getSession().setAttribute("index",val);
			request.getSession().setAttribute("1",stat.getObject(2));
			request.getSession().setAttribute("2",stat.getObject(3));
			request.getSession().setAttribute("3",processString((stat.getObject(4))));
			request.getSession().setAttribute("4",processString((stat.getObject(5))));

	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}

	
	public static String processString(Object object1) {
		String object = object1.toString();
		if (object == null)
			return null;
		if (object.equals(""))
			return "";
		object = object.replace("'", "&#39;");
		object = object.replace(",", "&#44;");
		object = object.replace("\n", "&#10;");
		return object;
	}

}
