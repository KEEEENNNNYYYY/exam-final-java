package hei.school.kenny.attendance.model;

public class Subject {
    private String name;
    private int totalHours;
    private String teacher;

    public Subject() {
        this.name = name;
        this.teacher = teacher;
        this.totalHours = totalHours;
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
