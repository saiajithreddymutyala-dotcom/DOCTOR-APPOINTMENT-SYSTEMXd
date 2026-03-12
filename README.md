# Hospital OP Booking System using DSA

## Project Title
Hospital OP Booking System (Console-based Java Application)

## Project Description
This is a simple, beginner-friendly console-based Java application designed for a Data Structures and Algorithms (DSA) expo demonstration. It simulates an Outpatient (OP) booking system where users can sign up, log in, and book appointments with doctors. The system effectively manages patient records and their waiting order.

## Data Structures Used
1.  **ArrayList**: 
    *   Used to store user accounts (signup data).
    *   Used to store the `appointmentHistory`, maintaining a dynamic and ordered list of all booked appointments. It allows efficient amortized $O(1)$ insertion at the end and $O(N)$ traversal.
2.  **Queue (Implemented via LinkedList)**:
    *   Used to manage the `patientQueue` (waiting order). This perfectly models a real-world waiting line using the FIFO (First-In-First-Out) principle. Adding a patient (enqueue) and serving them (dequeue) happens in $O(1)$ time complexity.

## Algorithms Used
*   **Linear Search**: Used during the login process to search through the ArrayList of registered users to verify credentials.
*   **Random Number Generation**: Used to generate unique 4-digit token numbers for each booked appointment.
*   **Traversal**: Used to iterate through the ArrayList and Queue to display the appointment history and the current patient waiting list ($O(N)$ time complexity).

## Features
*   **User Authentication**: Simple Signup and Login functionality.
*   **Book Appointment**: Capture patient details, doctor name, time slot, and payment method.
*   **Auto Token Generation**: Automatically assigns a random 4-digit token number to new appointments.
*   **View History**: Displays all the appointments made in the session.
*   **View Patient Queue**: Displays the current waiting list of patients in real-time order.

## How to Run the Program
1.  Ensure you have **Java** installed on your system.
2.  Save the provided code in a file named `HospitalOPBooking.java`.
3.  Open a terminal or command prompt.
4.  Navigate to the directory where the files are saved.
5.  Compile the Java file using the command:
    ```bash
    javac HospitalOPBooking.java
    ```
6.  Run the compiled program using the command:
    ```bash
    java HospitalOPBooking
    ```
7.  Follow the on-screen menu to interact with the system!
