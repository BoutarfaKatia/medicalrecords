/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package RMIService;

/**
 *
 * @author WINDOWS
 */
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class RMIServer {
    public static void main(String[] args) {
        try {
            // Set up RMI services
            AppointmentService appointmentService = new AppointmentServiceImpl();

            // Create an RMI registry on port 1099
            Registry registry = LocateRegistry.createRegistry(1099);

            // Bind the appointment service to the registry
            registry.rebind("AppointmentService", appointmentService);

            System.out.println("RMI Server is ready.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}