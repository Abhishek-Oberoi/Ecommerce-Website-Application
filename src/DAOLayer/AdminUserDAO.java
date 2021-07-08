package DAOLayer;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import Exceptions.MyException;

import Model.User;

public class AdminUserDAO{
	private User newUser;
	private boolean status;
	private Session session ;
	private Transaction getTransaction;
	
	
	public boolean remove(int id) throws MyException {
		try {
			status =false;
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			Query queryProd = session.createQuery("delete from User where userid="+id);
			int i = queryProd.executeUpdate();
			
			Query queryCatg = session.createQuery("delete from User where userid="+id);
			int i2 = queryCatg.executeUpdate();
			
			getTransaction.commit();
			if(i>0 || i2>0){
				status = true;
			}
		} catch (HibernateException he) {
			he.printStackTrace();	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return status;
	}

	public boolean update(Object newObject) throws MyException {
		User updateUser = new User();
		updateUser = (User)newObject;
		try {
			status =false;
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			session.get(User.class, updateUser.getUserid());
			session.merge(updateUser);
			getTransaction.commit();
			status = true;
		} catch (HibernateException he) {
			throw new MyException(he);	
		}catch(final Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		System.out.println("return true from user addition in table updating....");
		return status;
	}

	public List<?> readAll() throws MyException {
		List<?> userss = null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			//Query query = session.getNamedQuery(Category.FIND_ALL_CATEGORY);
			Query query = session.createQuery("From User");
			userss = query.list();
		} catch (HibernateException he) {
			throw new MyException(he);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			
		}
		return userss;
	}
	
	public User getUserByName(String userName) throws MyException{
		User user =null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			Query query = session.createQuery("FROM User where username like '"+userName +"'" );
			user = (User) query.uniqueResult();
		} catch (HibernateException he) {
			throw new MyException(he);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}
	
	public User getUserById(int userid) throws MyException{
		User user =null;
		try {
			session = HibernateUtil.getSessionFactory().openSession();
			getTransaction = session.beginTransaction();
			Query query = session.createQuery("FROM User where userid ="+userid );
			user = (User) query.uniqueResult();
		} catch (HibernateException he) {
			throw new MyException(he);	
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			session.close();
		}
		return user;
	}

	
	public boolean add(Object newObject) throws MyException {
		// TODO Auto-generated method stub
		return false;
	}
}
