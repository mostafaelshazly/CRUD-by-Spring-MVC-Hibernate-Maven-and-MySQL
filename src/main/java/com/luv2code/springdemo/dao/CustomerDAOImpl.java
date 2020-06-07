package com.luv2code.springdemo.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.luv2code.springdemo.entity.Customer;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

	// need to inject the session factory
	@Autowired
	private SessionFactory sessionFactory;
			
	@Override
	public List<Customer> getCustomers() {
		
		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
				
		// create a query
		Query<Customer> theQuery = 
				currentSession.createQuery("from Customer order by firstName ", Customer.class);
		
		// execute query and get result list
		List<Customer> customers = theQuery.getResultList();
				
		// return the results		
		return customers;
	}

	@Override
	public void saveCustomer(Customer theCustomer) {
		// TODO Auto-generated method stub
		
		Session currentSession = sessionFactory.getCurrentSession();
		currentSession.saveOrUpdate( theCustomer);
		
		
	}

	@Override
	public Customer getCustomer(int theId) {

		// get the current hibernate session
		Session currentSession = sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using the primary key
		Customer theCustomer = currentSession.get(Customer.class, theId);
		
		
		
		return theCustomer;
	}
	@Override
	public int maxId() {
		Session currentSession = sessionFactory.getCurrentSession();
		int id = (int ) currentSession.createQuery(" SELECT  max(id) from Customer  ") .getSingleResult();
		return id;
	}
	
	
	
	@Override
	public void deleteCustomer(int theId) {
		// TODO Auto-generated method stub
		Session currentSession = sessionFactory.getCurrentSession();
		Query deleteQuery= currentSession.createQuery("delete from Customer where id =:customerId");
		deleteQuery.setParameter("customerId", theId);
		deleteQuery.executeUpdate();
		
	}

}






