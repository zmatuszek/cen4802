<%@ page import="ToDoPackage.Singleton" %>
<%! Singleton newInstance = Singleton.getInstance();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do List: Add a To-Do Item</title>
</head>
<body>

<h2>Your To-Do List: Add a To-Do Item</h2>


<%= newInstance.printList() %>
<br/><br/>

<form name="ToDoForm" action="array.jsp">
    <input type="hidden" name="action" value="add" />
    <table>
        <tbody>
        <tr>
            <td>Task Description</td>
            <td>
                <label for="taskDescription"></label>
                <input type="text" name="taskDescription" value="" size="50" />
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="Add New Task" />
    <input type="reset" value="Clear" />
</form>
<form action="index.jsp" method="get">
    <input type="submit" value="Return to Main Menu" />
</form>
</body>
</html>
