import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// User Class
class User {
    String username;
    String role; 

    public User(String username, String role) {
        this.username = username;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public String getRole() {
        return role;
    }
}

// Course Class
class Course {
    String title;
    User teacher;
    List<User> students;

    public Course(String title, User teacher) {
        this.title = title;
        this.teacher = teacher;
        this.students = new ArrayList<>();
    }

    public void enrollStudent(User student) {
        students.add(student);
    }

    public void showEnrolledStudents() {
        System.out.println("Enrolled Students in " + title + ":");
        for (User student : students) {
            System.out.println(student.getUsername());
        }
    }
}

// Main Class: Learning Management System
public class LearningManagementSystem {
    static List<User> users = new ArrayList<>();
    static List<Course> courses = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Learning Management System ---");
            System.out.println("1. Create User");
            System.out.println("2. Create Course");
            System.out.println("3. Enroll in Course");
            System.out.println("4. View Course Enrollments");
            System.out.println("5. Exit");
            System.out.print("Choose an option: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    createUser(scanner);
                    break;
                case 2:
                    createCourse(scanner);
                    break;
                case 3:
                    enrollInCourse(scanner);
                    break;
                case 4:
                    viewCourseEnrollments(scanner);
                    break;
                case 5:
                    System.out.println("Exiting... Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    public static void createUser(Scanner scanner) {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter role (Student/Teacher): ");
        String role = scanner.nextLine();
        User user = new User(username, role);
        users.add(user);
        System.out.println("User created successfully.");
    }

    public static void createCourse(Scanner scanner) {
        System.out.print("Enter course title: ");
        String title = scanner.nextLine();
        System.out.print("Enter teacher username: ");
        String teacherUsername = scanner.nextLine();
        User teacher = findUserByUsername(teacherUsername);

        if (teacher != null && teacher.getRole().equalsIgnoreCase("Teacher")) {
            Course course = new Course(title, teacher);
            courses.add(course);
            System.out.println("Course created successfully.");
        } else {
            System.out.println("Invalid teacher username.");
        }
    }

    public static void enrollInCourse(Scanner scanner) {
        System.out.print("Enter course title: ");
        String courseTitle = scanner.nextLine();
        System.out.print("Enter student username: ");
        String studentUsername = scanner.nextLine();
        Course course = findCourseByTitle(courseTitle);
        User student = findUserByUsername(studentUsername);

        if (course != null && student != null && student.getRole().equalsIgnoreCase("Student")) {
            course.enrollStudent(student);
            System.out.println("Student enrolled successfully.");
        } else {
            System.out.println("Invalid course or student.");
        }
    }

    public static void viewCourseEnrollments(Scanner scanner) {
        System.out.print("Enter course title: ");
        String courseTitle = scanner.nextLine();
        Course course = findCourseByTitle(courseTitle);

        if (course != null) {
            course.showEnrolledStudents();
        } else {
            System.out.println("Course not found.");
        }
    }

    public static User findUserByUsername(String username) {
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                return user;
            }
        }
        return null;
    }

    public static Course findCourseByTitle(String title) {
        for (Course course : courses) {
            if (course.title.equals(title)) {
                return course;
            }
        }
        return null;
    }
}
