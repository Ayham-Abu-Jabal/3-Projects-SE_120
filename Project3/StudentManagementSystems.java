package Project3;

import java.sql.*;

public class StudentManagementSystems {

    public static void addStudent(Student student) {
        String sql = "INSERT INTO student (studentId, name, email, major) VALUES (?, ?, ?, ?)";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, student.getStudentId());
            preparedStatement.setString(2, student.getName());
            preparedStatement.setString(3, student.getEmail());  
            preparedStatement.setString(4, student.getMajor()); 

            preparedStatement.executeUpdate();
            System.out.println("Student Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

    public static void addCourse(Course course) {
        String sql = "INSERT INTO course (courseCode, courseTitle, creditHours) VALUES (?, ?, ?)";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getCourseCode());
            preparedStatement.setString(2, course.getCourseTitle());
            preparedStatement.setInt(3, course.getCreditHours());

            preparedStatement.executeUpdate();
            System.out.println("Course Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        catch(Exception e){
            System.out.println("Error: " + e);
        }
    }

     public static void deleteCourse(Course course) {
        String sql = "DELETE FROM course WHERE courseCode = ?";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, course.getCourseCode());

            int check = preparedStatement.executeUpdate(); // executeUpdate returns an integer
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

     public static void deleteStudent(Student student) {
        String sql = "DELETE FROM student WHERE studentId = ?";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, student.getStudentId());

            int check = preparedStatement.executeUpdate();
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
                 ResultSet resultSet = statement.executeQuery(studentSql)) {
                while (resultSet.next()) {
                    String studentId = resultSet.getString("studentId");
                    String name      = resultSet.getString("name");
                    String email     = resultSet.getString("email");
                    String major     = resultSet.getString("major");
                    System.out.printf("%-15s %-20s %-25s %-10s%n", studentId, name, email, major);
                }
            }
            System.out.println();

            System.out.println("==================== COURSE REPORT =====================");
            System.out.printf("%-15s %-30s %-12s%n", "Course Code", "Course Title", "Credit Hours");
            System.out.println("--------------------------------------------------------");

            try (Statement statement = connection.createStatement();
                 ResultSet resultSet = statement.executeQuery(courseSql)) {
                while (resultSet.next()) {
                    String courseCode  = resultSet.getString("courseCode");
                    String courseTitle = resultSet.getString("courseTitle");
                    int    creditHours = resultSet.getInt("creditHours");
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


}