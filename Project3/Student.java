package Project3;
import java.util.*;

public class Student extends Person{
    private String studentID;
    private String major;
    private ArrayList<String> enrolledCourses;//to add courses to a list
    private ArrayList<Double> grades;//to add grades to a list

    public Student(String id, String name, String email, String studentID, String major, ArrayList<String> enrolledCourses, ArrayList<Double> grades){
        super(id, name, email);
        this.studentID = studentID;
        this.major = major;
        this.enrolledCourses = enrolledCourses;
        this.grades = grades;
    }
  

    public void enroll(String course) {
        enrolledCourses.add(course);
    }

    public void addGrade(String courseCode ,double grade) {
        grades.add(grade);
    }

    public double calculateAverage(){
        double sum = 0;
        for(int i = 0; i < grades.size(); i++){
            sum+= grades.get(i);
        }
        return sum/grades.size();
    }

    public void displayTranscript(){

        System.out.printf(
        " Transcipt\n" +
        "----------------------------------------\n" +
        "Courses | Grades\n");
        for(int i = 0; i < grades.size(); i++){
            System.out.printf("%s \t| %.2f\n", enrolledCourses.get(i), grades.get(i));
        }
    }

}