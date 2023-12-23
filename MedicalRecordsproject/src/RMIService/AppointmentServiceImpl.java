/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIService;

import DbConnection.DatabaseConnectionManager;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement; // Add this import

public class AppointmentServiceImpl extends UnicastRemoteObject implements AppointmentService {
    //Constructor
    protected AppointmentServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int bookAppointment(String patientID, java.sql.Date date, String time, String visitType, String status) throws RemoteException {
        int appointmentID = -1; // Default value in case of failure

        try {
            // Get a connection to the database
            Connection connection = DatabaseConnectionManager.getConnection();

            // Insert appointment data into the appointments table
            String insertQuery = "INSERT INTO appointments (PatientID, AppointmentDate, AppointmentTime, VisitType, Status) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, patientID);
                preparedStatement.setDate(2, date);
                preparedStatement.setString(3, time);
                preparedStatement.setString(4, visitType);
                preparedStatement.setString(5, status);

                // Execute the query and get the generated keys
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            appointmentID = generatedKeys.getInt(1); // Set the AppointmentID
                        }
                    }
                }
            }

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return appointmentID; // Return the AppointmentID, or -1 if the insertion fails
    }

    @Override
    public boolean cancelAppointment(int appointmentID) throws RemoteException {
        try {
            // Get a connection to the database
            Connection connection = DatabaseConnectionManager.getConnection();

            // Delete the appointment from the appointments table
            String deleteQuery = "DELETE FROM appointments WHERE AppointmentID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(deleteQuery)) {
                preparedStatement.setInt(1, appointmentID);
                preparedStatement.executeUpdate();
            }

            // Close the connection
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
