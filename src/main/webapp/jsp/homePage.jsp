<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/utils.js"></script>
    <title>Home</title>
</head>
<body>
<div class="container">
    <h3 class="text-center">Welcome to QuizMaker!</h3>
    <div align="center">
     <button class="btn btn-info" id="showQuestionBtn">Show all questions</button>
        <button style="margin-left: 10px" class="btn btn-info" id="showTasksBtn">Show all tasks</button>
    </div>
</div>
</body>
</html>
