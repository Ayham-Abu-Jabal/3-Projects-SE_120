public class Patient extends Person {

    //All admitted patients will have a medical record number and a problem description.
    private String medicalRecordNumber, problemDescription;

    public Patient(String id, String name, int age, String phone) {
        super(id, name, age, phone);
        this.medicalRecordNumber = "";
        this.problemDescription = "";
    }//Constructor with basic fields only

    public Patient(String id, String name, int age, String phone, String medicalRecordNumber, String problemDescription) {
        super(id, name, age, phone);
        if (medicalRecordNumber != null) {
            this.medicalRecordNumber = medicalRecordNumber;
        } 
        else {
            this.medicalRecordNumber = "";
        }

        if (problemDescription != null) {
            this.problemDescription = problemDescription;
        }
        else {
            this.problemDescription = "";
        }
    }//Constructor with all fields

    //Getters

    public String getMedicalRecordNumber() {
        return medicalRecordNumber;
    }
    public String getProblemDescription() {
        return problemDescription;
    }

    // Updates the patient's problem description.
    public void updateProblem(String newDescription) {
        if (newDescription == null || newDescription.trim().isEmpty()) {
            System.out.println("[Error] Problem description cannot be empty.");
            return;
        }
        this.problemDescription = newDescription.trim();
        System.out.println("  Problem description updated for patient " + getId() + ".");
    }

    @Override
        public void displayInfo() {
            System.out.println("----Patient----");
            displayBasicInfo();
            System.out.println("Medical Record Number: " + medicalRecordNumber);
            System.out.println("Problem Description: " + problemDescription);
        }//Override
}//Class

