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
@RequestMapping("/missing")
public class MissingController {
    private final MissingService missingService;

    public MissingController(MissingService missingService) {
        this.missingService = missingService;
    }

    @GetMapping("/date/{date}")
    public List<MissingList> showMissingByDate(@PathVariable(value = "date") String date) {
        java.util.Date utilDate = java.sql.Date.valueOf(date);
        return missingService.getMissingListByDate(utilDate);
    }

    @GetMapping("/id/{id}")
    public List<MissingList> showMissingById(@PathVariable(value = "id") String id) {
        return missingService.getMissingListById(id);
    }

    @GetMapping("/name/{name}")
    public List<MissingList> showMissingBySubject(@PathVariable(value = "name") String name) {
        return missingService.getMissingListBySubject(name);
    }

    @GetMapping("/all")
    public List<MissingList> allMissingList(){
        return missingService.getAllMissingList();
    }

    @PostMapping("/add")
    public void addMissing(@RequestBody MissingListRequest missingListRequest) {
        missingService.addMissingList(missingListRequest);
    }

    @PutMapping("/change/course")
    public void updateMissingListBySubject(
            @RequestParam(value = "studentId", required = true) String studentId,
            @RequestParam(value = "oldCours", required = true) String oldCours,
            @RequestParam(value = "newCours", required = true) String newCours,
            @RequestParam(value = "date", required = true) String date
    ) {
        missingService.updateMissingListBySubject(studentId, oldCours, newCours, date);
    }


    @PutMapping("/change/date")
    public void updateMissingListByDate(
            @RequestParam(value = "studentId", required = true) String studentId,
            @RequestParam(value = "oldDate", required = false) String oldDate,
            @RequestParam(value = "newDate", required = false) String newDate
    ) {
        missingService.updateSubjectMissingDate(studentId, oldDate, newDate);
    }


    @PutMapping("/justify")
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

    @DeleteMapping("/del")
    public void updateMissingList(
            @RequestParam(value = "subject_id", required = true) String subjectId,
            @RequestParam(value = "student_id", required = true) String studentId,
            @RequestParam(value = "date", required = true) String date
    ) {
        missingService.deleteStudentOnMissingList(subjectId, studentId, date);
    }

}
