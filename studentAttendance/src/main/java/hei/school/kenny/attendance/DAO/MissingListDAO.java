package hei.school.kenny.attendance.DAO;

import hei.school.kenny.attendance.model.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class MissingListDAO implements Serializable {

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

    public List<MissingList> getAllMissingList() {
        List<MissingList> missingListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT * FROM missing_list";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String subjectId = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectId);

                        MissingList missingList = new MissingList();
                        missingList.setDate(rs.getDate("date"));
                        missingList.setId(missingListList.size() + 1);
                        missingList.setMissingStudent(List.of(student));
                        missingList.setSubjectMissed(List.of(subject));

                        missingListList.add(missingList);
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
        return missingListList;
    }

    public List<MissingList> getMissingListByDate(Date date) {
        List<MissingList> missingListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String sql = "SELECT student_id, date, subject_id FROM missing_list WHERE date = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setDate(1, new java.sql.Date(date.getTime()));
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        // Debugging
                        System.out.println("Found record: student_id = " + rs.getString("student_id") + ", subject_id = " + rs.getString("subject_id"));

                        String studentId = rs.getString("student_id");
                        String subjectName = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectName);

                        MissingList missingList = new MissingList();
                        missingList.setDate(date);
                        missingList.setId(missingListList.size() + 1);
                        missingList.setMissingStudent(List.of(student));
                        missingList.setSubjectMissed(List.of(subject));

                        missingListList.add(missingList);
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
        return missingListList;
    }

    public List<MissingList> getMissingListById(String id) {
        List<MissingList> missingListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT student_id, date, subject_id FROM missing_list WHERE student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String subjectId = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectId);

                        MissingList missingList = new MissingList();
                        missingList.setDate(rs.getDate("date"));
                        missingList.setId(missingListList.size() + 1);
                        missingList.setMissingStudent(List.of(student));
                        missingList.setSubjectMissed(List.of(subject));

                        missingListList.add(missingList);
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
        return missingListList;
    }

    public List<MissingList> getMissingListBySubject(String name) {
        List<MissingList> missingListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT student_id, date, subject_id FROM missing_list WHERE subject_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, name);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String subjectId = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectId);

                        MissingList missingList = new MissingList();
                        missingList.setDate(rs.getDate("date"));
                        missingList.setId(missingListList.size() + 1);
                        missingList.setMissingStudent(List.of(student));
                        missingList.setSubjectMissed(List.of(subject));

                        missingListList.add(missingList);
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
        return missingListList;
    }

    public void addMissingList(MissingListRequest missingListRequest) {
        Connection conn = connectToDb();

        if (conn != null) {
            try {
                conn.setAutoCommit(false);

                Student student = getStudentById(missingListRequest.getStudentId());
                if (student == null) {
                    throw new RuntimeException("Étudiant introuvable avec l'ID: " + missingListRequest.getStudentId());
                }

                String insertQuery = "INSERT INTO missing_list (student_id, first_name, last_name, date, subject_id, justified) VALUES (?, ?, ?, ?, ?, false)";
                try (PreparedStatement pstmt = conn.prepareStatement(insertQuery)) {
                    pstmt.setString(1, missingListRequest.getStudentId());
                    pstmt.setString(2, student.getFirstName());
                    pstmt.setString(3, student.getLastName());
                    pstmt.setDate(4, java.sql.Date.valueOf(missingListRequest.getDate()));
                    pstmt.setString(5, missingListRequest.getSubjectId());

                    pstmt.executeUpdate();
                }

                String updateCountQuery = "UPDATE student SET unjustified_missing_count = unjustified_missing_count + 1 WHERE id = ?";
                try (PreparedStatement updateCountStmt = conn.prepareStatement(updateCountQuery)) {
                    updateCountStmt.setString(1, missingListRequest.getStudentId());
                    updateCountStmt.executeUpdate();
                }

                String countQuery = "SELECT unjustified_missing_count FROM student WHERE id = ?";
                try (PreparedStatement countStmt = conn.prepareStatement(countQuery)) {
                    countStmt.setString(1, missingListRequest.getStudentId());
                    ResultSet rs = countStmt.executeQuery();

                    if (rs.next()) {
                        int unjustifiedCount = rs.getInt("unjustified_missing_count");

                        if (unjustifiedCount >= 3) {
                            String updateQuery = "UPDATE student SET cored = true WHERE id = ?";
                            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                                updateStmt.setString(1, missingListRequest.getStudentId());
                                updateStmt.executeUpdate();
                            }
                        }
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                try {
                    conn.rollback();
                } catch (SQLException rollbackEx) {
                    throw new RuntimeException("Erreur lors du rollback : " + rollbackEx.getMessage(), rollbackEx);
                }
                throw new RuntimeException("Erreur lors de l'insertion : " + e.getMessage(), e);
            } finally {
                try {
                    if (conn != null) {
                        conn.close();
                    }
                } catch (SQLException closeEx) {
                    throw new RuntimeException("Erreur lors de la fermeture de la connexion : " + closeEx.getMessage(), closeEx);
                }
            }
        } else {
            throw new RuntimeException("Erreur de connexion à la base de données.");
        }
    }

    public void updateMissingListBySubject(String studentId, String oldCours, String newCours, String date) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE missing_list SET subject_id = ? WHERE subject_id = ? AND student_id = ? AND date = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, newCours);
                pstmt.setString(2, oldCours);
                pstmt.setString(3, studentId);
                pstmt.setDate(4, java.sql.Date.valueOf(date));

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

    public void updateSubjectMissingDate(String studentId, String oldDate, String newDate) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "UPDATE missing_list SET date = ? WHERE date = ? AND student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                Timestamp oldDateTimestamp = Timestamp.valueOf(oldDate + " 00:00:00");
                Timestamp newDateTimestamp = Timestamp.valueOf(newDate + " 00:00:00");

                pstmt.setTimestamp(1, newDateTimestamp);
                pstmt.setTimestamp(2, oldDateTimestamp);
                pstmt.setString(3, studentId);

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

    public void justifyMissing(String studentId, String subject, Date date) {
        try (Connection conn = connectToDb()) {
            if (conn != null) {
                String query = "UPDATE missing_list SET justified = true WHERE student_id = ? AND subject_id = ? AND date = ?";
                try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                    pstmt.setString(1, studentId);
                    pstmt.setString(2, subject);
                    pstmt.setDate(3, new java.sql.Date(date.getTime()));

                    int rowsAffected = pstmt.executeUpdate();
                    if (rowsAffected > 0) {
                        String updateQuery = "UPDATE student SET unjustified_missing_count = unjustified_missing_count - 1 WHERE id = ?";
                        try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                            updateStmt.setString(1, studentId);
                            updateStmt.executeUpdate();
                        }
                    } else {
                        throw new RuntimeException("No records updated. Check studentId, subject, or date.");
                    }
                } catch (SQLException e) {
                    throw new RuntimeException("Error updating missing list: " + e.getMessage(), e);
                }
            } else {
                throw new RuntimeException("Failed to connect to the database.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteStudentOnMissingList(String subjectId, String studentId, String date) {
        Connection conn = connectToDb();

        if (conn != null) {
            try {
                conn.setAutoCommit(false);

                String checkQuery = "SELECT justified FROM missing_list WHERE subject_id = ? AND student_id = ? AND date = ?";
                try (PreparedStatement checkStmt = conn.prepareStatement(checkQuery)) {
                    checkStmt.setString(1, subjectId);
                    checkStmt.setString(2, studentId);
                    checkStmt.setDate(3, java.sql.Date.valueOf(date));
                    ResultSet rs = checkStmt.executeQuery();

                    boolean justified = false;
                    if (rs.next()) {
                        justified = rs.getBoolean("justified");
                    }

                    String deleteQuery = "DELETE FROM missing_list WHERE subject_id = ? AND student_id = ? AND date = ?";
                    try (PreparedStatement deleteStmt = conn.prepareStatement(deleteQuery)) {
                        deleteStmt.setString(1, subjectId);
                        deleteStmt.setString(2, studentId);
                        deleteStmt.setDate(3, java.sql.Date.valueOf(date));
                        int rowsAffected = deleteStmt.executeUpdate();
                        System.out.println("Rows deleted: " + rowsAffected);

                        if (!justified) {
                            String updateQuery = "UPDATE student SET unjustified_missing_count = unjustified_missing_count - 1 WHERE id = ?";
                            try (PreparedStatement updateStmt = conn.prepareStatement(updateQuery)) {
                                updateStmt.setString(1, studentId);
                                updateStmt.executeUpdate();
                            }
                        }
                    }
                }

                conn.commit();
            } catch (SQLException e) {
                e.printStackTrace();
                try {
                    if (conn != null) {
                        conn.rollback();
                    }
                } catch (SQLException rollbackEx) {
                    rollbackEx.printStackTrace();
                }
                throw new RuntimeException("Error deleting from missing list", e);
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

    private Student getStudentById(String studentId) {
        Student student = null;
        Connection conn = connectToDb();

        if (conn != null) {
            String sql = "SELECT id, first_name, last_name FROM student WHERE id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, studentId);
                try (ResultSet rs = pstmt.executeQuery()) {

                    if (rs.next()) {
                        student = new Student();
                        student.setId(rs.getString("id"));
                        student.setFirstName(rs.getString("first_name"));
                        student.setLastName(rs.getString("last_name"));
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
        return student;
    }

    private Subject getSubjectById(String subjectName) {
        Subject subject = null;
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT name, teacher FROM subject WHERE name = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, subjectName);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        subject = new Subject();
                        subject.setName(rs.getString("name"));
                        subject.setTeacher(rs.getString("teacher"));
                    } else {
                        System.out.println("No subject found with name: " + subjectName);
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
        return subject;
    }
}
