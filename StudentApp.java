public class StudentApp {
    public static void main(String[] args) {
        // Create the controller and view
        StudentController controller = new StudentController();
        StudentView view = new StudentView(controller);

        // Display the menu and start the application
        view.displayMenu();
    }
}
