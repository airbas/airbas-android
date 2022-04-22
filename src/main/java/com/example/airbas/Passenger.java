package com.example.airbas;

public class Passenger {
    private String id;
    private String firstname;
    private String secondname;
    private String birthdate;
    private String telephone;


    public Passenger(String id, String firstname, String secondname, String birthdate, String telephone) {
        this.id = id;
        this.firstname = firstname;
        this.secondname = secondname;
        this.birthdate = birthdate;
        this.telephone = telephone;
    }

    public String getId() {
        return id;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getSecondname() {
        return secondname;
    }

    public String getBirthdate() {
        return birthdate;
    }

    public String getTelephone() {
        return telephone;
    }
}
