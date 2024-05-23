package org.example;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Airport {
    private final String name;
    private final String code;
    private final String year;
    private final int numberOfEmployees;
    private final String manInCharge;
    private Resource airport_Res;

    private final Model model = Main.getModel();
    private final String airportNamespace = Main.getMyNamespace() + "Airport/";

    public Airport(String name, String code, String year, int numberOfEmployees, String manInCharge) {
        this.name = name;
        this.code = code;
        this.year = year;
        this.numberOfEmployees = numberOfEmployees;
        this.manInCharge = manInCharge;
    }

    // Initialisiert das RDF-Modell für den Flughafen
    public void init() {
        // Erstellung des Namensraums
        String codeNamespace = airportNamespace + this.code + "/";

        // Initialisierung der Ressource
        airport_Res = model.createResource(codeNamespace);

        // Initialisierung der Eigenschaften
        Property started = model.createProperty(codeNamespace, "started");
        Property hasEmpl = model.createProperty(codeNamespace, "hasNumOfEmployees");
        Property hasMan = model.createProperty(codeNamespace, "hasManager");
        Property name = model.createProperty(codeNamespace, "name");
        Property is = model.createProperty(codeNamespace, "is");

        // Hinzufügen der Eigenschaften mit getypten Literalen
        airport_Res.addProperty(started, model.createTypedLiteral(this.year, XSDDatatype.XSDgYear));
        airport_Res.addProperty(hasEmpl, model.createTypedLiteral(this.numberOfEmployees));
        airport_Res.addProperty(hasMan, this.manInCharge);
        airport_Res.addProperty(name, this.name);

        // Hinzufügen des Airports zur Airport-Klasse
        airport_Res.addProperty(is, model.createResource(airportNamespace));
    }

    // Getter für den Flughafencode
    public String getCode() {
        return code;
    }

    // Getter für die Flughafen-Ressource
    public Resource getAirport_Res() {
        return airport_Res;
    }
}
