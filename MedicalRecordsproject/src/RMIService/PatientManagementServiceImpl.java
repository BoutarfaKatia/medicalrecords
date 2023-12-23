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
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation of the PatientManagementService interface.
 * Handles remote method invocations related to patient management.
 * @author WINDOWS
 */
public class PatientManagementServiceImpl extends UnicastRemoteObject implements PatientManagementService {

    public PatientManagementServiceImpl() throws RemoteException {
        super();
    }

    @Override
    public int addPatient(Patient patient) throws RemoteException {
        Connection connection = null;
        try {
            connection = DatabaseConnectionManager.getConnection();

            // Insert Patient data into the patients table
            String insertQuery = "INSERT INTO patients (FirstName, LastName, DateOfBirth, Gender, PhoneNumber, Email, MedicalHistory, DiagnosisDetails, PrescriptionDetails) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            try (PreparedStatement preparedStatement = connection.prepareStatement(insertQuery, Statement.RETURN_GENERATED_KEYS)) {
                preparedStatement.setString(1, patient.FirstName);
                preparedStatement.setString(2, patient.LastName);
                preparedStatement.setDate(3, patient.DateOfBirth);
                preparedStatement.setString(4, patient.Gender);
                preparedStatement.setString(5, patient.PhoneNumber);
                preparedStatement.setString(6, patient.Email);
                preparedStatement.setString(7, "");
                preparedStatement.setString(8, "");
                preparedStatement.setString(9, "");

                // Execute the query to insert patient data
                int rowsAffected = preparedStatement.executeUpdate();
                if (rowsAffected > 0) {
                    // After successful addition, retrieve the primary key
                    try (ResultSet generatedKeys = preparedStatement.getGeneratedKeys()) {
                        if (generatedKeys.next()) {
                            return generatedKeys.getInt(1); // Here we get the PatientID
                        }
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close the connection in any case
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return -1;
    }

    @Override
    public Patient searchPatient(int patientID) throws RemoteException {
        try {
            // Get Connection from the Database
            Connection connection = DatabaseConnectionManager.getConnection();
            String selectQuery = "SELECT * FROM patients WHERE PatientID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(selectQuery)) {

                preparedStatement.setInt(1, patientID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        Patient patient = new Patient();
                        patient.FirstName = resultSet.getString("FirstName");
                        patient.LastName = resultSet.getString("LastName");
                        patient.DateOfBirth = resultSet.getDate("DateOfBirth");
                        patient.Gender = resultSet.getString("Gender");
                        patient.PhoneNumber = resultSet.getString("PhoneNumber");
                        patient.Email = resultSet.getString("Email");
                        patient.MedicalHistory =resultSet.getString("MedicalHistory");
                        patient.DiagnosisDetails =resultSet.getString("DiagnosisDetails");
                        patient.PrescriptionDetails=resultSet.getString("PrescriptionDetails");

                        return patient;
                    } else {
                        return null;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

  @Override
public boolean updatePatientInfo(int patientID, Patient updatedPatient) throws RemoteException {
    try {
        // Get Connection from the Database
        Connection connection = DatabaseConnectionManager.getConnection();

        String updateQuery = "UPDATE patients SET FirstName = ?, LastName = ?, DateOfBirth = ?, Gender = ?, PhoneNumber = ?, Email = ?, MedicalHistory = ?, DiagnosisDetails = ?, PrescriptionDetails = ? WHERE PatientID = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(updateQuery)) {
            preparedStatement.setString(1, updatedPatient.FirstName);
            preparedStatement.setString(2, updatedPatient.LastName);
            preparedStatement.setDate(3, updatedPatient.DateOfBirth);
            preparedStatement.setString(4, updatedPatient.Gender);
            preparedStatement.setString(5, updatedPatient.PhoneNumber);
            preparedStatement.setString(6, updatedPatient.Email);
            preparedStatement.setString(7, updatedPatient.MedicalHistory);
            preparedStatement.setString(8, updatedPatient.DiagnosisDetails);
            preparedStatement.setString(9, updatedPatient.PrescriptionDetails);
            preparedStatement.setInt(10, patientID);

            // Execute the query to update patient data
            int rowsAffected = preparedStatement.executeUpdate();

            // If the record is updated successfully, rowsAffected will be greater than 0
            return rowsAffected > 0;
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}

}
