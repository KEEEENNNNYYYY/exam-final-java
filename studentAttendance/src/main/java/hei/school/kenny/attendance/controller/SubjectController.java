package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.NewSubjectRequest;
import hei.school.kenny.attendance.service.SubjectService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/subject/add")
    public void addSubject(@RequestBody NewSubjectRequest newSubjectRequest) {
        subjectService.addSubject(newSubjectRequest);
    }
}
