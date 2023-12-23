/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIService;

import java.io.Serializable;

/**
 *
 * @author WINDOWS
 */
public class Patient implements Serializable {
    String FirstName ;
    String LastName ;
    java.sql.Date DateOfBirth ;
    String Gender ;
    String PhoneNumber ;
    String Email ;
    String MedicalHistory ;
    String DiagnosisDetails ;
    String PrescriptionDetails ;
}
