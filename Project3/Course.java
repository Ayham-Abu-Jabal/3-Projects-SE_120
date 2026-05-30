package Project3;

public class Course {
    private String courseCode;
    private String courseTitle;
    private int creditHours;

    public Course(String courseCode, String courseTitle, int creditHours) {
        this.courseCode = courseCode;
        this.courseTitle = courseTitle;
        this.creditHours = creditHours;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getCourseTitle() {
        return courseTitle;
    }

    
    public int getCreditHours() {
        return creditHours;
    }

        public void getCourseCode(String courseCode) {
        this.courseCode = courseCode;
    }

    public void setCourseTitle(String courseTitle) {
        this.courseTitle = courseTitle;
    }

    
    public void setCreditHours(int creditHours) {
        this.creditHours = creditHours;
    }


    public void displayCourseInfo(){
        String courseInfo = switch (this.courseCode){
            case "SE120" -> "After completing this course, students will be equipped with the necessary skills and tools to write programs in Java based on a procedural and object-oriented approach. Topics of focus will include basic Java programming, conditional statements, strings, iteration, methods, arrays, creating classes, encapsulation, inheritance and polymorphism, abstract classes, packages, principles of object-oriented design, as well as exceptions and interfaces.";
            case "MAT112" -> "This course is a continuation to Calculus I. The course covers basic mathematical analysis and mathematical tools that are widely used and are essential for mathematical analysis and applications. Topics include sequences; infinite series; power series; conics; polar, cylindrical, and spherical coordinates; vectors and the geometry of space; and vector valued functions.";
            case "PHU124" -> "The material of this course requires knowledge of differential and integral calculus. The covered material includes the basics of electricity and magnetism, electromagnetic radiation, and optics.";
            default -> "Invalid Course";
        };

        System.out.println("Course: " + courseTitle + " (" + courseCode + ")");
        System.out.println("Credit Hours: " + creditHours);
        System.out.println("Description : " + courseInfo);

    }
}
