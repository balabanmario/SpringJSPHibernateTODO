<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<div class="container">
    <div>
        <a type="button" class="btn btn-primary btn-md" href="/add-todo">Add Todo</a>
        <a type="button" class="btn btn-primary btn-md" href="/add-todo-with-subtask">Add Super Todo</a>
    </div>
    <br>
    <div class="panel panel-primary">
        <div class="panel-heading">
            <h3>List of TODO's</h3>
        </div>
        <div class="panel-body">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th width="40%">Description</th>
                    <th width="40%">Target Date</th>
                    <th width="20%"></th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${todos}" var="todo">
                    <tr>
                        <td>${todo.description}</td>
                        <td><fmt:formatDate value="${todo.targetDate}"
                                            pattern="dd/MM/yyyy"/>
						</td>
                        <td><a type="button" class="btn btn-success"
                               href="/update-todo?id=${todo.id}">Update</a>
                        </td>
                        <td><a type="button" class="btn btn-success"
                               href="/update-super-todo?id=${todo.id}">Update Super</a>
                        </td>
                        <td><a type="button" class="btn btn-warning"
                               href="/delete-todo?id=${todo.id}">Delete</a>
						</td>
                        <td><a type="button" class="btn btn-warning"
                               href="/add-todo-to-inbox?id=${todo.id}">Add to inbox</a>
						</td>
                        <td><a type="button" class="btn btn-warning"
                               href="/clear-inbox">Clear inbox</a>
						</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>

            <span>
				Inbox size: ${fn:length(inbox.todoList)}
			</span>
        </div>
    </div>

</div>
<%@ include file="common/footer.jspf" %>