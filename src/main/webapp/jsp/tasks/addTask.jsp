<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="${pageContext.servletContext.contextPath}/resources/css/awesome-bootstrap-checkbox.css">
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/utils.js"></script>
    <title>Add task</title>
</head>
<body>
<div class="container" style="width: 800px;">
    <h3>
        Creating a task
    </h3>
    <div class="errorContainer">
    </div>
    <form action="doAddTask" method="post">
        <table class="table">
            <tr>
                <td><p>Task name: </p></td>
                <td><input type="text" name="taskName" class="fieldQuestionText form-control has-success" value=""
                           maxlength="255"></td>
            </tr>
            <tr>
                <td><p>Total point: </p></td>
                <td><p style="width: 30%" id="totalPoint">0.0</p></td>
            </tr>
            <tr>
                <td><p>Available questions: </p></td>
                <td>
                    <c:if test="${questions.size() == 0}">
                        <em>No questions</em>
                    </c:if>

                    <c:if test="${questions.size() != 0}">
                        <table id="available_questions" class="table table-bordered">
                            <tr>
                                <th class="text-center col-sm-3">Question</th>
                                <th class="text-center col-sm-2">Point</th>
                                <th class="text-center col-sm-1">In task</th>
                            </tr>
                            <c:forEach items="${questions}" var="question">
                                <tr valign="top" id="trQuestion">
                                    <td class="text-center"><span
                                            class="fieldQuestionText">${question.questionText}</span></td>
                                    <td class="tdQuestionPoint text-center"><span
                                            class="fieldQuestionPoint">${question.point}</span></td>
                                    <td align="center" id="tdQuestionCheck"><input type="checkbox" name="isInTask"
                                                                                   class="questionCheck checkbox checkbox-primary"
                                                                                   value="${question.id}"></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </c:if>
                </td>
            </tr>
            <tr>
                <td></td>
                <td align="right">
                    <input id="saveTaskBtn" class="btn btn-success" type="submit" value="Save">
                    <a class="btn btn-danger" href="${pageContext.servletContext.contextPath}/tasks">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
