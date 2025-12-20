<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Register Business</title>
    <link rel="stylesheet" href="css/styles.css">
</head>
<body>
<jsp:include page="header.jsp" />
<div class="container">
    <h2>Register Your Business</h2>
    <c:if test="${not empty error}">
        <div class="error">${error}</div>
    </c:if>
    <c:if test="${not empty message}">
        <div class="message">${message}</div>
    </c:if>

    <form action="register" method="post" class="form">
        <label>Name*</label><input type="text" name="name" required>
        <label>Owner*</label><input type="text" name="owner" required>
        <label>Category</label><input type="text" name="category">
        <label>Address</label><input type="text" name="address">
        <label>Phone</label><input type="text" name="phone">
        <button type="submit" class="btn">Register</button>
    </form>
</div>
<jsp:include page="footer.jsp" />
</body>
</html>
