// This class uses Notification.java interface

package useful_examples;

public class NotificationDemo {

    public static void main(String[] args) {
        
        Notification notification = (message) -> {
            System.out.println("Sending: " + message);
        };

        notification.send("Welcome!");
        notification.sendSuccess("Registration completed");
        notification.sendFailure("Login failed");

        Notification.applicationInfo();

    }
    
}
