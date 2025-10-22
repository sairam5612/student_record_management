package com.SRM;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Optional; // Added for cleaner Stream use

/**
 * Main application class for the Student Record Management System (CRUD).
 * Implements the core logic as required by Task 2.
 */
public class StudRecManagement {

    /**
     * Corrected Student class for better Encapsulation (Task Requirement).
     * Fields are private, and public Getters/Setters are used.
     */
    private static class Student {
        // Fields made private for encapsulation
        private String id;
        private String name;
        private int marks;
        private String examStatus;

        // Constructor to initialize essential fields when creating an object
        public Student(String id, String name, int marks) {
            this.id = id;
            this.name = name;
            this.marks = marks;
        }

        // --- Getters ---
        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public int getMarks() {
            return marks;
        }

        public String getExamStatus() {
            return examStatus;
        }

        // --- Setters (for update operations) ---
        public void setName(String name) {
            this.name = name;
        }

        public void setMarks(int marks) {
            this.marks = marks;
        }

        public void setExamStatus(String examStatus) {
            this.examStatus = examStatus;
        }
    }

    // Static list to store all student records
    private static List<Student> studentRecords = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("--- Student Record Management System ---");
        System.out.println("1. User can add a student record");
        System.out.println("2. User can delete a student by ID");
        System.out.println("3. User can view a student record by ID");
        System.out.println("4. User can update a student's details by ID");
        System.out.println("\nWrite 'stop' to exit the application.");
        System.out.println("----------------------------------------");

        while (true) {
            try {
                System.out.print("\nPlease enter your choice: ");
                String ch = sc.next();

                if (ch.equalsIgnoreCase("stop")) {
                    System.out.println("Application has been stopped...");
                    break;
                }

                switch (ch) {
                    case "1":
                        System.out.println("\n-- Adding student record --");
                        System.out.print("Enter Student ID: ");
                        String id = sc.next();
                        System.out.print("Enter Student Name: ");
                        String name = sc.next();
                        System.out.print("Enter Student Marks: ");
                        int marks = sc.nextInt();

                        // Use the constructor to create the object cleanly
                        Student newRecord = new Student(id, name, marks);
                        add(newRecord);
                        break;

                    case "2":
                        System.out.println("\n-- Deleting student record --");
                        System.out.print("Enter Student ID to delete: ");
                        String stud_id = sc.next();
                        System.out.println(delete(stud_id));
                        break;

                    case "3":
                        System.out.println("\n-- Viewing student record --");
                        System.out.print("Enter Student ID: ");
                        String StudId = sc.next();
                        Student found_record = find(StudId);

                        // Fix: Corrected the logic to say "does not exist"
                        if (found_record == null) {
                            System.out.println("Student with ID " + StudId + " does not exist in the list.");
                        } else {
                            System.out.println("\n--- Student Details ---");
                            System.out.println("Student ID: " + found_record.getId());
                            System.out.println("Student Name: " + found_record.getName());
                            System.out.println("Student Marks: " + found_record.getMarks());
                            System.out.println("Student Exam Status: " + found_record.getExamStatus());
                            System.out.println("-----------------------");
                        }
                        break;

                    case "4":
                        System.out.println("\n-- Updating student record --");
                        System.out.print("Enter Student ID to update: ");
                        String sid = sc.next();
                        Student found = find(sid);

                        // Fix: Corrected the logic to say "does not exist"
                        if (found == null) {
                            System.out.println("Student with ID " + sid + " does not exist in the list.");
                        } else {
                            System.out.print("Enter new name (current: " + found.getName() + "): ");
                            String n = sc.next();
                            System.out.print("Enter new marks (current: " + found.getMarks() + "): ");
                            int m = sc.nextInt();
                            System.out.println(update(found, n, m));
                        }
                        break;

                    default:
                        System.out.println("Please enter a valid choice (1, 2, 3, 4, or 'stop').");
                        break;
                }
            } catch (java.util.InputMismatchException e) {
                // Catch error if user enters text where an integer (marks) is expected
                System.out.println("\nError: Invalid input type. Please enter numbers for marks.");
                sc.next(); // Consume the invalid input to prevent infinite loop
            }
        }
        sc.close();
    }

    /**
     * Finds a student record by ID using Java Streams.
     * * @param id The ID to search for.
     * @return The Student object if found, otherwise null.
     */
    public static Student find(String id) {
        return studentRecords.stream()
                .filter(student -> student.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    /**
     * Adds a new student record after validation checks.
     * Includes checks for null/empty name and duplicate ID.
     * * @param record The Student object to add.
     */
    public static void add(Student record) {
        // Validation 1: Check if Name is null or empty
        if (record.getName() == null || record.getName().trim().isEmpty()) {
            System.out.println("Student Name is required. Please add the name field.");
            return;
        }

        // Validation 2: Check for duplicate ID
        Student foundStudent = find(record.getId());
        if (foundStudent != null) {
            System.out.println("A student with ID " + foundStudent.getId() + " already exists. Please use a unique ID.");
            return;
        }

        // Calculate exam status using the efficient ternary operator
        record.setExamStatus(record.getMarks() > 35 ? "Pass" : "Fail");

        // Add the record to the ArrayList
        studentRecords.add(record);
        System.out.println("Student record added successfully! Status: " + record.getExamStatus());
    }

    /**
     * Deletes a student record by ID.
     * * @param id The ID of the student to delete.
     * @return A status message.
     */
    public static String delete(String id) {
        // Use Streams to find the record (alternative to your for loop)
        Optional<Student> recordToDelete = studentRecords.stream()
                .filter(rec -> rec.getId().equals(id))
                .findFirst();

        if (!recordToDelete.isPresent()) {
            return "Student with ID " + id + " is not found in the list.";
        }

        studentRecords.remove(recordToDelete.get());
        return "Student with ID " + id + " is deleted successfully!.";
    }

    /**
     * Updates the name and marks of an existing student record.
     * * @param found The existing Student object to update.
     * @param name The new name.
     * @param marks The new marks.
     * @return A status message.
     */
    public static String update(Student found, String name, int marks) {
        // Update fields using setters (Encapsulation)
        found.setName(name);
        found.setMarks(marks);

        // Recalculate exam status based on new marks
        found.setExamStatus(found.getMarks() > 35 ? "Pass" : "Fail");

        // The record is already in the studentRecords list (since it was passed as 'found'),
        // so we don't need to remove and re-add it. Just updating the object reference is enough.

        return "Student records for ID " + found.getId() + " updated successfully! New Status: " + found.getExamStatus();
    }
}
