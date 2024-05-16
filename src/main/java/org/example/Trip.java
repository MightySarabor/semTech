package org.example;

import org.apache.jena.rdf.model.*;
import static org.example.App.myNamespace;

public class Trip {

    private static Flug flug;
    private String trip;

    public Trip(String trip, Flug flug) {
        this.trip = trip;
        flug = flug;
    }

    public static Resource setTrip(String trip, Flug flug)
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
        Property seatProp = App.getModel().createProperty(myNamespace, "has_Seat");
        Property flightClassProp = App.getModel().createProperty(myNamespace, "has_FlightClass");
        Property startPointProp = App.getModel().createProperty(myNamespace, "has_StartPoint");
        Property connection = App.getModel().createProperty(myNamespace, "connection");
        Property destinationPointProp = App.getModel().createProperty(myNamespace, "has_DestinationPoint");
        Property startTimeProp = App.getModel().createProperty(myNamespace, "has_Start_Time");
        Property destinationTimeProp = App.getModel().createProperty(myNamespace, "has_Destination_Time");
        Property flightTimeProp = App.getModel().createProperty(myNamespace, "Flight_Time");
        Property has = App.getModel().createProperty(myNamespace, "has");


        //create Resources
        Resource flightRes = App.getModel().createResource(myNamespace + flug.getName());
        Resource tripRes = App.getModel().createResource(myNamespace + trip);

        flightRes
                .addProperty(seatProp, seat)
                .addProperty(flightClassProp, flightClass)
                .addProperty(connection, App.getModel().createResource()
                        .addProperty(startPointProp, startPoint)
                        .addProperty(destinationPointProp, destinationPoint) )

                .addProperty(flightTimeProp, App.getModel().createResource()
                        .addProperty(startTimeProp, App.getModel().createResource()
                                .addProperty(startTimeProp, startTime)
                                .addProperty(startTimeProp, startDate))
                        .addProperty(destinationTimeProp, App.getModel().createResource()
                                .addProperty(destinationTimeProp, endDate)
                                .addProperty(destinationTimeProp, destinationTime)));



        tripRes.addProperty(has, flightRes);

        return tripRes;

    }

}

