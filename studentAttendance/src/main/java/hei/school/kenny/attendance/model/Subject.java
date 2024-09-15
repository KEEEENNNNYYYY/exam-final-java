package hei.school.kenny.attendance.model;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject {
    private String name;
    @JsonInclude
    private int totalHours;
    private String teacher;
    @JsonInclude
    private int id;

    public Subject() {
        this.id = id;
        this.name = name;
        this.teacher = teacher;
        this.totalHours = totalHours;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
