
<%@page	import="Model.User"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
<title>Product Details</title>
<jsp:include page="AdminBar.jsp" />

<%
	
User user = new User();
user = (User) request.getSession().getAttribute("user");

	
%>
</head>
<body>
	<div align="center">
		<h2>Add New User</h2>
	</div>
	<div>
		<form action="../UpdateUserServlet" method="post">
			<table align="center">
				<tr>
					
					<td><input type="hidden" name="userid"
						value="<%=user.getUserid()%>" /></td>
				</tr>
				<tr>
					<td>User Name :</td>
					<td><input type="text" name="username" required="true"
						value="<%=user.getUsername()%>" /></td>
				</tr>
				<tr>
					<td>Password :</td>
					<td><input type="text" name="password" required="true"
						value="<%=user.getPassword()%>"  /></td>
				</tr>
				<tr>
					<td>Email Id :</td>
					<td><input type="text" name="emailid" required="true"
						value="<%=user.getEmailid()%>" /></td>
				</tr>
				
				<tr>
					<td>Mobile :</td>
					<td><input type="text" name="mobile" required="true"
						value="<%=user.getMobno()%>" /></td>
				</tr>
				<tr>
					<td colspan="2" align="center"><input value="Update User"
						type="submit" /></td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>
