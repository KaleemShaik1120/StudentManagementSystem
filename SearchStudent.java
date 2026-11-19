
package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class SearchStudent {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Student ID to search: ");
        int id = sc.nextInt();

        String sql = "SELECT * FROM students WHERE id = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {

                if (rs.next()) {
                    System.out.println("\nStudent Found!");
                    System.out.println("ID: " + rs.getInt("id"));
                    System.out.println("Name: " + rs.getString("name"));
                    System.out.println("Course: " + rs.getString("course"));
                    System.out.println("Marks: " + rs.getDouble("marks"));
                } else {
                    System.out.println("Student not found.");
                }
            }

        } catch (SQLException e) {
            System.out.println("Error searching for student:");
            e.printStackTrace();
        }

        sc.close();
    }
}
