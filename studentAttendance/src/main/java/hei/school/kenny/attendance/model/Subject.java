package hei.school.kenny.attendance.model;

public class Subject {
    private String name;
    private int totalHours;
    private String teacher;
    private State state;

    public Subject() {
        this.name = name;
        this.state = State.OnGoing;
        this.teacher = teacher;
        this.totalHours = totalHours;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(int totalHours) {
        this.totalHours = totalHours;
    }
}
