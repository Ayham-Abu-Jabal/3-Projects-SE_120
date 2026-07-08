public class Appointment {
    
    // Possible statuses (I learned this by googling it)
    public enum Status { BOOKED, CANCELLED, COMPLETED }

    private String  appointmentId;
    private Patient patient;       // object reference, not just a name
    private Doctor  doctor;        // object reference, not just a name
    private String  dateText;
    private String  timeText;
    private Status  status;

    // Constructor
    public Appointment(String appointmentId, Patient patient, Doctor doctor,String dateText, String timeText) {
        this.appointmentId = appointmentId;
        this.patient= patient;
        this.doctor= doctor;
        this.dateText= dateText;
        this.timeText= timeText;
        this.status= Status.BOOKED;
    }

    // ----- Getters -----
    public String  getAppointmentId() { 
        return appointmentId;
    }
    
    public Patient getPatient(){ 
        return patient;
    }
    
    public Doctor  getDoctor(){ 
        return doctor;
    }
    
    public String  getDateText(){ 
        return dateText;
    }
    
    public String  getTimeText(){ 
        return timeText;
    }
    
    public Status  getStatus(){
        return status;
    }

    // -----State changes-----

    //Cancels this appointment and frees the doctor's slot.
    public void cancel() {
        if (status == Status.CANCELLED) {
            System.out.println("  [Info] Appointment " + appointmentId + " is already cancelled.");
            return;
        }
        //Cancel
        status = Status.CANCELLED;

        //Free the doctor's slot for this date and time
        doctor.freeSlot(dateText, timeText);
        
        System.out.println("Appointment " + appointmentId + " has been cancelled.");
    }

    //Marks this appointment as completed.
    public void complete() {
        if (status != Status.BOOKED) {
            System.out.println("  [Info] Only BOOKED appointments can be completed.");
            return;
        }
        status = Status.COMPLETED;
        System.out.println("  Appointment " + appointmentId + " marked as completed.");
    }

    //Reschedule appointment to a new date and time, if the doctor is available (it made sense to add it).
    public boolean reschedule(String newDate, String newTime) {

        if (status != Status.BOOKED) {
            System.out.println("Only BOOKED appointments can be rescheduled.");
            return false;
        }

        if (dateText.equalsIgnoreCase(newDate.trim()) && timeText.equalsIgnoreCase(newTime.trim())) {
            System.out.println("[Error] Appointment is already scheduled at that date and time.");
            return false;
        }

        if (!doctor.isAvailable(newDate.trim(), newTime.trim())) {
            System.out.println("[Error] Dr. " + doctor.getName() + " is not available on " + newDate.trim() + " at " + newTime.trim() + ".");
            return false;
        }

        // Free the old slot
        doctor.freeSlot(dateText, timeText);
        
        // Book the new slot
        doctor.bookSlot(newDate.trim(), newTime.trim());


        dateText = newDate.trim();
        timeText = newTime.trim();

        System.out.println("Appointment " + appointmentId + " has been rescheduled to " + dateText + " at " + timeText + ".");

        return true;
    }

    // Prints a formatted summary of this appointment.
    public void displayAppointment() {
        System.out.println("----------------------------------------");
        System.out.println("Appointment ID: " + appointmentId);
        System.out.println("Patient: " + patient.getName() + "  (ID: " + patient.getId() + ")");
        System.out.println("Doctor: " + doctor.getName() + " (ID: " + doctor.getId() + ")");
        System.out.println("Specialty: " + doctor.getSpecialty());
        System.out.println("Room: " + doctor.getRoomNumber());
        System.out.println("Date / Time: " + dateText + "  " + timeText);
        System.out.println("Status: " + status);
        System.out.println("----------------------------------------");
    
    }// displayAppointment

}//Class