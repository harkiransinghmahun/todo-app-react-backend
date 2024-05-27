<%@include file="common/header.jspf"%>
<%@include file="common/navigation.jspf"%>
<div class="container">
    <h1>Your todos are: </h1>
        <table class="table">
            <thead>
                <th>ID</th>
                <th>Description</th>
                <th>Target Date</th>
                <th>Is Completed</th>
                <th>Update</th>
                <th>Delete</th>
            </thead>
            <tbody>
                <c:forEach items="${todos}" var="todos">
                    <tr>
                        <td>${todos.id}</td>
                        <td>${todos.description}</td>
                        <td>${todos.targetDate}</td>
                        <td>${todos.done}</td>
                        <td><a href="update-todo?id=${todos.id}" class="btn btn-success">Update</a> </td>
                        <td><a href="delete-todo?id=${todos.id}" class="btn btn-warning">Delete</a> </td>
                    </tr>
                </c:forEach>
            </tbody>

        </table>
<a href="add-todo" class="btn btn-success">Add Todo</a>
</div>
<%@include file="common/footer.jspf"%>
