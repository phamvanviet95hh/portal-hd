$(document).ready(function () {
    $(".btn-addProduct").click(function () {
        customLoadPage(`${localdomain}/web/admin/addProduct`, "content");
    });
    $(".productViewLink").click(function () {
        let productId = $(this).attr("data-id")
        customLoadPage(`${localdomain}/web/admin/editProduct?id=${productId}`, "content");
    });
});