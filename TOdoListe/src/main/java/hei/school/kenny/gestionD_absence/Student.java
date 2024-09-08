package hei.school.kenny.gestionD_absence;

import static hei.school.kenny.gestionD_absence.State.P;

public class Student {
    private String ID;
    private String firstName;
    private State state;

    Student(String ID, String firstName) {
        this.ID = ID;
        this.firstName = firstName;
        this.state = P;
    }

    public String getID() {
        return ID;
    }

    public String getFirstName() {
        return firstName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return firstName + " (" + ID + ") - " + state;
    }
}
