package com.WebFlexers.models;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Doctor extends Users{

    private List<String> schedule;
    private LocalDateTime dateTime;
    private final Specialty specialty;

    public Specialty getSpecialty() {
        return specialty;
    }

    public enum Specialty {
        ophthalmologist,
        orthopedic,
        internist;

        public String toFirstLetterUppercase() {
            var temp = this.toString();
            return temp.substring(0, 1).toUpperCase() + temp.substring(1);
        }
    }

    public Doctor(String username, String password, String name, String surname, Specialty specialty) {
        super(username, password, name, surname);
        this.specialty = specialty;
    }

    /**
     * Doctor inserts the date he is available to check in patients
     */
    public void insertDateAvailability(String doctorAvailability)
    {
        dateTime = LocalDateTime.now();
        System.out.println("Available date added");
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
    public void cancelAppointment(Appointment appointmentToBeCancelled)
    {
        System.out.println("Appointment Cancelled");
    }

}
