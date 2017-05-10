<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" type="text/css" href="../css/styles.css" media="screen"/>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <script src="../js/addAnswer.js"></script>
    <title>Add question</title>
</head>
<body>
<h3>
    Adding a question
</h3>
<p style="color: red;">${errorString}</p>
<form action="doAddQuestion" method="post">
    <table>
        <tr>
            <td><p>Question text: </p></td>
            <td><input type="text" name="questionText" value="" maxlength="255"
                       id="fieldQuestionText"></td>
        </tr>
        <tr>
            <td><p>Point: </p></td>
            <td><input type="text" name="questionPoint" value="" maxlength="6" id="fieldPoint"></td>
        </tr>
        <tr>
            <td><p>Question type: </p></td>
            <td><select name="questionType">
                <c:forEach items="${types}" var="type">
                    <option value="${type}">${type}</option>
                </c:forEach>
            </select></td>
        </tr>
        <tr>
            <td><p>Answers: </p></td>
            <td>
                <table id="answers" border="1">
                    <tr>
                        <th>Answer</th>
                        <th>Weight</th>
                    </tr>
                </table>
            </td>
        </tr>
        <tr>
            <td></td>
            <td>
                <button id="addAnswer">Add answer</button>
            </td>
        </tr>
        <tr>
            <td><input type="submit" value="Save"></td>
            <td><a href="${pageContext.servletContext.contextPath}/questions">Cancel</a></td>
        </tr>
    </table>
</form>
</body>

</html>
