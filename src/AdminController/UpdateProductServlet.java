package AdminController;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;
import javax.servlet.annotation.MultipartConfig;
import DAOLayer.AdminCategoryDAO;
import DAOLayer.AdminProductDAO;

import Model.Category;
import Model.Product;

@WebServlet("/UpdateProductServlet")
@MultipartConfig(
		fileSizeThreshold =1024*1024,  //10 kb
		maxFileSize =1024*1024,  //300kb
		maxRequestSize =1024*1024  //1 mb
		)
public class UpdateProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Product product = new Product();
		boolean status = false;
		Category category = new Category();
		AdminProductDAO adminProDAO = new AdminProductDAO();
		AdminCategoryDAO adminCatgDAO = new AdminCategoryDAO();
		PrintWriter out = response.getWriter();
		response.setContentType("text/html");
		/*byte[] productImage = request.getParameter("product_image").getBytes();*/
		String productId= request.getParameter("product_id");
		String productName= request.getParameter("product_name");
		String productPrice = request.getParameter("product_price");
		double productQuantity=Double.parseDouble(request.getParameter("product_quantity"));
		double addproductQuantity=Double.parseDouble(request.getParameter("addproduct_quantity"));
		String productDesc = request.getParameter("product_desc");
		String productCatg = request.getParameter("category");
		Part part= request.getPart("productImg");
		productQuantity=productQuantity+addproductQuantity;
		
		HttpSession adminSession = request.getSession(false);
		System.out.println("product id is :"+ productId);
		if( productId != null && productName != null && productPrice != null && productDesc != null){
			product.setProductId(Integer.parseInt(productId));
			product.setProductName(productName);
			product.setProductPrice(Double.parseDouble(productPrice));
			product.setProductQty(productQuantity);
	
			product.setProductDesc(productDesc);
			/*product.setProductImage(productImage);*/
			try {
				category = adminCatgDAO.getCategoryByName(productCatg);
				long size =part.getSize();
				byte[] imageBytes = new byte[(int) size];
				InputStream inputStream = part.getInputStream();
				inputStream.read(imageBytes);
				inputStream.close();
				product.setProductImage(imageBytes);
				product.setBase64Image("");
			} catch (Exception e) {
				adminSession.setAttribute("exception",e.getMessage());
			}
			product.setCategory(category);
			try {
				status = adminProDAO.update(product);
			} catch (Exception e) {
				request.getSession().setAttribute("exception",e.getMessage());
			}
			if(status == true){
				adminSession.setAttribute("message","You Have Succesfully Updated Product !!!" );
				response.sendRedirect("adminjsp/SelectCategoryProduct.jsp");
			}
		}else{
			System.out.println("else part of updte");
			out.println("<script>alert('Write Product Details Again !!!')</script>");
			response.sendRedirect("AdminLogin.jsp");
		}
	}
}
