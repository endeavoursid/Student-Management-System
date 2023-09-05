import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class StudentManagementApp {
    private static final String DATA_FILE = "students.txt";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        StudentManagementSystem system = new StudentManagementSystem();

        loadStudentData(system);

        while (true) {
            System.out.println("Student Management System");
            System.out.println("1. Add Student");
            System.out.println("2. Remove Student");
            System.out.println("3. Search Student");
            System.out.println("4. Display All Students");
            System.out.println("5. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                   
                    System.out.print("Enter name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter roll number: ");
                    int rollNumber = scanner.nextInt();
                    scanner.nextLine(); 
                    System.out.print("Enter grade: ");
                    String grade = scanner.nextLine();

                    Student student = new Student(name, rollNumber, grade);
                    system.addStudent(student);

                    saveStudentData(system);
                    break;
                case 2:
        
                    System.out.print("Enter roll number to remove: ");
                    int rollToRemove = scanner.nextInt();
                    scanner.nextLine(); 

                    Student studentToRemove = system.searchStudent(rollToRemove);
                    if (studentToRemove != null) {
                        system.removeStudent(studentToRemove);
                        System.out.println("Student removed.");

                        
                        saveStudentData(system);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 3:
                    
                    System.out.print("Enter roll number to search: ");
                    int rollToSearch = scanner.nextInt();
                    scanner.nextLine(); 

                    Student foundStudent = system.searchStudent(rollToSearch);
                    if (foundStudent != null) {
                        System.out.println("Student found: " + foundStudent);
                    } else {
                        System.out.println("Student not found.");
                    }
                    break;
                case 4:
                   
                    system.displayAllStudents();
                    break;
                case 5:
                    
                    System.out.println("Exiting the program.");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void loadStudentData(StudentManagementSystem system) {
        try (Scanner fileScanner = new Scanner(new File(DATA_FILE))) {
            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    int rollNumber = Integer.parseInt(parts[1].trim());
                    String grade = parts[2].trim();
                    Student student = new Student(name, rollNumber, grade);
                    system.addStudent(student);
                }
            }
        } catch (FileNotFoundException e) {
            
        }
    }

    private static void saveStudentData(StudentManagementSystem system) {
        try (PrintWriter writer = new PrintWriter(DATA_FILE)) {
            for (Student student : system.getStudents()) {
                writer.println(student.getName() + "," + student.getRollNumber() + "," + student.getGrade());
            }
        } catch (FileNotFoundException e) {
            
            System.err.println("Error saving student data: " + e.getMessage());
        }
    }
}



