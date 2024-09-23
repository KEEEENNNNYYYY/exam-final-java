package hei.school.kenny.attendance.service;

import hei.school.kenny.attendance.DAO.SubjectDAO;
import hei.school.kenny.attendance.model.NewSubjectRequest;
import hei.school.kenny.attendance.model.Student;
import hei.school.kenny.attendance.model.Subject;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubjectService {
    private final SubjectDAO subjectDAO;

    public SubjectService(SubjectDAO subjectDAO) {
        this.subjectDAO = subjectDAO;
    }

    public void  addSubject(NewSubjectRequest newSubjectRequest){
        subjectDAO.addSubject(newSubjectRequest);
    }

    public List<Subject> getAllSubjects() {
        return subjectDAO.fetchAllSubjects();
    }
}
