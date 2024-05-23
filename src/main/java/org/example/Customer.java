package org.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Customer {
    private final String firstName;
    private final String lastName;
    private final String passportID;

    private Resource passport_Res;

    private final Model model = Main.getModel();
    private final String customerNamespace = Main.getMyNamespace() + "Customer/";

    public Customer(String firstName, String lastName, String passportID) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.passportID = passportID;
    }

    // Initialisiert das RDF-Modell für den Kunden
    public void init() {
        // Initialisierung der Eigenschaften
        Property hasName = model.createProperty(customerNamespace, "hasName");
        Property hasFirstName = model.createProperty(customerNamespace, "hasFirstName");
        Property hasLastName = model.createProperty(customerNamespace, "hasLastName");
        Property hasPassportID = model.createProperty(customerNamespace, "hasPassportID");

        // Initialisierung der Ressourcen
        Resource customer_Res = model.createResource(customerNamespace);
        passport_Res = model.createResource(customerNamespace + passportID);

        // Hinzufügen der Eigenschaften
        passport_Res.addProperty(hasName, model.createResource()
                .addProperty(hasFirstName, this.firstName)
                .addProperty(hasLastName, this.lastName));
        customer_Res.addProperty(hasPassportID, passport_Res);
    }

    // Buchung einer Reise durch den Kunden
    public void booksTrip(Trip trip) {
        Model model = Main.getModel();
        Property booksTrip = model.createProperty(customerNamespace, "booksTrip");
        passport_Res.addProperty(booksTrip, trip.getTrip_Res());
    }
}
