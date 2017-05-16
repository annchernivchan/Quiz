$('document').ready(function () {

    $('#showQuestionBtn').click(function () {
        parent.location = '/questions';
    });

    $('#showTasksBtn').click(function () {
        parent.location = '/tasks';
    });


});