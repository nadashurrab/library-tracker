import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Loads and saves books from/to a small JSON file (books.json).
 * Uses only the Java standard library, so no build tool is needed.
 */
public class BookStore {

    private static final Pattern BOOK_PATTERN = Pattern.compile(
            "\\{\\s*\"id\"\\s*:\\s*(\\d+)\\s*,\\s*\"title\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"\\s*,"
                    + "\\s*\"author\"\\s*:\\s*\"((?:[^\"\\\\]|\\\\.)*)\"\\s*}");

    private final Path file;
    private final List<Book> books = new ArrayList<>();

    public BookStore(Path file) throws IOException {
        this.file = file;
        load();
    }

    public List<Book> all() {
        return List.copyOf(books);
    }

    public Book add(String title, String author) throws IOException {
        Book book = new Book(nextId(), title.trim(), author.trim());
        books.add(book);
        save();
        return book;
    }

    private int nextId() {
        int max = 0;
        for (Book book : books) {
            max = Math.max(max, book.id());
        }
        return max + 1;
    }

    private void load() throws IOException {
        books.clear();
        if (!Files.exists(file)) {
            return;
        }
        String json = Files.readString(file, StandardCharsets.UTF_8);
        Matcher m = BOOK_PATTERN.matcher(json);
        while (m.find()) {
            books.add(new Book(Integer.parseInt(m.group(1)), unescape(m.group(2)), unescape(m.group(3))));
        }
    }

    private void save() throws IOException {
        StringBuilder sb = new StringBuilder("[\n");
        for (int i = 0; i < books.size(); i++) {
            Book b = books.get(i);
            sb.append(String.format("  {\"id\": %d, \"title\": \"%s\", \"author\": \"%s\"}",
                    b.id(), escape(b.title()), escape(b.author())));
            sb.append(i < books.size() - 1 ? ",\n" : "\n");
        }
        sb.append("]\n");
        Files.writeString(file, sb.toString(), StandardCharsets.UTF_8);
    }

    private static String escape(String s) {
        return s.replace("\\", "\\\\").replace("\"", "\\\"");
    }

    private static String unescape(String s) {
        return s.replace("\\\"", "\"").replace("\\\\", "\\");
    }
}
