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
    public List<MissingList> showAllMissing(@RequestParam("date") String date) {
        java.util.Date utilDate = java.sql.Date.valueOf(date);
        return missingService.getMissingListByDate(utilDate);
    }
}
