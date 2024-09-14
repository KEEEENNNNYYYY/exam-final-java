package hei.school.kenny.attendance.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({
        "hei.school.kenny.attendance.controller",
        "hei.school.kenny.attendance.DAO",
        "hei.school.kenny.attendance.service",
        "hei.school.kenny.attendance.model"

})
public class Main {

    public static void main(String[] args) {

        SpringApplication.run(Main.class, args);
    }

}
