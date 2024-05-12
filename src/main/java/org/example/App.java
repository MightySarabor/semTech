package org.example;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.RDF;

public class App
{
    //E2
    // Namensraum für benutzerdefinierte Property
    static String myNamespace = "http://semTec.org/E2#";

    static String airLine = myNamespace + "semantisch_Fliegen";
    static String airPort_Rome = myNamespace + "FCO";
    static String airPort_Berlin = myNamespace + "BER";
    static String airPort_London = myNamespace + "LHR";
    static String airPort_NewYork = myNamespace + "JFK";

    static final String startOps_Rome = "1960";
    static final String startOps_Berlin = "2020";
    static final String startOps_London = "1946";
    static final String startOps_NewYork = "1948";

    private static Model model = ModelFactory.createDefaultModel();



    // Korrekte URIs ohne ungültige Zeichen






    public static void main(String[] args) {

        // create an empty Model

        // Literals
        String numEmpl = "number_of_employees";
        String manInCharge = "manager_in_charge";

        // Properties
        Property operates = model.createProperty(myNamespace, "operates");
        Property name = model.createProperty(myNamespace, "name");
        Property startOps = model.createProperty(myNamespace, "startOps");
        Property has = model.createProperty(myNamespace, "has");
        Property booksTrip = model.createProperty(myNamespace, "booksTrip");
        Property travelInfo = model.createProperty(myNamespace, "travelInfo");

        //Ressourcen
        Resource airLineRes = model.createResource(airLine);
        Resource blankAirportRes = model.createResource();
        Resource romeAirportRes = model.createResource(airPort_Rome);
        Resource berlinAirportRes = model.createResource(airPort_Berlin);
        Resource londonAirportRes = model.createResource(airPort_London);
        Resource newYorkAirportRes = model.createResource(airPort_NewYork);
        Resource customerRes = model.createResource(myNamespace + "customer");
        Resource blankCustomerRes = model.createResource();


        /*airLineModel.createResource(airLine)
                .addProperty(operates, model.createResource()
                    .addProperty(name, airPort_Rome)
                    .addProperty(name, airPort_Berlin)
                    .addProperty(name, airPort_NewYork)
                    .addProperty(name, airPort_London));
        */
        //Rom Flughafen
        airLineRes.addProperty(operates, blankAirportRes.addProperty(name,
                romeAirportRes.addProperty(startOps, startOps_Rome)));

        //Berlin Flughafen
        airLineRes.addProperty(operates, blankAirportRes.addProperty(name,
                berlinAirportRes.addProperty(startOps, startOps_Berlin)));

        //London Flughafen
        airLineRes.addProperty(operates, blankAirportRes.addProperty(name,
                londonAirportRes.addProperty(startOps, startOps_London)));

        //NewYork Flughafen
        airLineRes.addProperty(operates, blankAirportRes.addProperty(name,
                newYorkAirportRes.addProperty(startOps, startOps_NewYork)));

        //Mitarbeiter
        airLineRes.addProperty(has,numEmpl);
        airLineRes.addProperty(has,manInCharge);

        //Customer
        customerRes.addProperty(booksTrip, airLineRes);
        customerRes.addProperty(travelInfo, blankCustomerRes);

        //Trips
        Resource trip_1;
        Resource trip_2;
        Resource trip_3;

        trip_1 = Trip.setTrip("Trip_1",Flug.createFlight("T1F1", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_1 = Trip.setTrip("Trip_1",Flug.createFlight("T1F2", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_1 = Trip.setTrip("Trip_1",Flug.createFlight("T1F3", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_1 = Trip.setTrip("Trip_1",Flug.createFlight("T1F4", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));

        trip_2 = Trip.setTrip("Trip_2",Flug.createFlight("T2F1", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_2 = Trip.setTrip("Trip_2",Flug.createFlight("T2F2", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_2 = Trip.setTrip("Trip_2",Flug.createFlight("T2F3", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_2 = Trip.setTrip("Trip_2",Flug.createFlight("T2F4", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));

        trip_3 = Trip.setTrip("Trip_3",Flug.createFlight("T3F1", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_3 = Trip.setTrip("Trip_3",Flug.createFlight("T3F2", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_3 = Trip.setTrip("Trip_3",Flug.createFlight("T3F3", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));
        trip_3 = Trip.setTrip("Trip_3",Flug.createFlight("T3F4", "E32", "Economy", "Rome", "Berlin", "08.06.2024" , "12:20", "09.06.2024", "15:30"));



        blankCustomerRes
                .addProperty(travelInfo, trip_1)
                .addProperty(travelInfo, trip_2)
                .addProperty(travelInfo, trip_3);

       /* model.createResource(uniPage)
                .addProperty(createdBy,
                        model.createResource()
                                .addProperty(VCARD.FN, fullName)
                                .addProperty(VCARD.EMAIL, email));

        model.createResource(lecture)
                .addProperty(gives, fullName)
                .addProperty(has, model.createResource(exercise)
                        .addProperty(has, "Assignment_1")
                        .addProperty(has, "Assignment_2")
                        .addProperty(has, "Assignment_3")
                        .addProperty(gives, "Maximilian Hoffmann"));


        */
        // Statements im Modell auflisten
        StmtIterator iter = model.listStatements();


        // Ausgabe der Statements
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();
            Resource subject = stmt.getSubject();
            Property predicate = stmt.getPredicate();
            RDFNode object = stmt.getObject();

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                System.out.print("\"" + object.toString() + "\"");
            }
            System.out.println(" .");
        }

        // RDF-Modell in Turtle-Format ausgeben
        model.write(System.out);
    }

    public static Model getModel() {
        return model;
    }
}
