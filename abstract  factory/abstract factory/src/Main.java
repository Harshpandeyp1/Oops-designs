import app.Application;
import factory.*;

public class Main {
    public static void main(String[] args) {
        String os = System.getProperty("os.name").toLowerCase();

        UIFactory factory = os.contains("mac") ? new MacFactory() : new WindowsFactory();

        new Application(factory).render();
    }
}