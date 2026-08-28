package ToDoPackage;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ToDo {

    private String description = "";

    private boolean checkbox = false;

    public ToDo() {
    }

    public ToDo(String description, boolean checkbox){
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create("https://www.purgomalum.com/service/plain?text=" + description.replace(" ", "%20")))
//                    .POST(HttpRequest.BodyPublishers.ofString("{\"text\":\"" + description + "\"}"))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                this.description = response.body();
            } else {
                this.description = description;
            }
        } catch (Exception e) {
            this.description = description;
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
    public String toString() {
        String str = "";
        if(checkbox) str += "☑ | " + description;
        else str += "☐ | " + description;

        return str;
    }
}
