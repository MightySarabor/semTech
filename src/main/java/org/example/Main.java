package org.example;

import org.apache.jena.rdf.model.*;

/*
Zum Code:
Wir haben aus dem Text eine Spezifikation mit Klassen und Attributen erstellt, ähnlich einem UML-Diagramm.
Die Klassen stellen dabei die Ressourcen dar und die Attribute die Prädikate (bis auf einige Ausnahmen).
Die Methoden der Klassen sind dazu da die verschiedenen Ressourcen miteinander zu verbinden.
Außerdem hat jede Klasse zu ihrem Konstruktor, mit der die Objekte erstellt werden, eine init-Methode.
Dadurch wird das Objekt der Ontologie hinzugefügt. Das erhöht die Übersicht im Code und vereinfacht die Fehlersuche.

Zum Design:
Wir sind, was unser Design angeht möglichst nah an dem Text geblieben. Eine Airline Ressource haben wir nicht,
denn die Airline selbst hat weder eigene Literale, noch hat sie relevante Properties zu den anderen Ressourcen.
In unserem Design sind wir also ohne auskommen. Die Airline wird lediglich in der URI erwähnt.

 */


public class Main {
    // Namensraum für benutzerdefinierte Property
    private static final Model model = ModelFactory.createDefaultModel();
    private static final String myNamespace = "https://semTec.org/E2/Airline/";

    public static void main(String[] args) {

        //Hier werden die einzelnen Ressourcen mit ihren Literalen erstellt

        // Flughäfen initialisieren
        Airport berlin = new Airport("Berlin", "BER", "2005", 200, "Jürgen Schmitz");
        berlin.init();
        Airport london = new Airport("London", "LHR", "1980", 180, "Jeff Baker");
        london.init();
        Airport newYork = new Airport("New_York", "JFK", "1958", 620, "Linda Jones");
        newYork.init();
        Airport rom = new Airport("Rom", "FCO", "753", 521, "Julius Caesar");
        rom.init();

        // Kunden initialisieren
        Customer hansPeter = new Customer("Hans", "Peter", "A23DFG567");
        hansPeter.init();
        Customer dieterBohlen = new Customer("Dieter", "Bohlen", "C89HJL321");
        dieterBohlen.init();
        Customer michaelSchuhmacher = new Customer("Michael", "Schuhmacher", "K12QWE678");
        michaelSchuhmacher.init();

        // Flugverbindungen initialisieren
        Connection berLon = new Connection("PT2H30M", "Boeing_737", 180, berlin, london);
        berLon.init();
        Connection berNY = new Connection("PT5H30M", "Airbus_A320", 250, berlin, newYork);
        berNY.init();
        Connection berRom = new Connection("PT3H30M", "Boeing_747", 300, berlin, rom);
        berRom.init();
        Connection lonNY = new Connection("PT8H30M", "Boeing_777", 400, london, newYork);
        lonNY.init();
        Connection lonRom = new Connection("PT7H30M", "Airbus_A380", 350, london, rom);
        lonRom.init();
        Connection romNY = new Connection("PT8H30M", "Boeing_787", 400, rom, newYork);
        romNY.init();

        // Flüge für Buchungen und ihre Verbindungen erstellen
        Flug[] booking1Flights = {
                new Flug("F11", "C32", "Economy", "2024-05-01T00:00:00", "2024-05-01T04:00:00", "BER", "LHR")
                        .hasConnection(berLon),
                new Flug("F12", "D40", "Economy", "2024-05-01T13:00:00", "2024-05-01T17:00:00", "LHR", "BER")
                        .hasConnection(berLon),
                new Flug("F13", "A5", "Business", "2024-05-02T10:00:00", "2024-05-02T14:00:00", "BER", "FCO")
                        .hasConnection(berRom),
                new Flug("F14", "B77", "Business", "2024-05-03T09:00:00", "2024-05-03T13:00:00", "FCO", "JFK")
                        .hasConnection(romNY)
        };

        Flug[] booking2Flights = {
                new Flug("F21", "G14", "Economy", "2024-05-01T00:00:00", "2024-05-01T03:30:00", "JFK", "FCO")
                        .hasConnection(romNY),
                new Flug("F22", "L54", "Economy", "2024-05-01T14:00:00", "2024-05-01T17:30:00", "FCO", "LHR")
                        .hasConnection(lonRom),
                new Flug("F23", "A17", "Business", "2024-05-02T11:00:00", "2024-05-02T15:30:00", "LHR", "BER")
                        .hasConnection(berLon),
                new Flug("F24", "A32", "Business", "2024-05-03T10:00:00", "2024-05-03T14:30:00", "BER", "JFK")
                        .hasConnection(berNY)
        };

        Flug[] booking3Flights = {
                new Flug("F31", "C33", "Economy", "2024-05-01T00:00:00", "2024-05-01T03:30:00", "JFK", "BER")
                        .hasConnection(berLon),
                new Flug("F32", "E49", "Economy", "2024-05-01T15:00:00", "2024-05-01T18:30:00", "BER", "FCO")
                        .hasConnection(berRom),
                new Flug("F33", "B6", "Business", "2024-05-02T12:00:00", "2024-05-02T16:30:00", "FCO", "LHR")
                        .hasConnection(lonRom),
                new Flug("F34", "F13", "Business", "2024-05-03T11:00:00", "2024-05-03T15:30:00", "LHR", "JFK")
                        .hasConnection(lonNY)
        };

        // Flüge initialisieren
        Flug.initAll(booking1Flights);
        Flug.initAll(booking2Flights);
        Flug.initAll(booking3Flights);

        // Buchungen/Trips initialisieren
        Trip trip1 = new Trip("Trip1", booking1Flights, "2024-05-01T00:00:00", "2024-05-03T00:00:00", 500.00);
        trip1.init();

        Trip trip2 = new Trip("Trip2", booking2Flights, "2024-05-01T00:00:00", "2024-05-03T00:00:00", 700.00);
        trip2.init();

        Trip trip3 = new Trip("Trip3", booking3Flights, "2024-05-01T00:00:00", "2024-05-03T00:00:00", 600.00);
        trip3.init();

        //Hier werden die unterschiedlichen Ressourcen miteinander verbunden

        //Flüge werden den Trips hinzugefügt
        trip1.hasFlights(booking1Flights);
        trip2.hasFlights(booking2Flights);
        trip3.hasFlights(booking3Flights);

        // Kunden buchen Trips
        hansPeter.booksTrip(trip1);
        dieterBohlen.booksTrip(trip2);
        michaelSchuhmacher.booksTrip(trip3);

        // Flughafenverbindungen erstellen
        berLon.connects();
        berNY.connects();
        berRom.connects();
        lonNY.connects();
        lonRom.connects();
        romNY.connects();

        //Code zum Erstellen des RDF/XML Codes

        // Statement-Iterator initialisieren
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

    //Namespace und Model den anderen Klassen zu Verfügung stellen
    public static Model getModel() {
        return model;
    }

    public static String getMyNamespace() {
        return myNamespace;
    }
}

