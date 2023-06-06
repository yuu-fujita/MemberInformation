package jp.co.aforce.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import jp.co.aforce.member.Member;

public class MemberDAO extends DAO {
	
	public boolean memberCheck(String lastName, String firstName, int birthYear, int birthMonth, int birthDay) throws Exception{
		Connection con= getConnection();
		PreparedStatement st = con.prepareStatement(
				"SELECT * FROM member_list WHERE last_name = ? AND first_name = ? AND birth_year = ? AND birth_month = ? AND birth_day = ?"
				);
			st.setString(1, lastName);
			st.setString(2, firstName);
			st.setInt(3, birthYear);
			st.setInt(4, birthMonth);
			st.setInt(5, birthDay);
			ResultSet rs = st.executeQuery();
			boolean exists = rs.next();
			
			rs.close();
			st.close();
			closeConnection(con);
			 return exists;
	}
	
	public boolean registerMember(Member member) throws Exception {
		
		Connection con= getConnection();
		
		PreparedStatement st = con.prepareStatement(
			//会員情報登録処理
					"INSERT INTO member_list VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
					);
			st.setString(1, member.getMemberId());
			st.setString(2, member.getLastName());
			st.setString(3, member.getFirstName());
			st.setString(4, String.valueOf(member.getGender()));
			st.setInt(5, member.getBirthYear());
			st.setInt(6, member.getBirthMonth());
			st.setInt(7, member.getBirthDay());
			st.setString(8, member.getPhoneNumber());
			st.setString(9, member.getMailAddress());
			st.setString(10, member.getJob());
			int i = st.executeUpdate();
			
			if (i > 0) {
				System.out.println("会員情報の登録に成功しました。");
				return true;
			} else {
				System.out.println("会員情報の登録に失敗しました。MemberDAO.java");
				return false;
			}
	}
}





