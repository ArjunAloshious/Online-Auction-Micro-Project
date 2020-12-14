import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class UserRegistration extends HttpServlet
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
		out.println("<a href=\"index.html\"><img src=\"logo.jpg\" style=\"left: 0;margin-top: -100px;position: fixed;top: 15%;\"alt=\"Logo\" width=\"150\" height=\"100\"></a>");
		out.println("<html><head><title>User Registration</title></head><h1 align = \"center\">User Registration</h1>\n");
		Connection conn=null;
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			String name = request.getParameter("username");
			String pass = request.getParameter("password");

			PreparedStatement preparedStatement = null;
			String query = "INSERT INTO User VALUES(?, ?)"; 
			preparedStatement = conn.prepareStatement(query);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, pass);
			preparedStatement.executeUpdate();
			
			HttpSession session = request.getSession();
			session.setAttribute("UserName", name);
			
			RequestDispatcher rd = request.getRequestDispatcher("AuctionBidding");
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