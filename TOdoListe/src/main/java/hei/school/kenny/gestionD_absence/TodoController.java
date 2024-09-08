package hei.school.kenny.gestionD_absence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {
    @GetMapping("/todos")
    public String getAllTask(){
        Todo todo = new Todo();
        System.out.println("/todos here");
        return todo.fetchTasks();

    }
}
