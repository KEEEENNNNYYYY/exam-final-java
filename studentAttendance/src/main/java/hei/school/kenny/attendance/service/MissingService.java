package hei.school.kenny.attendance.service;

import hei.school.kenny.attendance.DAO.MissingListDAO;
import hei.school.kenny.attendance.model.MissingList;
import org.springframework.stereotype.Service;
import hei.school.kenny.attendance.model.MissingListRequest;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class MissingService {
    private final MissingListDAO missingListDAO;

    public MissingService(MissingListDAO missingListDAO) {
        this.missingListDAO = missingListDAO;
    }

    public List<MissingList> getAllMissingList() {
        return missingListDAO.getAllMissingList();
    }

    public List<MissingList> getMissingListByDate(Date date) {
        return missingListDAO.getMissingListByDate(date);
    }

    public List<MissingList> getMissingListById(String id) {
        return missingListDAO.getMissingListById(id);
    }

    public List<MissingList> getMissingListBySubject(String name) {
        return missingListDAO. getMissingListBySubject(name);
    }

    public void addMissingList(MissingListRequest missingListRequest) {
        missingListDAO.addMissingList(missingListRequest);
    }

    public void updateMissingListBySubject(String studentId, String oldCours, String newCours, String date) {
        missingListDAO.updateMissingListBySubject( studentId,  oldCours,  newCours,  date);
    }

    public void updateSubjectMissingDate(String studentId, String oldDate, String newDate) {
        missingListDAO.updateSubjectMissingDate(studentId, oldDate, newDate);
    }

    public void justifyMissing(String studentId, String subject, Date date) {
        missingListDAO.justifyMissing(studentId, subject, date);
    }

    public void deleteStudentOnMissingList(String subjectId, String studentId, String date) {
        missingListDAO.deleteStudentOnMissingList(subjectId, studentId, date);
    }

}
