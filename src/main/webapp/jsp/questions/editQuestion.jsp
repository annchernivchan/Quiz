<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit question</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css" media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/utils.js"></script>
</head>
<body>
<h3>
    Editing a question
</h3>
<p style="color: red;">${errorString}</p>
<form id="formSaveQuestion" action="doEditQuestion?id=${question.id}" method="post">
    <table>
        <tr>
            <td><p>Question text: </p></td>
            <td><input type="text" name="questionText" value="${question.questionText}" maxlength="255"
                       class="fieldQuestionText"></td>
        </tr>
        <tr>
            <td><p>Point: </p></td>
            <td><input type="text" name="questionPoint" value="${question.point}" maxlength="6" id="fieldPoint"></td>
        </tr>
        <tr>
            <td><p>Question type: </p></td>
            <td><select name="questionType">
                <c:forEach items="${types}" var="type">
                    <option ${type.equals(question.questionType.name()) ? 'selected' : ''}
                            value="${type}">${type}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td><p>Answers: </p></td>
            <td id="answersTableTd">
                <c:if test="${question.allAnswers.size() == 0}" >
                    <em>No answers</em>
                </c:if>

                <c:if test="${question.allAnswers.size() != 0}" >
                    <table id="answers" border="1">
                        <tr>
                            <th>Answer</th>
                            <th>Weight(%)</th>
                        </tr>
                        <c:forEach items="${question.allAnswers}" var="answer">
                            <tr valign="top">
                                <td hidden><input type="text" name="answerId" value="${answer.id}"></td>
                                <td><input type="text" name="fieldAnswerText" value="${answer.answerText}"></td>
                                <td><input type="text" name="fieldAnswerWeight" value="${answer.weight}"
                                           class="fieldAnswerWeight"></td>
                                <td>
                                    <a href="${pageContext.servletContext.contextPath}/deleteAnswer?answerId=${answer.id}&questionId=${question.id}">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>

                </c:if>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button id="addAnswer">Add answer</button>
                </td>
        </tr>
        <tr>
            <td>
                <input type="submit" class="saveQuestion" value="Save">
                <a href="${pageContext.servletContext.contextPath}/questions">Cancel</a>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
