import dao.StudentDao;
import service.StudentService;

public class Main {
    public static void main(String[] args) {
        StudentService service = new StudentService(new StudentDao());

        service.addStudent("Rahul", "CSE", 85.5);
        service.addStudent("Anita", "IT", 92);
        service.addStudent("Zoya", "CSE", 70);
        service.addStudent("Karan", "ECE", 92);

        System.out.println("By name:");
        service.getAllSortedByName().forEach(System.out::println);

        System.out.println("By marks:");
        service.getAllSortedByMarks().forEach(System.out::println);

        System.out.println("Top 2:");
        service.getTopK(2).forEach(System.out::println);

        System.out.println("Search 'an':");
        service.findByName("an").forEach(System.out::println);
    }
}