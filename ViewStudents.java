
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ViewStudents {

    public static void main(String[] args) {

        String sql = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            System.out.println("ID | Name | Course | Marks");
            System.out.println("--------------------------------");

            boolean found = false;

            while (rs.next()) {
                found = true;

                int id = rs.getInt("id");
                String name = rs.getString("name");
                String course = rs.getString("course");
                double marks = rs.getDouble("marks");

                System.out.println(
                    id + " | " + name + " | "
                    + course + " | " + marks
                );
            }

            if (!found) {
                System.out.println("No student records found.");
            }

        } catch (SQLException e) {
            System.out.println("Error retrieving student records:");
            e.printStackTrace();
        }
    }
}
