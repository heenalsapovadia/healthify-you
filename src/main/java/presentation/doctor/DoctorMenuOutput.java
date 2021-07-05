package presentation.doctor;

import java.util.Scanner;

public class DoctorMenuOutput {

    public static void prescribeMedication(){
        Scanner sc = new Scanner(System.in);
//        System.out.print("Prescription ID : ");
//        int prescription_id = sc.nextInt();
        System.out.print("Appointment ID : ");
        int appointmentId = sc.nextInt();

        /*
        Validate appointment ID --- where to do this validation?
         */

        System.out.print("Patient ID : ");
        int patientId = sc.nextInt();
        System.out.print("Number of medicines to prescribe : ");
        int medicineNumber = sc.nextInt();

        while(medicineNumber>0){
            System.out.print("Medicine Name : ");
            String medicineName = sc.next();
            System.out.print("Morning : ");
            int morning = sc.nextInt();
            System.out.print("Afternoon : ");
            int afternoon = sc.nextInt();
            System.out.print("Evening : ");
            int evening = sc.nextInt();
        }
        /*
        create prescription object
         */
    }
}
