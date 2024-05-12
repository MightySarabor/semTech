package org.example;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class App
{
    //E2
    // Namensraum für benutzerdefinierte Property
    static String myNamespace = "http://semTec.org/ex1#";

    // Korrekte URIs ohne ungültige Zeichen
    static String uniPage = "http://www.uni-trier.de/index.php?id=1890";
    static String lecture = myNamespace + "semantic_Technologies";
    static String exercise = myNamespace + "exercise";
    static String givenName = "Ralph";
    static String familyName = "Bergmann";
    static String email = "mailto:bergmann@uni-trier.de";
    static String fullName = givenName + " " + familyName;


    public static void main(String[] args) {

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // custom Properties erstellen
        Property createdBy = model.createProperty(myNamespace, "createdBy");
        Property gives = model.createProperty(myNamespace, "gives");
        Property has = model.createProperty(myNamespace, "has");



        // Resource erstellen und Property hinzufügen

        model.createResource(uniPage)
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
