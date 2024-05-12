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
        String Flightclass = flug.getFlightClass();
        String startPoint = flug.getStartPoint();
        String destinationPoint = flug.getDestinationPoint();
        String startTime = flug.getStart_Time();
        String destinationTime = flug.getDestination_Time();


        //create properties
        Property seatProp = App.getModel().createProperty(myNamespace, "has_Seat");
        Property flightClassProp = App.getModel().createProperty(myNamespace, "has_FlightClass");
        Property startPointProp = App.getModel().createProperty(myNamespace, "has_StartPoint");
        Property destinationPointProp = App.getModel().createProperty(myNamespace, "has_DestinationPoint");
        Property startTimeProp = App.getModel().createProperty(myNamespace, "has_Start_Time");
        Property destinationTimeProp = App.getModel().createProperty(myNamespace, "has_Destination_Time");
        Property has = App.getModel().createProperty(myNamespace, "has");


        //create Ressources
        Resource flightRes = App.getModel().createResource(myNamespace + flug.getName());
        Resource tripRes = App.getModel().createResource(myNamespace + trip);

        flightRes
                .addProperty(seatProp, seat)
                .addProperty(flightClassProp, Flightclass)
                .addProperty(startPointProp, startPoint)
                .addProperty(destinationPointProp, destinationPoint)
                .addProperty(startTimeProp, startTime)
                .addProperty(destinationTimeProp, destinationTime);


        tripRes.addProperty(has, flightRes);

        return tripRes;

    }

}
