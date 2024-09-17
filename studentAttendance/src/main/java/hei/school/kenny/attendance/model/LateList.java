package hei.school.kenny.attendance.model;

import java.util.Date;
import java.util.List;

public class LateList {
    private Date date;
    private int id;
    private List<Student> lateStudent;
    private List<Subject> subjectLate;

    public LateList() {
    }

    public LateList(Date date, int id, List<Student> lateStudent, List<Subject> subjectLate) {
        this.date = date;
        this.id = id;
        this.lateStudent = lateStudent;
        this.subjectLate = subjectLate;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Student> getLateStudent() {
        return lateStudent;
    }

    public void setLateStudent(List<Student> lateStudent) {
        this.lateStudent = lateStudent;
    }

    public List<Subject> getSubjectLate() {
        return subjectLate;
    }

    public void setSubjectLate(List<Subject> subjectLate) {
        this.subjectLate = subjectLate;
    }
}
