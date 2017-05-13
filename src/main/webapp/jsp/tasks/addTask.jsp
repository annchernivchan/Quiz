<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/utils.js"></script>
    <title>Add task</title>
</head>
<body>
<h3>
    Creating a task
</h3>
<form action="doAddTask" method="post">
    <table>
        <tr>
            <td><p>Task name: </p></td>
            <td><input type="text" name="taskName" class="fieldQuestionText" value="" maxlength="255"></td>
        </tr>
        <tr>
            <td><p>Total point: </p></td>
            <td><p id="totalPoint">0.0</p></td>
        </tr>
        <tr>
            <td><p>Available questions: </p></td>
            <td>
                <c:if test="${questions.size() == 0}">
                    <em>No questions</em>
                </c:if>

                <c:if test="${questions.size() != 0}">
                    <table id="available_questions" border="1">
                        <tr>
                            <th>Question</th>
                            <th>Point</th>
                            <th>In task</th>
                        </tr>
                        <c:forEach items="${questions}" var="question">
                            <tr valign="top" id="trQuestion">
                                <td><span class="fieldQuestionText">${question.questionText}</span></td>
                                <td class="tdQuestionPoint"><span class="fieldQuestionPoint">${question.point}</span></td>
                                <td align="center" id="tdQuestionCheck"><input type="checkbox" name="isInTask"
                                                                               class="questionCheck"
                                                                               value="${question.id}"></td>
                            </tr>
                        </c:forEach>
                    </table>
                </c:if>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Save">
                <a href="${pageContext.servletContext.contextPath}/tasks">Cancel</a>
            </td>
        </tr>
    </table>
</form>

</body>
</html>
