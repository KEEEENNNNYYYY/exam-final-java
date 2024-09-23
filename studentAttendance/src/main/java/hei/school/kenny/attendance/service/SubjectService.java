package hei.school.kenny.attendance.service;

import hei.school.kenny.attendance.DAO.SubjectDAO;
import hei.school.kenny.attendance.model.NewSubjectRequest;
import hei.school.kenny.attendance.model.Subject;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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

    public Subject  getSubjectById(String subjectId)  {
        return (subjectDAO. getSubjectById(subjectId) );
    }

    public void updateState(String value,String id) {
        subjectDAO.updateState( value, id);
    }
}
