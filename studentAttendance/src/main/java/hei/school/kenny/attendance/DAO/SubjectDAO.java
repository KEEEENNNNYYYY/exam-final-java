package hei.school.kenny.attendance.DAO;

import hei.school.kenny.attendance.model.NewSubjectRequest;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Repository
public class SubjectDAO {

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

    public void addSubject(NewSubjectRequest newSubjectRequest) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "INSERT INTO subject (name,total_hours,teacher) VALUES (?, ?, ?)";

            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newSubjectRequest.getName());
                pstmt.setInt(2, newSubjectRequest.getTotalHours());
                pstmt.setString(3, newSubjectRequest.getTeacher());

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
}
