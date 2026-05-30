package Project3;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManagementSystems {


    private static ArrayList<Student> studentsAdded = new ArrayList<>();
    private static ArrayList<Course> coursesAdded = new ArrayList<>();

    public static ArrayList<Student> getStudentsAdded() {
        return studentsAdded;
    }

    public static ArrayList<Course> getCoursesAdded() {
        return coursesAdded;
    }
    
    // Add (Memory only)
    public static void addStudentInMemory(Student student) {
        studentsAdded.add(student);
        System.out.println("Student added to memory.");
    }

    // Search (Memory only)
    public static Student searchStudentAdded(String studentId) {
        for (Student student : studentsAdded) {
            if (student.getStudentId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }

    // Update (Memory only)
    public static void updateStudentAdded(String studentId, String name, String email, String major) {
        Student student = searchStudentAdded(studentId);
        if (student != null) {
            student.setName(name);
            student.setEmail(email);
            student.setMajor(major);
            System.out.println("Student updated in memory.");
        } else {
            System.out.println("Student not found in memory.");
        }
    }

    public static void displayStudentsAdded() {
        System.out.println("\n--- Students Recently Added  ---");
        for (Student student : studentsAdded) {
            student.displayInfo(); 
            System.out.println("Major: " + student.getMajor());
            System.out.println("-------------------------");
        }
    }


    public static void addCourseAdded(Course course) {
        coursesAdded.add(course);
        System.out.println("Course added to memory.");
    }

    public static Course searchCourseAdded(String courseCode) {
        for (Course course : coursesAdded) {
            if (course.getCourseCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    public static void updateCourseAdded(String code, String title, int credits) {
        Course course = searchCourseAdded(code);
        if (course != null) {
            course.setCourseTitle(title);
            course.setCreditHours(credits);
            System.out.println("Course updated in memory.");
        } else {
            System.out.println("Course not found in memory.");
        }
    }

    public static void displayCoursesAdded() {
    System.out.println("\n--- Courses Recently Added ---");
    for (Course course : coursesAdded) {
        course.displayCourseInfo();
        System.out.println("-------------------------");
    }
}
    
    public static void addStudent(Student student) {//adds student to student table in SQL
        studentsAdded.add(student);
        if (isValidEmail(student.getEmail()) == false) {
            System.out.println("Error: Invalid email address '" + student.getEmail() + "''.");
            return;
        }
        String sql = "INSERT INTO student (studentId, name, email, major) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)){//sends the sql string to not use the raw data 

            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());  
            preparedStatement.setString(4, student.getMajor()); 

            preparedStatement.executeUpdate();//updates the table
            preparedStatement.close();//prevents memory leaks
            System.out.println("Student Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

        public static void enterGrade(String studentId, String courseCode, double grade) {
        if (grade < 0 || grade > 100) {
            System.out.println("Error: Grade must be between 0 and 100.");
            return;
        }
        if (isEnrolled(studentId, courseCode) == false) {
            System.out.println("Error: Student is not enrolled in this course.");
            return;
        }
        String sql = "UPDATE enrollment SET grade = ? WHERE studentId = ? AND courseCode = ?";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDouble(1, grade);
            preparedStatement.setString(2, studentId);
            preparedStatement.setString(3, courseCode);
            int rows = preparedStatement.executeUpdate();
            System.out.println(rows > 0 ? "Grade Updated Successfully" : "No record updated.");
        } catch (SQLException e) {
            System.out.println("Error updating grade: " + e.getMessage());
        }
    }


    public static void addCourse(Course course) {
        coursesAdded.add(course);
        String sql = "INSERT INTO course (courseCode, courseTitle, creditHours) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getCourseCode());
            preparedStatement.setString(2, course.getCourseTitle());
            preparedStatement.setInt(3, course.getCreditHours());

            preparedStatement.executeUpdate();
            preparedStatement.close();
            System.out.println("Course Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }


        public static void enrollStudent(String studentId, String courseCode) {
        if (studentExists(studentId) == false) {
            System.out.println("Error: Student ID '" + studentId + "' not found.");
            return;
        }
        if (courseExists(courseCode) == false) {
            System.out.println("Error: Course code '" + courseCode + "' not found.");
            return;
        }
        if (isEnrolled(studentId, courseCode)) {
            System.out.println("Error: Student is already enrolled in this course.");
            return;
        }
        String sql = "INSERT INTO enrollment (studentId, courseCode) VALUES (?, ?)";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseCode);
            preparedStatement.executeUpdate();
            System.out.println("Student Enrolled Successfully");
        } catch (SQLException e) {
            System.out.println("Error enrolling student: " + e.getMessage());
        }
    }


     public static void deleteCourse(String courseCode) {//only the courseCode is needed to delete the entire course row because it's the primary key
        String sql = "DELETE FROM course WHERE courseCode = ?";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, courseCode);

            int check = preparedStatement.executeUpdate(); // executeUpdate returns an integer so that's why check is int
            preparedStatement.close();
            if(check > 0){
            System.out.println("Course Deleted Successfully");
            }
            else{
              System.out.println("Course wasn't deleted or doesn't exist");  
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

     public static void deleteStudent(String studentId) {//only the studentId is needed to delete the entire student row because it's the primary key
        String sql = "DELETE FROM student WHERE studentId = ?";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentId);
            int check = preparedStatement.executeUpdate();
            preparedStatement.close();
            if(check > 0){
            System.out.println("Student Deleted Successfully");
            }
            else{
                System.out.println("Student wasn't deleted or doesn't exist");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public static void displayReport() {
        String studentSql = "SELECT studentId, name, email, major FROM student";
        String courseSql  = "SELECT courseCode, courseTitle, creditHours FROM course";

        try (Connection connection = DatabaseConnector.connect()) {

            System.out.println("================================ STUDENT REPORT ========================");
            System.out.printf("%-15s %-20s %-25s %-10s%n", "Student ID", "Name", "Email", "Major");
            System.out.println("-------------------------------------------------------------------------");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(studentSql)) {//the executeQuery sends a SQL query that returns the results
                while (resultSet.next()){ //goes to the next row in the student table
                    String studentId = resultSet.getString("studentId");
                    String name = resultSet.getString("name");
                    String email = resultSet.getString("email");
                    String major = resultSet.getString("major");
                    System.out.printf("%-15s %-20s %-25s %-10s%n", studentId, name, email, major);
                }
            }
            System.out.println();

            System.out.println("==================== COURSE REPORT =====================");
            System.out.printf("%-15s %-30s %-12s%n", "Course Code", "Course Title", "Credit Hours");
            System.out.println("--------------------------------------------------------");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(courseSql)) {
                while (resultSet.next()) {//goes to the next row in the course table
                    String courseCode  = resultSet.getString("courseCode");
                    String courseTitle = resultSet.getString("courseTitle");
                    int creditHours = resultSet.getInt("creditHours");
                    System.out.printf("%-15s %-30s %-12d%n", courseCode, courseTitle, creditHours);
                }
            }
            System.out.println("========================================================");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

        public static void displayTranscript(String studentId) {
        if (studentExists(studentId) == false) {
            System.out.println("Error: Student ID '" + studentId + "' not found.");
            return;
        }
        String sql = "SELECT e.courseCode, c.courseTitle, e.grade " +
                     "FROM enrollment e JOIN course c ON e.courseCode = c.courseCode " +
                     "WHERE e.studentId = ?";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentId);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println("\n=========== TRANSCRIPT: " + studentId + " ==========");
            System.out.printf("%-12s %-30s %-8s%n", "Code", "Title", "Grade");
            System.out.println("--------------------------------------------------");
            double total = 0;
            int count = 0;
            while (resultSet.next()) {
                String code  = resultSet.getString("courseCode");
                String title = resultSet.getString("courseTitle");
                double grade = resultSet.getDouble("grade");
                boolean hasGrade = resultSet.wasNull();
                if (hasGrade == false) {
                    System.out.printf("%-12s %-30s %-8.2f%n", code, title, grade);
                    total += grade;
                    count++;
                } else {
                    System.out.printf("%-12s %-30s %-8s%n", code, title, "N/A");
                }
            }
            System.out.println("--------------------------------------------------");
            System.out.printf("Average: %.2f%n", count > 0 ? total / count : 0.0);//calculates Average
            System.out.println("==================================================");
        } catch (SQLException e) {
            System.out.println("Error retrieving transcript: " + e.getMessage());
        }
    }

 
    private static boolean isEnrolled(String studentId, String courseCode) {
        String sql = "SELECT 1 FROM enrollment WHERE studentId = ? AND courseCode = ?";
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, studentId);
            preparedStatement.setString(2, courseCode);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("Error checking enrollment: " + e.getMessage());
            return false;
        }
    }
 
    private static boolean rowExists(String sql, String param) {
        try (Connection connection = DatabaseConnector.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, param);
            return preparedStatement.executeQuery().next();
        } catch (SQLException e) {
            System.out.println("Error checking existence: " + e.getMessage());
            return false;
        }
    }
    
    private static boolean studentExists(String studentId) {
        return rowExists("SELECT 1 FROM student WHERE studentId = ?", studentId);
    }
 
    private static boolean courseExists(String courseCode) {
        return rowExists("SELECT 1 FROM course WHERE courseCode = ?", courseCode);
    }


    private static boolean isValidEmail(String email) {
    if (email.contains("@") == false) {
        return false;
    }
    String[] parts = email.split("@");
    if (parts.length != 2) {
        return false;
    }
    if (parts[1].contains(".") == false) {
        return false;
    }
    return true;
}

}