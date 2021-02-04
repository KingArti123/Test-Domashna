package main.java.test.controler;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import main.java.test.entity.Customer;
import main.java.test.services.CustomerServices;



@Path("/customer")
public class CustomerControler {

	@POST
	@Path("/register")
	@Consumes(MediaType.APPLICATION_JSON)
	public String registerCustomer(Customer customer) {
		
		CustomerServices.registerCustomer(customer);
		
		return "New customer with id " + customer.getCustomerId() +" has registered!";
	}
	
	@PUT
	@Path("/updateCustomer/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public String updateProduct(@PathParam("id")Integer id, Customer customer) {
    	
    	CustomerServices.updateCustomer(id, customer);
    	
    	return "The customer with Id: " + customer.getCustomerId() + " was updated!";
    }
	
	@DELETE
	@Path("/deleteCustomer/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteCustomer(@PathParam("id")Integer id) {
		
		CustomerServices.deleteCustomer(id);
		
		return "Customer was deleted";
		
	}
	
}
