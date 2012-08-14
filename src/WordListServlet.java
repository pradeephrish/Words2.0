

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordListServlet
 */
@WebServlet("/WordListServlet")
public class WordListServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordListServlet() {
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
//		response.getWriter().print("<li onClick=\"fill('Pradeep')\">Pradeep</li><li onClick=\"fill('Prema')\">Prema</li>");

		ResultSet stat = null;
		String input = request.getParameter("queryString");
		Connection con = new SqlServer2008Connect().dbConnect("jdbc:jtds:sqlserver://localhost:1433","sa","Tibco@2012");
		
		try {
			stat = con.createStatement().executeQuery("SELECT  [Word]    FROM [movedb].[dbo].[wordInfo] where Word like '"+input+"%'");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
				String output = "";
				while(stat.next())
				{
					String temp = stat.getString(1);
					output += "<li onClick=\"fill('"+temp+"')\">"+temp+"</li>";
				}
				response.getWriter().print(output);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
