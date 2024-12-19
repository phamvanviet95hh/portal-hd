$(document).ready(function(){
    $(".item-partner-home").click(function(){
        let idPartner = $(this).val();
        customLoadPage(`${localdomain}/web/loadInforPartnerHome?id=${idPartner}`, "load-info-benA");
    })
})