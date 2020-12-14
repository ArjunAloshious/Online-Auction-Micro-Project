import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class Results extends HttpServlet
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
		out.println("<html><head><title>Auction Results</title></head><h1 align = \"center\"><br>Auction Results</h1>\n");
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
			
			HttpSession session=request.getSession(false);
			String name = (String) session.getAttribute("UserName");
			
			sql = "SELECT * FROM Auction_Items WHERE Item_no=101";
			ResultSet rs = stmt.executeQuery(sql);
			int flag=0;
			
			while(rs.next())
			{
				String uname = rs.getString("Username");
				if ( name.equals(uname) )
				{
					flag=1;
					int bid = rs.getInt("Bid");
					out.println("<br><br><center>Congratulations! You have won the auction for the item : Tennis Ball.<br><br>");
					out.println("Your Final Bid was : "+ bid +"</center>");
					out.println("<br><br><br><center><button>Proceed to checkout</button></center><br><br>");
				}
			}
			if(flag!=1)
			{
				out.println("<br><br><center>You have been outbid by another buyer for the item : Tennis Ball.<br><br>");
				out.println("Thank you for your participation in this auction.</center><br><br><br>");
			}
			
			RequestDispatcher rd = request.getRequestDispatcher("ResultsTwo");
			rd.include(request,response);
			
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