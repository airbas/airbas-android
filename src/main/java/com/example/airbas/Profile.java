package com.example.airbas;

public class Profile {
    //Template for the data we're going to parse
   /* "id": 1,
            "userEmail": "thegmiracle@gmail.com",
            "name": "1626693",
            "flightName": "1335453",
            "seatCord": "\n4B\n",
            "airPlaneName": "167ECOQ",
            "dapartureAirport": "Malpensa",
            "arrivalAirport": "Caselle",
            "date": "2022-05-17T18:14:41.000+00:00",
            "rate": "0",
            "passenger":*/
    private String firstname;
    private String secondname;
    private String birthdate;
    private String creditcard;
    private String telephone;
    private String email;


    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getCreditcard() {
        return creditcard;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getEmail() {
        return email;
    }
}
