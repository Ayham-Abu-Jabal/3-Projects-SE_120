
CREATE TABLE IF NOT EXISTS student (
    studentId  VARCHAR(10)  PRIMARY KEY,
    name       VARCHAR(255),
    email      VARCHAR(255) UNIQUE,
    major      VARCHAR(10)
);
 
CREATE TABLE IF NOT EXISTS course (
    courseCode  VARCHAR(10)  PRIMARY KEY,
    courseTitle VARCHAR(255),
    creditHours INT
);
 
CREATE TABLE IF NOT EXISTS enrollment (
    studentId  VARCHAR(10),
    courseCode VARCHAR(10),
    grade      DOUBLE PRECISION CHECK (grade >= 0 AND grade <= 100),
    PRIMARY KEY (studentId, courseCode),
    FOREIGN KEY (studentId)  REFERENCES student(studentId)  ON DELETE CASCADE,
    FOREIGN KEY (courseCode) REFERENCES course(courseCode)  ON DELETE CASCADE
);