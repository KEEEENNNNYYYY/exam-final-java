package hei.school.kenny.attendance.service;

import java.util.List;
import hei.school.kenny.attendance.DAO.ShowStudentDAO;
import hei.school.kenny.attendance.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final ShowStudentDAO showStudentDAO;

    public StudentService(ShowStudentDAO showStudentDAO) {
        this.showStudentDAO = showStudentDAO;
    }

    public List<Student> getAllStudent() {
        return showStudentDAO.fetchAllStudent();
    }
}
