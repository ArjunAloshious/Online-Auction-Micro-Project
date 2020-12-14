import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
 
public class SellerCentral extends HttpServlet
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
		out.println("<html><head><title>Seller Central</title></head><h1 align = \"center\"><br>Seller Central</h1>\n");
		Statement stmt= null;
		String sql=null;
		Connection conn=null;
		
		try
		{
			// Register JDBC driver
			Class.forName("com.mysql.jdbc.Driver");
			// Open a connection
			conn = DriverManager.getConnection(DB_URL, USER, PASS);
   
			out.print("<div style=\"float:left; width: 49%; background-color: #f0f0f0;\"><form method=\"post\" action=\"http://localhost:8080/SellerCentral2\"><center><table cellspacing=\"15\" style=\"height:283px;width:450px\"><tr><td align=\"center\">"+
			"<h2>Add Items for Auction</h2><h3>Enter the item details below :<h3></td></tr><tr><td align=\"right\">Unique Item No: <input type=\"text\" "+
			"style=\"height:28px;width:300px\" name=\"ino\" required><br><br></td></tr><tr><td align=\"right\">Item Name: <input type=\"text\" "+
			"style=\"height:28px;width:300px\" name=\"iname\" required><br><br></td></tr><br><br><br>");
			out.print("<tr><td align=\"center\"><input type=\"submit\" value=\"Submit Details\"></td>"+
			"</tr></table></center></form></div><div style=\"float:right; width: 49%; background-color: #f0f0f0;\"><form method=\"post\" action=\"http://localhost:8080/SellerCentral3\" required><center><table cellspacing=\"15\" style=\"height:283px;width:450px\"><tr><td align=\"center\">"+
			"<h2>Remove Items for Auction</h2><h3>Enter the item number below :<h3></td></tr><tr><td align=\"right\">Item Number: <input type=\"text\" "+
			"style=\"height:28px;width:300px\" name=\"ino\" required></td></tr><br><br><br>");
			out.print("<tr><td align=\"center\"><input type=\"submit\" value=\"Delete Item\"></td></tr></table></center></form></div>"+
			"<div style=\"position: absolute; left: 45%; margin-top: 30%;\"><form method=\"post\" action=\"http://localhost:8080/CheckTable\" target=\"_blank\"><tr>"+
			"<input type=\"submit\" value=\"Check Auction Table\"></tr></form></div>"+
			"<div style=\"position: absolute; left: 44.5%; margin-top: 36%;\"><form method=\"post\" action=\"index.html\"><tr><input type=\"submit\" "+
			"value=\"Proceed to Home Page\"></tr></form></div>");
			
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