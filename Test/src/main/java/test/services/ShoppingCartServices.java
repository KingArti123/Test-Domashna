package main.java.test.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.java.test.entity.CategoryResponce;
import main.java.test.entity.Customer;
import main.java.test.entity.DiscountedReturn;
import main.java.test.entity.Product;
import main.java.test.entity.ShoppingCart;

public class ShoppingCartServices {
	
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

	public static ShoppingCart getCartById(Integer id) {
		
        Session session = init();
		Transaction t = null;
		
		try {
			t = session.beginTransaction();
			
			Query query = session.createQuery("SELECT p FROM main.java.test.entity.ShoppingCart p WHERE p.shcaId =: shcaId");
			query.setParameter("shcaId", id);
			
			ShoppingCart cart = (ShoppingCart) query.getResultList().get(0);
			
			t.commit();
			
			session.close();
			
			return cart;
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			return null;
		}
		
	}

	public static void createCart(Integer id, ShoppingCart cart) {
		
		Session session = init();
		
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			
			Customer customer = session.get(Customer.class, id);
			
			Date date = new Date(System.currentTimeMillis());
			
			List<Product> products = cart.getProducts();
			
			ShoppingCart shoppingCart = new ShoppingCart();
			shoppingCart.setCustomer(customer);
			shoppingCart.setProducts(products);
			shoppingCart.setCreatedOn(date);
					
		 session.save(shoppingCart);
	     t.commit();
	     session.close();
			        
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

	public static void removeProductFromSC(Integer id, Product product) {
		
		Session session = init();
		
		Transaction t = null;
		
		
		try {
			t = session.beginTransaction();
			
			ShoppingCart cart = session.get(ShoppingCart.class, id);
			
			Query query = session.createQuery("FROM main.java.test.entity.Product p WHERE p.productId=:productId");
			query.setParameter("productId", product.getProductId());
			
			Product product2 = (Product) query.getResultList().get(0);
			
			if(cart.getProducts().contains(product2)) {
				cart.getProducts().remove(product2);
				
				session.update(cart);
			}
			
			t.commit();
			session.close();
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}

	
	

	

	

}
