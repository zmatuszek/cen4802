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
        this.description = description;
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
