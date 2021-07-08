package UserControllers;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAOLayer.UserOrderDao;
import Model.UserOrder;


@WebServlet("/AddToOrderServlet")
public class AddToOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			HttpSession session = request.getSession();
			int userid = (int) session.getAttribute("userid");
			String[] productname = request.getParameterValues("productname");
			String[] productId = request.getParameterValues("productId");
			UserOrderDao oderdao = new UserOrderDao();
			ArrayList<UserOrder> userOrderList = new ArrayList<UserOrder>();
			
			if(productId.length>0 && productname.length>0) {
				UserOrder userOrder = new UserOrder();
				
				for(int i=0;productId.length>i;i++) {
					
					userOrder.setUserid(userid);
					userOrder.setProductid(Integer.parseInt(productId[i]));
					userOrder.setProductname(productname[i]);
					userOrderList.add(userOrder);
				}
				for (UserOrder order : userOrderList) {
				
					oderdao.add(order);
				}	
				
				 
			}
			
			response.sendRedirect("userjsp/InvoicePage.jsp");
			doGet(request, response);
		}catch (Exception e) {
				
				e.printStackTrace();
			}
	}

}
