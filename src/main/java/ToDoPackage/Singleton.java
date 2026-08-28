package ToDoPackage;

import java.util.ArrayList;

public class Singleton {

    private static Singleton firstInstance = null;
    private ArrayList<ToDo> todoList = new ArrayList<ToDo>();

    private Singleton(){ }

    public static Singleton getInstance() {

        if(firstInstance == null) {
            firstInstance = new Singleton();
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

    public void addTask(ToDo newTask) { todoList.add(newTask); }

    public boolean isEmpty() {
        return todoList.isEmpty();
    }

    public long getArraySize() {
        return todoList.size();
    }

    public void removeTask(int index) {
        //System.out.println("[Removed task number " + index + "]");
        todoList.remove((index - 1));
    }

    public void toggleCheckboxByIndex(int index) {
        //System.out.println("[Toggled checkbox for task number " + index + "]");
        todoList.get(index - 1).setCheckbox(!todoList.get(index - 1).getCheckbox());
    }

    public void clearTasks() {
        //System.out.println("[ALL TASKS HAVE BEEN CLEARED]");
        todoList.clear();
    }

    public String printList() {
        String str = "";
        if (todoList.isEmpty())
            str += "Todo list is Empty.";
        else{
            //str += "[Todo List]<br/>";
            for(int i = 0; i < todoList.size(); i++) {
                str += ("[" + (i + 1) + "] " + encodeHtml(todoList.get(i).toString()) + "<br/>");
            }
        }
        return str;
    }
}


