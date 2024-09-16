package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.MissingListRequest;
import hei.school.kenny.attendance.model.NewStudentRequest;
import hei.school.kenny.attendance.model.Student;
import hei.school.kenny.attendance.service.StudentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/student/all")
    public List<Student> showAllStudent() {
        return studentService.getAllStudent();
    }
    @PostMapping("/student/add")
    public void addStudent(@RequestBody NewStudentRequest newStudentRequest) {
        studentService.addStudent(newStudentRequest);
    }

}
