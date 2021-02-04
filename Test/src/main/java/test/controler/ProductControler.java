package main.java.test.controler;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import main.java.test.entity.Category;
import main.java.test.entity.CategoryResponce;
import main.java.test.entity.Customer;
import main.java.test.entity.DiscountedReturn;
import main.java.test.entity.Product;
import main.java.test.services.CustomerServices;
import main.java.test.services.ProductServices;
import main.java.test.services.ShoppingCartServices;

@Path("/product")
public class ProductControler {
	
	
	@POST
	@Path("/createProduct")
	@Consumes(MediaType.APPLICATION_JSON)
	public String createProduct(Product product) {
		
		ProductServices.createProduct(product);
		
		return "New product with id " + product.getProductId() +" has registered!";
	}
	
	@PUT
	@Path("/updateProduct/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
    public String updateProduct(@PathParam("id")Integer id, Product product) {
    	
    ProductServices.updateProduct(id, product);
    	
    	return "The product with Id: " + product.getProductId()+ " was updated!";
    }
	
	@DELETE
	@Path("/deleteProduct/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public String deleteProduct(@PathParam("id")Integer id) {
		
		ProductServices.deleteProduct(id);
		
		return "Product was deleted";
		
	}
	@GET
	@Path("/getAllProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getAllProducts() {
		
		return ProductServices.getAllProducts();
	}
	@GET
	@Path("/getAllByOrigin/{origin}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> byOrigin(@PathParam("origin")String origin){
		return ProductServices.byOrigin(origin);
		
	}
	@GET
	@Path("/discount/{origin}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getDiscountByOrigin(@PathParam("origin") String origin) {
		return ProductServices.getDiscountByOrigin(origin);
	}
	
	@GET
	@Path("/responce/{category}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryResponce> responceByCategory(@PathParam("category") String category){
		
		return ProductServices.responceByCategory(category);
	}
	
	@GET
	@Path("/discounted/{origin}")
	@Produces(MediaType.APPLICATION_JSON)
	public List<DiscountedReturn> returnedDiscount(@PathParam("origin")String origin) {
		
		return ProductServices.returnedDiscount(origin);
	}
	

}
