package org.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Customer {
    private String firstName;
    private String lastName;
    private String passPort_ID;

    public Customer(String firstName, String lastName, String passPort_ID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passPort_ID = passPort_ID;
    }

    public void init() {
        Model model = Main.getModel();
        String customerNamespace = Main.getMyNamespace() + "Customer/";

        // Init properties
        Property hasFirstName = model.createProperty(customerNamespace, "hasFirstName");
        Property hasLastName = model.createProperty(customerNamespace, "hasLastName");
        Property hasPassportID = model.createProperty(customerNamespace, "hasPassportID");

        // Init resource
        Resource customer_Res = model.createResource(customerNamespace);

        // Add properties
        customer_Res.addProperty(hasFirstName, this.firstName);
        customer_Res.addProperty(hasLastName, this.lastName);
        customer_Res.addProperty(hasPassportID, this.passPort_ID);
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getPassportID() {
        return passPort_ID;
    }
}

