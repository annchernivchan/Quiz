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
        }

        return false;
    });


    $('#addAnswer').click(function () {
        var html = '<tr><td hidden><input type="text" name="answerId" value="000e0000-e00b-0d0-a000-000000000000"></td>' +
            '<td><input type="text" name="fieldAnswerText" value="${}"></td>' +
            '<td><input type="text" name="fieldAnswerWeight"></td></tr>';

        var table = $('#answers');
        if (!table.length) {
            $('#answersTableTd').html('<table id="answers" border="1">' +
                '<tr><th>Answer</th><th>Weight(%)</th></tr>' + html + '</table>');
        } else {
            table.append(html);
        }
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

});