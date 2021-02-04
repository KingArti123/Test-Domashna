package main.java.test.services;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import main.java.test.entity.Address;
import main.java.test.entity.Category;
import main.java.test.entity.CategoryResponce;
import main.java.test.entity.Customer;
import main.java.test.entity.DiscountedReturn;
import main.java.test.entity.Manufactorer;
import main.java.test.entity.Product;

public class ProductServices {
	
static SessionFactory factory;
	
	public static Session init() {
		
		Configuration cfg = new Configuration();
		
		cfg.addAnnotatedClass(main.java.test.entity.Product.class);
		cfg.addAnnotatedClass(main.java.test.entity.Manufactorer.class);
		cfg.addAnnotatedClass(main.java.test.entity.Address.class);
		cfg.addAnnotatedClass(main.java.test.entity.ShoppingCart.class);
		cfg.addAnnotatedClass(main.java.test.entity.Customer.class);
		cfg.addAnnotatedClass(main.java.test.entity.Category.class);
		
		cfg.configure();
		
		factory = cfg.configure().buildSessionFactory();
		
		Session session = factory.openSession();
		
		return session;
	}

	public static void createProduct(Product product) {
		

		Session session = init();
		
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			
			Category c = checkCategory(product.getCategory().getProductCategory());
			if(c == null) {
			
			Category category = new Category(product.getCategory().getProductCategory());
			product.setCategory(category);
			session.save(category);
			
			}else {
				product.setCategory(c);;
			}
			
			Address address = new Address(product.getManufactorer().getAddress().getStreet(), product.getManufactorer().getAddress().getCity(), product.getManufactorer().getAddress().getZipCode());
			
			session.save(address);
			
            Manufactorer m = checkMan(product.getManufactorer().getName());
			
			if(m == null) {
			
			Manufactorer manufactorer = new Manufactorer(product.getManufactorer().getName(), address);
			product.setManufactorer(manufactorer);
		    session.save(manufactorer);
			}else {
				product.setManufactorer(m);
			}
		    
			session.save(product);
			
			
			
			t.commit();
			session.close();
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
	}

private static Category checkCategory(String productCategory) {
		
	Session session = init();
	
	Transaction t = null;
	
	try {
		
		
		t = session.beginTransaction();
		
		Query query = session.createQuery("FROM main.java.test.entity.Category c WHERE c.productCategory =:productCategory");
		query.setParameter("productCategory", productCategory);
		
		Category category = (Category) query.getResultList().get(0);
		
		t.commit();
		session.close();
		return category;
	} catch (Exception e) {
		t.rollback();
		System.out.println(e);
		return null;
	}
}
private static Manufactorer checkMan(String manufactorerName) {
		
        Session session = init();
		
		Transaction t = null;
		
		try {
			
			
			t = session.beginTransaction();
			
			Query query = session.createQuery("FROM main.java.test.entity.Manufactorer m WHERE m.name =:name");
			query.setParameter("name", manufactorerName);
			
			Manufactorer manufactorer = (Manufactorer) query.getResultList().get(0);
			
			t.commit();
			session.close();
			return manufactorer;
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
			return null;
		}
}
	public static void updateProduct(Integer id, Product product) {
		

		Session session = init();
		
		Transaction t = null;
		
		try {
			
			t= session.beginTransaction();
			
			Address address = session.get(Address.class, id);
			
			address.setStreet(product.getManufactorer().getAddress().getStreet());
			address.setCity(product.getManufactorer().getAddress().getCity());
			address.setZipCode(product.getManufactorer().getAddress().getZipCode());
			session.update(address);
			
			Category category = session.get(Category.class, id);
			
			category.setProductCategory(product.getCategory().getProductCategory());
			session.update(category);
			
			Manufactorer manufactorer = session.get(Manufactorer.class, id);
			
			manufactorer.setAddress(address);
			manufactorer.setName(product.getManufactorer().getName());
			
			session.update(manufactorer);
			
			Product product2 = session.get(Product.class, id);
			product2.setCategory(category);
			product2.setManufactorer(manufactorer);
			product2.setName(product.getName());
			product2.setPrice(product.getPrice());
			product2.setOrigin(product2.getOrigin());
			
			session.update(product2);
			
			t.commit();
			session.close();
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		
	}

	public static void deleteProduct(Integer id) {
		
		Session session = init();
		
		Transaction t = null;
		
		try {
			
			t = session.beginTransaction();
			
			Product product = session.get(Product.class, id);
			
			session.delete(product);
			
			t.commit();
			session.close();
		} catch (Exception e) {
		t.rollback();
		System.out.println(e);
		}
		
	}

	public static List<Product> getAllProducts() {
		
		Session session = init();
		
		Transaction t = null;
		
		List<Product>allProducts= new ArrayList<>();
		
		try {
			
			t = session.beginTransaction();
			
			List<Product> products = session.createQuery("FROM main.java.test.entity.Product").list();
			
			for (Product product : products) {
				
				allProducts.add(product);
			}
			
			t.commit();
			session.close();
			return allProducts;
			
		} catch (Exception e) {
			t.rollback();
			
			System.out.println(e);
		}
		return null;
	}

	public static List<Product> byOrigin(String origin) {
		

		Session session = init();
		
		Transaction t = null;
		
		List<Product>allProducts= new ArrayList<>();
		
		try {
			
			t = session.beginTransaction();
			
			List<Product> products = session.createQuery("FROM main.java.test.entity.Product").list();
			
			for (Product product2 : products) {
				
				if(product2.getOrigin().equals(origin)) {
					
					allProducts.add(product2);
				}
					
				}
			
			t.commit();
			session.close();
			return allProducts;
			
		} catch (Exception e) {
			t.rollback();
			
			System.out.println(e);
		}
		
		return null;
	}

	public static List<Product> getDiscountByOrigin(String origin) {
		
		Session session = init();
		
		Transaction t = null;
		
		List<Product> result = new ArrayList<>();
		
		try {
			
			t = session.beginTransaction();
			
			List<Product> products = session.createQuery("FROM main.java.test.entity.Product").list();
			
			for (Product product : products) {
				
				if(product.getOrigin().equals(origin)) {
					product.setPrice(product.getPrice()-(product.getPrice() * 0.18));
					
					result.add(product);
				}
			}
			t.commit();
			session.close();
			return result;
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		return null;
	}
	
public static List<CategoryResponce> responceByCategory(String category) {
		
		Session session = init();
		
		Transaction t = null;
		
		List<CategoryResponce> result = new ArrayList<>();
		try {
			
			t = session.beginTransaction();
			
			Query query = session.createQuery("FROM main.java.test.entity.Product");
			
			List<Product> products = query.getResultList();
			
			for (Product product : products) {
				if(product.getCategory().getProductCategory().equals(category)) {
					
				CategoryResponce responce = new CategoryResponce();
				responce.setCategory(product.getCategory().getProductCategory());
				responce.setName(product.getName());
				responce.setPrice(product.getPrice());
				
				result.add(responce);
				
				}
			}
			t.commit();
			session.close();
			return result;
			
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		return null;
	}

	public static List<DiscountedReturn> returnedDiscount(String origin) {
		

		Session session = init();
		
		Transaction t = null;
		
		List<DiscountedReturn> result = new ArrayList<>();
		try {
			
			t = session.beginTransaction();
			
			Query query = session.createQuery("FROM main.java.test.entity.Product");
			
			List<Product> products = query.getResultList();
			
			for (Product product : products) {
				if(product.getOrigin().equals(origin)) {
					
				DiscountedReturn responce = new DiscountedReturn();
				responce.setName(product.getName());
				responce.setOrigin(product.getOrigin());
				responce.setPrice(product.getPrice());
				responce.setDiscountedPrice(product.getPrice()-(product.getPrice() * 0.18));
				
			    result.add(responce);
				
				}
			}
			t.commit();
			session.close();
			return result;
			
			
		} catch (Exception e) {
			t.rollback();
			System.out.println(e);
		}
		
		
		
		return null;
	}

}
