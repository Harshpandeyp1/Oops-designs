package factory;

import model.NotificationType;
import notification.EmailNotification;
import notification.Notification;
import notification.PushNotification;
import notification.SmsNotification;

public class NotificationFactory {

    public static Notification create(NotificationType type){
        switch(type){
            case EMAIL -> {
                return new EmailNotification();
            }
            case SMS -> {
                return new SmsNotification();
            }
            case PUSH -> {
                return new PushNotification();
            }
            default -> throw new IllegalArgumentException("unknown type"+type);
        }
    }
}
