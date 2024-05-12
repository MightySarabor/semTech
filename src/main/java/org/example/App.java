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

    static Property operates;
    static Property name;


    // Korrekte URIs ohne ungültige Zeichen






    public static void main(String[] args) {

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // custom Properties erstellen

        // Resource erstellen und Property hinzufügen
        operates = model.createProperty(myNamespace, "operates");
        name = model.createProperty(myNamespace, "name");

        model.createResource(airLine)
                .addProperty(operates, model.createResource()
                    .addProperty(name,  airPort_Rome)
                    .addProperty(name, airPort_Berlin)
                    .addProperty(name, airPort_NewYork)
                    .addProperty(name, airPort_London));



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
}
