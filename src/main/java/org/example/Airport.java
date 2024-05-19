package org.example;

import org.apache.jena.datatypes.xsd.XSDDatatype;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.Resource;

public class Airport {
    private String name;
    private String code;
    private String year;
    private boolean init;

    private int numberOfEmployees;
    private String manInCharge;

    public Airport(String name, String code, String year, int numberOfEmployees, String manInCharge) {
        this.name = name;
        this.code = code;
        this.year = year;
        this.numberOfEmployees = numberOfEmployees;
        this.manInCharge = manInCharge;
    }


    public void init(){
        //Create NameSpace
        String airportNamespace = Main.getMyNamespace() + this.code + "/";
        //init Resource
        Resource airport_Res = Main.getModel().createResource(airportNamespace);

        //init Properties
        Property started = Main.getModel().createProperty(airportNamespace, "started");
        Property hasEmpl = Main.getModel().createProperty(airportNamespace, "hasNumOfEmployees");
        Property hasMan = Main.getModel().createProperty(airportNamespace, "hasManager");
        Property name = Main.getModel().createProperty(airportNamespace, "name");

        // Add properties with typed literals
        airport_Res.addProperty(started, Main.getModel().createTypedLiteral(this.year, XSDDatatype.XSDgYear));
        airport_Res.addProperty(hasEmpl, Main.getModel().createTypedLiteral(this.numberOfEmployees));
        airport_Res.addProperty(hasMan, this.manInCharge);  // Assuming this is a string, no need for typed literal
        airport_Res.addProperty(name, this.name);  // Assuming this is a string, no need for typed literal

        /*// Connect Literals to Resource
        airport_Res.addProperty(started, year);
        airport_Res.addProperty(hasEmpl,""+ numberOfEmployees);
        airport_Res.addProperty(hasMan, manInCharge);
        airport_Res.addProperty(name, this.name);
        */

    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getYear() {
        return year;
    }

    public int getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public String getManInCharge() {
        return manInCharge;
    }
}
