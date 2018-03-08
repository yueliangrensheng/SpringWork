<%@ taglib prefix="c" uri="http://www.springframework.org/tags" %>
<%--
  Created by IntelliJ IDEA.
  User: zhaishaoping
  Date: 03/03/2018
  Time: 2:12 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Product Form</title>
    <style type="text/css">@import url("<c:url value="../css/main.css"/>");</style>
</head>
<body>
<form method="post" action="save-product">
    <h1>Add Product
        <span>Please use this form to enter product details</span>
    </h1>

    ${empty requestScope.errors? "" : "<p style='color:red'>"
            += "Error(s)!"
            += "<url>"
            }

    <!-- ${requestScope.errors.stream().map(
        x -> "--><li>" += x += "</li><!--"
    ).toList()} -->

    ${empty requestScope.errors?"":"</url></p>"}

    <label>
        <span>Product Name:</span>
        <input id="name" type="text" name="name"
               placeholder="The complete product name"
               value="${form.name}">
    </label>

    <label>
        <span>Description:</span>
        <input id="description" type="text" name="description"
               placeholder="Product description"
               value="${form.description}">
    </label>

    <label>
        <span>Price:</span>
        <input id="price" name="price" type="number" step="any"
               placeholder="Product price in #.## format"
               value="${form.price}">
    </label>

    <label>
        <span>&nbsp;</span>
        <input type="submit">
    </label>

</form>
</body>
</html>
