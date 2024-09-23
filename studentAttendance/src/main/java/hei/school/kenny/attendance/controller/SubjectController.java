package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.NewSubjectRequest;
import hei.school.kenny.attendance.model.Student;
import hei.school.kenny.attendance.model.Subject;
import hei.school.kenny.attendance.service.SubjectService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/subject/all")
    public List<Subject> showAllSubject() {
        return subjectService.getAllSubjects();
    }

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/subject/add")
    public void addSubject(@RequestBody NewSubjectRequest newSubjectRequest) {
        subjectService.addSubject(newSubjectRequest);
    }
}
