package hei.school.kenny.attendance.DAO;

import hei.school.kenny.attendance.model.*;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Repository
public class LateListDAO implements Serializable {

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

    public List<LateList> getAllLateList() {
        List<LateList> lateListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT * FROM late_list";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String subjectId = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectId);

                        LateList lateList = new LateList();
                        lateList.setDate(rs.getTimestamp("date"));
                        lateList.setId(lateListList.size() + 1);
                        lateList.setLateStudent(List.of(student));
                        lateList.setSubjectLate(List.of(subject));

                        lateListList.add(lateList);
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
        return lateListList;
    }

    public List<LateList> getLateListByDate(Date date) {
        List<LateList> lateListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String sql = "SELECT student_id, date, subject_id FROM late_list WHERE date = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setTimestamp(1, new Timestamp(date.getTime()));
                try (ResultSet rs = pstmt.executeQuery()) {
                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String subjectName = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectName);

                        LateList lateList = new LateList();
                        lateList.setDate(date);
                        lateList.setId(lateListList.size() + 1);
                        lateList.setLateStudent(List.of(student));
                        lateList.setSubjectLate(List.of(subject));

                        lateListList.add(lateList);
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
        return lateListList;
    }

    public List<LateList> getLateListByStudentId(String id) {
        List<LateList> lateListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT student_id, date, subject_id FROM late_list WHERE student_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, id);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String subjectId = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectId);

                        LateList lateList = new LateList();
                        lateList.setDate(rs.getTimestamp("date"));
                        lateList.setId(lateListList.size() + 1);
                        lateList.setLateStudent(List.of(student));
                        lateList.setSubjectLate(List.of(subject));

                        lateListList.add(lateList);
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
        return lateListList;
    }

    public List<LateList> getLateListBySubject(String name) {
        List<LateList> lateListList = new ArrayList<>();
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "SELECT student_id, date, subject_id FROM late_list WHERE subject_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, name);
                try (ResultSet rs = pstmt.executeQuery()) {

                    while (rs.next()) {
                        String studentId = rs.getString("student_id");
                        String subjectId = rs.getString("subject_id");

                        Student student = getStudentById(studentId);
                        Subject subject = getSubjectById(subjectId);

                        LateList lateList = new LateList();
                        lateList.setDate(rs.getTimestamp("date"));
                        lateList.setId(lateListList.size() + 1);
                        lateList.setLateStudent(List.of(student));
                        lateList.setSubjectLate(List.of(subject));

                        lateListList.add(lateList);
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
        return lateListList;
    }

    public void addLateList(List<LateListRequest> lateListRequests) {
        Connection conn = connectToDb();

        if (conn != null) {
            String query = "INSERT INTO late_list (student_id, first_name, last_name, date, subject_id) VALUES (?, ?, ?, ?, ?)";
            try (PreparedStatement pstmt = conn.prepareStatement(query)) {
                for (LateListRequest request : lateListRequests) {
                    pstmt.setString(1, request.getStudentId());
                    pstmt.setString(2, request.getFirstName());
                    pstmt.setString(3, request.getLastName());

                    
                    Timestamp timestamp = Timestamp.valueOf(request.getDate());
                    pstmt.setTimestamp(4, timestamp);

                    pstmt.setString(5, request.getSubjectId());

                    pstmt.addBatch();
                }
                pstmt.executeBatch();

            } catch (SQLException e) {
                System.err.println("SQL Exception: " + e.getMessage());
                e.printStackTrace();
            } finally {
                try {
                    if (conn != null && !conn.isClosed()) {
                        conn.close();
                    }
                } catch (SQLException e) {
                    System.err.println("SQL Exception on close: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        } else {
            System.err.println("Failed to connect to the database.");
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
