package main.java.test.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Manufactorer {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer manufactorerId;
	
	private String name;
	
	@ManyToOne
	@JoinColumn(referencedColumnName = "addressId")
	private Address address;

	public Manufactorer() {
		super();
	}

	public Manufactorer(String name, Address address) {
		super();
		this.name = name;
		this.address = address;
	}

	public Integer getManufactorerId() {
		return manufactorerId;
	}

	public void setManufactorerId(Integer manufactorerId) {
		this.manufactorerId = manufactorerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
	
	
}
