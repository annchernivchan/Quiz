<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/questionsUtils.js"></script>
</head>
<body>
<div style="width: 800px" class="container">
    <h3 style="text-align: center">Questions List</h3>

    <c:if test="${questions.size() == 0}">
        <div><em>No questions</em></div>
    </c:if>
    <c:if test="${questions.size() != 0}">
        <table class="table table-bordered table-responsive">
            <tr>
                <th class="col-sm-6 text-center">Question</th>
                <th class="col-sm-1 text-center">Point</th>
                <th class="col-sm-1 text-center">Verified</th>
                <th class="col-sm-1"></th>
                <th class="col-sm-1"></th>
            </tr>
            <c:forEach items="${questions}" var="question" varStatus="status">
                <tr valign="top">
                    <td class="text-center"><span>${question.questionText}</span></td>
                    <td class="text-center"><span>${question.point}</span></td>
                    <td align="center"><img src="${pageContext.servletContext.contextPath}/resources/images/${question.verified ? 'verified' : 'not_verified'}.png"
                            class="imgIsVerified" name="verified"></td>
                    <td class="text-center"><a class="btn btn-primary glyphicon glyphicon-edit"
                                               href="${pageContext.servletContext.contextPath}/editQuestion?id=${question.id}"> Edit</a></td>
                    <td class="text-center"><button class="deleteQuestionLink btn btn-danger glyphicon glyphicon-trash"
                                                    questionId="${question.id}" isInTask="${isInTasks[status.index]}"> Delete</button></td>
                </tr>
            </c:forEach>
        </table>
    </c:if>
    <input class="btn btn-info" type="button" onclick="parent.location='/addQuestion'" value="Add question">
    <input class="btn btn-default" type="button" onclick="parent.location='/'" value="Return to home page">
</div>
</body>
</html>
