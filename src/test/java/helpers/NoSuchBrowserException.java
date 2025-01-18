package helpers;

/**
 * NoSuchBrowserException is an exception that is thrown when a browser is not
 * supported or the name of the browser is incorrect.
 */
public class NoSuchBrowserException extends Exception {
    public NoSuchBrowserException(String browser) {
        super("Browser not supported or the name of the browser is incorrect: " + browser);
    }
}
