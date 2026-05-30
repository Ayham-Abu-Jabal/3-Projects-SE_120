package Project3;


public class Enrollment {
    private double grade;
    private Student student;
    private Course course;

    public Enrollment(Student student, Course course) {
        this.student = student;
        this.course = course;
    }

    public double getGrade() {
        return grade;
    }

    public Student getStudent() {
        return student;
    }

    public Course getCourse() {
        return course;
    }

    public void addGrade(double grade, Course course) {
        if(grade >= 0 && grade <= 100){
            this.grade = grade;
            return;
        }
        else{
            System.out.println("Error: Grade cannot be less than 0 or more than 100");
        }
    }
 
    public void displayEnrollment(){
        System.out.println("Student: " + student.getName() + " (" + student.getStudentId() + ")");
        System.out.println("Course: " + course.getCourseTitle() + " (" + course.getCourseCode() + ")");
        if (grade >= 0) {
            System.out.printf( "Grade: %.2f%n", grade);
        } 
        else {
            System.out.println("Grade: Not yet assigned");
        }
    }
}