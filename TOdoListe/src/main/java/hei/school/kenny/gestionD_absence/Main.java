package hei.school.kenny.gestionD_absence;

public class Main {
    public static void main(String[] args) {
        StudentList studentList = new StudentList();
        studentList.addToList(new Student("1", "Alice"));
        studentList.addToList(new Student("2", "Bob"));

        Subject math = new Subject("Mathematics", studentList.getList());
        math.doPresence("2024-09-08");

        math.updatePresence("2024-09-08", "1", State.A);
        math.updatePresence("2024-09-08", "2", State.P);

        // Afficher l'état des étudiants après la mise à jour
        for (Student student : studentList.getList()) {
            System.out.println(student);
        }
    }
}
