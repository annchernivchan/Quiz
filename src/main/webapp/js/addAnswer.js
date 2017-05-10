$('document').ready(function () {
    $('#addAnswer').click(function () {
        var table = $('#answers');
        table.append("<tr><td hidden><input type='text' name='answerId' value='000e0000-e00b-0d0-a000-000000000000'></td><td><input type='text' name='fieldAnswerText' value=''></td><td><input type='text' name='fieldAnswerWeight' value='' class='fieldAnswerWeight'></td></tr>");
        console.log('OK');
        return false;
    });


    $('#saveAnswers').click(function () {
        // $('tr').each(function () {
        // if ($(this).is('.newAnswer')) {
        //     var td = $(this).find('td');
        //     td.each(function (index) {
        //         console.log($(this).innerHTML);
        //     });
        // }
        // });
        var data = {};
        var answers = {};
        console.log('OK');
        return false;
    });

});