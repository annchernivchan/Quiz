$('document').ready(function () {

    $('.saveQuestion').click(function () {

        var answersWeight = 0;
        $('[name="fieldAnswerWeight"]').each(function () {
            answersWeight += parseFloat($(this).val());
        });

        if (answersWeight < 100) {
            if (confirm("Answers weights are less than 100%! Do you want to continue? (the question will be not verified)")) {
                $('#formSaveQuestion').submit();
            }
        } else if (answersWeight > 100) {
            if (confirm("Answers weights are more than 100%! Do you want to continue? (the question will be not verified)")) {
                $('#formSaveQuestion').submit();
            }
        } else if (answersWeight === 100) {
            $('#formSaveQuestion').submit();
        }
        return false;
    });


    $('#addAnswer').click(function () {
        var html = '<tr><td hidden><input type="text" name="answerId" value="000e0000-e00b-0d0-a000-000000000000"></td>' +
            '<td><input type="text" name="fieldAnswerText" class="form-control"></td>' +
            '<td><input type="text" name="fieldAnswerWeight" class="form-control text-center"></td>' +
            '<td><button class="deleteNewAnswer btn btn-default glyphicon glyphicon-trash"</button>' +
            '</td></tr>';

        var table = $('#answers');
        if (!table.length) {
            $('#answersTableTd').html('<table class="table table-bordered" id="answers">' +
                '<tr class="mainAnswersRow"><th class="text-center">Answer</th><th style="width: 30%" class="text-center">Weight (%)</th>' +
                '</tr>' + html + '</table>');
        } else {
            table.append(html);
        }

        $('.deleteNewAnswer').each(function () {
            $(this).click(function () {
                var td = $(this).parent();
                var tr = $(td).parent();
                tr.remove();
                var table = $('#answers');
                if (table.find('tr').length === 1) {
                    var mainTr = $('.mainAnswersRow');
                    $(mainTr).remove();
                    $('#answersTableTd').html('<em>No answers</em>');
                }
                return false;
            });
        });
        return false;
    });

    $('.questionCheck').each(function () {
        $(this).click(function () {
            var totalPoint = $('#totalPoint');
            var td = $(this).parent();
            var tr = $(td).parent();
            var pointTd = $(tr).find('.tdQuestionPoint');
            var value = parseFloat($(pointTd).find('.fieldQuestionPoint').text());
            var before = parseFloat(totalPoint.text());
            totalPoint.text($(this).prop('checked') ? before + value : before - value);
        });
    });

    $('.deleteQuestionLink').each(function () {
        var btn = $(this);
        btn.click(function () {
            console.log(btn);
            if (btn.attr('isInTask') === 'true'){
            if (confirm("Are you sure you want to delete this questions? It's in task!")) {
                $.ajax({
                    url: '/deleteQuestion',
                    type: 'post',
                    data: {id: btn.attr('questionId')},
                    success: function () {
                        var td = btn.parent();
                        var tr = $(td).parent();
                        $(tr).remove();
                    }
                });
            }} else {
                $.ajax({
                    url: '/deleteQuestion',
                    type: 'post',
                    data: {id: btn.attr('questionId')},
                    success: function () {
                        var td = btn.parent();
                        var tr = $(td).parent();
                        $(tr).remove();
                    }
                });
            }
        });
    });

    $('.deleteAnswerLink').each(function () {
        var btn = $(this);
        btn.click(function () {
            $.ajax({
                url: '/deleteAnswer',
                type: 'post',
                data: {questionId: btn.attr('questionId'), answerId: btn.attr('answerId')},
                success: function () {
                    var td = btn.parent();
                    var tr = $(td).parent();
                    $(tr).remove();
                    var table = $('#answers');
                    if (table.find('tr').length === 1) {
                        var mainTr = $('.mainAnswersRow');
                        $(mainTr).remove();
                        $('#answersTableTd').html('<em>No answers</em>');
                    }
                }
            });
            return false;
        });
    });


    $('#showQuestionBtn').click(function () {
        parent.location = '/questions';
    });

    $('#showTasksBtn').click(function () {
        parent.location = '/tasks';
    });

    $('.saveTaskBtn').click(function () {
        alert("here");
        $.ajax({
           url: '/doAddTask',
            type: 'post',
            data: '',
            success: function (errorString) {
                $('.errorContainer').html('<div class="alert alert-danger"><strong>Error!</strong>' + errorString + '</div>');
            }
        });
        return false;

        });

});



