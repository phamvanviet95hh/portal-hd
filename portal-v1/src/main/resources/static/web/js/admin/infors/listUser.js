$(document).ready(function(){
    $(".user-edit").click(function(){
        let id = $(this).attr("data-id");
        customLoadPage(`${localdomain}/admin/user/inforUser?idUser=${id}`, "content_box");
    });
    $(".user-remove").click(function(){
        let checkRemove = confirm("Bạn có muốn xoá tài khoản này");
        let idpart = $(this).attr("data-id");
        if (checkRemove) {
            xoa(`${localdomain}/user/v1/deletePartner`, idpart);
            setTimeout(loadPageUser, 1000);
        }
    });
    function loadPageUser(){
        customLoadPage(`${localdomain}/admin/user/get/list-user`, "content");
    }
    $(".btn-addAccount").click(function () {
        customLoadPage(`${localdomain}/admin/user/register`, "content_box");
    })
})