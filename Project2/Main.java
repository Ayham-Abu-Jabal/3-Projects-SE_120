
import java.util.Scanner;

public class Main {

    private static final Scanner input = new Scanner(System.in);
    private static final HospitalSystem syst = new HospitalSystem();

    //Reads an integer safely and keeps asking until valid input is entered.
    private static int readInt(String prompt) {

        while (true) {

            System.out.print(prompt);

            try {
                return Integer.parseInt(input.nextLine());
            }

            catch (NumberFormatException e) {
                System.out.println("[Error] Please enter a valid number.");
            }

        }//while

    }//readInt

    //Main method with menu loop
    public static void main(String[] args) {

        System.out.println();

        boolean running = true;

        while (running) {

            System.out.println("\n========= Hospital Appointment System =========");

            System.out.println("1.  Register Patient        2.  Add Doctor");
            System.out.println("3.  Search Doctor           4.  Book Appointment");
            System.out.println("5.  All Appointments        6.  Cancel Appointment");
            System.out.println("7.  Search Appointment      8.  Complete Appointment");
            System.out.println("9.  Appointments by Person  10. Reschedule");
            System.out.println("11. All Patients            12. All Doctors");
            System.out.println("13. Update Problem          0.  Exit");

            System.out.println("===============================================");

            //Take choice input
            int choice = readInt("Enter Choice: ");

            System.out.println();

            //Check what choice is and use the method required for that choice
            switch (choice) {

                //Register patient
                case 1:

                    System.out.print("Name: ");
                    String name = input.nextLine();

                    int age = readInt("Age: ");

                    System.out.print("Phone: ");
                    String phone = input.nextLine();

                    System.out.print("MRN: ");
                    String mrn = input.nextLine();

                    System.out.print("Problem: ");
                    String problem = input.nextLine();

                    syst.registerPatient(name, age, phone, mrn, problem);

                    System.out.println();
                    break;

                //Add doctor
                case 2:

                    System.out.print("Name: ");
                    String doctorName = input.nextLine();

                    int doctorAge = readInt("Age: ");

                    System.out.print("Phone: ");
                    String doctorPhone = input.nextLine();

                    System.out.print("Specialty: ");
                    String specialty = input.nextLine();

                    System.out.print("Room: ");
                    String room = input.nextLine();

                    syst.addDoctor(
                            doctorName,
                            doctorAge,
                            doctorPhone,
                            specialty,
                            room);

                    System.out.println();
                    break;

                //Search doctor
                case 3:

                    int searchChoice =
                            readInt("1.By Specialty  2.By ID: ");

                    if (searchChoice == 1) {

                        System.out.print("Specialty: ");
                        String searchSpecialty = input.nextLine();

                        syst.searchDoctorBySpecialty(searchSpecialty);

                    }//if

                    else if (searchChoice == 2) {

                        System.out.print("Doctor ID: ");
                        String doctorId = input.nextLine();

                        Doctor doctor =
                                syst.findDoctorById(doctorId);

                        if (doctor != null) {
                            doctor.displayInfo();
                        }

                    }//else if

                    else {
                        System.out.println("[Error] Invalid choice.");
                    }

                    System.out.println();
                    break;

                //Book appointment
                case 4:

                    System.out.print("Patient ID: ");
                    String patientId = input.nextLine();

                    System.out.print("Doctor ID: ");
                    String doctorId4 = input.nextLine();

                    System.out.print("Date (DD-MM-YYYY): ");
                    String date = input.nextLine();

                    System.out.print("Time (HH:MM): ");
                    String time = input.nextLine();

                    syst.bookAppointment(
                            patientId,
                            doctorId4,
                            date,
                            time);

                    System.out.println();
                    break;

                //Display all appointments
                case 5:

                    syst.displayAllAppointments();

                    System.out.println();
                    break;

                //Cancel appointment
                case 6:

                    System.out.print("Appointment ID: ");
                    String cancelId = input.nextLine();

                    syst.cancelAppointment(cancelId);

                    System.out.println();
                    break;

                //Search appointment
                case 7:

                    System.out.print("Appointment ID: ");
                    String searchAppointmentId =
                            input.nextLine();

                    syst.searchAppointment(searchAppointmentId);

                    System.out.println();
                    break;

                //Complete appointment
                case 8:

                    System.out.print("Appointment ID: ");
                    String completeId = input.nextLine();

                    syst.completeAppointment(completeId);

                    System.out.println();
                    break;

                //Display appointments by patient or doctor
                case 9:

                    int filterChoice =
                            readInt("1.By Patient  2.By Doctor: ");

                    if (filterChoice == 1) {

                        System.out.print("Patient ID: ");
                        String filterPatientId =
                                input.nextLine();

                        syst.displayAppointmentsByPatient(
                                filterPatientId);

                    }//if

                    else if (filterChoice == 2) {

                        System.out.print("Doctor ID: ");
                        String filterDoctorId =
                                input.nextLine();

                        syst.displayAppointmentsByDoctor(
                                filterDoctorId);

                    }//else if

                    else {
                        System.out.println("[Error] Invalid choice.");
                    }

                    System.out.println();
                    break;

                //Reschedule appointment
                case 10:

                    System.out.print("Appointment ID: ");
                    String rescheduleId = input.nextLine();

                    System.out.print("New Date (DD-MM-YYYY): ");
                    String newDate = input.nextLine();

                    System.out.print("New Time (HH:MM): ");
                    String newTime = input.nextLine();

                    syst.rescheduleAppointment(
                            rescheduleId,
                            newDate,
                            newTime);

                    System.out.println();
                    break;

                //Display all patients
                case 11:

                    syst.displayAllPatients();

                    System.out.println();
                    break;

                //Display all doctors
                case 12:

                    syst.displayAllDoctors();

                    System.out.println();
                    break;

                //Update patient problem
                case 13:

                    System.out.print("Patient ID: ");
                    String updatePatientId =
                            input.nextLine();

                    System.out.print("New Problem Description: ");
                    String newDescription =
                            input.nextLine();

                    syst.updatePatientProblem(
                            updatePatientId,
                            newDescription);

                    System.out.println();
                    break;

                //Exit
                case 0:

                    System.out.println("Goodbye!");

                    running = false;

                    System.out.println();
                    break;

                default:

                    System.out.println("[Error] Invalid choice.");

                    System.out.println();
                    break;

            }//switch

        }//while

    }//main

}//Class
