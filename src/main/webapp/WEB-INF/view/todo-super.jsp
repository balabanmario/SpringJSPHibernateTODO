<%@ include file="common/header.jspf" %>
<%@ include file="common/navigation.jspf" %>

<style type="text/css">
    td {
        padding: 0 15px;
    }
</style>

<div class="container">
    <div class="row">
        <div class="col-md-6 col-md-offset-3 ">
            <div class="panel panel-primary">
                <div class="panel-heading">Add TODO</div>
                <div class="panel-body">
                    <p><a type="button" class="btn btn-success"
                          href="/add-subtask">Add Subtask Get</a>
                    </p>
                    <form:form action="/add-todo-with-subtask" method="post" modelAttribute="todo">
                        <button value="add-sbtsk" name="add-sbtsk" type="submit" class="btn btn-success">Add Subtask Btn</button>

                        <form:hidden path="id"/>
                        <fieldset class="form-group">
                            <form:label path="description">Description</form:label>
                            <form:input path="description" type="text" class="form-control"
                                        required="required"/>
                            <form:errors path="description" cssClass="text-warning"/>
                        </fieldset>

                        <fieldset class="form-group">
                            <form:label path="targetDate">Target Date</form:label>
                            <form:input path="targetDate" type="text" class="form-control"
                                        required="required"/>
                            <form:errors path="targetDate" cssClass="text-warning"/>
                        </fieldset>

                        <table>
                            <tbody>


                            <c:forEach items="${todo.subTasks}" varStatus="theCount" var="subTask">
                                <tr>
                                    <td>
                                        <fieldset class="form-group">
                                            <form:input path="subTasks[${theCount.index}].name" value="${subTask.name}"/>

                                        </fieldset>
                                    </td>
                                    <td>
                                        <fieldset class="form-group">
                                            <form:label path="subTasks[${theCount.index}].tag.value" cssErrorClass="error">
                                                Tag
                                            </form:label>
                                            <form:select path="subTasks[${theCount.index}].tag.value">
                                                <form:option value="" label="Default"> --SELECT--</form:option>
                                                <form:options items="${tags}" itemLabel="value" itemValue="value"/>
                                            </form:select>
                                        </fieldset>

                                    </td>
                                    <td>
                                        <a href="/delete-subtask?index=${theCount.index}">Delete</a>
                                    </td>
                                </tr>

                            </c:forEach>
                            </tbody>
                        </table>


                        <button type="submit" class="btn btn-success">Save</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="common/footer.jspf" %>