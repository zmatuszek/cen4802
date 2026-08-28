<%@ page import="ToDoPackage.Singleton" %>
<%@ page import="ToDoPackage.ToDo" %>
<%! Singleton newInstance = Singleton.getInstance();%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>ToDo List</title>

</head>

<body>
<%
    if(request.getParameter("action").equals("add")){
        String description = request.getParameter("taskDescription");
        newInstance.addTask(new ToDo(description, false));

    } else if (request.getParameter("action").equals("remove")){
        String removeIndex = request.getParameter("removeIndex");
        if(removeIndex != null && !removeIndex.trim().isEmpty()) {
                int index = Integer.parseInt(removeIndex);
                newInstance.removeTask(index);
        }
    } else if (request.getParameter("action").equals("update")){
        String updateIndex = request.getParameter("updateIndex");
        if(updateIndex != null && !updateIndex.trim().isEmpty()) {
            int index = Integer.parseInt(updateIndex);
            newInstance.toggleCheckboxByIndex(index);
        }
    } else if (request.getParameter("action").equals("clear")){
        newInstance.clearTasks();
    } else {}

    response.sendRedirect("index.jsp");
%>
</body>
</html>