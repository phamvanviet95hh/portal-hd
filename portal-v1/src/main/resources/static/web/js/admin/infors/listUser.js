$(document).ready(function(){
    $(".user-edit").click(function(){
        let id = $(this).attr("data-id");
        customLoadPage(`${localdomain}/admin/user/inforUser?idUser=${id}`, "content");
    });
    $(".user-remove").click(function(){
        let checkRemove = confirm("Bạn có muốn xoá tài khoản này");
        let idpart = $(this).attr("data-id");
        if (checkRemove) {
            xoa(`${localdomain}/user/v1/deletePartner`, idpart);
        }
    })
})