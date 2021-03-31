package com.WebFlexers.mainpackage;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Doctor extends Users{

    private List<String> schedule;
    private LocalDateTime dateTime;
    private Specialty specialty;

    public Specialty getSpecialty() {
        return specialty;
    }

    public enum Specialty {
        ophthalmologist,
        orthopedic,
        internist;
    }

    public Doctor(String username, String password, String name, String surname, Specialty specialty) {
        super(username, password, name, surname);
    }

    /**
     * Doctor inserts the date he is available to check in patients
     */
    public void insertDateAvailability(String doctorAvailability)
    {
        dateTime = LocalDateTime.now();
    }

    /**
     * View doctor's appointment availability
     */
    public void viewAppointmentAvailability()
    {
        System.out.println("The doctor is available 24/7");
    }

    /**
     * Cancel patient's appointment only if it's scheduled at least 3 days later compared to the current date.
     */
    public void cancelAppointment(String cancelAppointment)
    {

    }

}
