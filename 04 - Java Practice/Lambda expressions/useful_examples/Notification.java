/*
This interface eas used in class NotificationDemo.java

I created this interface to demonstrate that a Functional Interface can also have 
Multiple default methods
Multiple static methods
private methods

And also instance methods of Object class.

But the fundamental rule is a functional interface should only have one abstract method.
*/

package useful_examples;

@FunctionalInterface 
public interface Notification {

    void send(String message);

    default void sendSuccess(String message) {
        printMessage("SUCCESS", message);
    }

    default void sendFailure(String message) {
        printMessage("ERROR", message);
    }

    private void printMessage(String type, String message) {
        System.out.println("[" + type + "] " + message);
    }

    static void applicationInfo() {
        System.out.println("Notification Service v1.0");
    }
    
} 
