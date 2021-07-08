
<%@page import="Model.User"%>
<%@page import="DAOLayer.AdminUserDAO"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Categories</title>
<jsp:include page="AdminBar.jsp" />
<%
	AdminUserDAO userDAO = new AdminUserDAO();
    List<?> userss = userDAO.readAll();
	User userg = new User();
%>
</head>
<body>
	<div align="center" id="edit">
		<table border="1">
			<tr>
				<!-- <th width="100">Category ID</th> -->
				<th width="200">UserName</th>
				<th width="200">Password</th>
				<th width="200">Emailid</th>
				<th width="200">MobileNo</th>
				<th width="80">Edit</th>
				<th width="80">Delete</th>
			</tr>
			<%
				for (Object obj : userss) {
					userg = (User) obj;
			%>
			<tr>
				<%-- <td align="center"><%=catg.getCatgId()%></td> --%>
				<td align="center"><%=userg.getUsername()%></td>
				<td align="center"><%=userg.getPassword()%></td>
				<td align="center"><%=userg.getEmailid()%></td>
				<td align="center"><%=userg.getMobno()%></td>
				<td align="center"><a href="../EditUserServlet?userid=<%=userg.getUserid()%>"><button
							type="button">Edit</button></a></td>
				<td align="center"><a
					href="../DeleteUserServlet?userid=<%=userg.getUserid()%>"><button
							type="button">Delete</button></a></td>
			</tr>
			<%
				}
			%>
		</table>
	</div>
</body>
</html>