package com.example.airbas;

public class Reservation {private String id;
    private String userEmail;
    private String name;
    private String flightName;
    private String seatCord;
    private String airPlaneName;
    private String dapartureAirport;
    private String arrivalAirport;
    private String date;
    private String rate;

    private Passenger passenger;

    private boolean expanded;


    public Reservation(String id, String userEmail, String name, String flightName, String seatCord,
                       String airPlaneName, String dapartureAirport, String arrivalAirport, String date,
                       String rate, Passenger passenger) {
        this.id = id;
        this.userEmail = userEmail;
        this.name = name;
        this.flightName = flightName;
        this.seatCord = seatCord;
        this.airPlaneName = airPlaneName;
        this.dapartureAirport = dapartureAirport;
        this.arrivalAirport = arrivalAirport;
        this.date = date;
        this.rate = rate;
        this.passenger = passenger;
        this.expanded=false;
    }

    public boolean isExpanded() {
        return expanded;
    }

    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    public String getId() {
        return id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getName() {
        return name;
    }

    public String getFlightName() {
        return flightName;
    }

    public String getSeatCord() {
        return seatCord;
    }

    public String getAirPlaneName() {
        return airPlaneName;
    }

    public String getDapartureAirport() {
        return dapartureAirport;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public String getDate() {
        return date;
    }

    public String getRate() {
        return rate;
    }

    public Passenger getPassenger() {
        return passenger;
    }
}
