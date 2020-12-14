import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class AuctionBidding1 extends HttpServlet
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
		Connection conn=null;
		
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");

			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);

			// Execute SQL query
			stmt = conn.createStatement();
			sql = "SELECT * FROM Auction_Items WHERE Item_no=101";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				String item_no = rs.getString("Item_no");
				String item_name = rs.getString("Item_name");
				out.println("<table align=\"left\" style=\"padding-left: 20px\" width=\"570\"><tr><td rowspan=\"4\"><img src=\"ball.jpeg\" "+
				"alt=\"Ball\" width=\"120\" height=\"120\"></td><td><h2>Item Name: Tennis Ball</h2></td></tr><tr><td>"+
				"<p>Item Number: 101<br>Description: Highly Durable and Non-Pressurized<br>Condition: Excellent<br></p></td></tr>"+
				"</table><br><br><br><br><br><br><br><br><form method=\"post\" style=\"padding-left: 20px\" action=\"http://localhost:8080/AuctionBidding\">"+
				"<table><tr><td align=\"right\"><input style=\"width:200px;\" type=\"text\" placeholder=\"Place New Bid Here\" name=\"newbid\" required>"+
				"</td><td align=\"right\"><input type=\"submit\" name=\"form1\" value=\"Submit Bid\"></td></tr></table></form><br><br>");
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