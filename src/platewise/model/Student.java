package platewise.model;

public class Student extends User {

    private String course;
    private int semester;

    public Student(int userId, String name, String email,
                   String course, int semester) {

        super(userId, name, email);
        this.course = course;
        this.semester = semester;
    }

    public String getCourse() {
        return course;
    }

    public int getSemester() {
        return semester;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    @Override
    public void displayMenu() {
        System.out.println();
        System.out.println("========== STUDENT PORTAL ==========");
        System.out.println("Welcome, " + getName());
        System.out.println("1. Mark Meal Attendance");
        System.out.println("2. View Meal History");
        System.out.println("3. View Profile");
        System.out.println("4. Exit");
        System.out.println("====================================");
    }

    public void displayStudentInfo() {
        displayUserInfo();
        System.out.println("Course: " + course);
        System.out.println("Semester: " + semester);
    }
}
