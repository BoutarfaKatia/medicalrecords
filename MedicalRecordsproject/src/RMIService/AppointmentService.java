/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMIService;

/**
 *
 * @author WINDOWS
 */
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface AppointmentService extends Remote {
    int bookAppointment(String patientID, java.sql.Date date, String time, String visitType,String status) throws RemoteException;
    boolean cancelAppointment(int appointmentID) throws RemoteException;
}
