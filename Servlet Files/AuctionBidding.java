import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class AuctionBidding extends HttpServlet
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
		out.println("<html><head><title>Auction Bidding</title></head><h1 align = \"center\">Auction Bidding</h1><br>\n");
		
		RequestDispatcher rd = request.getRequestDispatcher("Timer.jsp");
		rd.include(request,response);
		
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
			int j=0;
			int l=0;
			int[] arr = new int[4];
			
			sql = "SELECT * FROM Auction_Items";
			ResultSet rs = stmt.executeQuery(sql);
			while(rs.next())
			{
				int itemno = rs.getInt("Item_no");
				arr[j] = itemno;
				flag=1;
				j=j+1;
			}
			if(flag==0)
			{
				out.println("<h2><br><br><br><center>Currently, no items are listed for bidding.</center></h2>");
			}
			
			for( l=0; l<4; l++)
			{
				if(arr[l]==101)
				{
					RequestDispatcher rd2 = request.getRequestDispatcher("AuctionBidding1");
					rd2.include(request,response);
				}
				if(arr[l]==102)
				{
					RequestDispatcher rd3 = request.getRequestDispatcher("AuctionBidding2");
					rd3.include(request,response);
				}
			}
			
			String tempBid = request.getParameter("newbid");			
			if( !( tempBid.isEmpty() || tempBid == null ) )
			{	
				RequestDispatcher rd4 = request.getRequestDispatcher("UpdateBid");
				rd4.include(request,response);
			}
			
			out.println("<br><center><form method=\"post\" action=\"http://localhost:8080/CheckStatus\" target=\"_blank\">"+
						"<input type=\"submit\" value=\"Check Bid Status\"></form>");
		
		
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