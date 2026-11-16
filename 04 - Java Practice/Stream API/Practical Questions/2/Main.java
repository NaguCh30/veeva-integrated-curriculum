import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Main {
    public static void main(String[] args) {
      
      Scanner sc = new Scanner(System.in);
      List<Integer> list1 = List.of(10, 20, 19, 30, 40, 50, 13, 10, 20, 5, 60, 70, 80, 30, 90);
      List<Integer> list2 = List.of(30, 40, 50, 60, 60, 70, 100, 110, 20, 20, 120, 130);

      System.out.println("1. find sum, average, min, and max of each list separately.");
      
      IntSummaryStatistics list1Statisctics = list1.stream()
        .mapToInt(Integer::intValue)
        .summaryStatistics();
      System.out.println("\nList 1 statistics: ");
      System.out.println("Count: " + list1Statisctics.getCount());
      System.out.println("Sum: " + list1Statisctics.getSum());
      System.out.println("Minimum: " + list1Statisctics.getMin());
      System.out.println("Maximum: " + list1Statisctics.getMax());
      System.out.println("Average: " + list1Statisctics.getAverage());

      IntSummaryStatistics list2Statisctics = list2.stream()
        .mapToInt(Integer::intValue)
        .summaryStatistics();
      System.out.println("\nList 2 statistics: ");
      System.out.println("Count: " + list2Statisctics.getCount());
      System.out.println("Sum: " + list2Statisctics.getSum());
      System.out.println("Minimum: " + list2Statisctics.getMin());
      System.out.println("Maximum: " + list2Statisctics.getMax());
      System.out.println("Average: " + list2Statisctics.getAverage());

      System.out.println("\n2. Find K most frequent elements from list 1, and list 2 separately.");
      System.out.print("\nEnter k value: ");
      int k = sc.nextInt();
      sc.nextLine();
      Map<Integer, Long> freq1 = list1.stream()
        .collect(Collectors.groupingBy(
          Function.identity(),
          Collectors.counting()
        ));
      System.out.println("\nList 1: ");
      freq1.entrySet().stream()
        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
        .limit(k)
        .forEach(System.out::println);

      Map<Integer, Long> freq2 = list2.stream()
        .collect(Collectors.groupingBy(
          Function.identity(),
          Collectors.counting()
        ));
      System.out.println("\nList 2: ");
      freq2.entrySet().stream()
        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
        .limit(k)
        .forEach(System.out::println);

      System.out.println("\n3. Find second most frequent elements.");
      Optional<Map.Entry<Integer, Long>> secondMostFrequent1 = list1.stream()
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ))
        .entrySet()
        .stream()
        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
        .skip(1)
        .findFirst();

      System.out.println("\nList1: ");
      secondMostFrequent1.ifPresent(System.out::println);

      Optional<Map.Entry<Integer, Long>> secondMostFrequent2 = list2.stream()
        .collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ))
        .entrySet()
        .stream()
        .sorted(Map.Entry.<Integer, Long>comparingByValue().reversed())
        .skip(1)
        .findFirst();

      System.out.println("\nList2: ");
      secondMostFrequent2.ifPresent(System.out::println);

      System.out.println("\n4. Merge two lists into single list using flatMap().");
      List<Integer> mergedList = Stream.of(list1, list2)
        .flatMap(List::stream)
        .toList();
      System.out.println(mergedList);

      System.out.println("\n5. Find common elements from two lists using streams.");
      List<Integer> commonElements = list1.stream()
        .filter(list2::contains)
        .distinct()
        .toList();
      System.out.println(commonElements);

      System.out.println("\n6. Find all prime numbers from list 1 using streams");
      List<Integer> list1Primes = list1.stream()
        .filter(Main::isPrime)
        .toList();
      System.out.println(list1Primes);
    }

  public static boolean isPrime(int n) {
    if (n <= 1) {
      return false;
    }
    for (int i = 2; i <= Math.sqrt(n); i++) {
      if (n % i == 0) {
        return false;
      }
    }

    return true;
  }
}