
import java.util.HashSet;

public class Doctor extends Person {

    // Data fields specific to doctor
    private String specialty;
    private String roomNumber;

    // To keep track of booked slots for this doctor
    //HashSet is used to store unique date-time without duplicates.
    private HashSet<String> bookedSlots = new HashSet<>();
    // Constructor
    
    public Doctor(String id, String name, int age, String phone, String specialty, String roomNumber) {
        super(id, name, age, phone);
        this.specialty = specialty;
        this.roomNumber = roomNumber;
    }

    // Getters
    
    public String getSpecialty() { 
        return specialty; 
    }
    
    public String getRoomNumber() { 
        return roomNumber; 
    }
    
    public void bookSlot(String date, String time) {
        bookedSlots.add((date + "|" + time).toLowerCase());
    }

    public void freeSlot(String date, String time) {
        bookedSlots.remove((date + "|" + time).toLowerCase());
    }

    public boolean isAvailable(String date, String time) {
        return !bookedSlots.contains((date + "|" + time).toLowerCase());
    }

    @Override
        public void displayInfo() {
            System.out.println("----Doctor----");
            displayBasicInfo();
            System.out.println("Specialty: " + specialty);
            System.out.println("Room Number: " + roomNumber);
            System.out.println("Booked Slots: " + bookedSlots);
        }//Override
}//Class
