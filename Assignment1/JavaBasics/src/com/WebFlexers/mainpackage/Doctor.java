package com.WebFlexers.mainpackage;

import java.util.Date;

public class Doctor extends Users{

    


    public Doctor(String username, String password, String name, String surname) {
        super(username, password, name, surname);
    }

    /**
     * Doctor inserts the date he is available to check in patients
     * @param date
     */
    public void insertDoctorAvailability(Date date)
    {

    }

    /**
     * View doctor's patient availability
     */
    public void viewDoctorAvailability()
    {

    }

    public void cancelAppointment(Date date)
    {
    }

}
