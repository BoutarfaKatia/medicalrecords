/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package RMIService;
import java.rmi.Remote;
import java.rmi.RemoteException;
/**
 *
 * @author WINDOWS
 */
public interface PatientManagementService extends Remote {
    int addPatient(Patient patient) throws RemoteException;
    Patient searchPatient(int patientID) throws RemoteException;
    boolean updatePatientInfo(int patientID, Patient updatedPatient) throws RemoteException;
    
}
