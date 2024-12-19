
$(document).ready(function () {
    $(function () {
        toastr.options.escapeHtml = false;
        toastr.options.closeButton = true;
        toastr.options.positionClass = "toast-bottom-right";
        toastr.options.timeOut = 5000;
        toastr.options.showMethod = 'fadeIn';
        toastr.options.hideMethod = 'fadeOut';
    });
    
})


$(document).ready(function () {
    $(".open-nav-mb").click(function () {
        if ($(this).hasClass("fa-bars")) {
            $(this).removeClass("fa-bars");
            $(this).addClass("fa-times-circle-o");
            $(".mobi-nav").addClass("hien");
        } else if ($(this).hasClass("fa-times-circle-o")) {
            $(this).removeClass("fa-times-circle-o");
            $(this).addClass("fa-bars");
            $(".mobi-nav").removeClass("hien");
        }
    })
})
