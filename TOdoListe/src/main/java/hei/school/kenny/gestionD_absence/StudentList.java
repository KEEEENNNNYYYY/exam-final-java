package hei.school.kenny.gestionD_absence;

import java.util.ArrayList;
import java.util.List;

public class StudentList {
    private List<Student> list;

    protected StudentList() {
        this.list = new ArrayList<Student>();
    }

    public List<Student> addToList(Student s) {
        list.add(s);
        return list;
    }

    public Student findStudentByID(String ID) {
        for (Student student : list) {
            if (student.getID().equals(ID)) {
                return student;
            }
        }
        return null;
    }

    public List<Student> getList() {
        return list;
    }
}
