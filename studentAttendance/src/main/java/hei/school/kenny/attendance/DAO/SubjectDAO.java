package hei.school.kenny.attendance.DAO;

import hei.school.kenny.attendance.model.*;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
            String query = "INSERT INTO subject (name,total_hours,teacher,state) VALUES (?, ?, ?,'OnGoing')";

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

    public List<Subject> fetchAllSubjects() {
        Connection conn = connectToDb();
        List<Subject> subjectList = new ArrayList<>();

        if (conn != null) {
            String query = "SELECT * FROM subject";
            try (PreparedStatement pstmt = conn.prepareStatement(query);
                 ResultSet rs = pstmt.executeQuery()) {

                while (rs.next()) {
                    String subjectName = rs.getString("name");
                    int totalHours = rs.getInt("total_hours");
                    String teacher = rs.getString("teacher");

                    Subject subject = new Subject();
                    subject.setName(subjectName);
                    subject.setTotalHours(totalHours);
                    subject.setTeacher(teacher);

                    subjectList.add(subject);
                }

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
        } else {
            System.out.println("Connection to the database failed.");
        }
        return subjectList;
    }

    public Subject getSubjectById(String subjectId) {
        Subject subject = null;
        Connection conn = connectToDb();

        if (conn != null) {
            String sql = "SELECT * FROM subject WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, subjectId);
                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {
                        subject = new Subject();
                        subject.setName(rs.getString("name"));
                        subject.setTotalHours(rs.getInt("total_hours"));
                        subject.setTeacher(rs.getString("teacher"));
                    }

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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return subject;
    }

    public List<Subject> getSubjectByTeacher(String teacher) {
        List<Subject> subjectList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT * FROM subject WHERE teacher = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, teacher);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        Subject subject = new Subject();
                        subject.setName(rs.getString("name"));
                        subject.setTotalHours(rs.getInt("total_hours"));
                        subject.setTeacher(rs.getString("teacher"));
                        subject.setState(State.valueOf(rs.getString("state")));

                        subjectList.add(subject);
                    }

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
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        return subjectList;
    }

    public void updateState(String value,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE subject SET state = ? WHERE name = ?";
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

    public void updateName(String value,String id) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE subject SET name = ? WHERE name = ?";
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
}
