package hei.school.kenny.gestionD_absence;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Subject {
    private String name;
    private List<Student> studentList;
    private Map<String, Presence> presenceRecords;

    Subject(String name, List<Student> studentList) {
        this.name = name;
        this.studentList = studentList;
        this.presenceRecords = new HashMap<>();
    }

    public void doPresence(String date) {
        Presence presence = new Presence(date);
        presenceRecords.put(date, presence);
        System.out.println("Présence enregistrée pour la date : " + date);
    }

    public void updatePresence(String date, String studentID, State state) {
        Presence presence = presenceRecords.get(date);
        if (presence != null) {
            Student student = findStudent(studentID);
            if (student != null) {
                student.setState(state);
                System.out.println("Mise à jour de la présence de " + student.getFirstName() + " pour la date : " + date);
            } else {
                System.out.println("Étudiant avec ID " + studentID + " non trouvé.");
            }
        } else {
            System.out.println("Présence pour la date " + date + " non enregistrée.");
        }
    }

    private Student findStudent(String ID) {
        for (Student student : studentList) {
            if (student.getID().equals(ID)) {
                return student;
            }
        }
        return null;
    }
}
