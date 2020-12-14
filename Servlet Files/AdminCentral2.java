import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class AdminCentral2 extends HttpServlet
{
   public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
   {
		// JDBC driver name and database URL
		String JDBC_DRIVER = "com.mysql.jdbc.Driver";  
		String DB_URL="jdbc:mysql://localhost/Auction";

		//  Database credentials
		String USER = "root";
		String PASS = "rajagiri";
		
		// Set response content type
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		Statement stmt= null;
		Connection conn=null;
		String uname = request.getParameter("name");
		
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			
			PreparedStatement ps = conn.prepareStatement("DELETE FROM User WHERE Username = '"+uname+"'");
			ps.executeUpdate();
			
			RequestDispatcher rd = request.getRequestDispatcher("AdminCentral");
			rd.forward(request,response);
			
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