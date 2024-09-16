package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.MissingList;
import hei.school.kenny.attendance.service.MissingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class ShowMissingController {
    private final MissingService missingService;

    public ShowMissingController(MissingService missingService) {
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
}
