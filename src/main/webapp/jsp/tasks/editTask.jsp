<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/utils.js"></script>
    <title>Edit task</title>
</head>
<body>
<h3>
    Editing a task
</h3>
<p style="color: red;">${errorString}</p>
<form action="doEditTask?id=${task.id}" method="post">
    <table>
        <tr>
            <td><p>Task name: </p></td>
            <td><input type="text" name="taskName" value="${task.name}" maxlength="255"
                       class="fieldTaskName"></td>
        </tr>
        <tr>
            <td><p>Total point: </p></td>
            <td><p name="taskPoint" id="totalPoint">${task.totalPoint}</td>
        </tr>
        <tr>
            <td><p>Questions in task: </p></td>
            <td>
                <c:if test="${task.questions.size() == 0}">
                    <em>No questions</em>
                </c:if>

                <c:if test="${task.questions.size() != 0}">
                    <table id="questions" border="1">
                        <tr>
                            <th>Question</th>
                            <th>Point</th>
                            <th>In task</th>
                        </tr>
                        <c:forEach items="${task.questions}" var="question">
                            <tr valign="top" id="trQuestion">
                                <td><span>${question.questionText}</span></td>
                                <td class="tdQuestionPoint"><span class="fieldQuestionPoint">${question.point}</span></td>
                                <td align="center"><input type="checkbox" name="isInTask" value="${question.id}"
                                                          class="questionCheck" checked></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><p>Available questions: </p></td>
            <td>
                <c:if test="${availableQuestions.size() == 0}">
                    <em>No questions</em>
                </c:if>

                <c:if test="${availableQuestions.size() != 0}">
                    <table id="available_questions" border="1">
                        <tr>
                            <th>Question</th>
                            <th>Point</th>
                            <th>In task</th>
                        </tr>
                        <c:forEach items="${availableQuestions}" var="question">
                            <tr valign="top" id="trQuestion">
                                <td><span>${question.questionText}</span></td>
                                <td class="tdQuestionPoint"><span class="fieldQuestionPoint">${question.point}</span></td>
                                <td align="center"><input type="checkbox" name="isInTask" value="${question.id}"
                                                          class="questionCheck" unchecked>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Save">
                <a href="${pageContext.servletContext.contextPath}/tasks">Cancel</a></td>
        </tr>
    </table>
</form>

</body>
</html>
