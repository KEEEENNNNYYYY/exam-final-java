package hei.school.kenny.attendance.controller;

import hei.school.kenny.attendance.model.NewStudentRequest;
import hei.school.kenny.attendance.model.Student;
import hei.school.kenny.attendance.service.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public List<Student> showAllStudent() {
        return studentService.getAllStudent();
    }

    @GetMapping("/search/cored/{value}")
    public List<Student> showCored(@PathVariable(value = "value") Boolean cored) {
        return studentService.getStudentListByCor(cored);
    }

    @GetMapping("/search/groupe/{groupe}")
    public List<Student> showByGroupe(@PathVariable(value = "groupe") String groupe) {
        return studentService.getStudentByGroupe(groupe);
    }

    @GetMapping("/search/grades/{grades}")
    public List<Student> showByGrades(@PathVariable(value = "grades") String grades) {
        return studentService.getStudentByGrades(grades);
    }

    @GetMapping("/search/missingCount")
    public List<Student> showByUnjustifiedMissing(@RequestParam(value = "missingCount") Integer missingCount) {
        return studentService.getStudentByUnjustifiedMissing(missingCount);
    }

    @GetMapping("/{id}")
    public Student showStudent(
            @PathVariable(value = "id", required = false) String studentId
    ) {
            return studentService.getStudentById(studentId);
    }

    @PostMapping("/add")
    public void addStudent(@RequestBody NewStudentRequest newStudentRequest) {
        studentService.addStudent(newStudentRequest);
    }

    @PutMapping("/{id}/state")
    public void updateState(
            @RequestParam(value = "value", required = true) String value,
            @PathVariable(value = "id", required = false) String id
    ) {
        studentService.updateState( value, id);
    }

    @PutMapping("/change")
    public void updateFirstName(
            @RequestParam(value = "firstName", required = false) String firstName,
            @RequestParam(value = "lastName", required = false) String lastName,
            @RequestParam(value = "newEmail", required = false) String newEmail,
            @RequestParam(value = "newGrades", required = false) String newGrades,
            @RequestParam(value = "newGroupes", required = false) String newGroupes,
            @RequestParam(value = "newBirthday", required = false) String newBirthday,
            @RequestParam(value = "id", required = true) String id
    ) {
        if (firstName !=null){
            studentService.updateFirstName( firstName, id);
        } else if (lastName != null) {
            studentService.updateLastName( lastName, id);
        } else if (newEmail != null) {
            studentService.updateEmail(newEmail, id);
        } else if (newGrades != null) {
            studentService.updateGrades(newGrades, id);
        } else if (newGroupes != null) {
            studentService.updateGroupes(newGroupes, id);
        } else if (newBirthday != null) {
            studentService.updateBirthday(newBirthday,id);
        }
    }

}
