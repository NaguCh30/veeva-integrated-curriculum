package part_a.q3;

public class Participant {

    private String username;
    private int problemsSolved;
    private int penaltyTime;
    private int rating;

    public Participant(String username, int problemsSolved, int penaltyTime, int rating) {
        this.username = username;
        this.problemsSolved = problemsSolved;
        this.penaltyTime = penaltyTime;
        this.rating = rating;
    }

    public String getUsername() {
        return username;
    }

    public int getProblemsSolved() {
        return problemsSolved;
    }

    public int getPenaltyTime() {
        return penaltyTime;
    }

    public int getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return username + " " + penaltyTime + " " + problemsSolved + " " + rating;
    }
}
