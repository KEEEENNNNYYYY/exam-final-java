package hei.school.kenny.attendance.model;

import java.util.Date;
import java.util.List;

public class MissingList {
    private Date date;
    private int id;
    private List<Student> missingStudent;
    private List<Subject> subjectMissed;

    public MissingList() {
        this.date = date;
        this.id = id;
        this.missingStudent = missingStudent;
        this.subjectMissed = subjectMissed;
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

    public List<Student> getMissingStudent() {
        return missingStudent;
    }

    public void setMissingStudent(List<Student> missingStudent) {
        this.missingStudent = missingStudent;
    }

    public List<Subject> getSubjectMissed() {
        return subjectMissed;
    }

    public void setSubjectMissed(List<Subject> subjectMissed) {
        this.subjectMissed = subjectMissed;
    }
}
