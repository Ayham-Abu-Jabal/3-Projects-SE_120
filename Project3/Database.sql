CREATE TABLE IF NOT EXISTS Student(
    studentId VARCHAR(10) PRIMARY KEY,
    name VARCHAR(255),
    email VARCHAR(255) UNIQUE,
    major VARCHAR(10)
);

CREATE TABLE IF NOT EXISTS Course(
    courseCode VARCHAR(10) PRIMARY KEY,
    courseTitle VARCHAR(255),
    creditHours INT
);

CREATE TABLE IF NOT EXISTS Enrollment (
    studentId VARCHAR(10),
    courseCode VARCHAR(10),
    grade DOUBLE PRECISION CHECK (grade >= 0 AND grade <= 100),
    PRIMARY KEY (studentId, courseCode),
    FOREIGN KEY (studentId) REFERENCES Student(studentId) ON DELETE CASCADE,
    FOREIGN KEY (courseCode) REFERENCES Course(courseCode) ON DELETE CASCADE
);