package Project3;

public class Student extends Person{
    private String studentId;
    private String major;

    public Student(String id, String name, String email, String studentID, String major){
        super(id, name, email);
        this.studentId = studentID;
        this.major = major;
    }
    
    public String getMajor() {
        return major;
    }

    public String getStudentId() {
        return studentId;
    }

}