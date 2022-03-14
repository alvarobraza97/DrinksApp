package DrinksPackage;

import org.glassfish.jersey.server.ResourceConfig;
import com.fasterxml.jackson.jaxrs.xml.JacksonJaxbXMLProvider;

public class MyXMLSerializer extends ResourceConfig {
    public  MyXMLSerializer() {
        //register your resources
        packages("DrinksPackage");
        //if you're using Jackson as your XMLProvider for example
        register(JacksonJaxbXMLProvider.class);
    }
} 
