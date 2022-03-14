package DrinksPackage;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import DrinksPackage.Drink;

@Path("/DrinkApp")
public class MainWeb {
	static Logger logger = LogManager.getLogger(Drink.class);
	private static Map<Integer, Drink> myMap = new HashMap<>();
	
	static {
		Drink cocacola = new Drink();
		cocacola.setName("CocaCola");
		cocacola.setAmount(5);
		cocacola.setPrice(1.50);
		cocacola.setVolume("33 cl");
		
		myMap.put(1, cocacola);
		
		Drink fanta = new Drink();
		fanta.setName("Fanta");
		fanta.setAmount(7);
		fanta.setPrice(1.50);
		fanta.setVolume("33 cl");
		
		myMap.put(2, fanta);	
	}
	
	@GET
	@Path("/GetDrinks")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetPlainDrinks() {
		return myMap.toString();
	}
	
	@GET
	@Path("/GetDrinksJSON")
	@Produces(MediaType.APPLICATION_JSON)
	public String GetPlainDrinksJSON() {
		return myMap.toString();
	}
	
	@GET
	@Path("/GetDrink/{id}")
	@Produces(MediaType.TEXT_PLAIN)
	public String GetPlainDrinks(@PathParam("id") Integer _id) {
		return 
				"<h2>"+myMap.get((Integer)_id).getName()+"</h2>";
	}
}
