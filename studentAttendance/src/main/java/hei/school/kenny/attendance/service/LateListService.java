package hei.school.kenny.attendance.service;

import hei.school.kenny.attendance.DAO.LateListDAO;
import hei.school.kenny.attendance.model.LateList;
import hei.school.kenny.attendance.model.LateListRequest;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class LateListService {
    private final LateListDAO LateListDAO;

    public LateListService(LateListDAO LateListDAO) {
        this.LateListDAO = LateListDAO;
    }

    public List<LateList> getAllLateList() {
        return LateListDAO.getAllLateList();
    }

    public List<LateList> getLateListByDate(Date date) {
        return LateListDAO.getLateListByDate(date);
    }

    public List<LateList> getLateListByStudentId(String id) {
        return LateListDAO.getLateListByStudentId(id);
    }

    public List<LateList> getLateListBySubject(String name) {
        return LateListDAO. getLateListBySubject(name);
    }

    public void addLateList(List<LateListRequest> lateListRequests) {
        LateListDAO.addLateList(lateListRequests);
    }

    public void updateLateListBySubject(String studentId, String oldSubject, String newSubject) {
        LateListDAO.updateSubjectLateList(studentId, oldSubject, newSubject);
    }
}
