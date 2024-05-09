package org.example;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class App
{
    // Korrekte URIs ohne ungültige Zeichen
    static String personURI = "http://example.org/person/1234";
    static String givenName = "Ralph";
    static String familyName = "Bergmann";
    static String email = "mailto:bergmann@uni-trier.de";
    static String fullName = givenName + " " + familyName;

    public static void main(String[] args) {
        // Namensraum für benutzerdefinierte Property
        String myNamespace = "http://example.org/myOntology#";

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // custom Property erstellen
        Property creatorOf = model.createProperty(myNamespace, "creatorOf");

        // Resource erstellen und Property hinzufügen
        Resource person = model.createResource(personURI)
                .addProperty(creatorOf, fullName)
                .addProperty(VCARD.FN, fullName)
                .addProperty(VCARD.EMAIL, email);

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
