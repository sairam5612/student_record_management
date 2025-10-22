# Student Record Management System (SRM) - Task 2

## 🎯 Objective
This project fulfills **Task 2: Student Record Management System** for the Elevate Labs Java Developer Internship. The goal was to build a Command Line Interface (CLI) application capable of performing **CRUD** (Create, Read, Update, Delete) operations on student records.

## ✨ Key Concepts Implemented

* **Classes and Encapsulation:** A `Student` class was created with private fields (`id`, `name`, `marks`) and public **Getters and Setters** to control data access.
* **Collections:** An **`ArrayList`** is used as the data structure to store and manage `Student` objects dynamically.
* **CRUD Operations:** Implemented methods to **Add**, **View**, **Update**, and **Delete** student records by ID.
* **I/O and Control Flow:** Utilizes the `Scanner` for console input and a `while` loop for continuous menu interaction.

## 💻 Features and Menu Options

The application is menu-driven and allows the user to perform the following actions:

| Choice | Operation | Description |
| :---: | :--- | :--- |
| **1** | Add Record | Creates a new student record (ID, Name, Marks) and validates the result (Pass/Fail). |
| **2** | Delete Record | Removes a student record after searching by their unique ID. |
| **3** | View Record | Finds and displays the complete details of a single student by ID. |
| **4** | Update Record | Modifies the Name and Marks of an existing student record by ID. |
| **stop** | Exit | Terminates the application. |

## 🚀 How to Run the Application

1.  **Prerequisites:** Ensure the **Java Development Kit (JDK)** is installed.
2.  **Compilation:** Navigate to the directory that contains the `com` folder and compile the main class:
    ```bash
    javac com/SRM/StudRecManagement.java
    ```
3.  **Execution:** Run the compiled class file from the parent directory:
    ```bash
    java com/SRM/StudRecManagement
    ```
