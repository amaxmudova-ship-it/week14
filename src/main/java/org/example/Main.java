package org.example;
import java.sql.*;
public class Main {
    public static void main(String[] args) {
        String sql = "SELECT * FROM Authors";
        try (Connection c = DatabaseUtil.getConnection();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            System.out.println("books DB  Authors:");
            while (rs.next())
                System.out.println(
                        rs.getInt("AuthorID") + " | " +
                                rs.getString("FirstName") + " " +
                                rs.getString("LastName"));
        } catch (SQLException e) {
            System.out.println(
                    "Connection failed: " + e.getMessage());
        }
    }

}
