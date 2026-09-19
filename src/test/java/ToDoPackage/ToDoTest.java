package ToDoPackage;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ToDoTest {

    @Test
    @DisplayName("Default constructor initializes empty description and unchecked state")
    void defaultConstructor() {
        ToDo todo = new ToDo();
        assertEquals("", todo.getDescription());
        assertFalse(todo.getCheckbox());
    }

    @Test
    @DisplayName("Setters update fields and getters reflect them")
    void settersAndGetters() {
        ToDo todo = new ToDo();
        todo.setDescription("Buy milk");
        todo.setCheckbox(true);

        assertEquals("Buy milk", todo.getDescription());
        assertTrue(todo.getCheckbox());
    }

    @Test
    @DisplayName("toString shows checkbox and description correctly when checked")
    void toStringChecked() {
        ToDo todo = new ToDo();
        todo.setDescription("Submit report");
        todo.setCheckbox(true);
        assertEquals("☑ | Submit report", todo.toString());
    }

    @Test
    @DisplayName("toString shows checkbox and description correctly when unchecked")
    void toStringUnchecked() {
        ToDo todo = new ToDo();
        todo.setDescription("Read book");
        todo.setCheckbox(false);
        assertEquals("☐ | Read book", todo.toString());
    }

    @Test
    @DisplayName("setDescription ignores null or empty values")
    void setDescriptionIgnoresNullOrEmpty() {
        ToDo todo = new ToDo();
        todo.setDescription("First");
        todo.setDescription("");
        assertEquals("First", todo.getDescription(), "Empty string should be ignored");
        todo.setDescription(null);
        assertEquals("First", todo.getDescription(), "Null should be ignored");
    }
}
