package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.LateList;
import hei.school.kenny.attendance.model.LateListRequest;
import hei.school.kenny.attendance.service.LateListService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class LateController {
    private final LateListService lateListService;

    public LateController(LateListService lateListService) {
        this.lateListService = lateListService;
    }

    @GetMapping("/students/late")
    public List<LateList> showLate(
            @RequestParam(value = "date", required = false) String date,
            @RequestParam(value = "id", required = false) String id,
            @RequestParam(value = "name", required = false) String name
    ) {

        if (date != null) {
            java.util.Date utilDate = java.sql.Date.valueOf(date);
            return lateListService.getLateListByDate(utilDate);
        } else if (id != null) {
            return lateListService.getLateListByStudentId(id);
        }else if (name != null) {
            return lateListService.getLateListBySubject(name);
        }
        else {
            throw new IllegalArgumentException("Either 'date' or 'id' or 'name'");
        }
    }
    @GetMapping("/students/late/all")
    public List<LateList> allMissingList(){
        return lateListService.getAllLateList();
    }

    @PostMapping("/students/late/add")
    public void addMissing(@RequestBody  List<LateListRequest> lateListRequest) {
        lateListService.addLateList(lateListRequest);
    }

    @PutMapping("/students/missing")
    public void updateMissingList(
            @RequestParam(value = "studentId", required = true) String studentId,
            @RequestParam(value = "oldValue", required = false) String oldValue,
            @RequestParam(value = "newValue", required = false) String newValue
    ) {
        lateListService.updateLateListBySubject(studentId, oldValue, newValue);
    }
}
