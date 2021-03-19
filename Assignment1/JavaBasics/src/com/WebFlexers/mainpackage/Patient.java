package com.WebFlexers.mainpackage;

import java.util.ArrayList;

public class Patient extends Users {

    private final String amka;

    public Patient(String username, String password, String name, String surname, String amka) {
        super(username, password, name, surname);
        this.amka = amka;
    }

    public String getAmka() {
        return amka;
    }

    /**
     * Registers a new user to the database
     */
    public void registerUser() {
        System.out.println("User registered");
    }

    /**
     * Finds the available appointments of the given doctor
     * @return ArrayList of type Appointment
     */
    public ArrayList<Appointment> getAvailableAppointments(Doctor doctor) {
        ArrayList<Appointment> availableAppointments = new ArrayList<Appointment>();

        System.out.println("Found appointments");
        return availableAppointments;
    }

}
