import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class CheckStatus extends HttpServlet
{
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
		response.setIntHeader("Refresh", 3);
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL="jdbc:mysql://localhost/Auction";

		//  Database credentials
		String USER = "root";
		String PASS = "rajagiri";
		Statement stmt= null;
		String sql=null;
		
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.println("<a href=\"index.html\"><img src=\"logo.jpg\" style=\"left: 0;margin-top: -100px;position: fixed;top: 15%;\"alt=\"Logo\" width=\"150\" height=\"100\"></a>");
		out.println("<html><head><title>Bid Status</title><style>table, th, td { border: 1px solid black;}"+
					"</style></head><h1 align = \"center\"><br>Check Bid Status</h1>\n");
		Connection conn=null;
		
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Execute SQL query
			stmt = conn.createStatement();
			
			int flag=0;
			sql = "SELECT * FROM Auction_Items";
			ResultSet rs = stmt.executeQuery(sql);
			out.println("<br><br><center><table style=\"width:50%;\"><tr><th>Item_no</th><th>Item_name</th><th>User Name</th></tr>");
			int i=0;
			
			while(rs.next())
			{
				int itemno = rs.getInt("Item_no");
				String itemname = rs.getString("Item_name");
				String Uname = rs.getString("Username");
				out.println("<tr><td>"+itemno+"</td><td>"+itemname+"</td><td>"+Uname+"</td></tr>");
			}
			
			out.println("</table></center></body></html>");
			rs.close();
			stmt.close();
			conn.close();
		}
		catch(SQLException se)
		{
			//Handle errors for JDBC
			se.printStackTrace();
		}
		catch(Exception e)
		{
			//Handle errors for Class.forName
			e.printStackTrace();
		}
		finally
		{
			//finally block used to close resources
			try
			{
				if(stmt!=null)
				stmt.close();
			}
			catch(SQLException se2)
			{
			} // nothing we can do
			try
			{
				if(conn!=null)
				conn.close();
			}
			catch(SQLException se)
			{
				se.printStackTrace();
			} //end finally try
		} //end try
	}
	
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		doPost(request, response);
	}
}