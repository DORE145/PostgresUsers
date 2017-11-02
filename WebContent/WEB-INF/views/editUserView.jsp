<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Edit user</title>
</head>
<body>
	<h2>Edit user</h2>
	<p style="color: red;">${errorString}</p>
	
	<c:if test="${not empty user}">
		<form method="POST" action="${pageContext.request.contextPath}/editUser">
		<input type="hidden" name="id" value="${user.id}">
		<table border="0">
			<tr>
				<td>Name</td>
				<td><input type="text" name="name" value="${user.name}" /></td>
			</tr>
			<tr>
				<td>Surname</td>
				<td><input type="text" name="surname" value="${user.surname}" /></td>
			</tr>
			<tr>
				<td>Login</td>
				<td><input type="text" name="login" value="${user.login}" /></td>
			</tr>
			<tr>
				<td>Password</td>
				<td><input type="text" name="password" value="${user.password}" /></td>
			</tr>
			<tr>
				<td>Birthday</td>
				<td><input type="date" name="birthday" value="${user.birthday}" /></td>
			</tr>
			<tr>
				<td colspan="2">
					<input type="submit" value="Submit">
					<a href="usersList">Cancel</a>
				</td>
			</tr>
		</table>
	</form>
	</c:if>
</body>
</html>