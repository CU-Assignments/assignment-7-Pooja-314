-- Create the StudentDB database
CREATE DATABASE IF NOT EXISTS StudentDB;
USE StudentDB;

-- Create the Student table
CREATE TABLE IF NOT EXISTS Student (
    StudentID INT PRIMARY KEY,
    Name VARCHAR(100),
    Department VARCHAR(50),
    Marks DECIMAL(5, 2)
);

-- Insert some sample data
INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (1, 'John Doe', 'Computer Science', 85.50);
INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (2, 'Jane Smith', 'Mathematics', 90.00);
INSERT INTO Student (StudentID, Name, Department, Marks) VALUES (3, 'Michael Johnson', 'Physics', 78.75);
