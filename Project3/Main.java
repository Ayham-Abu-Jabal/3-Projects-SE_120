package Project3;
import java.util.*;

public class Main{
    public static void main(String[] args){
        ArrayList<String> l1 = new ArrayList<>();
        ArrayList<Double> l2 = new ArrayList<>();
        l1.add("MAT112");
        l1.add("SE120");
        l2.add(95.0);
        l2.add(88.0);
        Student std = new Student("505", "Ayham", "ayham.abujabal@gmail.com", "S1001", "SE", l1, l2);
        Student std2 = new Student("606", "John", "ayham.abujabal@gmail.com", "S1002", "SE", l1, l2);
        Course cou = new Course("MAT112", "Calculus II", 3);
        Course cou2 = new Course("SE120", "Object-Oriented Programming", 3);
        StudentManagementSystems.displayReport();

    }  
}

