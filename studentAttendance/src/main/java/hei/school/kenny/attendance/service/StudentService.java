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

    public void updateState(String value,String id) {
        studentDAO.updateState( value, id);
    }

    public void updateFirstName(String firstName,String id) {
        studentDAO.updateFirstName( firstName, id);
    }

    public void updateLastName(String lastName,String id) {
        studentDAO.updateLastName( lastName, id);
    }

    public void updateEmail(String newEmail,String id) {
        studentDAO.updateEmail( newEmail, id);
    }

    public void updateGrades(String newGrades,String id)  {
        studentDAO.updateGrades( newGrades,id) ;
    }

    public void  updateGroupes(String newGroupes,String id)   {
        studentDAO.updateGroupes( newGroupes, id)  ;
    }

    public void  updateBirthday(String newBirthday, String id)   {
        studentDAO.updateBirthday( newBirthday,id) ;
    }

    public void addStudent(NewStudentRequest newStudentRequest){studentDAO.addStudent(newStudentRequest);}
}
