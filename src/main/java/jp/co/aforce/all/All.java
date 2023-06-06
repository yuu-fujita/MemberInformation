package jp.co.aforce.all;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

/**
 * Servlet implementation class All
 */
@WebServlet("/views/all")
public class All extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public All() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out=response.getWriter();

		try {
			InitialContext ic=new InitialContext();
			DataSource ds=(DataSource)ic.lookup(
					"java:/comp/env/jdbc/member_information");
			Connection con=ds.getConnection();
			
			PreparedStatement st=con.prepareStatement(
					"select * from member_list");
			ResultSet rs=st.executeQuery();
			
			while(rs.next()) {
				out.println(rs.getString("member_id"));
				out.println("<br>");
				out.println(rs.getString("last_name"));
				out.println(rs.getString("first_name"));
				out.println("<br>");
				out.println(rs.getString("gender"));
				out.println("<br>");
				out.println(rs.getInt("birth_year"));
				out.println("/");
				out.println(rs.getInt("birth_month"));
				out.println("/");
				out.println(rs.getInt("birth_day"));
				out.println("<br>");
				out.println(rs.getString("phone_number"));
				out.println("<br>");
				out.println(rs.getString("mail_address"));
				out.println("<br>");
				out.println(rs.getString("job"));
			}
			
			st.close();
			con.close();		
		}catch(Exception e) {
			e.printStackTrace(out);
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

