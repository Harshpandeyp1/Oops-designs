package service;

import dao.StudentDao;
import exception.DatabaseException;
import exception.StudentNotFoundException;
import Model.Student;
import sort.MergeSort;

import java.util.*;

public class StudentService {
    private final StudentDao dao;
    private final Map<Integer, Student> students = new HashMap<>();

    // On startup, load everything from the DB into the HashMap
    public StudentService(StudentDao dao) {
        this.dao = dao;
        for (Student s : dao.getAll()) {
            students.put(s.getId(), s);
        }
    }

    // ---------- CREATE ----------
    public Student addStudent(String name, String course, double marks) {
        validateMarks(marks);
        Student s = dao.add(name, course, marks);   // DB first (it gives us the ID)
        students.put(s.getId(), s);                 // then the map
        return s;
    }

    // ---------- READ ----------
    public Student findById(int id) {               // O(1)
        Student s = students.get(id);
        if (s == null) {
            throw new StudentNotFoundException("No student with id " + id);
        }
        return s;
    }

    public List<Student> findByName(String keyword) {   // O(n)
        List<Student> result = new ArrayList<>();
        for (Student s : students.values()) {
            if (s.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(s);
            }
        }
        return result;
    }

    // ---------- UPDATE ----------
    public void updateMarks(int id, double newMarks) {
        validateMarks(newMarks);
        Student s = findById(id);
        double oldMarks = s.getMarks();
        s.setMarks(newMarks);
        try {
            dao.update(s);
        } catch (DatabaseException e) {
            s.setMarks(oldMarks);                   // rollback so map and DB stay consistent
            throw e;
        }
    }

    // ---------- DELETE ----------
    public void deleteStudent(int id) {
        findById(id);                               // throws if missing
        dao.delete(id);                             // DB first
        students.remove(id);                        // then the map
    }

    // ---------- SORTING (merge sort) ----------
    public List<Student> getAllSortedByName() {
        return MergeSort.sort(new ArrayList<>(students.values()),
                Comparator.comparing(Student::getName, String.CASE_INSENSITIVE_ORDER));
    }

    public List<Student> getAllSortedByMarks() {    // highest first
        return MergeSort.sort(new ArrayList<>(students.values()),
                Comparator.comparingDouble(Student::getMarks).reversed());
    }

    // ---------- TOP K (min-heap of size K) ----------
    public List<Student> getTopK(int k) {
        if (k <= 0) {
            throw new IllegalArgumentException("k must be positive");
        }
        PriorityQueue<Student> heap =
                new PriorityQueue<>(Comparator.comparingDouble(Student::getMarks));

        for (Student s : students.values()) {
            if (heap.size() < k) {
                heap.add(s);
            } else if (s.getMarks() > heap.peek().getMarks()) {
                heap.poll();                        // remove the smallest of the top K
                heap.add(s);
            }
        }
        return MergeSort.sort(new ArrayList<>(heap),
                Comparator.comparingDouble(Student::getMarks).reversed());
    }

    private void validateMarks(double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
    }
}