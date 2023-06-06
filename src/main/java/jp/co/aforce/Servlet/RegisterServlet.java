package jp.co.aforce.Servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jp.co.aforce.dao.MemberDAO;
import jp.co.aforce.member.Member;

/**
 * Servlet implementation class RegisterServlet
 */
@WebServlet("/views/RegisterServlet")
public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
        // TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String birthYear_num = request.getParameter("birth_year");
		String birthMonth_num = request.getParameter("birth_month");
		String birthDay_num = request.getParameter("birth_day");
		
		//会員情報の取得
		String memberId = request.getParameter("member_id");
		String lastName = request.getParameter("last_name");
		String firstName = request.getParameter("first_name");
		String genderString = request.getParameter("gender");
		char gender = genderString.charAt(0);
		int birthYear = Integer.parseInt(birthYear_num);
		int birthMonth = Integer.parseInt(birthMonth_num);
		int birthDay = Integer.parseInt(birthDay_num);
		String phoneNumber = request.getParameter("phone_number");
		String mailAddress = request.getParameter("mail_address");
		String job = request.getParameter("job");
		
		MemberDAO memberDAO = new MemberDAO();
		boolean memberChecks = false;
		try {
			memberChecks = memberDAO.memberCheck(lastName, firstName, birthYear, birthMonth, birthDay);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if(memberChecks) {
			response.getWriter().println("会員情報が既に存在します。");
			return;
		}
		
		//パラメータを設定
		Member member = new Member(memberId, lastName, firstName, gender, birthYear, birthMonth, birthDay, phoneNumber, mailAddress, job);
		//会員情報の登録
//		MemberDAO memberDAO = new MemberDAO();
		boolean success = false;
		try {
			success = memberDAO.registerMember(member);
		} catch (Exception e) {
			// TODO 自動生成された catch ブロック
			e.printStackTrace();
		}
		if (success) {
			response.getWriter().println("会員情報の登録に成功しました。");
		} else {
			response.getWriter().println("会員情報の登録に失敗しました。");
		}

	}
}