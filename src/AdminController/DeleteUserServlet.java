package AdminController;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOLayer.AdminUserDAO;


@WebServlet("/DeleteUserServlet")
public class DeleteUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String userid = request.getParameter("userid");
		if(userid != null && !(userid.equals(""))){
			boolean status = false;
			try {
				
				status=new AdminUserDAO().remove(Integer.parseInt(userid));
			} catch (NumberFormatException e) {
				request.getSession().setAttribute("exception",e.getMessage());
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			if(status == true){
				request.getSession().setAttribute("message", "Selected User has been deleted Successfully.");
				response.sendRedirect("adminjsp/AdminHome.jsp");
			}else{
				request.getSession().setAttribute("message", "Error !!!! Selected User has not been deleted !!!");
			}
		}
	}

}
