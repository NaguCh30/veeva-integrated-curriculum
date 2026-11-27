package part_a.q3;

import java.util.*;

public class Main {

    private static final PriorityQueue<Participant> participants =
            new PriorityQueue<>(
                    Comparator.comparingInt(Participant::getProblemsSolved).reversed()
                            .thenComparingInt(Participant::getPenaltyTime)
                            .thenComparing(
                                    Comparator.comparingInt(Participant::getRating).reversed()
                            )
                            .thenComparing(Participant::getUsername)
            );

    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Enter number of participants: ");
        int n = sc.nextInt();
        sc.nextLine();

        for (int i = 1; i <= n; i++) {
            addParticipant(i);
        }

        displayParticipants();
    }




    private static void showMenu() {
        System.out.println();
        System.out.println("1. Add Participant");
        System.out.println("2. Display participants");
        System.out.println("3. Exit");
        System.out.println();
    }
    private static void addParticipant(int number) {
        System.out.println("Enter participant "+ number +" details in following format(username penalty-time problems-solved rating): ");
        String input = sc.nextLine();

        String[] parts = input.split(" ");


        String username = parts[0];
        if (username.contains(" ") || username.contains("@") || username.contains("#")) {
            System.out.println("Username can only contains alphanumeric characters and underscores..");
            return;
        }

        int penaltyTime = Integer.parseInt(parts[1]);
        int problemsSolved = Integer.parseInt(parts[2]);
        int rating = Integer.parseInt(parts[3]);

        Participant participant = new Participant(username, problemsSolved, penaltyTime, rating);
        participants.add(participant);
    }

    private static void displayParticipants() {
        PriorityQueue<Participant> participantsCopy = new PriorityQueue<>(participants);
        System.out.println("\nDisplaying Participants from rank 1 to N...");

        while (!participantsCopy.isEmpty()) {
            System.out.println(participantsCopy.poll());
        }
    }
}
