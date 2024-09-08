package hei.school.kenny.gestionD_absence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Todo {

    private Connection connectToDb(String dbname, String user, String pass) {
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/" + dbname + "?sslmode=disable";
            conn = DriverManager.getConnection(url, user, pass);
            if (conn != null) {
                System.out.println("Connected");
            } else {
                System.out.println("Failed to connect");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public String fetchTasks() {
        Connection conn = connectToDb("todolistjava", "postgres", "0000");

        StringBuilder result = new StringBuilder();
        if (conn != null) {
            try {
                Statement stmt = conn.createStatement();
                String query = "SELECT * FROM task";
                ResultSet rs = stmt.executeQuery(query);

                while (rs.next()) {
                    String id = rs.getString("id");
                    String name = rs.getString("name");
                    String state = rs.getString("state");
                    result.append("ID: ").append(id)
                            .append(", Name: ").append(name)
                            .append(", State: ").append(state)
                            .append("\n");
                }

                rs.close();
                stmt.close();
                conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return result.toString();
    }
}
