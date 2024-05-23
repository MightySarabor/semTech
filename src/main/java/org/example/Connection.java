package org.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.datatypes.xsd.XSDDatatype;

public class Connection {
    private final String duration;
    private final String planeModel;
    private final int maxNumPassengers;
    private final Airport airportA;
    private final Airport airportB;

    private Resource connections_Res;

    private final Model model = Main.getModel();
    private final String namespace = Main.getMyNamespace() + "Connection/";

    public Connection(String duration, String planeModel, int maxNumPassengers, Airport airportA, Airport airportB) {
        this.duration = duration;
        this.planeModel = planeModel;
        this.maxNumPassengers = maxNumPassengers;
        this.airportA = airportA;
        this.airportB = airportB;
    }

    // Initialisiert das RDF-Modell für die Verbindung
    public void init() {
        // Initialisierung der Eigenschaften
        Property hasDuration = model.createProperty(namespace, "hasDuration");
        Property hasPlaneModel = model.createProperty(namespace, "hasPlaneModel");
        Property hasMaxNumPass = model.createProperty(namespace, "hasMaxNumPassengers");
        Property is = model.createProperty(namespace, "is");

        // Initialisierung der Ressource
        connections_Res = model.createResource(namespace + airportA.getCode() + "-" + airportB.getCode());

        // Hinzufügen der Eigenschaften
        connections_Res.addProperty(hasDuration, model.createTypedLiteral(this.duration, XSDDatatype.XSDduration));
        connections_Res.addProperty(hasPlaneModel, this.planeModel);
        connections_Res.addProperty(hasMaxNumPass, model.createTypedLiteral(this.maxNumPassengers));

        // Hinzufügen der Connection zur Connection-Klasse
        connections_Res.addProperty(is, model.createResource(namespace));
    }

    // Verbindet zwei Flughäfen durch eine Verbindung
    public void connects() {
        // Initialisierung der Eigenschaften
        Property connects = model.createProperty(Main.getMyNamespace(), "connects");
        Property hasAirport = model.createProperty(Main.getMyNamespace(), "hasAirport");

        // Erstellen der Ressource für die Verbindung zwischen den Flughäfen
        Resource connection_Res = model.createResource(namespace + airportA.getCode() + "-" + airportB.getCode());

        // Hinzufügen der Verbindung zur Liste der Verbindungen der aktuellen Verbindung
        connections_Res.addProperty(connects, connection_Res);

        // Hinzufügen der Flughäfen zur Verbindung
        connection_Res.addProperty(hasAirport, airportA.getAirport_Res());
        connection_Res.addProperty(hasAirport, airportB.getAirport_Res());
    }

    public Resource getConnections_Res() {
        return connections_Res;
    }
}
