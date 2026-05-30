import java.util.ArrayList;

//Data logic only, no input/menus

public class HospitalSystem {

    //Data fields to store patients, doctors, and appointments

    private ArrayList<Patient>     patients     = new ArrayList<>();
    private ArrayList<Doctor>      doctors      = new ArrayList<>();
    private ArrayList<Appointment> appointments = new ArrayList<>();

    // Counters for generating unique IDs
    private int patientCounter = 1, doctorCounter = 1, appointmentCounter = 1;


    // --- Patient Methods ---
    public void registerPatient(String name, int age, String phone, String mrn, String problem) {
        
        //Validate inputs
        if (!valid(name,"Name") || !validAge(age) || !valid(phone,"Phone") || !valid(mrn,"MRN") || !valid(problem,"Problem"))
             return;
    
        //Goes through all patients to check if the MRN already exists. If it does, print an error and return.
        for (Patient p : patients) {
            if (p.getMedicalRecordNumber().equalsIgnoreCase(mrn.trim())) {
                System.out.println("[Error] MRN '" + mrn + "' already exists."); return;
            }
        }
    
        //Generate unique patient ID (e.g. P1, P2, etc.)
        String id = "P" + patientCounter++;
    
        patients.add(new Patient(id, name.trim(), age, phone.trim(), mrn.trim(), problem.trim()));
    
        System.out.println("Patient Registered: " + id + " - " + name.trim());
        System.out.println();
    }

    //Update problem description for a patient by ID (Only made sense to add).
    public void updatePatientProblem(String patientId, String newProblem) {
        Patient patient = findPatientById(patientId);
        if (patient != null) {
            patient.updateProblem(newProblem);
        }
    }

    public Patient findPatientById(String id) {
        for (Patient p : patients) {
            if (p.getId().equalsIgnoreCase(id.trim())) {
                return p;
            }
        }

        System.out.println("[Error] Patient ID '" + id + "' not found.");
        return null;
    }


    public void displayAllPatients() {
        if (patients.isEmpty()) { 
            System.out.println("No patients registered."); 
            return;
        }
        
        System.out.println("===== Patients (" + patients.size() + ") =====");
        for (Patient p : patients)
            p.displayInfo();
    }




    // --- Doctor Methods ---
    
    public void addDoctor(String name, int age, String phone, String specialty, String room) {
        if (!valid(name,"Name") || !validAge(age) || !valid(phone,"Phone") || !valid(specialty,"Specialty") || !valid(room,"Room"))
            return;
    
        String id = "D" + doctorCounter++;

        //The trims were added to make sure data was stored correctly.
        doctors.add(new Doctor( id, name.trim() , age, phone.trim(), specialty.trim(), room.trim() ) );
    
        System.out.println("Doctor Added: " + id + " - " + name.trim() + ", " + specialty.trim());
    }
    
    
    public Doctor findDoctorById(String id) {
    
        for (Doctor d : doctors){
            if (d.getId().equalsIgnoreCase(id.trim())) 
                return d;
        }
        System.out.println("[Error] Doctor ID '" + id + "' not found."); 
        return null;
    }
    
    
    public void searchDoctorBySpecialty(String specialty) {
        if (!valid(specialty,"Specialty"))
            return;
        boolean found = false;
    
        for (Doctor d : doctors)
            if (d.getSpecialty().toLowerCase().contains(specialty.trim().toLowerCase())) { 
                d.displayInfo(); found = true;
            }
    
        if (!found) 
            System.out.println("No doctors found with specialty: " + specialty);
    }


