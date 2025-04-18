-- Create the InventoryDB database
CREATE DATABASE IF NOT EXISTS InventoryDB;
USE InventoryDB;

-- Create the Product table
CREATE TABLE IF NOT EXISTS Product (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100),
    Price DECIMAL(10, 2),
    Quantity INT
);

-- Insert sample data into the Product table
INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (1, 'Laptop', 800.00, 10);
INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (2, 'Smartphone', 500.00, 15);
INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (3, 'Tablet', 250.00, 20);
