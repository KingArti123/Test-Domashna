package main.java.test.services;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import main.java.test.entity.Address;
import main.java.test.entity.Customer;

public class CustomerServices {
	
     static SessionFactory factory;
	
	public static Session init() {
		
		Configuration cfg = new Configuration();
		
		cfg.addAnnotatedClass(main.java.test.entity.Address.class);
		cfg.addAnnotatedClass(main.java.test.entity.Customer.class);
		cfg.addAnnotatedClass(main.java.test.entity.Manufactorer.class);
		cfg.addAnnotatedClass(main.java.test.entity.Product.class);
		cfg.addAnnotatedClass(main.java.test.entity.ShoppingCart.class);
		cfg.addAnnotatedClass(main.java.test.entity.Category.class);
		
		cfg.configure();
		
		factory = cfg.configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		return session;
	}

	public static void registerCustomer(Customer customer) {
		
		Session session = init();
		
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			
		    Address address = new Address(customer.getAddress().getStreet(), customer.getAddress().getCity(), customer.getAddress().getZipCode());
			session.save(address);
			
			customer.setAddress(address);
			session.save(customer);
			
			t.commit();
			session.close();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

	public static void updateCustomer(Integer id, Customer customer) {
		

		Session session = init();
		
		Transaction t = null;
		
		try {
			
			t= session.beginTransaction();
			
			Address address = session.get(Address.class, id);
			
			address.setStreet(customer.getAddress().getStreet());
			address.setCity(customer.getAddress().getCity());
			address.setZipCode(customer.getAddress().getZipCode());
			session.update(address);
			Customer customer2 = session.get(Customer.class, id);
			
			customer2.setAddress(address);
			customer2.setLastName(customer.getLastName());
			session.update(customer2);
			
			t.commit();
			session.close();
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

	public static void deleteCustomer(Integer id) {
		
		Session session = init();
		
		Transaction t = null;
		
		try {
			t= session.beginTransaction();
			
			Customer customer = session.get(Customer.class, id);
			
			session.delete(customer);
			
			t.commit();
			session.close();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}
	
	


}
