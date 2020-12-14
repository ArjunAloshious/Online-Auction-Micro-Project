import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class ReportGeneration2 extends HttpServlet
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
		out.println("<html><head><style>table, th, td { text-align:center; font-size: 20px;border: 2px solid black; padding:8px;border-collapse:collapse;}</style></head>");
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
			out.println("<br><h2><center>Listed Auction Items</h2><br><br><center><table style=\"width:50%;\"><tr><th>Item Number</th><th>Item Name</th><th>Username</th><th>Final Bid</th></tr>");

			while(rs.next())
			{
				int itemno = rs.getInt("Item_no");
				String itemname = rs.getString("Item_name");
				String uname = rs.getString("Username");
				String bid = rs.getString("Bid");
				out.println("<tr><td>"+itemno+"</td><td>"+itemname+"</td><td>"+uname+"</td><td>"+bid+"</td></tr>");
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
}