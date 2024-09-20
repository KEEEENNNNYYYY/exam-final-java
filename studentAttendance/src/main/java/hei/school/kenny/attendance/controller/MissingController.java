package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.MissingList;
import hei.school.kenny.attendance.model.MissingListRequest;
import hei.school.kenny.attendance.service.MissingService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
@RestController
public class MissingController {
    private final MissingService missingService;

    public MissingController(MissingService missingService) {
        this.missingService = missingService;
    }

    @GetMapping("/students/missing")
    public List<MissingList> showMissing(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name
    ) {

        if (date != null) {
            java.util.Date utilDate = java.sql.Date.valueOf(date);
            return missingService.getMissingListByDate(utilDate);
        } else if (id != null) {
            return missingService.getMissingListById(id);
        }else if (name != null) {
            return missingService.getMissingListBySubject(name);
        }
        else {
            throw new IllegalArgumentException("Either 'date' or 'id' or 'name'");
        }
    }
    @GetMapping("/students/missing/all")
    public List<MissingList> allMissingList(){
        return missingService.getAllMissingList();
    }

    @PostMapping("/students/missing/add")
    public void addMissing(@RequestBody MissingListRequest missingListRequest) {
        missingService.addMissingList(missingListRequest);
    }

    @PutMapping("/students/missing/change/course")
    public void updateMissingListBySubject(
            @RequestParam(value = "studentId", required = true) String studentId,
            @RequestParam(value = "oldCours", required = false) String oldCours,
            @RequestParam(value = "newCours", required = false) String newCours
    ) {
        missingService.updateMissingListBySubject(studentId, oldCours, newCours);
    }

    @PutMapping("/students/missing/change/date")
    public void updateMissingListByDate(
            @RequestParam(value = "studentId", required = true) String studentId,
            @RequestParam(value = "oldDate", required = false) String oldDate,
            @RequestParam(value = "newDate", required = false) String newDate
    ) {
        missingService.updateSubjectMissingDate(studentId, oldDate, newDate);
    }


    @PutMapping("/students/missing/justify")
    public void updateJustification(
            @RequestParam(value = "studentId", required = true) String studentId,
            @RequestParam(value = "subject", required = false) String subject,
            @RequestParam(value = "date", required = false) String date
    ) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date parsedDate = sdf.parse(date);
            missingService.justifyMissing(studentId, subject, parsedDate);
        } catch (ParseException e) {
            throw new RuntimeException("Invalid date format: " + e.getMessage());
        }
    }

    @DeleteMapping("/students/missing/del")
    public void updateMissingList(
            @RequestParam(value = "subject_id", required = true) String subjectId,
            @RequestParam(value = "student_id", required = true) String studentId,
            @RequestParam(value = "date", required = true) String date
    ) {
        missingService.deleteStudentOnMissingList(subjectId, studentId, date);
    }

}
