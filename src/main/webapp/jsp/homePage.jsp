<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/homePageUtils.js"></script>
    <title>Home</title>
</head>
<body>
<div class="container">
    <h3 class="text-center">Welcome to QuizMaker!</h3>
    <div style="margin-bottom: 10px" align="center">
        <button class="btn btn-success" id="initDatabase">Init database</button>
        <input style="visibility: hidden" type="file" name="questionsFile" id="questionsFile"
               accept="application/json">
        <button class="btn btn-success" id="loadQuestions" type="submit">Load questions</button>
        <button class="btn btn-success" id="loadTasks">Load tasks</button>
    </div>
    <div align="center">
        <button class="btn btn-info" id="showQuestionBtn">Show all questions</button>
        <button class="btn btn-info" id="showTasksBtn">Show all tasks</button>
    </div>
</div>
</body>
</html>
