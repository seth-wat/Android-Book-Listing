/**
 * For each book returned in the search query response a Book object will be created
 */

public class Book {
    private String title;
    private String[] authors;
    private String description;
    private int pageCount;
    private double rating;

    public Book(String title, String[] authors, String description, int pageCount, double rating) {
        setTitle(title);
        setAuthors(authors);
        setDescription(description);
        setPageCount(pageCount);
        setRating(rating);
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getDescription() {
        return description;
    }

    public int getPage_count() {
        return pageCount;
    }

    public double getRating() {
        return rating;
    }


    private void setTitle(String title) {
        this.title = title;
    }

    private void setAuthors(String[] authors) {
        this.authors = authors;
    }

    private void setDescription(String description) {
        this.description = description;
    }

    private void setPageCount(int page_count) {
        this.pageCount = page_count;
    }

    private void setRating(double rating) {
        this.rating = rating;
    }


}
