package com.WebFlexers.mainpackage;

import java.time.LocalDateTime;

public class Appointment {

    private Patient patient;
    private Doctor doctor;
    private LocalDateTime DateTime;

    // Constructor
    public Appointment(Patient patient, Doctor doctor, LocalDateTime dateTime) {
        this.patient = patient;
        this.doctor = doctor;
        DateTime = dateTime;
    }

    // Getters and setters
    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public LocalDateTime getDateTime() {
        return DateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        DateTime = dateTime;
    }
}
