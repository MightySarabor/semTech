package org.example;

import javax.xml.datatype.Duration;

public class Connection {
    private Duration flightDuration;
    private String planeModel;
    private int maximumNumberOfPassengers;

    //Constructor
    public Connection(Duration flightDuration, String planeModel, int maximumNumberOfPassengers) {
        this.flightDuration = flightDuration;
        this.planeModel = planeModel;
        this.maximumNumberOfPassengers = maximumNumberOfPassengers;
    }
}
