package com.society.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.jfree.util.Log;

import com.society.hibernate.UserDetailasHibernate;
import com.society.utility.HibernateUtility;

public class UserDetailasDao
{
	public void valUserDetails(UserDetailasHibernate u)
	{
		System.out.println("In DAO");
		
		System.out.println("In DAO------Name"+u.getName());
		
		
		
		HibernateUtility hbu=null;
		Session session=null;
		Transaction transaction=null;
		try{
		hbu = HibernateUtility.getInstance();
		session = hbu.getHibernateSession();
		 System.out.println("got session ");
		 transaction = session.beginTransaction();
	
		 System.out.println("Tx started");
		 
		session.saveOrUpdate(u);
		System.out.println("oooooooooooooooooo");
		transaction.commit();
		System.out.println("Successful");
		}
		
		catch(RuntimeException e){
			e.printStackTrace();
			try{
				transaction.rollback();
			}catch(RuntimeException rbe)
			{
				Log.error("Couldn't roll back tranaction",rbe);
			}	
		}finally{
			hbu.closeSession(session);
		}
		
	}

}
