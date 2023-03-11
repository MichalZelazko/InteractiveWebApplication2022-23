<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional //EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Quadratic Equation</title>
</head>
<body>
<h1>${message}</h1>
<h2>Quadratic Equation Solver</h2>
<form:form method="post" action="solveQuadraticEquation.html" modelAttribute="quadraticEquation">
    <form:label path="a" type="number">Parameter a</form:label>
    <form:input path="a"/>
    <form:errors path="a" cssStyle="color:red;"/>
    <br>
    <form:label path="b" type="number">Parameter b</form:label>
    <form:input path="b"/>
    <form:errors path="b" cssStyle="color:red;"/>
    <br>
    <form:label path="c" type="number">Parameter c</form:label>
    <form:input path="c"/>
    <form:errors path="c" cssStyle="color:red;"/>
    <br>
    <input type="submit" value="Solve the quadratic equation"/>
</form:form>
</body>
</html>