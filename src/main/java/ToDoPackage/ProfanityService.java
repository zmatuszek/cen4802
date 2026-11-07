package ToDoPackage;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Centralized, reusable profanity filtering service.
 *
 * Notes:
 * - Uses a single shared HttpClient instance with short timeouts.
 * - Fails fast and returns the original text on any error.
 * - Stateless and thread-safe.
 */
public final class ProfanityService {
    private static final Logger logger = LogManager.getLogger(ProfanityService.class);

    private static final Duration CONNECT_TIMEOUT = Duration.ofSeconds(2);
    private static final Duration REQUEST_TIMEOUT = Duration.ofSeconds(2);

    private static final HttpClient CLIENT = HttpClient.newBuilder()
            .connectTimeout(CONNECT_TIMEOUT)
            .build();

    private ProfanityService() {}

    public static String filter(String text) {
        if (text == null || text.isBlank()) return "";
        try {
            String encoded = URLEncoder.encode(text, StandardCharsets.UTF_8);
            String url = "https://www.purgomalum.com/service/plain?text=" + encoded;
            HttpRequest req = HttpRequest.newBuilder()
                    .uri(URI.create(url))
                    .timeout(REQUEST_TIMEOUT)
                    .GET()
                    .build();
            HttpResponse<String> resp = CLIENT.send(req, HttpResponse.BodyHandlers.ofString());
            if (resp.statusCode() == 200 && resp.body() != null) {
                String body = resp.body();
                logger.debug("Profanity filter applied ({} chars)", body.length());
                return body;
            }
            logger.warn("Profanity service returned status {}. Using raw text.", resp.statusCode());
            return text;
        } catch (Exception e) {
            logger.debug("Profanity service failed ({}). Using raw text.", e.toString());
            return text;
        }
    }
}
