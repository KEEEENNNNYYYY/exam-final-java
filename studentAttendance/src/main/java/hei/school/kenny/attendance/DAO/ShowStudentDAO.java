package hei.school.kenny.attendance.DAO;

import hei.school.kenny.attendance.model.Student;
import org.springframework.stereotype.Repository;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ShowStudentDAO implements Serializable {
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
                    student.setBirthday(rs.getString("birthday"));
                    student.setGrades(rs.getString("grades"));
                    student.setAdress(rs.getString("adress"));
                    student.setSexe(rs.getString("sexe"));
                    student.setCored(rs.getString("cored"));
                    student.setEmail(rs.getString("email"));
                    student.setGroupe(rs.getString("groupe"));
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
}
