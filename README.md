# SE 120 Java Projects
 
Three console-based Java projects for **SE 120 (Object-Oriented Programming I)**.
 
---
 
## Project 1: To be added and written
 
🚧 Coming soon.
 
---
 
## Project 2: Hospital Appointment System
 
A menu-based app for registering patients and doctors and booking appointments.
 
**Features**
- Register patients and add doctors (IDs are generated automatically: `P1`, `D1`, `A1`)
- Book, cancel, complete, and reschedule appointments
- Prevents booking a doctor twice at the same time
- Search doctors by specialty or ID
- Checks date (`DD-MM-YYYY`) and time (`HH:MM`) formats
**Run** (Java 8+)
```bash
cd Project2
javac *.java
java Main
```
Or open the `Project2` folder in your IDE and run `Main.java`.

(USed Netbeans with JDK 8 for this one)
 
> Data is stored in memory only, so it's lost when the program closes.
 
---
 
## Project 3: Student Management System
 
A menu-based app for managing students, courses, enrollments, and grades using a **PostgreSQL** database.
 
**Features**
- Add and delete students and courses
- Enroll students in courses and enter grades (0–100)
- Show a student's transcript with their average
- Show a report of all students and courses
**Setup**
 
1. Install PostgreSQL, create a database, then create the tables:
```sql
CREATE TABLE student (studentId VARCHAR(20) PRIMARY KEY, name VARCHAR(100), email VARCHAR(100), major VARCHAR(50));
CREATE TABLE course (courseCode VARCHAR(20) PRIMARY KEY, courseTitle VARCHAR(100), creditHours INT);
CREATE TABLE enrollment (studentId VARCHAR(20) REFERENCES student ON DELETE CASCADE, courseCode VARCHAR(20) REFERENCES course ON DELETE CASCADE, grade NUMERIC(5,2), PRIMARY KEY (studentId, courseCode));
```
 
2. Download the [PostgreSQL JDBC driver](https://jdbc.postgresql.org/download/) (`.jar` file).
**Run in an IDE** (Java 14+)
1. Open the main folder (not the `Project3` folder).
2. Add the driver `.jar` to the project's libraries.
3. In the run configuration, add these environment variables:
```
   DB_URL=jdbc:postgresql://localhost:5432/your_database
   DB_USER=your_username
   DB_PASS=your_password
```
4. Run `Project3/Main.java`.
**Or run from the terminal** (put the driver in the main folder and rename it to `postgresql.jar`)
 
macOS / Linux:
```bash
export DB_URL="jdbc:postgresql://localhost:5432/your_database"
export DB_USER="your_username"
export DB_PASS="your_password"
javac -d out Project3/*.java
java -cp "out:postgresql.jar" Project3.Main
```
 
Windows (PowerShell):
```powershell
$env:DB_URL  = "jdbc:postgresql://localhost:5432/your_database"
$env:DB_USER = "your_username"
$env:DB_PASS = "your_password"
javac -d out Project3/*.java
java -cp "out;postgresql.jar" Project3.Main
```
 
