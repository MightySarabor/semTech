package org.example;

import org.apache.jena.rdf.model.*;

public class Trip {

    private static Flug flug;
    private String trip;

    public Trip(String trip, Flug flug) {
        this.trip = trip;
        flug = flug;
    }

    /*public static Resource setTrip(String trip, Flug flug)
    {

        //create Literal
        String seat = flug.getSeat();
        String flightClass = flug.getFlightClass();
        String startPoint = flug.getStartPoint();
        String destinationPoint = flug.getDestinationPoint();
        String startDate = flug.getStart_Date();
        String startTime = flug.getStart_Time();
        String endDate = flug.getEnd_Date();
        String destinationTime = flug.getDestination_Time();


        //create properties
        /*Property seatProp = Main.getModel().createProperty(myNamespace, "has_Seat");
        Property flightClassProp = Main.getModel().createProperty(myNamespace, "has_FlightClass");
        Property startPointProp = Main.getModel().createProperty(myNamespace, "has_StartPoint");
        Property connection = Main.getModel().createProperty(myNamespace, "connection");
        Property destinationPointProp = Main.getModel().createProperty(myNamespace, "has_DestinationPoint");
        Property startTimeProp = Main.getModel().createProperty(myNamespace, "has_Start_Time");
        Property destinationTimeProp = Main.getModel().createProperty(myNamespace, "has_Destination_Time");
        Property flightTimeProp = Main.getModel().createProperty(myNamespace, "Flight_Time");
        Property has = Main.getModel().createProperty(myNamespace, "has");


        //create Resources
        Resource flightRes = Main.getModel().createResource(myNamespace + flug.getName());
        Resource tripRes = Main.getModel().createResource(myNamespace + trip);

        flightRes
                .addProperty(seatProp, seat)
                .addProperty(flightClassProp, flightClass)
                .addProperty(connection, Main.getModel().createResource()
                        .addProperty(startPointProp, startPoint)
                        .addProperty(destinationPointProp, destinationPoint) )

                .addProperty(flightTimeProp, Main.getModel().createResource()
                        .addProperty(startTimeProp, Main.getModel().createResource()
                                .addProperty(startTimeProp, startTime)
                                .addProperty(startTimeProp, startDate))
                        .addProperty(destinationTimeProp, Main.getModel().createResource()
                                .addProperty(destinationTimeProp, endDate)
                                .addProperty(destinationTimeProp, destinationTime)));



        tripRes.addProperty(has, flightRes);

        return tripRes;

    }*/

}
