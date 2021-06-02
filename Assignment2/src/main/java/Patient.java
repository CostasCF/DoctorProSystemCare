import javax.print.Doc;
import java.util.ArrayList;

public class Patient extends Users {

    private final String amka;
    private ArrayList<Appointment> scheduledAppointments = new ArrayList<>();

    public Patient(String username, String password, String name, String surname, String amka) {
        super(username, password, name, surname);
        this.amka = amka;
    }

    public String getAmka() {
        return amka;
    }

    public ArrayList<Appointment> getScheduledAppointments() {
        return scheduledAppointments;
    }

    /**
     * Sets the appointment schedule
     * @param scheduledAppointments
     */
    public void setScheduledAppointments(ArrayList<Appointment> scheduledAppointments) {
        this.scheduledAppointments = scheduledAppointments;
    }

    /**
     * Adds an appointment to the schedule
     * @param appointment
     */
    public void addAppointment(Appointment appointment) {
        scheduledAppointments.add(appointment);
    }

    /**
     * Removes appointment from the list of scheduled appointments
     * @param appointment
     */
    public void cancelAppointment(Appointment appointment) {
        scheduledAppointments.remove(appointment);
    }

    /**
     * Replaces the old appointment with the new one in the scheduled appointments list
     * @param oldAppointment
     * @param newAppointment
     */
    public void replaceAppointment(Appointment oldAppointment, Appointment newAppointment) {
        if (scheduledAppointments.contains(oldAppointment)) {
            scheduledAppointments.set(scheduledAppointments.indexOf(oldAppointment), newAppointment);
        }
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

    /**
     * Finds the available appointments of any doctor
     * of the given specialty
     * @return ArrayList of type Appointment
     */
    public ArrayList<Appointment> getAvailableAppointments(String specialty) {
        ArrayList<Appointment> availableAppointments = new ArrayList<>();

        System.out.println("Found appointments");
        return availableAppointments;
    }

    /**
     * Prints out the scheduled appointments
     */
    public void viewScheduledAppointments() {
        for (var appointment : scheduledAppointments) {
            System.out.println(appointment);
        }
    }

    /**
     * Prints out the appointment history
     */
    public void viewAppointmentHistory() {
        System.out.println("The history of appointments");
    }
}
