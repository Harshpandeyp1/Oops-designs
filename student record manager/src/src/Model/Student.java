package Model;

public class Student {
    private final int id;

    public Student(int id) {
        this.id = id;
    }

    public Student(int id, String name, String course, double marks) {
        this.id = id;
        this.name = name;
        this.course = course;
        setMarks(marks);
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public Double getMarks() {
        return marks;
    }

    public void setMarks(Double marks) {
        if (marks < 0 || marks > 100) {
            throw new IllegalArgumentException("Marks must be between 0 and 100");
        }
        this.marks = marks;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }



    private String name;
    private Double marks;
    private String course;
    @Override
    public String toString() {
        return id + " | " + name + " | " + course + " | " + marks;
    }
}
