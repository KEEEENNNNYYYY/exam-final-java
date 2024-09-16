package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.Student;
import hei.school.kenny.attendance.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShowStudentController {

    private final StudentService studentService;

    public ShowStudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/students")
    public List<Student> showAllStudent() {
        return studentService.getAllStudent();
    }
}
