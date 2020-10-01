<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit List</title>
</head>
<body>
<h2>Edit My Shopping List</h2>

<form action ="editItemServlet"method="post">
Store: <input type ="text"name ="store"value="${itemToEdit.store}">
Item: <input type ="text"name ="item"value="${itemToEdit.item}">
<input type ="hidden"name ="id"value="${itemToEdit.id}">
<input type ="submit"value="Save Edited Item">
</form>
</body>
</html>