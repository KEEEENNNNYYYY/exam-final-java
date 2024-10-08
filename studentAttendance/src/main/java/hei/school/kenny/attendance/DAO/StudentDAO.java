package hei.school.kenny.attendance.DAO;

import hei.school.kenny.attendance.model.*;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import static hei.school.kenny.attendance.model.state.IN;

@Repository
public class StudentDAO implements Serializable {
    private Connection connectToDb() {
        String url = "jdbc:postgresql://localhost:5432/studentattendance?sslmode=disable";
        String user = "postgres";
        String password = "0000";
        try {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Student> fetchAllStudent() {
        Connection conn = connectToDb();
        List<Student> studentList = new ArrayList<>();
        if (conn != null) {
            try (PreparedStatement pstmt = conn.prepareStatement("SELECT * FROM student");
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    Student student = new Student();

                    student.setId(rs.getString("id"));
                    student.setFirstName(rs.getString("first_name"));
                    student.setLastName(rs.getString("last_name"));
                    student.setBirthday(rs.getDate("birthday"));

                    String gradeString = rs.getString("grades");
                    System.out.println("Fetched grade value: " + gradeString);
                    student.setGrade(Grade.valueOf(gradeString));

                    student.setAdress(rs.getString("adress"));


                    String sexeString = rs.getString("sexe");
                    student.setSexe(Sexe.valueOf(sexeString));

                    boolean cored = rs.getBoolean("cored");
                    student.setCored(cored);

                    student.setEmail(rs.getString("email"));

                    String groupeString = rs.getString("groupe");
                    student.setGroupe(Groupe.valueOf(groupeString));

                    studentList.add(student);
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return studentList;
    }

    public void addStudent(NewStudentRequest newStudentRequest) {
        Connection conn = connectToDb();

        if (conn != null) {
            state in = IN;
            String query = "INSERT INTO student (" +
                    "id, " +
                    "first_name, " +
                    "last_name, " +
                    "birthday, " +
                    "grades, " +
                    "email, " +
                    "adress, " +
                    "sexe, " +
                    "cored, " +
                    "groupe" +
                    ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newStudentRequest.getId());
                pstmt.setString(2, newStudentRequest.getFirstName());
                pstmt.setString(3, newStudentRequest.getLastName());
                pstmt.setDate(4, new java.sql.Date(newStudentRequest.getBirthday().getTime()));
                pstmt.setString(5, newStudentRequest.getGrade().toString());
                pstmt.setString(6, newStudentRequest.getEmail());
                pstmt.setString(7, newStudentRequest.getAdress());
                pstmt.setString(8, newStudentRequest.getSexe().toString());
                pstmt.setBoolean(9, newStudentRequest.isCored());
                pstmt.setString(10, newStudentRequest.getGroupe().toString());

                pstmt.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public List<Student> fecthStudentListByCor(boolean coredValue) {
        List<Student> studentList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT * FROM student WHERE cored = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setBoolean(1, coredValue);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        Student student = new Student();
                        student.setId(rs.getString("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setGrade(Grade.valueOf(rs.getString("grades")));
                        student.setCored(rs.getBoolean("cored"));
                        student.setEmail(rs.getString("email"));
                        student.setGroupe(Groupe.valueOf(rs.getString("groupe")));

                        studentList.add(student);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return studentList;
    }

    public Student getStudentById(String studentId) {
        Student student = new Student();
        Connection conn = connectToDb();

        if (conn != null) {
            String sql = "SELECT * FROM student WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, studentId);
                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {
                        student.setId(rs.getString("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setBirthday(rs.getDate("birthday"));

                        String gradeString = rs.getString("grades");
                        System.out.println("Fetched grade value: " + gradeString);
                        student.setGrade(Grade.valueOf(gradeString));

                        student.setAdress(rs.getString("adress"));

                        String sexeString = rs.getString("sexe");
                        student.setSexe(Sexe.valueOf(sexeString));

                        boolean cored = rs.getBoolean("cored");
                        student.setCored(cored);

                        student.setEmail(rs.getString("email"));

                        String groupeString = rs.getString("groupe");
                        student.setGroupe(Groupe.valueOf(groupeString));
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return student;
    }


    public List<Student> getStudentByUnjustifiedMissing(int count) {
        List<Student> studentList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT * FROM student WHERE unjustified_missing_count = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setInt(1, count);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        Student student = new Student();
                        student.setId(rs.getString("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setGrade(Grade.valueOf(rs.getString("grades")));
                        student.setCored(rs.getBoolean("cored"));
                        student.setEmail(rs.getString("email"));
                        student.setGroupe(Groupe.valueOf(rs.getString("groupe")));

                        studentList.add(student);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return studentList;
    }

    public List<Student> getStudentByGroupe(String studentGroupe) {
        List<Student> studentList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT * FROM student WHERE groupe = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, studentGroupe);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        Student student = new Student();
                        student.setId(rs.getString("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setGrade(Grade.valueOf(rs.getString("grades")));
                        student.setCored(rs.getBoolean("cored"));
                        student.setEmail(rs.getString("email"));
                        student.setGroupe(Groupe.valueOf(rs.getString("groupe")));

                        studentList.add(student);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return studentList;
    }

    public List<Student> getStudentByGrades(String studentGrades) {
        List<Student> studentList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT * FROM student WHERE grades = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, studentGrades);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        Student student = new Student();
                        student.setId(rs.getString("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
                        student.setGrade(Grade.valueOf(rs.getString("grades")));
                        student.setCored(rs.getBoolean("cored"));
                        student.setEmail(rs.getString("email"));
                        student.setGroupe(Groupe.valueOf(rs.getString("groupe")));

                        studentList.add(student);
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (conn != null && !conn.isClosed()) {
                            conn.close();
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return studentList;
    }

    public void updateState(String value,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE student SET state = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, value);
                pstmt.setString(2, id);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows updated: " + rowsAffected);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error updating missing list", e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateFirstName(String firstName,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE student SET first_name = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, firstName);
                pstmt.setString(2, id);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows updated: " + rowsAffected);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error updating missing list", e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateLastName(String lastName,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE student SET last_name = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, lastName);
                pstmt.setString(2, id);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows updated: " + rowsAffected);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error updating missing list", e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateEmail(String newEmail,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE student SET email = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newEmail);
                pstmt.setString(2, id);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows updated: " + rowsAffected);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error updating missing list", e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateGrades(String newGrades,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE student SET grades = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newGrades);
                pstmt.setString(2, id);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows updated: " + rowsAffected);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error updating missing list", e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateGroupes(String newGroupes,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE student SET groupe = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newGroupes);
                pstmt.setString(2, id);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows updated: " + rowsAffected);

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Error updating missing list", e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void updateBirthday(String newBirthday, String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE student SET birthday = ? WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                Timestamp newDateTimestamp = Timestamp.valueOf(newBirthday + " 00:00:00");
                pstmt.setTimestamp(1, newDateTimestamp);
                pstmt.setString(2, id);

                int rowsAffected = pstmt.executeUpdate();
                System.out.println("Rows updated: " + rowsAffected);

                if (rowsAffected == 0) {
                    System.out.println("Aucun étudiant trouvé avec l'ID : " + id);
                } else {
                    System.out.println("Date de naissance mise à jour pour l'étudiant ID : " + id);
                }

            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException("Erreur lors de la mise à jour de la date de naissance", e);
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } else {
            System.out.println("Échec de la connexion à la base de données.");
        }
    }


}
