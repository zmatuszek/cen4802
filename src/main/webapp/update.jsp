<%@ page import="ToDoPackage.Singleton" %>
<%! Singleton newInstance = Singleton.getInstance();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>To-Do List: Check a To-Do Item</title>
</head>
<body>

<h2>Your To-Do List: Check a To-Do Item</h2>

<%= newInstance.printList() %>
<br/><br/>

<form name="ToDoForm" action="array.jsp">
    <input type="hidden" name="action" value="update" />
    <table>
        <tbody>
        <tr>
            <td>Task Number</td>
            <td>
                <label for="taskUpdate"></label>
                <select name = "updateIndex" id = "updateIndex">
                    <% for (int i = 0; i < newInstance.getArraySize(); i++) {%>
                    <option value="<%= i + 1 %>"><%= (i + 1) %></option>
                    <%} %>
                </select>
            </td>
        </tr>
        </tbody>
    </table>
    <input type="submit" value="Toggle Task Check" />

</form>
<form action="index.jsp" method="get">
    <input type="submit" value="Return to Main Menu" />
</form>
</body>
</html>