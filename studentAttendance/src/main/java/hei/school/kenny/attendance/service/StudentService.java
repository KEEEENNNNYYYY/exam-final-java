package hei.school.kenny.attendance.service;

import java.util.List;
import hei.school.kenny.attendance.DAO.StudentDAO;
import hei.school.kenny.attendance.model.NewStudentRequest;
import hei.school.kenny.attendance.model.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentDAO studentDAO;

    public StudentService(StudentDAO studentDAO) {
        this.studentDAO = studentDAO;
    }

    public List<Student> getAllStudent() {
        return studentDAO.fetchAllStudent();
    }

    public List<Student> getStudentListByCor(boolean coredValue) {
        return studentDAO.fecthStudentListByCor(coredValue);
    }
    public Student getStudentById(String studentId) {
        return (studentDAO.getStudentById(studentId));
    }

    public List<Student> getStudentByGroupe(String studentGroupe) {
        return (studentDAO.getStudentByGroupe(studentGroupe));
    }

    public List<Student>getStudentByGrades(String studentGrades) {
        return (studentDAO.getStudentByGrades(studentGrades));
    }

    public void addStudent(NewStudentRequest newStudentRequest){studentDAO.addStudent(newStudentRequest);}
}
