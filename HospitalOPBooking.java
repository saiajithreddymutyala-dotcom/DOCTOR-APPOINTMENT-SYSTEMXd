import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Random;

class Appointment {
    String patientName;
    String doctorName;
    String timeSlot;
    int tokenNumber;
    String paymentMethod;

    public Appointment(String patientName, String doctorName, String timeSlot, int tokenNumber, String paymentMethod) {
        this.patientName = patientName;
        this.doctorName = doctorName;
        this.timeSlot = timeSlot;
        this.tokenNumber = tokenNumber;
        this.paymentMethod = paymentMethod;
    }

    @Override
    public String toString() {
        return "Token: " + tokenNumber + " | Patient: " + patientName + " | Doctor: " + doctorName + " | Time: " + timeSlot + " | Payment: " + paymentMethod;
    }
}

class User {
    String username;
    String password;
    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }
}

public class HospitalOPBooking {
    
    // ==========================================
    // DATA STRUCTURES USED
    // ==========================================
    
    // DSA Concept: ArrayList used to store dynamic list of appointments (History)
    private static ArrayList<Appointment> appointmentHistory = new ArrayList<>();
    
    // DSA Concept: Queue (implemented using LinkedList) used to manage patient waiting order (FIFO)
    private static Queue<Appointment> patientQueue = new LinkedList<>();

    // ArrayList for users (Signup/Login)
    private static ArrayList<User> users = new ArrayList<>();
    
    // Track login status
    private static boolean isLoggedIn = false;
    private static String loggedInUser = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        System.out.println("==================================================");
        System.out.println("   Hospital OP Booking System (DSA Expo Demo)     ");
        System.out.println("==================================================");

        while (!exit) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1. Signup");
            System.out.println("2. Login");
            System.out.println("3. Book Appointment");
            System.out.println("4. View Appointment History");
            System.out.println("5. View Patient Queue");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = -1;
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // consume newline
            } else {
                scanner.nextLine();
            }

            switch (choice) {
                case 1:
                    signup(scanner);
                    break;
                case 2:
                    login(scanner);
                    break;
                case 3:
                    if (isLoggedIn) {
                        bookAppointment(scanner);
                    } else {
                        System.out.println("Please login first to book an appointment.");
                    }
                    break;
                case 4:
                    if (isLoggedIn) {
                        viewAppointmentHistory();
                    } else {
                        System.out.println("Please login first to view history.");
                    }
                    break;
                case 5:
                    if (isLoggedIn) {
                        viewPatientQueue();
                    } else {
                        System.out.println("Please login first to view queue.");
                    }
                    break;
                case 6:
                    exit = true;
                    System.out.println("Exiting the system. Stay Healthy!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void signup(Scanner scanner) {
        System.out.println("\n--- Signup ---");
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        
        // Add new user to ArrayList
        users.add(new User(username, password));
        System.out.println("Signup successful! You can now login.");
    }

    private static void login(Scanner scanner) {
        System.out.println("\n--- Login ---");
        if (isLoggedIn) {
            System.out.println("You are already logged in as " + loggedInUser);
            return;
        }
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        // DSA Concept / Algorithm: Linear Search to verify credentials
        boolean found = false;
        for (User user : users) {
            if (user.username.equals(username) && user.password.equals(password)) {
                found = true;
                break; // Stop searching once user is found
            }
        }

        if (found) {
            isLoggedIn = true;
            loggedInUser = username;
            System.out.println("Login successful! Welcome " + username);
        } else {
            System.out.println("Invalid username or password. Please try again.");
        }
    }

    private static void bookAppointment(Scanner scanner) {
        System.out.println("\n--- Book Appointment ---");
        System.out.print("Enter Patient Name: ");
        String patientName = scanner.nextLine();
        System.out.print("Enter Doctor Name: ");
        String doctorName = scanner.nextLine();
        System.out.print("Enter Time Slot (e.g., 10:00 AM): ");
        String timeSlot = scanner.nextLine();
        System.out.print("Enter Payment Method (Cash/Card/UPI): ");
        String paymentMethod = scanner.nextLine();

        // Algorithm: Generate Random Token Number
        Random rand = new Random();
        int tokenNumber = rand.nextInt(9000) + 1000; // Generates a 4-digit token

        Appointment newAppointment = new Appointment(patientName, doctorName, timeSlot, tokenNumber, paymentMethod);

        // ==========================================
        //  DSA OPERATIONS: INSERTION & ENQUEUE
        // ==========================================

        // DSA Operation: Insertion into ArrayList
        // Adds record to the end in O(1) amortized time complexity
        appointmentHistory.add(newAppointment);

        // DSA Operation: Queue Enqueue operation
        // Adds the patient to the end of the waiting queue in O(1) time complexity (FIFO principle)
        patientQueue.offer(newAppointment);

        System.out.println("Appointment booked successfully!");
        System.out.println("Your Token Number is: " + tokenNumber);
    }

    private static void viewAppointmentHistory() {
        System.out.println("\n--- Appointment History ---");
        if (appointmentHistory.isEmpty()) {
            System.out.println("No appointments booked yet.");
            return;
        }
        
        // ==========================================
        //  DSA OPERATION: TRAVERSAL
        // ==========================================
        
        // DSA Operation: Traversal of ArrayList (O(N) time complexity)
        for (Appointment app : appointmentHistory) {
            System.out.println(app.toString());
        }
        System.out.println("Total Appointments: " + appointmentHistory.size());
    }

    private static void viewPatientQueue() {
        System.out.println("\n--- Current Patient Queue (Waiting List) ---");
        if (patientQueue.isEmpty()) {
            System.out.println("The queue is empty. No patients waiting.");
            return;
        }

        // DSA Operation: Queue Traversal
        // We iterate through the queue without removing elements
        int position = 1;
        for (Appointment app : patientQueue) {
            System.out.println(position + ". " + app.patientName + " (Token: " + app.tokenNumber + ") - Doctor: " + app.doctorName);
            position++;
        }
    }
}
