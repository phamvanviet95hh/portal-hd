$(document).ready(function(){

    $("#fullName").html(fullName)

    $(".show-info").click(function(){
        $(".box-infoUser").slideToggle();
    })
    $(".btn-adminLogout").click(function(){
        clearInfo();
    })
    $(".btn-infoAccount").click(function(){
        customLoadPage(`${localdomain}/admin/user/inforUser?idUser=${userId}`, "content_box");
    })
})