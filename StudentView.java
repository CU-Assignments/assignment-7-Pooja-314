import java.util.List;
import java.util.Scanner;

public class StudentView {

    private static final Scanner scanner = new Scanner(System.in);
    private final StudentController controller;

    public StudentView(StudentController controller) {
        this.controller = controller;
    }

    // Display the menu
    public void displayMenu() {
        boolean exit = false;
        while (!exit) {
            System.out.println("\nStudent Management System");
            System.out.println("1. Create Student");
            System.out.println("2. View Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (choice) {
                case 1:
                    createStudent();
                    break;
                case 2:
                    viewStudents();
                    break;
                case 3:
                    updateStudent();
                    break;
                case 4:
                    deleteStudent();
                    break;
                case 5:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
    }

    // Create a student
    private void createStudent() {
        System.out.print("Enter Student ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character
        System.out.print("Enter Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter Marks: ");
        double marks = scanner.nextDouble();

        Student student = new Student(id, name, department, marks);
        controller.createStudent(student);
        System.out.println("Student created successfully.");
    }

    // View all students
    private void viewStudents() {
        List<Student> students = controller.readStudents();
        System.out.println("\nStudentID | Name       | Department | Marks");
        System.out.println("----------------------------------------------");
        for (Student student : students) {
            System.out.println(student.getStudentID() + " | " + student.getName() + " | " + student.getDepartment() + " | " + student.getMarks());
        }
    }

    // Update a student
    private void updateStudent() {
        System.out.print("Enter Student ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        System.out.print("Enter new Name: ");
        String name = scanner.nextLine();
        System.out.print("Enter new Department: ");
        String department = scanner.nextLine();
        System.out.print("Enter new Marks: ");
        double marks = scanner.nextDouble();

        Student student = new Student(id, name, department, marks);
        controller.updateStudent(student);
        System.out.println("Student updated successfully.");
    }

    // Delete a student
    private void deleteStudent() {
        System.out.print("Enter Student ID to delete: ");
        int id = scanner.nextInt();
        controller.deleteStudent(id);
        System.out.println("Student deleted successfully.");
    }
}
