package org.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.datatypes.xsd.XSDDatatype;

public class Trip {
    private final Flug[] fluege;
    private final String tripID;
    private final String startTime;
    private final String endTime;
    private final double price;

    private Resource trip_Res;

    private final Model model = Main.getModel();
    private final String namespace = Main.getMyNamespace() + "Trip/";

    public Trip(String tripID, Flug[] fluege, String startTime, String endTime, double price) {
        this.tripID = tripID;
        this.fluege = fluege;
        this.startTime = startTime;
        this.endTime = endTime;
        this.price = price;
    }

    // Initialisiert das RDF-Modell für die Trip-Ressource
    public void init() {
        // Erstellung von RDF-Properties
        Property hasStartTime = model.createProperty(namespace, "hasStartTime");
        Property hasEndTime = model.createProperty(namespace, "hasEndTime");
        Property hasPrice = model.createProperty(namespace, "hasPrice"); // Neue Property für den Preis
        Property is = model.createProperty(namespace, "is");

        // Erstellung der Trip-Ressource
        trip_Res = model.createResource(namespace + this.tripID);

        // Hinzufügen der Startzeit
        trip_Res.addProperty(hasStartTime, model.createTypedLiteral(this.startTime, XSDDatatype.XSDdateTime));

        // Hinzufügen der Endzeit
        trip_Res.addProperty(hasEndTime, model.createTypedLiteral(this.endTime, XSDDatatype.XSDdateTime));

        // Hinzufügen des Preises
        trip_Res.addProperty(hasPrice, model.createTypedLiteral(this.price, XSDDatatype.XSDdecimal));

        // Hinzufügen des Trips zur Trip-Klasse
        trip_Res.addProperty(is, model.createResource(namespace));
    }

    public String getTripID() {
        return tripID;
    }

    public Resource getTrip_Res() {
        return trip_Res;
    }

    // Fügt Flüge zum Trip hinzu
    public void hasFlights(Flug[] flights) {
        Property hasFlight = model.createProperty(namespace, "hasFlight");

        for (Flug flug : fluege) {
            // Erstellung der Flug-Ressource und Hinzufügen zum Trip
            Resource flightRes = model.createResource(flug.getNamespace() + flug.getFlugID());
            trip_Res.addProperty(hasFlight, flightRes);
        }
    }

}
