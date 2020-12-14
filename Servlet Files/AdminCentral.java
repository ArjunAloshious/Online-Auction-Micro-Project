import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class AdminCentral extends HttpServlet
{
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
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
		out.println("<html><head><title>Admin Central</title></head><h1 align = \"center\"><br>Admin Central</h1>\n");
		Connection conn=null;
		
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Execute SQL query
			stmt = conn.createStatement();
		
			out.println("<br><center><p style=\"font-size: 130%;\">To generate a report of all registered users and listed items, click below :<form method=\"post\" "+
						"action=\"http://localhost:8080/ReportGeneration\" target=\"_blank\"><input type=\"submit\" value=\"Generate Report\"></form></p>");
			
			out.println("<br><br><form method=\"post\" action=\"http://localhost:8080/AdminCentral2\"><center><table cellspacing=\"10\" style=\"height:280px;width:450px;background-color: #f0f0f0;\"><tr><td align=\"center\">"+
						"<h2>Deleting Unwanted Users</h2><br><h3>Enter the Username below :<h3></td></tr><br><tr><td align=\"center\">Username: <input type=\"text\" "+
						"style=\"height:28px;width:250px\" name=\"name\" required></td></tr>"+
						"<tr><td align=\"center\"><br><input type=\"submit\" value=\"Delete User\"></td></tr></table></center></form>");
						
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
}