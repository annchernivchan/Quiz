<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit question</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.servletContext.contextPath}/resources/css/styles.css"
          media="screen"/>
    <link rel="stylesheet" href="${pageContext.servletContext.contextPath}/resources/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/bootstrap.min.js"></script>
    <script src="${pageContext.servletContext.contextPath}/resources/js/questionsUtils.js"></script>
</head>
<body>
<div style="width: 800px" class="container">
    <h3>
        Editing a question
    </h3>

    <div class="errorContainer">
    </div>
    <form class="formQuestion" toUrl="/doEditQuestion">
        <table class="table">
            <tr>
                <td hidden><input name="id" value="${question.id}"></td>
                <td><p>Question text: </p></td>
                <td><input type="text" name="questionText" value="${question.questionText}" maxlength="255"
                           class="fieldQuestionText form-control"></td>
            </tr>
            <tr>
                <td><p>Point: </p></td>
                <td><input style="width: 20%" type="text" name="questionPoint" value="${question.point}" maxlength="6"
                           id="fieldPoint"
                           class="form-control"></td>
            </tr>
            <tr>
                <td><p>Question type: </p></td>
                <td><select style="width: 70%" class="form-control" name="questionType">
                    <c:forEach items="${types}" var="type">
                        <option ${type.equals(question.questionType.name()) ? 'selected' : ''}
                                value="${type}">${type}</option>
                    </c:forEach>
                </select></td>
            </tr>
            <tr>
                <td><p>Answers: </p></td>
                <td id="answersTableTd">
                    <c:if test="${question.allAnswers.size() == 0}">
                        <em>No answers</em>
                    </c:if>

                    <c:if test="${question.allAnswers.size() != 0}">
                        <table class="table table-bordered" id="answers">
                            <tr>
                                <th class="text-center">Answer</th>
                                <th style="width: 30%" class="text-center">Weight (%)</th>
                            </tr>
                            <c:forEach items="${question.allAnswers}" var="answer">
                                <tr valign="top">
                                    <td hidden><input type="text" name="answerId" value="${answer.id}"></td>
                                    <td><input class="form-control" type="text" name="fieldAnswerText"
                                               value="${answer.answerText}"></td>
                                    <td><input type="text" name="fieldAnswerWeight" value="${answer.weight}"
                                               class="fieldAnswerWeight form-control"></td>
                                    <td>
                                        <button class="btn btn-primary glyphicon glyphicon-trash deleteAnswerLink"
                                                questionId="${question.id}" answerId="${answer.id}"></button>
                                    </td>
                                </tr>
                            </c:forEach>
                        </table>

                    </c:if>
                <td></td>
                <td>
                    <button class="btn btn-warning" id="addAnswer">Add answer</button>
                </td>
            </tr>
            <tr>
                <td>
                </td>
                <td align="right">
                    <input type="submit" class="saveQuestion btn btn-success" value="Save">
                    <a href="${pageContext.servletContext.contextPath}/questions" class="btn btn-danger">Cancel</a>
                </td>
            </tr>
        </table>
    </form>
</div>
</body>
</html>
