<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/questionsUtils.js"></script>
    <title>Add question</title>
</head>
<body>
<div style="width: 800px" class="container">
    <h3>
        Adding a question
    </h3>
    <div class="errorContainer">
    </div>
    <form class="formQuestion" toUrl="/doAddQuestion">
        <table class="table">
            <tr>
                <td><p>Question text: </p></td>
                <td><input type="text" name="questionText" value="${question.questionText}" maxlength="255"
                           id="fieldQuestionText" class="form-control"></td>
            </tr>
            <tr>
                <td><p>Point: </p></td>
                <td><input style="width: 30%" type="text" class="fieldQuestionPoint form-control" name="questionPoint"
                           value="${question.questionPoint}"
                           maxlength="6"></td>
            </tr>
            <tr>
                <td><p>Question type: </p></td>
                <td><select style="width: 70%" class="form-control" name="questionType">
                    <c:forEach items="${types}" var="type">
                        <option value="${type}">${type}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td><p>Answers: </p></td>
                <td id="answersTableTd">
                    <em>No answers</em>
                </td>
                <td></td>
                <td>
                    <button class="btn btn-warning" id="addAnswer">Add answer</button>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="right">
                    <input type="button" class="saveQuestion btn btn-success" value="Save">
                    <a href="${pageContext.servletContext.contextPath}/questions" class="btn btn-danger">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>

</html>
