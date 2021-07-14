package com.WebFlexers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class that contains all the queries.
 * Every method returns a prepared statement based on the query
 */
public class Query {

    private Query(PreparedStatement statement) { this.statement = statement; }

    private final PreparedStatement statement;
    public PreparedStatement getStatement() { return statement; }

    /*
     ******************************************************************************************************************
     *                                 Queries for getting data from the database                                     *
     *                        These queries return a prepared statement ready for execution                           *
     ******************************************************************************************************************
     */

    public static Query getPatientByAmka(Connection connection, String amka) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Patient\" where \"amka\"=?");
        statement.setString(1, amka);
        return new Query(statement);
    }

    public static Query getPatientByUsername(Connection connection, String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Patient\" where \"username\"=?");
        statement.setString(1, username);
        return new Query(statement);
    }

    public static Query getDoctorByAmka(Connection connection, String amka) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Doctor\" where \"amka\"=?");
        statement.setString(1, amka);
        return new Query(statement);
    }

    public static Query getDoctorByUsername(Connection connection, String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Doctor\" where \"username\"=?");
        statement.setString(1, username);
        return new Query(statement);
    }

    public static Query getAdminByUsername(Connection connection, String username) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Admin\" where \"username\"=?");
        statement.setString(1, username);
        return new Query(statement);
    }

    public static Query getAdminByEmail(Connection connection, String email) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Admin\" where \"email\"=?");
        statement.setString(1, email);
        return new Query(statement);
    }

    public static Query getUserByUsername(Connection connection, String username) throws  SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Admin\" where \"username\"=?");
        statement.setString(1, username);
        return new Query(statement);
    }

    public static Query getAvailableAppointmentByID(Connection connection, String id) throws  SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Available_Appointment\" " +
                "where \"appointment_id\"=?");
        statement.setString(1, id);
        return new Query(statement);
    }

    public static Query getScheduledAppointmentByID(Connection connection, String id) throws  SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Scheduled_Appointment\" " +
                "where \"appointment_id\"=?");
        statement.setString(1, id);
        return new Query(statement);
    }

    /*
     ******************************************************************************************************************
     *                                 Queries for getting multiple rows of a table                                   *
     *                        These queries return a prepared statement ready to be executed                          *
     ******************************************************************************************************************
     */

    public static Query getAllAdmins(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("select * from \"Admin\""));
    }

    public static Query getAllDoctors(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("select * from \"Doctor\""));
    }

    public static Query getAllAvailableAppointments(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("select * from \"Available_Appointment\""));
    }

    public static Query getAllScheduledAppointments(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("select * from \"Scheduled_Appointment\""));
    }

    public static Query getAvailableAppointmentsByDoctorAmka(Connection connection, String doctorAmka) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Available_Appointment\" where \"doctor_amka\"=?");
        statement.setString(1, doctorAmka);
        return new Query(statement);
    }

    public static Query getAvailableAppointmentsByDoctorSpecialty(Connection connection, String specialty) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Available_Appointment\" INNER JOIN \"Doctor\"" +
                "ON \"Doctor\".amka = \"Available_Appointment\".doctor_amka WHERE (\"speciality\" = ?) ORDER BY date");
        statement.setString(1, specialty);
        return new Query(statement);
    }

    public static Query getScheduledAppointmentsByDoctorAmka(Connection connection, String doctorAmka) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Scheduled_Appointment\" where (\"doctor_amka\")=? ORDER BY \"date\"");
        statement.setString(1, doctorAmka);
        return new Query(statement);
    }

    public static Query getScheduledAppointmentsByPatientAmka(Connection connection, String patientAmka) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("select * from \"Scheduled_Appointment\" where \"patient_amka\"=? ORDER BY \"date\"");
        statement.setString(1, patientAmka);
        return new Query(statement);
    }

    /*
     ******************************************************************************************************************
     *                                 Queries for adding data to the database                                        *
     *                   These queries return a prepared statement whose values haven't been set                      *
     ******************************************************************************************************************
     */

    public static Query addPatient(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Patient\" (\"amka\", \"username\", \"password\", \"first_name\", \"last_name\", " +
                "\"email\", \"phone_num\") values (?, ?, ?, ?, ?, ?, ?)"));
    }

    public static Query addDoctor(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Doctor\" (\"amka\", \"username\", \"password\", \"first_name\", \"last_name\" , speciality , \"email\", \"phone_num\", \"admin_id\") " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)"));
    }

    public static Query addAdmin(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Admin\" (\"admin_id\", \"username\", \"password\", \"email\", \"first_name\", \"last_name\", \"IsSuperUser\") " +
                "values (?, ?, ?, ?, ?, ?, ?)"));
    }

    public static Query addAvailableAppointment(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Available_Appointment\" (\"appointment_id\", \"doctor_amka\", \"date\", \"start_time\", \"end_time\") " +
                "values (?, ?, ?, ?, ?)"));
    }

    public static Query addScheduledAppointment(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Scheduled_Appointment\" (\"appointment_id\", \"doctor_amka\", \"patient_amka\", \"date\", \"start_time\", \"end_time\") " +
                "values (?, ?, ?, ?, ?, ?)"));
    }

    /*
     ******************************************************************************************************************
     *                                 Queries for removing data from the database                                    *
     *                        These queries return a prepared statement ready for execution                           *
     ******************************************************************************************************************
     */
    public static Query removePatient(Connection connection, String patientAMKA) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Patient\" where \"amka\" = ?");
        statement.setString(1, patientAMKA);
        return new Query(statement);
    }

    public static Query removeDoctor(Connection connection, String doctorAmka) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Doctor\" where \"amka\" = ?");
        statement.setString(1, doctorAmka);
        return new Query(statement);
    }

    public static Query removeAdmin(Connection connection, String adminID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Admin\" where \"admin_id\" = ?");
        statement.setString(1, adminID);
        return new Query(statement);
    }

    public static Query removeAvailableAppointment(Connection connection, String appointmentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Available_Appointment\" where \"appointment_id\" = ?");
        statement.setString(1, appointmentID);
        return new Query(statement);
    }

    public static Query removeScheduledAppointment(Connection connection, String appointmentID) throws SQLException {
        PreparedStatement statement = connection.prepareStatement("DELETE FROM \"Scheduled_Appointment\" where \"appointment_id\" = ?");
        statement.setString(1, appointmentID);
        return new Query(statement);
    }
}
