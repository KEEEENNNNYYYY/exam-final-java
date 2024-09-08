package hei.school.kenny.gestionD_absence;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloWorld {
    @GetMapping("/")
    public String pingPong(){
        return "Themes : gestionnaire d'absences et retards";
    }
}
