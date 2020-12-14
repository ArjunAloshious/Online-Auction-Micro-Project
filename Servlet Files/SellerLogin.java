import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class SellerLogin extends HttpServlet
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
		out.println("<a href=\"index.html\"><img src=\"logo.jpg\" style=\"left: 0;margin-top: -100px;position: absolute;top: 15%;\"alt=\"Logo\" width=\"150\" height=\"100\"></a>");
		out.println("<html><head><title>Seller Login</title></head>");
		int flag=0;
		Statement stmt= null;
		String sql=null;
		Connection conn=null;
		
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
			// Execute SQL query
			stmt = conn.createStatement();
			
			sql = "SELECT * FROM Seller";
			ResultSet rs = stmt.executeQuery(sql);
			String name = request.getParameter("username");
			String pass = request.getParameter("password");
			
			while(rs.next())
			{
				String uname = rs.getString("Sellername");
				String passw = rs.getString("Password");
				if ( name.equals(uname) && pass.equals(passw) )
				{
					flag=1;
					RequestDispatcher rd = request.getRequestDispatcher("SellerCentral");
					rd.forward(request,response);
				}
			}
			if(flag==0)
			{
				out.println("<br><center><h2 style=\"top: 20vh; width: 100%; text-align: center;font-size:4vh;height:2vh;\">Invalid Seller Credentials! Try Again.<h2></center><br><br>");
				RequestDispatcher rd2 = request.getRequestDispatcher("index.html");
				rd2.include(request,response);
			}
			out.println("</body></html>");
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
}