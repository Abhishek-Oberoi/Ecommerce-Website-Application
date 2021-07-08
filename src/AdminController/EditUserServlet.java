package AdminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOLayer.AdminUserDAO;
import Model.User;


@WebServlet("/EditUserServlet")
public class EditUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		User user = new User();
		
		if(userid != null && !(userid.equals(""))){	
			try {
				
				
				user=new AdminUserDAO().getUserById(Integer.parseInt(userid));
			} catch (NumberFormatException e) {
				request.getSession().setAttribute("exception",e.getMessage());
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			request.getSession().setAttribute("user", user);
			response.sendRedirect("adminjsp/editUser.jsp");
		}else{
			request.getSession().setAttribute("message", "Error !!!! User not edited!!!!!!");
			response.sendRedirect("adminjsp/ListUsers.jsp");
		}
		
		
	}
	
	
}	
	

