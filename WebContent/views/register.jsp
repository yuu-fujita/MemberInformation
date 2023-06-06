<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% java.util.Calendar calendar = java.util.Calendar.getInstance();
int currentYear = calendar.get(java.util.Calendar.YEAR);%>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyMMddHHmmss");
	Date currentDate = new Date();
	String formattedDate = dateFormat.format(currentDate);
	String member_id = "A" + formattedDate;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>会員情報登録</title>
</head>
<body>

	<h2>会員情報の登録</h2>

	<form method="post" action="RegisterServlet">
	
		<!-- 会員番号の生成 -->
		<label for="member_id">会員番号:</label>
		<input type="text" id="member_id" name="member_id" value="<%= member_id %>" readonly><br>
		</label><br>
		
	  	<!-- 名前の入力 -->
	  	■名前<br>
		<label for="last_name">姓</label>
		<input type="text" id="last_name" name="last_name" required>
		<label for="first_name">名</label>
		<input type="text" id="first_name" name="first_name" required><br><br>
	    
	    <!-- 性別の入力 -->
		<label>■性別<br></label>
		<input type="radio" id="male" name="gender" value="男" required>
		<label for="male">男</label>
		<input type="radio" id="female" name="gender" value="女" required>
		<label for="female">女</label><br><br>
	    
		<!-- 生年月日の入力 -->
		<label for="birth_year">■生年月日<br></label>
		<select id="birth_year" name="birth_year" required>
			<option value="">-- 年 --</option>
			<% for (int year = 1923; year <= 2023; year++) { %>
			<option value="<%= year %>"><%= year %></option>
			<% } %>
		</select>

	    <select id="birth_month" name="birth_month" required>
			<option value="">-- 月 --</option>
			<% for (int month = 1; month <= 12; month++) {%>
			<option value="<%= month %>"><%= month %></option>
			<% } %>
		</select>

		<select id="birth_day" name="birth_day" required>
			<option value="">-- 日 --</option>
			<% for (int day = 1; day <= 31; day++) {%>
			<option value="<%= day %>"><%= day %></option>
			<% } %>
		</select><br><br>
	    

		<!-- 電話番号の入力 -->
		<label for="phone_number">■電話番号<br></label>
		<input type="text" id="phone_number" name="phone_number" required><br><br>

		<!-- メールアドレスの入力 -->
		<label for="mail_address">■メールアドレス<br></label>
		<input type="email" id="mail_address" name="mail_address" required><br><br>

		<!-- 職業の選択 -->
		<label for="job">■職業<br></label>
		<select id="job" name="job" required>
			<option value="">-- 選択してください --</option>
			<option value="会社員">会社員</option>
			<option value="自営業">自営業</option>
			<option value="学生">学生</option>
			<option value="その他">その他</option>
		</select><br><br>
	    
		<!-- ボタン -->
		<input type="submit" value="登録">
		<input type="reset" value="リセット">
		<button type="buttun" onclick="goBack()">戻る</button>
	</form>
	
	<script>
		<!-- 戻るボタンがrequiredを無視 -->
		function goBack(){
			window.history.back();
		}
	</script>

</body>
</html>