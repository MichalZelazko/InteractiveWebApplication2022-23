<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional //EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Add student</title>
</head>
<body>
<h1>${message}</h1>
<h2>Student</h2>
<form:form method="post" action="addStudent.html" modelAttribute="student">
    <form:label path="name">Name</form:label>
    <form:input path="name"/>
    <br>
    <form:label path="surname">Surname</form:label>
    <form:input path="surname"/>
    <br>
    <form:label path="email">Email</form:label>
    <form:input path="email"/>
    <br>
    <form:label path="telephone">Telephone</form:label>
    <form:input path="telephone"/>
    <br>
    <input type="submit" value="Add Student"/>
</form:form>
</body>
</html>