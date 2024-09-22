package hei.school.kenny.attendance.controller;

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
            @RequestParam(value = "groupe", required = false) String groupe,
            @RequestParam(value = "grades", required = false) String grades
    ) {
        if (cored != null) {
            return studentService.getStudentListByCor(cored);
        } else if (groupe != null) {
            return  studentService.getStudentByGroupe(groupe);
        } else if (grades != null) {
            return studentService.getStudentByGrades(grades);
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

    @PutMapping("/students/missing/state")
    public void updateState(
            @RequestParam(value = "value", required = true) String value,
            @RequestParam(value = "id", required = false) String id
    ) {
        studentService.updateState( value, id);
    }

    @PutMapping("/students/missing/change")
    public void updateFirstName(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "id", required = true) String id
    ) {
        if (firstName !=null){
            studentService.updateFirstName( firstName, id);
        } else if (lastName != null) {
            studentService.updateLastName( lastName, id);
        }
    }

}
