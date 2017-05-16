$('document').ready(function () {

    $('.saveQuestion').click(function () {

        var form = $('.formQuestion');
        var formData = form.serialize();
        $.ajax({
            url: '/validateQuestion',
            type: 'post',
            data: formData,
            success: function (jsonErrors) {
                console.log(jsonErrors);
                var errorContainer = $('.errorContainer');
                errorContainer.empty();
                if (jsonErrors !== '') {
                    var errors = $.makeArray($.parseJSON(jsonErrors));
                    for (var i = 0; i < errors.length; i++) {
                        errorContainer.append('<div class="alert alert-danger"><strong>Error!</strong>' + errors[i] + '</div>');

                    }
                } else {
                    var answersWeight = 0;
                    $('[name="fieldAnswerWeight"]').each(function () {
                        answersWeight += parseFloat($(this).val());
                    });

                    if (answersWeight < 100) {
                        if (confirm("Answers weights are less than 100%! Do you want to continue? (the question will be not verified)")) {
                            console.log(formData);
                            $.ajax({
                                url: form.attr('toUrl'),
                                type: 'post',
                                data: formData,
                                success: function () {
                                    parent.location = '/questions';
                                }
                            });
                        }
                    } else if (answersWeight > 100) {
                        if (confirm("Answers weights are more than 100%! Do you want to continue? (the question will be not verified)")) {
                            console.log(formData);
                            $.ajax({
                                url: form.attr('toUrl'),
                                type: 'post',
                                data: formData,
                                success: function () {
                                    parent.location = '/questions';
                                }
                            });
                        }
                    } else {
                        $.ajax({
                            url: form.attr('toUrl'),
                            type: 'post',
                            data: formData,
                            success: function () {
                                parent.location = '/questions';
                            }
                        });
                    }

                }
            }
        });

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
            if (btn.attr('isInTask') === 'true') {
                if (confirm("Are you sure you want to delete this questions? It's in task!")) {
                    $.ajax({
                        url: '/deleteQuestion',
                        type: 'post',
                        data: {id: btn.attr('questionId')},
                        success: function () {
                            console.log("success");
                            var td = btn.parent();
                            var tr = $(td).parent();
                            $(tr).remove();
                        }
                    });
                }
            } else {
                $.ajax({
                    url: '/deleteQuestion',
                    type: 'post',
                    data: {id: btn.attr('questionId')},
                    success: function () {
                        console.log("success");
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

});