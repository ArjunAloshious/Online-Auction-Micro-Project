import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class SellerCentral2 extends HttpServlet
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

			int flag=0;
			// Execute SQL query
			stmt = conn.createStatement();
			String sql=null;
			sql = "SELECT * FROM Auction_Items";
			ResultSet rs = stmt.executeQuery(sql);
			String itemno = request.getParameter("ino");
			
			while(rs.next())
			{
				String ino = rs.getString("Item_no");
				if( ino.equals(itemno) )
				{
					flag=1;
				}
			}
			
			if(flag==0)
			{
				RequestDispatcher rd = request.getRequestDispatcher("SellerCentral4");
				rd.include(request,response);
			}
			
			if(flag==1)
			{
				out.println("<br><center><h2>Entered Item Number Not Unique! Try Again.<h2></center>");
				
				RequestDispatcher rd = request.getRequestDispatcher("SellerCentral");
				rd.include(request,response);
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