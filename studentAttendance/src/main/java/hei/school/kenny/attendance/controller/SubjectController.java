package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.NewSubjectRequest;
import hei.school.kenny.attendance.model.Subject;
import hei.school.kenny.attendance.service.SubjectService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/subject")
public class SubjectController {

    private final SubjectService subjectService;

    @GetMapping("/all")
    public List<Subject> showAllSubject() {
        return subjectService.getAllSubjects();
    }

    public SubjectController(SubjectService subjectService) {
        this.subjectService = subjectService;
    }

    @PostMapping("/add")
    public void addSubject(@RequestBody NewSubjectRequest newSubjectRequest) {
        subjectService.addSubject(newSubjectRequest);
    }

    @GetMapping("/search/{name}")
    public Subject showById(
            @PathVariable(value = "name", required = false) String name
    ) {
        return subjectService.getSubjectById(name);
    }

    @GetMapping("/show/{teacher}")
    public List<Subject> showByTeacher(
            @PathVariable(value = "teacher", required = false) String teacher
    ) {
        return subjectService.getSubjectByTeacher(teacher);
    }

    @PutMapping("/update/state")
    public void updateState(
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "id", required = true) String id
    ) {
            subjectService.updateState( value, id);
    }

    @PutMapping("/update/name")
    public void updateName(
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "id", required = true) String id
    ) {
        subjectService.updateName( value, id);
    }

    @PutMapping("/update/teacher")
    public void updateTeacher(
            @RequestParam(value = "value", required = false) String value,
            @RequestParam(value = "id", required = true) String id
    ) {
        subjectService.updateTeacher(value, id);
    }

    @PutMapping("/update/totalHours")
    public void updateTotalHours(
            @RequestParam(value = "value", required = false) int value,
            @RequestParam(value = "id", required = true) String id
    ) {
        subjectService.updateTotalHours(value, id);
    }
}
