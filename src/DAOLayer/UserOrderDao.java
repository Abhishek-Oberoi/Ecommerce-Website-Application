package DAOLayer;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Exceptions.MyException;
import Model.Product;
import Model.UserOrder;

public class UserOrderDao {
	private boolean status = false;
	
	private Session session ;
	private Transaction getTransaction;
	
	
	public boolean add(UserOrder order) throws MyException {
		status = false;
		try {
			status =false;
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			session.save(order);
			getTransaction.commit();
			status = true;
		} catch (HibernateException he) {
			//System.out.println("testing...........products");
			he.printStackTrace();
			throw new MyException(he);
		}catch(final Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		System.out.println("order inserted");
		return status;
	}

	public int getuserId(String UserName) throws MyException {
		Query query = null;
		int userid =0;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			query = session.createQuery("SELECT p.userid FROM User p WHERE p.username=:username");
		    query.setParameter("username", UserName);
		    userid = (int) query.list().get(0);
		} catch (HibernateException he) {
			throw new MyException(he);
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return userid;
		
	}

}
