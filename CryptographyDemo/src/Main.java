import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.sql.*;
import java.util.Arrays;
import java.util.Base64;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

public class Main
{
    /**
     * Connects to postgres database
     * @return connection object
     */
    private Connection connectToDB() {
        Connection _conn = null;
        try {
            String url = "jdbc:postgresql://ec2-54-73-68-39.eu-west-1.compute.amazonaws.com:5432/d8kt47qh55c24g";
            String user = "snzbrrltdfagct";
            String password = "618b0e656d64c06ca167eca5179abd8bd4b7f8e3295784547642c1a5a465464a";
            _conn = DriverManager.getConnection(url, user, password);
         //   System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println("Failed to connect to the PostgreSQL server .");
            System.out.println(e.getMessage());
        }
        return _conn;
    }

    /**
     * Executes a query request into the database
     * @param query Query statement
     * @return Returns the result set of the sql request
     */
    private ResultSet executeQuery(String query)
    {
        Statement _statement= null;
        ResultSet _resultSet= null;
        Connection _conn;
        try
        {
            _conn = connectToDB();
            _statement=_conn.createStatement();
            _resultSet=_statement.executeQuery(query);
            _conn.close();
        }
        catch (Exception ex)
        {
            System.out.println(ex.toString());
        }
        return  _resultSet;
    }

    /**
     * Hashes a given field from a table (usually used for password hashing inside the databases)
     * @param _resultSet contains the data prompt out by the query execution
     */
    private void hashesQuerySelectedData(ResultSet _resultSet)
    {
        PasswordAuthentication _passwordA = new PasswordAuthentication();
        try
        {
            ResultSetMetaData _rsmd = _resultSet.getMetaData();
            int columnsNumber = _rsmd.getColumnCount();
            int affectedRows = 0;
            //data
            while (_resultSet.next()) {
                //Print one row
                String[] values = new String[columnsNumber];
                values[0] = _resultSet.getString(1);
                values[1] = _resultSet.getString(2);
                System.out.println( "Username "+values[0]);  //username
                System.out.println( "Password "+ values[1]);  //password

                // use with caution
               // String token =  _passwordA.hash( values[1].toCharArray()); //hashing the password
              // affectedRows = updatePassword(values[0],token); //updates the old password

                System.out.println();//Move to the next line to print the next row.
            }

            System.out.println("Affected Rows:" + affectedRows);
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }


    /**
     * Prints out the table data from a specified resultSet object
     * @param _resultSet contains the data prompt out by the query execution
     */
    private void printQueryOutput(ResultSet _resultSet, String spacing)
    {
        try
        {
            ResultSetMetaData _rsmd = _resultSet.getMetaData();
            int columnsNumber = _rsmd.getColumnCount();

            // header
            for(int i = 1; i <= columnsNumber; i++) {
                System.out.print(_rsmd.getColumnLabel(i)+ "  ");
            }
            System.out.println();

            //data
            while (_resultSet.next()) {
                //Print one row
                for(int i = 1 ; i <= columnsNumber; i++){
                    System.out.print(_resultSet.getString(i) + spacing); //Print one element of a row
                }

                System.out.println();//Move to the next line to print the next row.
            }
        }
        catch (SQLException exception)
        {
            System.out.println(exception.getMessage());
        }
    }

    public int updatePassword( String username,String hash) {
        String SQL = "UPDATE \"Doctor\" "
                + "SET \"password\" = ? "
                + "WHERE \"username\" = ?";

        int affectedrows = 0;

        try (Connection conn = connectToDB();
             PreparedStatement pstmt = conn.prepareStatement(SQL)) {

            pstmt.setString(1, hash);
            pstmt.setString(2, username);


            affectedrows = pstmt.executeUpdate();

        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
        return affectedrows;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InvalidKeySpecException {


//        PasswordAuthentication _passwordauth = new PasswordAuthentication();
//        long startTime = System.nanoTime();
//        String hash1 = _passwordauth.hash("tbbbbbbbbbest".toCharArray());    //returns a hash to save it into the database.
//        long elapsedTime = System.nanoTime() - startTime;
//        System.out.println("Total execution time in milliseconds: "
//                + elapsedTime/1000000);
//        boolean match = _passwordauth.authenticate("tbbbbbbbbbest".toCharArray(),hash1); //returns true if password matches the hash
//
//        System.out.print(hash1 + "\n");
//       System.out.println("Password match: " + match);

        Main obj = new Main();

        //query execution
        ResultSet _resultSet = obj.executeQuery("SELECT \"username\" , \"password\" FROM \"Doctor\"");                         // insert query for execution
        obj.hashesQuerySelectedData(_resultSet );
        obj.printQueryOutput(_resultSet , "      ");
        System.out.println(" ");


    }
}

// https://www.baeldung.com/java-password-hashing
// https://stackoverflow.com/questions/2860943/how-can-i-hash-a-password-in-java
// https://security.stackexchange.com/questions/195563/why-is-sha-256-not-good-for-passwords
// https://www.youtube.com/watch?v=cMykd0jScSY PBKDF2 explanation
// http://tutorials.jenkov.com/java-regex/matcher.html Matcher class in java