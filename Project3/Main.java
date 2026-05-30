package Project3;
import java.util.*;

public class Main {

    public static void main(String[] args) {
        runTests();
        menu();
    }

    public static void runTests() {
        System.out.println("\n========== RUNNING TESTS ==========");

        Student s1 = new Student("224569011", "Ayham Abu Jabal",  "ayham@gmail.com",  "S001", "SE");
        Student s2 = new Student("386202765", "Mohammed Ahmed",    "mohammed@gmail.com",   "S002", "CS");
        Course  c1 = new Course("SE120",  "Object-Oriented Programming I", 3);
        Course  c2 = new Course("MAT112", "Calculus II",                   3);

        
        System.out.println("\n-- Add Students --");
        StudentManagementSystems.addStudent(s1);
        StudentManagementSystems.addStudent(s2);

        System.out.println("\n-- Invalid Email (should fail) --");
        StudentManagementSystems.addStudent(new Student("707112773", "Bad Email", "notanemail", "S003", "CS"));
 

        System.out.println("\n-- Add Courses --");
        StudentManagementSystems.addCourse(c1);
        StudentManagementSystems.addCourse(c2);

        System.out.println("\n-- Enroll Students --");
        StudentManagementSystems.enrollStudent("S001", "SE120");
        StudentManagementSystems.enrollStudent("S001", "MAT112");
        StudentManagementSystems.enrollStudent("S002", "SE120");

        System.out.println("\n-- Duplicate Enrollment (should fail) --");
        StudentManagementSystems.enrollStudent("S001", "SE120");

        System.out.println("\n-- Enroll Non-Existent Student (should fail) --");
        StudentManagementSystems.enrollStudent("S999", "SE120");

        System.out.println("\n-- Enroll in Non-Existent Course (should fail) --");
        StudentManagementSystems.enrollStudent("S001", "XX999");

        System.out.println("\n-- Enter Grades --");
        StudentManagementSystems.enterGrade("S001", "SE120",  88.5);
        StudentManagementSystems.enterGrade("S001", "MAT112", 76.0);
        StudentManagementSystems.enterGrade("S002", "SE120",  91.0);

        System.out.println("\n-- Invalid Grade (should fail) --");
        StudentManagementSystems.enterGrade("S001", "SE120", 150.0);

        System.out.println("\n-- Grade for Non-Enrolled Course (should fail) --");
        StudentManagementSystems.enterGrade("S002", "MAT112", 80.0);

        System.out.println("\n-- Display Transcripts --");
        StudentManagementSystems.displayTranscript("S001");
        StudentManagementSystems.displayTranscript("S002");

        System.out.println("\n-- Transcript for Non-Existent Student (should fail) --");
        StudentManagementSystems.displayTranscript("S999");

        System.out.println("\n-- Full Report --");
        StudentManagementSystems.displayReport();

    }

    public static void menu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        while (true) {
            System.out.println("\n----- Menu -----");
            System.out.println("1- Add Student");
            System.out.println("2- Add Course");
            System.out.println("3- Delete Student");
            System.out.println("4- Delete Course");
            System.out.println("5- Enroll Student in Course");
            System.out.println("6- Enter/Update Grade");
            System.out.println("7- Display Student Transcript");
            System.out.println("8- Display All Students and Courses");
            System.out.println("Enter any other number to exit");
            System.out.print("Enter your choice: ");


            try {
                choice = scanner.nextInt();
                scanner.nextLine();//this is common practice

                if (choice == 1) {
                    System.out.print("Enter Student ID: ");
                    String id = scanner.nextLine();
                    System.out.print("Enter Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine();
                    System.out.print("Enter Major: ");
                    String major = scanner.nextLine();
                    StudentManagementSystems.addStudent(new Student(id, name, email, id, major));

                } 
                else if (choice == 2) {
                    System.out.print("Enter Course Code: ");
                    String code = scanner.nextLine();
                    System.out.print("Enter Course Title: ");
                    String title = scanner.nextLine();
                    System.out.print("Enter Credit Hours: ");
                    int credits = Integer.parseInt(scanner.nextLine().trim());
                    StudentManagementSystems.addCourse(new Course(code, title, credits));
                }
                else if (choice == 3) {
                    System.out.print("Enter Student ID to delete: ");
                    String studentId = scanner.nextLine();
                    StudentManagementSystems.deleteStudent(studentId);
 
                } 
                else if (choice == 4) {
                    System.out.print("Enter Course Code to delete: ");
                    String courseCode = scanner.nextLine();
                    StudentManagementSystems.deleteCourse(courseCode);
                }
                 else if (choice == 5) {
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    StudentManagementSystems.enrollStudent(studentId, courseCode);

                } 
                else if (choice == 6) {
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    System.out.print("Enter Course Code: ");
                    String courseCode = scanner.nextLine();
                    System.out.print("Enter Grade (0-100): ");
                    double grade = Double.parseDouble(scanner.nextLine().trim());
                    StudentManagementSystems.enterGrade(studentId, courseCode, grade);

                } 
                else if (choice == 7) {
                    System.out.print("Enter Student ID: ");
                    String studentId = scanner.nextLine();
                    StudentManagementSystems.displayTranscript(studentId);

                }
                else if (choice == 8) {
                    StudentManagementSystems.displayReport();

                } 
                
                else {
                    System.out.println("Exited");
                    scanner.close();
                    return;
                }

            } catch (Exception e) {
                System.out.println("Invalid input. Please try again.");
                scanner.nextLine();
            }
        }
    }
}