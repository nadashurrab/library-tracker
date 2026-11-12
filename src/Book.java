/**
 * A single book in the library.
 */
public record Book(int id, String title, String author) {

    @Override
    public String toString() {
        return String.format("#%-3d %-35s by %s", id, title, author);
    }
}
