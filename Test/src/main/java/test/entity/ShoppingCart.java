package main.java.test.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer shcaId;
	
	@ManyToMany
	@JoinColumn(referencedColumnName = "productId")
	private List<Product> products;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "customerId")
	private Customer customer;

	private Date createdOn;
	
	public ShoppingCart() {
		super();
	}

	public ShoppingCart(List<Product> products, Customer customer, Date createdOn) {
		super();
		this.products = products;
		this.customer = customer;
		this.createdOn = createdOn;
	}

	public Integer getShcaId() {
		return shcaId;
	}

	public void setShcaId(Integer shcaId) {
		this.shcaId = shcaId;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
}
