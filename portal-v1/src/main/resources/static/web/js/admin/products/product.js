$(document).ready(function () {
    $(".btn-addProduct").click(function () {
        customLoadPage(`${localdomain}/web/product/addProduct`, "content_box");
    });
    $(".productViewLink").click(function () {
        let productId = $(this).attr("data-id")
        customLoadPage(`${localdomain}/web/product/editProduct?id=${productId}`, "content_box");
    });
    $(".remove-product").click(function () {
        let checkRemove = confirm("Bạn có chắc chắn muốn xóa sản phẩm này không ?");
        let productId = $(this).attr("data-id");
        if (checkRemove){
            fetch(`${localdomain}/api/asm/v1/delete/product?id=${productId}`, {
                method: "DELETE",
                headers: {"Content-Type": "application/json", "Authorization": `Bearer ${token}`}
            }).then(response => {
                if(!response.ok){
                    console.log(response)
                }
                return response.json();
            }).then(data => {
                if (data.success) {
                    alertGloable("Xóa sản phẩm thành công!!!", "success");
                    setTimeout(loadListProduct, 1000);
                }else {
                    alertGloable("Xóa sản phẩm không thành công!!!", "false");
                }
            }).catch(err => {
                console.log(err);
            })
        }
    });
    function loadListProduct(){
        customLoadPage(`${localdomain}/web/product/list-product`, "content_box");
    }
});