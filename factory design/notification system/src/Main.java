import factory.NotificationFactory;
import model.NotificationType;
import notification.Notification;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Notification n1 = NotificationFactory.create(NotificationType.EMAIL);
        n1.send("Your order is placed");

        Notification n2 = NotificationFactory.create(NotificationType.SMS);
        n2.send("Your OTP is 4821");
    }
}