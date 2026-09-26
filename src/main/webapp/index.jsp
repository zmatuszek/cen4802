<%@ page import="ToDoPackage.Singleton" %>
<%! Singleton newInstance = Singleton.getInstance();%>

<html>
<body style="Background-Color: #80ef80">

<h2>Your To-Do List: Main Menu</h2>

<%= newInstance.printList() %>
<br/><br/>

<form action="input.jsp" method="get">
    <input type="submit" value="Add new Task" />
</form>

<form action="update.jsp" method="get">
    <input type="submit" value="Check off a Task" />
</form>

<form action="remove.jsp" method="get">
    <input type="submit" value="Remove a Task" />
</form>

<form action="array.jsp" method="get">
    <input type="hidden" name="action" value="clear" />
    <input type="submit" value="Remove all Tasks" />
</form>

</body>
</html>
