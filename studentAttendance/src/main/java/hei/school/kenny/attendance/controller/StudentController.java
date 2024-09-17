package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.MissingList;
import hei.school.kenny.attendance.model.MissingListRequest;
import hei.school.kenny.attendance.model.NewStudentRequest;
import hei.school.kenny.attendance.model.Student;
import hei.school.kenny.attendance.service.StudentService;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/students/search")
    public List<Student> showCored(
            @RequestParam(value = "cored", required = false) Boolean cored
    ) {
        if (cored != null) {
            return studentService.getStudentListByCor(cored);
        } else {
            throw new IllegalArgumentException("Parameter 'cored' must be provided");
        }
    }


    @PostMapping("/student/add")
    public void addStudent(@RequestBody NewStudentRequest newStudentRequest) {
        studentService.addStudent(newStudentRequest);
    }
    
}
