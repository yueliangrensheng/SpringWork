<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: zhaishaoping
  Date: 13/03/2018
  Time: 3:24 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Book List</title>
    <style type="text/css">@import url("<c:url value="../../css/main.css"/>");</style>
</head>
<body>
<div id="global">
    <h1>Book List</h1>
    <a href="<c:url value="/input-book"/>">Add Book</a>
    <table>
        <tr>
            <th>Category</th>
            <th>Title</th>
            <th>ISBN</th>
            <th>Author</th>
            <th>Price</th>
            <th>&nbsp;</th>
        </tr>

        <c:forEach var="book" items="${books}">
            <tr>
                <td>${book.category.name}</td>
                <td>${book.title}</td>
                <td>${book.isbn}</td>
                <td>${book.author}</td>
                <td>${book.price}</td>
                <td><a href="/edit-book/${book.id}"></a></td>
            </tr>
        </c:forEach>
    </table>

</div>

</body>
</html>
