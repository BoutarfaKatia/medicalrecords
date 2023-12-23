/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIService;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
/**
 *
 * @author WINDOWS
 */
public class RMIServerp {
    public static void main(String[] args) {
        try{
        PatientManagementService patientManagement = new PatientManagementServiceImpl();
       // Create an RMI registry on port 1098
        Registry registry = LocateRegistry.createRegistry(1098);
          // Bind the appointment service to the registry
        registry.rebind("PatientManagement",patientManagement);
        System.out.println("RMI Server is ready.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
        
        
        
        }
    

