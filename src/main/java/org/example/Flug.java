package org.example;

public class Flug {

    private String name;
    private String seat;
    private String flightClass;
    private String startPoint;
    private String destinationPoint;
    private String start_Time;
    private String destination_Time;

    public Flug(String name, String seat, String flightClass, String startPoint, String destinationPoint, String start_Time, String destination_Time) {
        this.name = name;
        this.seat = seat;
        this.flightClass = flightClass;
        this.startPoint = startPoint;
        this.destinationPoint = destinationPoint;
        this.start_Time = start_Time;
        this.destination_Time = destination_Time;
    }

    public String getName() {
        return name;
    }

    public String getSeat() {
        return seat;
    }

    public String getFlightClass() {
        return flightClass;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public String getDestinationPoint() {
        return destinationPoint;
    }

    public String getStart_Time() {
        return start_Time;
    }

    public String getDestination_Time() {
        return destination_Time;
    }

    public static Flug createFlight(String name, String seat, String flightClass, String startPoint, String destinationPoint, String start_Time, String destination_Time){
        return new Flug(name, seat, flightClass, startPoint, destinationPoint, start_Time, destination_Time);
    }
}
