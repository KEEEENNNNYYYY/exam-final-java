package hei.school.kenny.gestionD_absence.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LandingController {
    @GetMapping("/")
    public String pingPong(){
        return "Themes : gestionnaire d'absences et retards";
    }
}
