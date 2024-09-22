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
            @RequestParam(value = "cored", required = false) Boolean cored,
            @RequestParam(value = "groupe", required = false) String groupe
    ) {
        if (cored != null) {
            return studentService.getStudentListByCor(cored);
        } else if (groupe != null) {
            return  studentService.getStudentByGroupe(groupe);
        } else {
            throw new IllegalArgumentException("Parameter 'cored' must be provided");
        }
    }

    @GetMapping("/students/list")
    public Student showStudent(
            @RequestParam(value = "id", required = false) String studentId
    ) {
            return studentService.getStudentById(studentId);
    }

    @PostMapping("/student/add")
    public void addStudent(@RequestBody NewStudentRequest newStudentRequest) {
        studentService.addStudent(newStudentRequest);
    }
    
}
