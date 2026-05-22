package Project3;

import java.util.*;
import java.sql.*;

public class StudentManagementSystems {
    private int students;
    private int courses;

    public void addStudents(Student student) {
        String sql = "INSERT INTO Student (studentId, 'user', major) INTO (?, ?, ?)";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preState = connection.prepareStatement(sql)) {
            preState.setString(1, student.getStudentId());
            preState.setString(2, student.getName());
            preState.setString(3, student.getEmail());

            preState.executeUpdate();
            System.out.println("Student Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addCourses(int courses) {
        String sql = "INSERT INTO Course (courseCode, courseTitle, creditHours) INTO (?, ?, ?)";
        try (Connection connection = DatabaseConnector.connect();
                PreparedStatement preState = connection.prepareStatement(sql)) {
            preState.setString(1, student.getStudentId());
            preState.setString(2, student.getName());
            preState.setString(3, student.getEmail());

            preState.executeUpdate();
            System.out.println("Student Added Successfully");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void displayReports() {

    }
}
