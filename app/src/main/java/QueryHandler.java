import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * This class handles establishing the connection, making the query to google.books.com
 * and parsing the JSON response.
 *
 * The class should only be accessed through fetchBookData
 */

public final class QueryHandler {
    public static final String LOG_TAG = QueryHandler.class.getSimpleName();

    private QueryHandler() {
    }

    private static URL createURL(String url) {
        return null;
    }

    private static HttpURLConnection makeConnection(URL url) {
        return null;
    }

    private static String getRawJSONFromStream(HttpURLConnection httpUrlCon) {
        return null;
    }

    private static List<Book> parseJSONData(String rawJSON) {
        return null;
    }

    public static List<Book> fetchBookData(String url) {
        return null;
    }

}
