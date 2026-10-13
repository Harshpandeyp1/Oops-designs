package dao;

import Model.Student;
import exception.DatabaseException;
import util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {

    public Student add(String name, String course, double marks) {
        String sql = "INSERT INTO students (name, course, marks) VALUES (?, ?, ?)";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, name);
            ps.setString(2, course);
            ps.setDouble(3, marks);
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    return new Student(keys.getInt(1), name, course, marks);
                }
            }
            throw new DatabaseException("Insert worked but no Id was  returned", null);
        } catch (SQLException e) {
            throw new DatabaseException("failed to add student", e);
        }
    }

    // READ: load everything (used once at startup to fill the HashMap)
    public List<Student> getAll() {
        String sql = "SELECT id, name, course, marks FROM students";
        List<Student> list = new ArrayList<>();
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                list.add(new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("course"),
                        rs.getDouble("marks")));
            }
            return list;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to load students", e);
        }
    }

    public boolean update(Student s) {
        String sql = "UPDATE students SET course=?, marks=? WHERE id=?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, s.getCourse());
            ps.setDouble(2, s.getMarks());
            ps.setInt(3, s.getId());
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to update student " + s.getId(), e);
        }
    }
    public boolean delete(int id) {
        String sql = "DELETE FROM students WHERE id = ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DatabaseException("Failed to delete student " + id, e);
        }
    }
}
