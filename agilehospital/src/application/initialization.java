package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class initialization {

    public static void main(String[] args) {
        
    	String url = "jdbc:mysql://localhost:3306/";
        
        String tempDbName = "SessionDB";
        String dbName = "PermaDB";
        
        String user = "root";
        String password = "Droxol@270.";

        createTempDatabase(url, tempDbName, user, password);

        boolean exists = checkDatabaseExists(url, dbName, user, password);

        if (exists) {

            System.out.println("The database " + dbName + " exists.");

        } else {

            System.out.println("The database " + dbName + " does not exist.");

        }

        // Listen for user input
        Scanner scanner = new Scanner(System.in);
        String input = "";
        System.out.println("Enter 'exit' to terminate program and delete temporary database.");

        while (!"exit".equals(input)) {

            input = scanner.nextLine();

        }
        
        scanner.close();

        deleteTempDatabase(url, tempDbName, user, password);
        System.out.println("Temporary database deleted. Exiting program.");

    }

    public static void createTempDatabase(String url, String dbName, String user, String password) {

        try (Connection conn = DriverManager.getConnection(url, user, password);

             Statement stmt = conn.createStatement()) {

            String sql = "CREATE DATABASE IF NOT EXISTS " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Temporary database " + dbName + " created or already exists.");

        } catch (SQLException e) {

            System.out.println("Failed to create the temporary database: " + e.getMessage());

        }
    }

    public static void deleteTempDatabase(String url, String dbName, String user, String password) {

        try (Connection conn = DriverManager.getConnection(url, user, password);

             Statement stmt = conn.createStatement()) {

            String sql = "DROP DATABASE " + dbName;
            stmt.executeUpdate(sql);
            System.out.println("Temporary database " + dbName + " has been deleted.");

        } catch (SQLException e) {

            System.out.println("Failed to delete the temporary database: " + e.getMessage());

        }
    }

    public static boolean checkDatabaseExists(String url, String dbName, String user, String password) {
        
        try (Connection conn = DriverManager.getConnection(url + dbName, user, password)) {

            System.out.println("Successfully connected to the database.");
            return true; 

        } catch (SQLException e) {

            System.out.println("Failed to connect to the database: " + e.getMessage());
            return false; 
        }
    }
}
