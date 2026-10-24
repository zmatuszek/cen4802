package ToDoPackage;

import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Singleton {

    private static final Logger logger = LogManager.getLogger(Singleton.class);

    private static Singleton firstInstance = null;
    private ArrayList<ToDo> todoList = new ArrayList<ToDo>();

    private Singleton(){ }

    public static Singleton getInstance() {

        if(firstInstance == null) {
            firstInstance = new Singleton();
            logger.info("Singleton instance created");
        }
        return firstInstance;
    }

    private String encodeHtml(String input) {
        if (input == null) return "";
        return input.replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#x27;");
    }

    public void addTask(ToDo newTask) { 
        todoList.add(newTask); 
        logger.debug("Task added. Total now: {}", todoList.size());
    }

    public boolean isEmpty() {
        return todoList.isEmpty();
    }

    public long getArraySize() {
        return todoList.size();
    }

    public void removeTask(int index) {
        todoList.remove((index - 1));
        logger.info("Removed task at index {}.", index);
    }

    public void toggleCheckboxByIndex(int index) {
        todoList.get(index - 1).setCheckbox(!todoList.get(index - 1).getCheckbox());
        logger.debug("Toggled checkbox for task {}", index);
    }

    public void clearTasks() {
        todoList.clear();
        logger.warn("All tasks have been cleared");
    }

    public String printList() {
        String str = "";
        if (todoList.isEmpty())
            str += "Todo list is Empty.";
        else{
            for(int i = 0; i < todoList.size(); i++) {
                str += ("[" + (i + 1) + "] " + encodeHtml(todoList.get(i).toString()) + "<br/>");
            }
        }
        return str;
    }
}


