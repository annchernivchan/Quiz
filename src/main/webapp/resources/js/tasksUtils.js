$('document').ready(function () {

    $('.deleteTaskLink').each(function () {
        var link = $(this);
        link.click(function () {
            if (confirm("Are you sure you want to delete this task?")) {
                $.ajax({
                    url: '/deleteTask',
                    type: 'post',
                    data: {id: link.attr('taskId')},
                    success: function () {
                        var td = link.parent();
                        var tr = $(td).parent();
                        $(tr).remove();
                    }
                });
            }

        });
    });

    $('.saveEditedTaskBtn').click(function () {
        var form = $('.taskForm');
        var isAnythingChecked = false;

        $(form).find('.questionCheck').each(function () {
            if ($(this).prop('checked')) {
                isAnythingChecked = true;
            }
        });

        if (isAnythingChecked) {
            var formData = form.serialize();
            console.log(formData);
            $.ajax({
                url: '/doEditTask',
                type: 'post',
                data: formData,
                success: function (errorString) {
                    if (errorString !== '') {
                        $('.errorContainer').html('<div class="alert alert-danger"><strong>Error!</strong>' + errorString + '</div>');
                    } else parent.location = '/tasks';
                }
            });
        } else {
            $('.errorContainer').html('<div class="alert alert-danger"><strong>Error!</strong> You have to include at least one question in task!</div>');
        }
        return false;

    });

    $('.saveAddedTaskBtn').click(function () {
        var form = $('.taskForm');
        var isAnythingChecked = false;

        $(form).find('.questionCheck').each(function () {
            if ($(this).prop('checked')) {
                isAnythingChecked = true;
            }
        });

        if (isAnythingChecked) {
            var formData = form.serialize();
            $.ajax({
                url: '/doAddTask',
                type: 'post',
                data: formData,
                success: function (errorString) {
                    if (errorString !== '') {
                        $('.errorContainer').html('<div class="alert alert-danger"><strong>Error!</strong>' + errorString + '</div>');
                    } else parent.location = '/tasks';
                }
            });
        } else {
            $('.errorContainer').html('<div class="alert alert-danger"><strong>Error!</strong> You have to include at least one question in task!</div>');
        }

    });



});