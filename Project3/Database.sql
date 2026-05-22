CREATE TABLE IF NOT EXIST Student(
    studentId PRIMARY KEY,
    'name' VARCHAR(255),
    email VARCHAR(255),
    major VARCHAR(10)
);

CREATE TABLE IF NOT EXIST Course(
    courseCode PRIMARY KEY,
    courseTitle VARCHAR(255),
    creditHours INT
);

CREATE TABLE IF NOT EXIST Student(
    studentId FOREIGN KEY,
    courseCode FOREIGN KEY,
    grade INT
);

