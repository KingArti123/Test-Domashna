package main.java.test.controler;



import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.test.entity.Product;
import main.java.test.entity.ShoppingCart;
import main.java.test.services.ShoppingCartServices;

@Path("/cart")
public class ShoppingCartControler {
	
	@POST
	@Path("/createCart/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createCart(@PathParam("id")Integer id, ShoppingCart cart) {
		
		ShoppingCartServices.createCart(id,cart);
		
		return "Shopping cart with Id: " + cart.getShcaId() + " was created";
	}
	
	@GET
	@Path("/getCartById/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public ShoppingCart getCartById(@PathParam("id")Integer id) {
		return ShoppingCartServices.getCartById(id);
		
	}
	
	
	@DELETE
	@Path("/removeProduct/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String removeProductFromSC(@PathParam("id")Integer id, Product product ) {
		
		ShoppingCartServices.removeProductFromSC(id,product);
		
		return "Product was deleted";
		
		
	}
	

	
	
	

}
