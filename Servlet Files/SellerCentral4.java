import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class SellerCentral4 extends HttpServlet
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
		
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			int itemno = Integer.parseInt(request.getParameter("ino"));
			String itemname = request.getParameter("iname");

			PreparedStatement preparedStatement = null;
			String query = "INSERT INTO Auction_Items VALUES(?, ?, ?, ?)"; 
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setInt(1, itemno);
			preparedStatement.setString(2, itemname);
			preparedStatement.setString(3, "");
			preparedStatement.setInt(4, 0);
			preparedStatement.executeUpdate();
	
			RequestDispatcher rd = request.getRequestDispatcher("SellerCentral");
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