package DrinksPackage;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jose4j.http.Response;
import org.jose4j.jwk.JsonWebKey;
import org.jose4j.jwk.RsaJsonWebKey;
import org.jose4j.jwk.RsaJwkGenerator;
import org.jose4j.jws.AlgorithmIdentifiers;
import org.jose4j.jws.JsonWebSignature;
import org.jose4j.jwt.JwtClaims;
import org.jose4j.lang.JoseException;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;

import DrinksPackage.Drink;
import DrinksPackage.User;

@Path("/DrinkApp")
public class MainWeb {
	static Logger logger = LogManager.getLogger(Drink.class);
	private static Map<Integer, Drink> myMap = new HashMap<>();
	private static Map<String, User> myUsers = new HashMap<>();
	static JsonWebKey myJwk = null;

	
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
		
		User admin = new User();
		admin.setUser("admin");
		admin.setTipo(0);
		admin.setPassword("admin");
		
		myUsers.put("admin", admin);
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
	
	@POST
	@Path("/putApiKey")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getApiKey(User myUser) {
		UUID apikey = UUID.randomUUID();
		User newUser = new User();
		newUser.setUser(myUser.getUser());
		newUser.setPassword(myUser.getPassword());
		newUser.setApikey(apikey.toString());
		myUsers.put(myUser.getUser(), newUser);
		return apikey.toString();
	}

	@POST
	@Path("/testApikey1")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String testing(User myUser, @HeaderParam("apikey") String apikey) {
		if (myUsers.containsKey(myUser.getUser())) {
			if ((myUsers.get(myUser.getUser()).getApikey()).equals(apikey)) {
				return "GRANTED";
			}
		}
		return "Denied";
	}
	
//	@Path("/authenticateJWT")
//	@GET
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response authenticateCredentials(@HeaderParam("username") String username,
//			@HeaderParam("password") String password)
//			throws JsonGenerationException, JsonMappingException, IOException {
//		User user = new User();
//		user.setUser(username);
//		user.setPassword(password);
//
//		RsaJsonWebKey jwk = null;
//		try {
//			jwk = RsaJwkGenerator.generateJwk(2048);
//			jwk.setKeyId("1");
//			myJwk = jwk;
//		} catch (JoseException e) {
//			e.printStackTrace();
//		}
//
//		JwtClaims claims = new JwtClaims();
//		claims.setIssuer("uca");
//		claims.setExpirationTimeMinutesInTheFuture(10);
//		claims.setGeneratedJwtId();
//		claims.setIssuedAtToNow();
//		claims.setNotBeforeMinutesInThePast(2);
//		claims.setSubject(user.getUser());
//		claims.setStringListClaim("roles", "restUser2");
//		JsonWebSignature jws = new JsonWebSignature();
//		jws.setPayload(claims.toJson());
//		jws.setKeyIdHeaderValue(jwk.getKeyId());
//		jws.setKey(jwk.getPrivateKey());
//		jws.setAlgorithmHeaderValue(AlgorithmIdentifiers.RSA_USING_SHA256);
//
//		String jwt = null;
//		try {
//			jwt = jws.getCompactSerialization();
//		} catch (JoseException e) {
//			System.out.println(e);
//		}
//		user.setApikey(jwt); // SET TOKEN
//		return Response.status(200).entity(jwt).build();
//	}
}
