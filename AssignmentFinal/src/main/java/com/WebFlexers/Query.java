package com.WebFlexers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * A class that contains all the queries
 * Every method returns a prepared statement based on the query
 */
public class Query {

    private Query(PreparedStatement statement) { this.statement = statement; }

    private final PreparedStatement statement;
    public PreparedStatement getStatement() { return statement; }

    // Queries for getting data from the database
    // These queries return a prepared statement ready to be executed
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

    // Queries for getting all rows of a table
    public static Query getAllAdmins(Connection connection, String username) throws SQLException {
        return new Query(connection.prepareStatement("select * from \"Admin\""));
    }

    public static Query getAllDoctors(Connection connection, String username) throws SQLException {
        return new Query(connection.prepareStatement("select * from \"Doctor\""));
    }

    // Queries for adding data to the database
    // These queries return a prepared statement whose values haven't been set
    public static Query addPatient(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Patient\" (\"amka\", \"username\", \"password\", \"first_name\", \"last_name\", " +
                "\"email\", \"phone_num\") values (?, ?, ?, ?, ?, ?, ?)"));
    }

    public static Query addDoctor(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Doctor\" (\"amka\", \"username\", \"password\", \"email\", \"first_name\", \"last_name\" , speciality ,phone_num,admin_id) " +
                "values (?, ?, ?, ?, ?, ?, ?, ?, ?)"));
    }

    public static Query addAdmin(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("insert into \"Admin\" (\"admin_id\", \"username\", \"password\", \"email\", \"first_name\", \"last_name\", \"IsSuperUser\") " +
                "values (?, ?, ?, ?, ?, ?, ?)"));
    }

    // Queries for removing data from the database
    public static Query removeDoctor(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("DELETE FROM \"Doctor\" where \"amka\" = ?"));
    }

    public static Query removeAdmin(Connection connection) throws SQLException {
        return new Query(connection.prepareStatement("DELETE FROM \"Admin\" where \"admin_id\" = ?"));
    }
}
