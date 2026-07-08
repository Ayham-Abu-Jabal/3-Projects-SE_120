================================================================================
                   HOSPITAL APPOINTMENT MANAGEMENT SYSTEM
================================================================================

PROJECT OVERVIEW
--------------------------------------------------------------------------------
This project is a robust, Object-Oriented, console-based Java application 
designed to streamline daily hospital operations. The system manages patient 
registrations, doctor profiles, and appointment scheduling while actively 
preventing double-booking through dynamic time-slot validation and hashing 
techniques.


KEY FEATURES
--------------------------------------------------------------------------------
1. Dynamic ID Auto-Generation:
   Automatically generates sequential, unique identifiers for Patients (P1, P2), 
   Doctors (D1, D2), and Appointments (A1, A2).

2. Conflict-Free Scheduling:
   Utilizes a HashSet to track available time slots, ensuring a doctor cannot 
   be double-booked for the same date and time.

3. Strict Input Validation:
   Implements Regular Expressions (Regex) to enforce standardized data formats:
   - Dates must follow the DD-MM-YYYY format.
   - Times must follow 24-hour military notation (HH:MM).

4. Appointment Lifecycle Management:
   Appointments transition cleanly through three states: BOOKED, COMPLETED, 
   and CANCELLED. Cancelling or rescheduling an appointment automatically frees 
   up the doctor's previously reserved time slot.

5. Advanced Search & Filtering:
   Allows users to search for doctors by specialty or ID, look up specific 
   appointments by their ID, and filter complete schedules by individual 
   patients or doctors.

6. Crash-Resistant UI:
   Features a safely handled menu system that catches invalid user inputs 
   without crashing the program.


SYSTEM ARCHITECTURE & CLASS BREAKDOWN
--------------------------------------------------------------------------------

1. THE DOMAIN MODELS (Data Classes)

   Person (Abstract Base Class)
   - Responsibilities: Serves as the blueprint for all human entities in the 
     system. Holds shared encapsulated attributes (id, name, age, phone) and 
     enforces polymorphism via the abstract displayInfo() method.

   Patient (Subclass of Person)
   - Responsibilities: Extends Person by introducing medical-specific data: 
     Medical Record Number (MRN) and problem descriptions. Includes functionality 
     to update diagnoses over time.

   Doctor (Subclass of Person)
   - Responsibilities: Extends Person by adding specialty and roomNumber. 
     Integrates a HashSet of strings to uniquely store and manage booked time 
     slots (date|time), allowing fast availability checking without duplicates.

   Appointment (Association Class)
   - Responsibilities: Links a Patient object and a Doctor object together at a 
     specific date and time. Uses an inner enum Status (BOOKED, CANCELLED, 
     COMPLETED) to safely track the lifecycle of the visit.


2. THE CONTROLLER & UI LAYERS

   HospitalSystem (The Logic Hub)
   - Acts as the central service layer and data repository.
   - Maintains centralized ArrayList collections for patients, doctors, and 
     appointments.
   - Validates unique constraints (e.g., preventing duplicate MRNs).
   - Handles the coordination required when rescheduling or cancelling an 
     appointment (freeing the doctor's old slot, verifying the new slot, and 
     updating the appointment state).
   - Houses the input validation methods for dates, times, and string integrity.

   Main (The Interactive User Interface)
   - The entry point of the application. 
   - Runs a continuous loop presenting a 13-option interactive console menu. 
   - Utilizes a safe reading mechanism (readInt) that catches NumberFormatException 
     errors, ensuring the program remains stable even if text is accidentally 
     entered into number fields.


HOW THE DATA FLOWS
--------------------------------------------------------------------------------
1. Registration: 
   When a user registers a patient or adds a doctor via Main, HospitalSystem 
   validates the inputs, assigns a unique ID, instantiates the subclass (Patient 
   or Doctor), and stores it in memory.

2. Booking an Appointment:
   - HospitalSystem verifies that both the Patient ID and Doctor ID exist.
   - It checks the doctor's bookedSlots set via doctor.isAvailable(date, time).
   - If free, a new Appointment object is created, the time slot is reserved on 
     the doctor's profile, and the appointment is saved to the list.

3. Rescheduling:
   - The system verifies the appointment is currently in the BOOKED state.
   - It checks if the requested doctor is available at the NEW time.
   - If valid, it tells the Doctor object to release the old timestamp and 
     reserve the new one, seamlessly updating the Appointment object's state.


GETTING STARTED (HOW TO RUN)
--------------------------------------------------------------------------------
Prerequisites:
- Java Development Kit (JDK) 8 (1.8) or higher installed on your computer.
- A terminal, command prompt, or Java IDE (IntelliJ, Eclipse, VS Code, etc.).

Steps to Run:
1. Place all 6 Java files (Person.java, Patient.java, Doctor.java, 
   Appointment.java, HospitalSystem.java, Main.java) into the same folder.
2. Open your terminal or command prompt and navigate to that folder.
3. Compile the application by running:
   javac *.java
4. Launch the application by running:
   java Main


WHAT I LEARNED
--------------------------------------------------------------------------------
Building this project solidified my understanding of:
- Connecting complex object models and managing state changes across multiple 
  classes.
- Using Java Collections (ArrayList for sequential storage and HashSet for fast, 
  duplicate-free lookup).
- Implementing Regular Expressions (Regex) for strict data validation.
- Writing clean, separation-of-concerns architecture where data logic 
  (HospitalSystem) is completely isolated from user presentation (Main).


KNOWN LIMITATIONS
--------------------------------------------------------------------------------
1. No Database Integration:
   	Could not successfully implement a database or create my own file storage system.
	Because of this, all registered patients, doctors, and appointments are 
	stored in temporary ArrayList memory and are lost the moment you close the program.


2. No Graphical User Interface (GUI):
   	The application has no visual UI and is entirely console-based,
	 requiring users to interact with it using text numbers from a command-line menu.

3. No Login or User Roles:
   	There is no password or authentication system.
	Anyone running the console has full administrative access and 
	can view medical records, book, or cancel appointments without needing	permission.

4. Basic Date and Time Checks:
   	The system correctly checks that dates and times follow the DD-MM-YYYY and HH:MM formats, but it does not check for real-world 	logic. 
	For example, the system will not stop someone from typing an impossible date (like February 31st) or booking a doctor at 3:00 AM.

5. No Medical History Log:
   	When you update a patient's problem description using the menu, 
	the new text completely replaces and erases the old diagnosis 
	instead of keeping a running history log of past visits.

6. Cannot Edit Existing Profiles:
   	While you can update a patient's medical problem, 
	there is no menu option to fix typos or change basic details 
	(like a name, age, phone number, or doctor's room number) after the registration is complete.

================================================================================