package PRODUCTFETCH;

import java.io.IOException;

import java.sql.CallableStatement;
import java.io.PrintWriter;
import java.sql.Connection;
//import java.sql.Types;
import java.sql.Types;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//import FetchingData.DatabaseConnection;

/**
 * Servlet implementation class productdetails
 */
@WebServlet("/productdetails")
public class productdetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public productdetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		int productid=Integer.parseInt(request.getParameter("product_id"));
		try {
			//Call Connection Method
				Connection con=DatabaseConnection.getMyConnection();
			//Write sql command
				CallableStatement stmt=(CallableStatement) con.prepareCall("{call getRecord(?,?,?,?)}");
				stmt.setInt(1,productid);
			    stmt.registerOutParameter(2, Types.VARCHAR);
			    stmt.registerOutParameter(3, Types.VARCHAR);
			    stmt.registerOutParameter(4, Types.INTEGER);
			    stmt.execute();
			    PrintWriter out=response.getWriter();
			    out.println("<table border=2>");
				out.println("<tr><th>Productid</th><th>Type</th><th>Name</th><th>Price</th></tr>");
				out.println("<tr>");
				out.print("<td>"+productid+"</td>");
				out.print("<td>"+stmt.getString(2)+"</td>");
				out.print("<td>"+stmt.getString(3)+"</td>");
				
				out.print("<td>"+stmt.getInt(4)+"</td>");
				out.println("</tr>");
			
			out.println("</table>");
					
					
				con.close();
			
			}catch(Exception e) {
				e.printStackTrace();
			}
			    


			    
			    //stmt.execute();
			    
			    
			     
			    
	}

}
