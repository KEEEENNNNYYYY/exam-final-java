package hei.school.kenny.gestionD_absence;

import java.util.HashMap;
import java.util.Map;

public class Presence {
    private String date;
    private Map<String, State> studentStates;

    Presence(String date) {
        this.date = date;
        this.studentStates = new HashMap<>();
    }

    public String getDate() {
        return date;
    }

    public void addStudentPresence(String studentID, State state) {
        studentStates.put(studentID, state);
    }

    public State getStudentState(String studentID) {
        return studentStates.get(studentID);
    }
}
