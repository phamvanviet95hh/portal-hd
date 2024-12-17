$("#contract").click(function(){
    $(this).parent().find("ul.ul-children").slideToggle();
});
$("#viewListUser").click(function (){
    customLoadPage(`${localdomain}/admin/user/get/list-user`, "content_box");
})
$("#viewListPartner").click(function (){
    customLoadPage(`${localdomain}/web/partner/list-partner`, "content_box");
})