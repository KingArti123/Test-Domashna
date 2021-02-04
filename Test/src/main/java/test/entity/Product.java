package main.java.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer productId;
	
    private String name;
	
	private Double price;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "manufactorerId")
	private Manufactorer manufactorer;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "categoryId")
	private Category category;
	
	private String origin;

	public Product() {
		super();
	}

	public Product(String name, Double price, Manufactorer manufactorer, Category category, String origin) {
		super();
		this.name = name;
		this.price = price;
		this.manufactorer = manufactorer;
		this.category = category;
		this.origin = origin;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Manufactorer getManufactorer() {
		return manufactorer;
	}

	public void setManufactorer(Manufactorer manufactorer) {
		this.manufactorer = manufactorer;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}
	
	
}
