package hei.school.kenny.attendance.model;

import java.util.Date;

public class Student {
    private String id;
    private String firstName;
    private String lastName;
    private Date birthday;
    private Grade grade;
    private String adress;
    private Sexe sexe;
    private boolean cored;
    private String email;
    private Groupe groupe;

    public Student(
            ) {
        this.adress = adress;
        this.birthday = birthday;
        this.cored = cored;
        this.email = email;
        this.firstName = firstName;
        this.grade = grade;
        this.groupe = groupe;
        this.id = id;
        this.lastName = lastName;
        this.sexe = sexe;
    }


    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public boolean isCored() {
        return cored;
    }

    public void setCored(boolean cored) {
        this.cored = cored;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Grade getGrade() {
        return grade;
    }

    public void setGrade(Grade grade) {
        this.grade = grade;
    }

    public Groupe getGroupe() {
        return groupe;
    }

    public void setGroupe(Groupe groupe) {
        this.groupe = groupe;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Sexe getSexe() {
        return sexe;
    }

    public void setSexe(Sexe sexe) {
        this.sexe = sexe;
    }
}