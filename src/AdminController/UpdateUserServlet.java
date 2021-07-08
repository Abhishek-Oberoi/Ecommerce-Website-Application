package AdminController;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import DAOLayer.AdminUserDAO;

import Model.User;

/**
 * Servlet implementation class Update
 */
@WebServlet("/UpdateUserServlet")
public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = new User();
		boolean status = false;
		
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		/*byte[] productImage = request.getParameter("product_image").getBytes();*/
		String userId= request.getParameter("userid");
		String userName= request.getParameter("username");
		String password = request.getParameter("password");
		String emailid=request.getParameter("emailid");
		String mobile = request.getParameter("mobile");
		
		
		
		HttpSession adminSession = request.getSession(false);
		System.out.println("user id is :"+ userId);
		if( userId != null && userName != null && password != null && emailid != null && mobile !=null){
			user.setUserid(Integer.parseInt(userId));
			user.setUsername(userName);
			user.setPassword(password);
			user.setEmailid(emailid);
			user.setMobno(mobile);
			/*product.setProductImage(productImage);*/
			try {
				
			} catch (Exception e) {
				adminSession.setAttribute("exception",e.getMessage());
			}
			AdminUserDAO dao=new AdminUserDAO();
			try {
				status = dao.update(user);
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			if(status == true){
				adminSession.setAttribute("message","You Have Succesfully Updated User !!!" );
				response.sendRedirect("adminjsp/ListUser.jsp");
			}
		}else{
			System.out.println("else part of updte");
			out.println("<script>alert('Write Product Details Again !!!')</script>");
			response.sendRedirect("AdminLogin.jsp");
		}
	}
}


