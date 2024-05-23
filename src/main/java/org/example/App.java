package org.example;

import org.apache.jena.rdf.model.*;
import org.apache.jena.vocabulary.VCARD;

public class App {

    public static void main(String[] args) {

        //Namespace, Ressourcen und Literale als Strings
        String uniPage = "http://www.uni-trier.de/index.php?id=1890";
        String lecture = uniPage + "/semantic_Technologies";
        String exercise = uniPage + "/exercise";
        String email = "mailto:bergmann@uni-trier.de";
        String name = "Ralph Bergmann";

        // create an empty Model
        Model model = ModelFactory.createDefaultModel();

        // custom Properties erstellen
        Property createdBy = model.createProperty(uniPage, "/createdBy");
        Property gives = model.createProperty(uniPage, "/gives");
        Property has = model.createProperty(uniPage, "/has");

        // Blanke Ressource als Objekt erstellt
        Resource someone = model.createResource();

        //Erste Teilaufgabe
        model.createResource(uniPage)
                .addProperty(createdBy,
                        someone
                                .addProperty(VCARD.FN, name)
                                .addProperty(VCARD.EMAIL, email));

        //Zweite Teilaufgabe
        someone
                .addProperty(gives, model.createResource(lecture))
                .addProperty(has, model.createResource(exercise));

        model.createResource(uniPage + "/MaximilianHoffmann")
                .addProperty(gives, model.createResource(exercise)
                        .addProperty(has, "Assignment_1")
                        .addProperty(has, "Assignment_2")
                        .addProperty(has, "Assignment_3"));


        //Model in RDF/XML schreiben und ausgeben

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
