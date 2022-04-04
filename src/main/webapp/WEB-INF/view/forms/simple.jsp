<%@ include file="../common/header.jspf" %>
<%@ include file="../common/navigation.jspf" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Forms example</title>

</head>
<body>

This is a page with different Forms examples.

Returned Data model: ${todo}
<p>Simple Form with GET</p>
<c:set var="myObjId" value="123" scope="page"/>
<form method="GET" action="<c:url value="/forms/simple/${myObjId}"/>">
    <fieldset>
        <legend>Simple form Data: title</legend>
        <input id="title" name="title"/>
    </fieldset>
    <button id="aId">Action</button>
</form>

<p>Simple Form with POST</p>
<c:set var="myObjId" value="111" scope="page"/>
<form method="POST" action="<c:url value="/forms/simple/${myObjId}"/>">
    <fieldset>
        <legend>Simple form Data: descritpion</legend>
        <input name="descritpion"/>
    </fieldset>
    <button>Action</button>
</form>
<br/>

<a href="/forms/simple-add">Add new element</a>
<c:if test="${not empty brandNew}">
    <form:form method="POST" modelAttribute="brandNew" action="/forms/anAction">
        <fieldset>
            <legend>Simple form Data: new element</legend>
            <table>
                <tr>
                    <td>
                        <form:label path="description" cssErrorClass="error">
                            Description
                        </form:label></td>
                    <td>
                        <form:select path="description">
                            <form:option value="" label="Default"> --SELECT--</form:option>
                            <form:options items="${tags}" itemLabel="value" itemValue="value"></form:options>
                        </form:select></td>
                    <td><form:errors path="description"/></td>
                </tr>
            </table>
        </fieldset>
        <fieldset>
            <table>
                <tr>
                    <td>
                        <form:label path="userName" cssErrorClass="error">
                            Username
                        </form:label></td>
                    <td>
                        <form:select path="userName">
                            <form:option value="NONE" label="NONE">--NONE--</form:option>
                            <form:options items="${criteriaList}" itemLabel="name" itemValue="id"></form:options>
                        </form:select>
                    </td>
                </tr>
            </table>
        </fieldset>
        <button>Action</button>
    </form:form>
</c:if>


<footer>
    <a href="https://docs.spring.io/spring-framework/docs/4.2.x/spring-framework-reference/html/spring-form-tld.html">
        Docs
    </a>
    <br/>
</footer>
</body>
</html>
