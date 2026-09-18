package platewise.model;

public class Attendance {

    private int attendanceId;
    private Student student;
    private Meal meal;
    private boolean attended;

    public Attendance(int attendanceId, Student student,
                      Meal meal, boolean attended) {

        this.attendanceId = attendanceId;
        this.student = student;
        this.meal = meal;
        this.attended = attended;
    }

    public int getAttendanceId() {
        return attendanceId;
    }

    public Student getStudent() {
        return student;
    }

    public Meal getMeal() {
        return meal;
    }

    public boolean isAttended() {
        return attended;
    }

    public void setAttended(boolean attended) {
        this.attended = attended;
    }

    public void displayAttendance() {
        System.out.println("Attendance ID: " + attendanceId);
        System.out.println("Student: " + student.getName());
        System.out.println("Meal: " + meal.getMealType());
        System.out.println("Date: " + meal.getDate());
        System.out.println("Status: " + (attended ? "Present" : "Absent"));
    }
}