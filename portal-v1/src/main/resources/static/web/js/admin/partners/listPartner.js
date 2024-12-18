$(document).ready(function(){
    $(".btn-addPartner").click(function(){
        customLoadPage(`${localdomain}/web/partner/register`, "content_box")
    })
    $(".partner-edit").click(function(){
        let dataId = $(this).attr("data-id");
        customLoadPage(`${localdomain}/web/partner/editPartner?id=${dataId}`, "content_box")
    })
})