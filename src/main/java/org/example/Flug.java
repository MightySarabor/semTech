package org.example;

import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.datatypes.xsd.XSDDatatype;

public class Flug {
    private final String seat;
    private final String seatingClass;
    private final String startTime;
    private final String endTime;
    private final String startAirport;
    private final String destAirport;
    private final Resource flug_Res;
    private final String flugID;

    private final Model model = Main.getModel();
    private final String namespace = Main.getMyNamespace() + "Flug/";

    public String getFlugID() {
        return flugID;
    }

    public Flug(String flugID, String seat, String seatingClass, String startTime, String endTime, String startAirport, String destAirport) {
        this.flugID = flugID;
        this.seat = seat;
        this.seatingClass = seatingClass;
        this.startTime = startTime;
        this.endTime = endTime;
        this.startAirport = startAirport;
        this.destAirport = destAirport;

        flug_Res = model.createResource(namespace + this.flugID);
    }

    // Initialisiert das RDF-Modell für den Flug
    public void init() {
        // Erstellung von RDF-Properties
        Property hasSeat = model.createProperty(namespace, "hasSeat");
        Property hasSeatingClass = model.createProperty(namespace, "hasSeatingClass");
        Property hasStartTime = model.createProperty(namespace, "hasStartTime");
        Property hasEndTime = model.createProperty(namespace, "hasEndTime");
        Property hasStartAirport = model.createProperty(namespace, "hasStartAirport");
        Property hasDestAirport = model.createProperty(namespace, "hasDestAirport");
        Property is = model.createProperty(namespace, "is");



        // Hinzufügen des Flugs zur Flug-Klasse
        flug_Res.addProperty(is, model.createResource(namespace));

        // Hinzufügen der Literale
        flug_Res.addProperty(hasStartTime, model.createTypedLiteral(this.startTime, XSDDatatype.XSDdateTime));
        flug_Res.addProperty(hasEndTime, model.createTypedLiteral(this.endTime, XSDDatatype.XSDdateTime));
        flug_Res.addProperty(hasSeat, this.seat);
        flug_Res.addProperty(hasSeatingClass, this.seatingClass);
        flug_Res.addProperty(hasStartAirport, this.startAirport);
        flug_Res.addProperty(hasDestAirport, this.destAirport);
    }

    // Initialisiert alle Flüge in einem Array
    public static void initAll(Flug[] fluege) {
        for (Flug flug : fluege) {
            flug.init();
        }
    }

    // Fügt eine Verbindung zum Flug hinzu
    public Flug hasConnection(Connection connection) {
        Property hasConnection = model.createProperty(namespace, "hasConnection");
        flug_Res.addProperty(hasConnection, connection.getConnections_Res());
        return this;
    }

    // Getter für Sitz
    public String getSeat() {
        return seat;
    }

    // Getter für Namespace
    public String getNamespace() {
        return namespace;
    }
}
