package ToDoPackage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ToDo {

    private static final Logger logger = LogManager.getLogger(ToDo.class);

    private String description = "";

    private boolean checkbox = false;

    public ToDo() {
        logger.debug("Created empty ToDo");
    }

    public ToDo(String description, boolean checkbox){
        logger.debug("Creating ToDo: desc='{}', checkbox={}", description, checkbox);
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.purgomalum.com/service/plain?text=" + description.replace(" ", "%20")))
//                    .POST(HttpRequest.BodyPublishers.ofString("{\"text\":\"" + description + "\"}"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                this.description = response.body();
                logger.info("Profanity-filtered description applied ({} chars)", this.description.length());
            } else {
                this.description = description;
                logger.warn("Profanity filter service returned status {}. Using raw description.", response.statusCode());
            }
        } catch (java.net.ConnectException | java.net.NoRouteToHostException e) {
            this.description = description;
            logger.error("Could not connect to profanity filter service. Using raw description.");
        } catch (java.net.http.HttpTimeoutException e) {
            this.description = description;
            logger.error("Profanity filter service timed out. Using raw description.");
        } catch (Exception e) {
            this.description = description;
            logger.error("Unexpected error while filtering description. Using raw description.");
        }

        this.checkbox = checkbox;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String newDescription){
        if (newDescription != null && !newDescription.isEmpty())
            this.description = newDescription;

    }

    public boolean getCheckbox() {
        return checkbox;
    }
    public void setCheckbox(boolean newCheckbox){
        this.checkbox = newCheckbox;
    }

    @Override
    public String toString() { // ☑ ☐ important characters
        String str = "";
        if(checkbox) str += "☑ | " + description; //should be ☑
        else str += "☐ | " + description; //should be ☐

        return str;
    }
}
