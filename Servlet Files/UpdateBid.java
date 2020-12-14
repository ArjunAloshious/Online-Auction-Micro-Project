import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class UpdateBid extends HttpServlet
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
			
			HttpSession session=request.getSession(false);
			String name = (String) session.getAttribute("UserName");
			String tempBid = request.getParameter("newbid");
			int bid = Integer.parseInt(tempBid);
			String f1 = request.getParameter("form1");
			String f2 = request.getParameter("form2");
			
			if(!(f1 == null))
			{
				sql = "SELECT * FROM Auction_Items WHERE Item_no = 101";
				ResultSet rs = stmt.executeQuery(sql);
				int price = 0;
				while(rs.next())
				{
					price = rs.getInt("Bid");
				}
				if(bid > price)
				{
					PreparedStatement ps =conn.prepareStatement("UPDATE Auction_Items SET Username = ? , Bid = ? WHERE Item_no = ?");
					ps.setString(1,name);
					ps.setInt(2,bid);
					ps.setInt(3,101);
					ps.executeUpdate();
					out.println("<br><center>You are currently the highest bidder for the Item : Tennis Ball.</center><br>");
				}
				else
				{
					out.println("<br><center>Your current bid for Tennis Ball is not high enough.</center><br>");
				}
			}
			
			if(!(f2 == null))
			{
				sql = "SELECT * FROM Auction_Items WHERE Item_no = 102";
				ResultSet rs = stmt.executeQuery(sql);
				int price2 = 0;
				while(rs.next())
				{
					price2 = rs.getInt("Bid");
				}
				if(bid > price2)
				{
					PreparedStatement ps =conn.prepareStatement("UPDATE Auction_Items SET Username = ? , Bid = ? WHERE Item_no = ?");
					ps.setString(1,name);
					ps.setInt(2,bid);
					ps.setInt(3,102);
					ps.executeUpdate();
					out.println("<br><center>You are currently the highest bidder for the Item : Toy Car.</center><br>");
				}
				else
				{
					out.println("<br><center>Your current bid for Toy Car is not high enough.</center><br>");
				}
			}
			out.println("</body></html>");
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