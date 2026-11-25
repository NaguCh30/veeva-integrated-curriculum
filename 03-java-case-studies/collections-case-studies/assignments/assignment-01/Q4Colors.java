import java.util.*;
import java.util.stream.Collectors;

public class Q4Colors {
    public static void main(String[] args) {
        List<String> colors = new ArrayList<>(Arrays.asList(
                "Red", "Blue", "Green", "Yellow", "Orange", "Purple", "Black", "White", "Pink", "Brown",
                "Blue", "Red", "Green", "Violet", "Orange", "Cyan", "Yellow", "Pink", "Magenta", "Black",
                "Gray", "White", "Red", "Teal", "Blue"
        ));

        Map<String, Long> colorFrequency = colors.stream()
                .collect(
                        Collectors.groupingBy(
                                color -> color,
                                Collectors.counting()
                        )
                );

        // color with the highest frequency
        System.out.println("Color with Highest Frequency: ");
        Map.Entry<String, Long> maxFrequency = colorFrequency.entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .orElse(null);
        if (maxFrequency != null) {
            System.out.println("Color: " + maxFrequency.getKey());
            System.out.println("Frequency: " + maxFrequency.getValue());
        } else {
            System.out.println("No color exist with highest frequency.");
        }

        // color with the lowest frequency
        System.out.println("\nColor with Lowest Frequency: ");
        Map.Entry<String, Long> lowFrequency = colorFrequency.entrySet()
                .stream()
                .min(Map.Entry.comparingByValue())
                .orElse(null);
        if (lowFrequency != null) {
            System.out.println("Color: " + lowFrequency.getKey());
            System.out.println("Frequency: " + lowFrequency.getValue());
        } else {
            System.out.println("No color exists with low frequency.");
        }

        Scanner sc = new Scanner(System.in);
        System.out.print("Enter any value: ");
        int k = sc.nextInt();

        // colors with an occurrence of K times
        System.out.println("\nColors with " + k + " frequency: ");
        colorFrequency.entrySet()
                .stream()
                .filter(
                        e -> e.getValue() == k
                )
                .forEach(entry -> System.out.println(entry.getKey() + " - " + entry.getValue()));
    }
}
