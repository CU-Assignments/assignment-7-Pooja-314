CREATE DATABASE EmployeeDB;

USE EmployeeDB;

CREATE TABLE Employee (
    EmpID INT PRIMARY KEY,
    Name VARCHAR(100),
    Salary DECIMAL(10, 2)
);

INSERT INTO Employee VALUES (1, 'John Doe', 5000);
INSERT INTO Employee VALUES (2, 'Jane Smith', 6000);
INSERT INTO Employee VALUES (3, 'Mike Johnson', 4500);
