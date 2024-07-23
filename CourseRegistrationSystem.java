//Task - 3 

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Course {
    private String code;
    private String title;
    private String description;
    private int capacity;
    private String schedule;

    public Course(String code, String title, String description, int capacity, String schedule) {
        this.code = code;
        this.title = title;
        this.description = description;
        this.capacity = capacity;
        this.schedule = schedule;
    }

    public String getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getSchedule() {
        return schedule;
    }
}

class Student {
    private int studentID;
    private String name;
    private List<String> registeredCourses;

    public Student(int studentID, String name) {
        this.studentID = studentID;
        this.name = name;
        this.registeredCourses = new ArrayList<>();
    }

    public int getStudentID() {
        return studentID;
    }

    public String getName() {
        return name;
    }

    public List<String> getRegisteredCourses() {
        return registeredCourses;
    }

    public void registerCourse(String courseCode) {
        registeredCourses.add(courseCode);
    }

    public void dropCourse(String courseCode) {
        registeredCourses.remove(courseCode);
    }
}

public class CourseRegistrationSystem {
    private static List<Course> courses = new ArrayList<>();
    private static List<Student> students = new ArrayList<>();
    private static int studentIDCounter = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("Course Registration System");
            System.out.println("1. Add Course");
            System.out.println("2. List Courses");
            System.out.println("3. Register Student");
            System.out.println("4. List Students");
            System.out.println("5. Register Student for Course");
            System.out.println("6. Drop Course for Student");
            System.out.println("0. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    addCourse(scanner);
                    break;
                case 2:
                    listCourses();
                    break;
                case 3:
                    registerStudent(scanner);
                    break;
                case 4:
                    listStudents();
                    break;
                case 5:
                    registerStudentForCourse(scanner);
                    break;
                case 6:
                    dropCourseForStudent(scanner);
                    break;
            }
        } while (choice != 0);

        System.out.println("Thank you for using the Course Registration System.");
    }

    private static void addCourse(Scanner scanner) {
        System.out.print("Enter course code: ");
        String code = scanner.next();
        System.out.print("Enter course title: ");
        String title = scanner.next();
        System.out.print("Enter course description: ");
        String description = scanner.next();
        System.out.print("Enter course capacity: ");
        int capacity = scanner.nextInt();
        System.out.print("Enter course schedule: ");
        String schedule = scanner.next();

        Course course = new Course(code, title, description, capacity, schedule);
        courses.add(course);

        System.out.println("Course added successfully.");
    }

    private static void listCourses() {
        System.out.println("Available Courses:");
        for (Course course : courses) {
            int availableSlots = course.getCapacity() - getStudentCountForCourse(course.getCode());
            System.out.println("Code: " + course.getCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Capacity: " + course.getCapacity());
            System.out.println("Available Slots: " + availableSlots);
            System.out.println();
        }
    }

    private static void registerStudent(Scanner scanner) {
        System.out.print("Enter student name: ");
        String name = scanner.next();
        Student student = new Student(studentIDCounter, name);
        students.add(student);
        studentIDCounter++;

        System.out.println("Student registered successfully.");
    }

    private static void listStudents() {
        System.out.println("Registered Students:");
        for (Student student : students) {
            System.out.println("Student ID: " + student.getStudentID());
            System.out.println("Name: " + student.getName());
            System.out.println("Registered Courses: " + student.getRegisteredCourses());
            System.out.println();
        }
    }

    private static void registerStudentForCourse(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        System.out.print("Enter course code: ");
        String courseCode = scanner.next();

        Student student = getStudentByID(studentID);
        Course course = getCourseByCode(courseCode);

        if (student != null && course != null) {
            int availableSlots = course.getCapacity() - getStudentCountForCourse(course.getCode());
            if (availableSlots > 0) {
                student.registerCourse(courseCode);
                System.out.println("Student " + student.getName() + " registered for course " + course.getTitle());
            } else {
                System.out.println("No available slots for the course.");
            }
        } else {
            System.out.println("Student or course not found.");
        }
    }

    private static void dropCourseForStudent(Scanner scanner) {
        System.out.print("Enter student ID: ");
        int studentID = scanner.nextInt();
        System.out.print("Enter course code: ");
        String courseCode = scanner.next();

        Student student = getStudentByID(studentID);

        if (student != null) {
            student.dropCourse(courseCode);
            System.out.println("Student " + student.getName() + " dropped course " + courseCode);
        } else {
            System.out.println("Student not found.");
        }
    }

    private static Student getStudentByID(int studentID) {
        for (Student student : students) {
            if (student.getStudentID() == studentID) {
                return student;
            }
        }
        return null;
    }

    private static Course getCourseByCode(String courseCode) {
        for (Course course : courses) {
            if (course.getCode().equals(courseCode)) {
                return course;
            }
        }
        return null;
    }

    private static int getStudentCountForCourse(String courseCode) {
        int count = 0;
        for (Student student : students) {
            if (student.getRegisteredCourses().contains(courseCode)) {
                count++;
            }
        }
        return count;
    }
}