import java.nio.file.Path;
import java.util.List;

/**
 * Library Tracker - command-line entry point.
 *
 * Usage:
 *   java -cp out App add "Title" "Author"
 *   java -cp out App list
 */
public class App {

    private static final Path DATA_FILE = Path.of("books.json");

    public static void main(String[] args) throws Exception {
        print_banner();
        if (args.length == 0) {
            printUsage();
            return;
        }

        BookStore store = new BookStore(DATA_FILE);
        switch (args[0]) {
            case "add" -> {
                if (args.length < 3) {
                    System.out.println("Usage: add \"Title\" \"Author\"");
                    return;
                }
                try {
                    Book book = store.add(args[1], args[2]);
                    System.out.println("Added: " + book);
                } catch (IllegalArgumentException e) {
                    System.out.println("Error: " + e.getMessage());
                }
            }
            case "list" -> printBooks(store.all(), "No books yet.");
            default -> printUsage();
        }
    }

    static void print_banner() {
        System.out.println("=== Library Tracker ===");
    }

    private static void printBooks(List<Book> books, String emptyMessage) {
        if (books.isEmpty()) {
            System.out.println(emptyMessage);
            return;
        }
        books.forEach(System.out::println);
    }

    private static void printUsage() {
        System.out.println("Commands: add \"Title\" \"Author\" | list");
    }
}