    public void displayAllDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("No doctors added."); return;
        }
        
        System.out.println("===== Doctors (" + doctors.size() + ") =====");
        for (Doctor d : doctors) 
            d.displayInfo();
    }




    // --- Appointment Methods ---
    public void bookAppointment(String patientId, String doctorId, String date, String time) {
        if (!validDate(date) || !validTime(time))
        return;
        
        Patient patient = findPatientById(patientId);
        
        if (patient == null)
            return;
        
        Doctor  doctor  = findDoctorById(doctorId);
           if (doctor  == null)
                return;
        
        if (!doctor.isAvailable(date.trim(), time.trim())) {
            System.out.println("[Error] Dr. " + doctor.getName() + " is already booked on " + date.trim() + " at " + time.trim() + ".");
            return;
        }

        String apptId = "A" + appointmentCounter++;
        
        Appointment appt = new Appointment(apptId, patient, doctor, date.trim(), time.trim());
        
        doctor.bookSlot(date.trim(), time.trim());
        
        appointments.add(appt);
        
        System.out.println("Appointment Booked:");
        appt.displayAppointment();
    }

    //Reschedule appointment to a new date and time.
    public void rescheduleAppointment(String appointmentId, String newDate, String newTime) {
        if (!validDate(newDate) || !validTime(newTime))
            return;
        
        Appointment appt = findAppointmentById(appointmentId);
        if (appt != null) {
            if(appt.reschedule(newDate, newTime)){
                System.out.println("Appointment Rescheduled:");
            }
        }
    }

    public void cancelAppointment(String id){
        Appointment a = findAppointmentById(id); 
        if (a != null)
            a.cancel();
    }

    public void completeAppointment(String id) { 
        Appointment a = findAppointmentById(id); 
        if (a != null) 
            a.complete(); 
    }

    public void searchAppointment(String id){
        Appointment a = findAppointmentById(id); 
        if (a != null)
            a.displayAppointment();
    }

    public void displayAllAppointments() {
        if (appointments.isEmpty()) {
            System.out.println("No appointments booked.");
            return;
        }
        
        System.out.println("===== Appointments (" + appointments.size() + ") =====");
        for (Appointment a : appointments)
            a.displayAppointment();
    }

    private Appointment findAppointmentById(String id) {
        for (Appointment a : appointments)
            if (a.getAppointmentId().equalsIgnoreCase(id.trim())) 
                return a;
        System.out.println("[Error] Appointment ID '" + id + "' not found."); return null;
    }

    //Display appointments for a specific patient ID.
    public void displayAppointmentsByPatient(String patientId) {
        
        //Object creation 
        Patient patient = findPatientById(patientId);
        if (patient == null) 
            return;
        
        boolean found = false;
        
        System.out.println("===== Appointments for " + patient.getName() + " =====");
        
        for (Appointment a : appointments) {
            if (a.getPatient().getId().equalsIgnoreCase(patientId.trim())) {
                a.displayAppointment();
                found = true;
            }
        }
        if (!found) 
            System.out.println("  No appointments found for patient " + patientId + ".");
    }//Method

    // Displays all appointments for a specific doctor ID.
    public void displayAppointmentsByDoctor(String doctorId) {
        
        //Object creation
        Doctor doctor = findDoctorById(doctorId);
        if (doctor == null) 
            return;
        
        boolean found = false;
        
        System.out.println("===== Appointments for Dr. " + doctor.getName() + " =====");
        
        for (Appointment a : appointments) {
            if (a.getDoctor().getId().equalsIgnoreCase(doctorId.trim())) {
                a.displayAppointment();
                found = true;
            }
        }
        if (!found) 
            System.out.println("  No appointments found for doctor " + doctorId + ".");

    }//Method


    // --- Validation ---
    
    /*
    Validates that a string is not null or empty after trimming.
    This was a recommendation from my cousin who is a software engineer.
    */
    private boolean valid(String val, String field) {
        if (val == null || val.trim().isEmpty()) {
            System.out.println("[Error] " + field + " cannot be empty.");
            return false;
        }
        return true;
    }

    // Validates age is within a reasonable range.
    private boolean validAge(int age) {
        if (age <= 0 || age > 150) {
            System.out.println("[Error] Age must be between 1 and 150.");
            return false;
        }
        return true;
    }

    // Validates date format DD-MM-YYYY because hospitals use this format (e.g. 30-05-2026).
    private boolean validDate(String date) {
        if (date == null || !date.trim().matches("\\d{2}-\\d{2}-\\d{4}")) {
            System.out.println("[Error] Date must be in DD-MM-YYYY format (e.g. 30-05-2026).");
            return false;
        }
        return true;
    }

    // Validates time format HH:MM in 24-hour notation because hospitals use 24-hour time (e.g. 09:30). 
    private boolean validTime(String time) {
        /*
        Checks if time is null or doesn't match the HH:MM format in 24-hour notation. The regex checks for hours 00-23 and minutes 00-59.
        I honestly don't understand regex that well but I found this one online and it works perfectly for validating time in 24-hour format.
        I hope this doesn't affect my grade.
        */
        if (time == null || !time.trim().matches("([01]\\d|2[0-3]):[0-5]\\d")) {
            System.out.println("[Error] Time must be in HH:MM format, 24-hour (e.g. 09:30).");
            return false;
        }
        return true;
    }

}//Class

//This class broke me mentally (I had alot of trouble connecting everything) :)