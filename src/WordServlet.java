

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.sql.Clob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class WordServlet
 */
@WebServlet("/WordServlet")
public class WordServlet extends HttpServlet {
	HashMap<String, Integer> map = new HashMap<>();
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WordServlet() {
        super();
        map.put("A", 1);
        map.put("B", 362);
        map.put("C", 513);
        map.put("D", 906);
        map.put("E", 1190);
        map.put("F", 1418);
        map.put("G", 1583);
        map.put("H", 1701);
        map.put("I", 1794);
        map.put("J", 2084);
        map.put("K", 2109);
        map.put("L", 2125);
        map.put("M", 2232);
        map.put("N", 2424);
        map.put("O", 2489);
        map.put("P", 2588);
        map.put("Q", 2971);
        map.put("R",3004);
        map.put("S",3228);
        map.put("T",3573);
        map.put("U",3723);
        map.put("V",3792);
        map.put("W",3898);
        map.put("X",3955);
        map.put("Y",3956);
        map.put("Z",3963);
        
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
		int index = 1;
		if(request.getSession().getAttribute("index")!=null&&request.getParameter("base")==null){
			index =(int) request.getSession().getAttribute("index"); 
			if(request.getParameter("in").equals("p"))
			{ 
				request.getSession().setAttribute("index",--index);
			}
			else
			{
				
				request.getSession().setAttribute("index",++index);
			}
			System.out.println("index"+index);
		}
		
		if(request.getSession().getAttribute("index")==null)
		{
			request.getSession().setAttribute("index",index);
		}
		
		if(request.getParameter("base")!=null)
			{
				index= map.get(request.getParameter("base"));
				request.getSession().setAttribute("index",index);
			}
		
		ResultSet stat = null;
		System.out.println(request.getParameter("in"));
		Connection con = new SqlServer2008Connect().dbConnect("jdbc:jtds:sqlserver://localhost:1433","sa","Tibco@2012");
		
		try {
			stat = con.createStatement().executeQuery("SELECT  [ID]       ,[Word]       ,[Meaning]       ,[Usage]       ,[Mnemonic]   FROM [movedb].[dbo].[wordInfo] where ID ="+index);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
				stat.next();
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
