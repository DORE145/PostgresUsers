<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>  
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Users List</title>
</head>
<body>
	<h2>Users List</h2>
	<p style="color:red;">${errorString}</p>
	
	<table border="1" cellpadding="5" cellspacing="1">
		<tr>
			<th>Id</th>
			<th>Name</th>
			<th>Surname</th>
			<th>Login</th>
			<th>Password</th>
			<th>Birthday</th>
			<th>Edit</th>
			<th>Delete</th>
		</tr>
		<c:forEach items="${usersList}" var="user">
			<tr>
				<td>${user.id}</td>
				<td>${user.name}</td>
				<td>${user.surname}</td>
				<td>${user.login}</td>
				<td>${user.password}</td>
				<td>${user.birthday}</td>
				<td>
					<a href="editUser?id=${user.id}">Edit</a>
				</td>
				<td>
					<a href="deleteUser?id=${user.id}">Delete</a>
				</td>
			</tr>
		</c:forEach>
	</table>
	
	<a href="createUser">Create user</a>
</body>
</html>