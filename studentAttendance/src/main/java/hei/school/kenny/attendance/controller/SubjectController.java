package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.NewSubjectRequest;
import hei.school.kenny.attendance.model.Subject;
import hei.school.kenny.attendance.service.SubjectService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/subject/search")
    public Subject showById(
            @RequestParam(value = "name", required = false) String name
    ) {
        return subjectService.getSubjectById(name);
    }

    @GetMapping("/subject")
    public List<Subject> showByTeacher(
            @RequestParam(value = "teacher", required = false) String teacher
    ) {
        return subjectService.getSubjectByTeacher(teacher);
    }

    @PutMapping("/subject/state")
    public void updateState(
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "id", required = true) String id
    ) {
            subjectService.updateState( value, id);
    }

    @PutMapping("/subject/name")
    public void updateName(
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "id", required = true) String id
    ) {
        subjectService.updateName( value, id);
    }

    @PutMapping("/subject/teacher")
    public void updateTeacher(
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "id", required = true) String id
    ) {
        subjectService.updateTeacher( value, id);
    }
}
