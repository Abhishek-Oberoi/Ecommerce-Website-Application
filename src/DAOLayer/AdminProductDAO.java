package DAOLayer;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import Model.Category;
import Model.Product;

public class AdminProductDAO{
	private boolean status = false;
	
	private Session session ;
	private Transaction getTransaction;
	
	
	public boolean add(Object newObject){
		Product newProduct = new Product();
		status = false;
		System.out.println("adding the product .....into database");
		newProduct = (Product)newObject;
	
			status =false;
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			session.save(newProduct);
			getTransaction.commit();
			status = true;
		
		System.out.println("return true from product addition in table");
		return status;
	}

	
	public boolean remove(int id){
		
			status =false;
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			Query query = session.createQuery("delete from Product where productid="+id);
			int i = query.executeUpdate();
			getTransaction.commit();
			if(i>0){
				status = true;
			}
		
	
		
		return status;
	}


	public boolean update(Object newObject){
		Product updateProduct = new Product();
		status = false;
		System.out.println("updating the product .....into database");
		updateProduct = (Product)newObject;
		
			status =false;
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			session.get(Product.class, updateProduct.getProductId());
			session.merge(updateProduct);
			getTransaction.commit();
			status = true;
		
		System.out.println("return true from product addition in table updating....");
		return status;
	}
	public List<?> readAll() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Product> getProductsByCatg(Category category){
		
		
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			Query query = session.createQuery("FROM Product where catgid="+category.getCatgId());
			
			List<Product> products = query.list();
		
		return products;
	}

	public Product getProductById(int productId){
		Product product = null;
		
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			Query query = session.createQuery("FROM Product where productId="+productId);
			product = (Product)query.uniqueResult();
		
		return product;
	}

	public List<?> getProductsByCatg(int catgId){
		List<?> products = null;
		
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			Query query = session.createQuery("FROM Product where catgid="+catgId);
			products = query.list();
		
		return products;
	}
}
