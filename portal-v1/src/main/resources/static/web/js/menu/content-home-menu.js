$("#contract").click(function(){
    $(this).parent().find("ul.ul-children").slideToggle();
});
$("#viewListUser").click(function (){
    customLoadPage(`${localdomain}/admin/user/get/list-user`, "content_box");
});
$("#viewListPartner").click(function (){
    customLoadPage(`${localdomain}/web/partner/list-partner`, "content_box");
});
$("#viewListDm").click(function (){
    customLoadPage(`${localdomain}/web/daumoi/list-daumoi`, "content_box");
});
$("#viewProduct").click(function (){
    customLoadPage(`${localdomain}/web/product/list-product`, "content_box");
});
$("#contract-form").click(function (){
    customLoadPage(`${localdomain}/web/content-home`, "content_box");
})