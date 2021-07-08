<%@page import="Model.Product"%>
<%@page import="Model.User"%>
<%@page import="DAOLayer.AdminProductDAO"%>
<%@page import="DAOLayer.AdminUserDAO"%>
<%@page import="java.util.List"%>
<%@ page import="java.io.*"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>List Categories</title>
<style>
body {
	background: url(../images/bg.png) repeat;
}
</style>
<%
	
	List<Product> selectedProducts = (List<Product>) request.getSession().getAttribute("selectedProducts");
AdminUserDAO userDAO = new AdminUserDAO();
List<?> userss = userDAO.readAll();
User userg = new User();

%>

</head>

<body>
	<div align="center" id="edit">
		<form action="../AddToOrderServlet" method="post">
			<table border="1">
				<caption>
					<h3>
						<u>Your Cart Products</u>
					</h3>
				</caption>
				<tr>
					<!-- <th width="150">Select Products</th> -->
					<!-- <th width="200">Product Image</th> -->
					<th width="100">Product Name</th>
					<th width="100">Product Price</th>
					<th width="300">Product Description</th>
				</tr>
				<%
					if (selectedProducts != null) {
						for (Product cproduct : selectedProducts) {
							
						
				%>
				<tr>
					<!-- <td align="center"></td> -->
					<%-- <td align="center"><%=cproduct.getProductImage()%></td> --%>
					<input type="hidden" id="userid" name="userid" value="<%=userg.getUserid() %>">
					<input type="hidden" id="productname" name="productname" value="<%=cproduct.getProductName()%>">
					<input type="hidden" id="productId" name="productId" value="<%=cproduct.getProductId()%>">
					<td align="center"><%=cproduct.getProductName()%></td>
					<td align="center">Rs. <%=cproduct.getProductPrice()%></td>
					<td align="center"><%=cproduct.getProductDesc()%></td>
				</tr>
				<%
					}
					
					}	else {
						response.setContentType("text/html");
						out.print("<h2>No product is selected.</h2>");
					}
					
				%>
				<tr>
				<td colspan="2" align="center"><input value="Submit"
						type="submit" /></td>
					<!-- <td colspan="3" align="center"><a href="InvoicePage.jsp"><input type="button" value="CheckOut"></a></td> -->
				</tr>
			</table>
		</form>
	</div>
</body>
</html>






