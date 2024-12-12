
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
    let endpoint1 = `${localdomain}/get/productList?status=1`
    fetch(endpoint1, {
        method: "GET",
        headers: {
            'Content-Type': 'application/json'
        }
    }).then(
        response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        }
    ).then(data => {
        data.data.map(value => console.log(value.link))
        $("#order-sp1").attr("data-id", data.data[0].id).attr("data-price", data.data[0].productPrice).attr("data-name", data.data[0].productName).attr("data-cart", "SP_" + data.data[0].id)
    }).catch(error => {
        console.error('There has been a problem with your fetch operation:', error);
    })
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
