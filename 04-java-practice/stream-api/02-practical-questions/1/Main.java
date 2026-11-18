import java.util.List;
import java.util.Scanner;

class Main {
    public static void main(String[] args) {
      
      Scanner sc = new Scanner(System.in);
      List<String> technologies = List.of(
          "Java", "Python", "JavaScript", "TypeScript", "C", "C++", "C#", "Go", "Rust", "TypeScript",
          "Kotlin", "Swift", "PHP", "Ruby", "Dart", "R", "Scala", "Perl", "Objective-C", "SQL", "Assembly",
          "MySQL", "PostgreSQL", "MongoDB", "MySQL", "Oracle Database", "Microsoft SQL Server", 
          "SQLite", "Redis", "Cassandra", "Amazon DynamoDB", "Firebase Firestore", "MariaDB", "CouchDB",
          "Neo4j", "Elasticsearch", "InfluxDB", "CockroachDB", "Microsoft SQL Server", "Supabase",
          "Windows", "Linux", "Ubuntu", "Debian", "Fedora",
          "Arch Linux", "macOS", "Android", "iOS", "Unix", "FreeBSD", "ChromeOS", "Kali Linux", "Red Hat Enterprise Linux", "CentOS",
          "ChatGPT", "Google Gemini", "Claude", "GitHub Copilot", "Cursor", "Windsurf", "Amazon Q Developer", "Tabnine", 
          "Codeium", "Replit AI", "v0", "Lovable", "Bolt.new", "Perplexity AI", "Hugging Face", "Ollama", "LangChain",
          "CrewAI", "AutoGen", "TensorFlow", "PyTorch", "Scikit-learn",
          "React", "Angular", "Vue.js", "Next.js", "Node.js", "Express.js", "Spring Boot", "Django", "Flask", "FastAPI", "Laravel", 
          "ASP.NET Core", "Hibernate", "Flutter", "React Native", "Bootstrap", "Tailwind CSS", "Flutter",
          "Visual Studio Code", "IntelliJ IDEA", "Eclipse", "PyCharm", "Visual Studio", "Git", "GitHub", "GitLab", "Bitbucket",
          "Postman", "Docker", "Kubernetes", "Jenkins", "Maven", "Gradle", "Jira", "Figma", "Vercel", "Netlify", "Render",
          "Amazon Web Services", "Microsoft Azure", "Google Cloud Platform", "Oracle Cloud", "IBM Cloud",
          "DigitalOcean", "Cloudflare", "Firebase", "Heroku"
      );
      
      System.out.println("1. Find all the names that starts with a specific letter.");
      System.out.print("Enter any letter: ");
      String letter1 = sc.nextLine();
      technologies.stream()
        .filter(name -> name.toLowerCase().startsWith(letter1.toLowerCase()))
        .forEach(System.out::println);

      System.out.println("\n2. Find all the content that starts with J and ends with a.");
      technologies.stream()
        .filter(name -> name.toLowerCase().startsWith("j") && name.toLowerCase().endsWith("a"))
        .forEach(System.out::println);

      System.out.println("\n3. Find all the content that starts with specific letter but in uppercase.");
      System.out.print("Enter any letter: ");
      String letter2 = sc.nextLine();
      technologies.stream()
        .filter(name -> name.startsWith(letter2.toUpperCase()))
        .forEach(System.out::println);

      System.out.println("\n4. Find all the content that starts with specific letter but produce distinct content.");
      System.out.print("Enter any letter: ");
      String letter3 = sc.nextLine();
      technologies.stream()
        .filter(name -> name.toLowerCase().startsWith(letter3.toLowerCase()))
        .distinct()
        .forEach(System.out::println);

      System.out.println("\n5. Find all the content that starts with specific letter but in uppercase and distinct content only.");
      System.out.print("Enter any letter: ");
      String letter4 = sc.nextLine();
      technologies.stream()
        .filter(name -> name.startsWith(letter4.toUpperCase()))
        .distinct()
        .forEach(System.out::println);

      System.out.println("\n6. Find all content with specific letter but in sorted order.");
      System.out.print("Enter any letter: ");
      String letter5 = sc.nextLine();
      technologies.stream()
        .filter(name -> name.toLowerCase().startsWith(letter5.toLowerCase()))
        .sorted()
        .forEach(System.out::println);

      System.out.println("\n7. Find all the distinct content starting with specific letter in upper case and sorted order.");
      System.out.print("Enter any letter: ");
      String letter6 = sc.nextLine();
      technologies.stream()
        .filter(name -> name.toLowerCase().startsWith(letter6.toLowerCase()))
        .map(String::toUpperCase)
        .distinct()
        .forEach(System.out::println);

      System.out.println("\n8. Find the count of distinct content starting with specific letter.");
      System.out.print("Enter any letter: ");
      String letter7 = sc.nextLine();
      long count = technologies.stream()
        .filter(name -> name.toLowerCase().startsWith(letter7.toLowerCase()))
        .distinct()
        .count();
      System.out.println("Count of names starts with " + letter7 + ": " + count);

      System.out.println("\n9. Find first element of stream which filters data starting with S.");
      String firstNameWithS = technologies.stream()
        .filter(name -> name.toLowerCase().startsWith("s"))
        .findFirst()
        .orElse(null);
      System.out.println("First name starting with S is: " + firstNameWithS);
      
      System.out.println("\n10. Check whether any element or content starts with s from given ArrayList.");
      boolean anyMatchResult = technologies.stream()
        .anyMatch(name -> name.startsWith("S"));
      System.out.println("Any name start with S: " + anyMatchResult);

      System.out.println("\n11. Check whether all elements or contents starts with S from the given ArrayList.");
      boolean allMatchResult = technologies.stream()
        .allMatch(name -> name.startsWith("S"));
      System.out.println("All names start with S: " + allMatchResult);
    }
}