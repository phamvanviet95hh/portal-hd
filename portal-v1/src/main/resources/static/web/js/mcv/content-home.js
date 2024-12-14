$(document).ready(function(){
    
    let checkInput = true;
    let msgIsEmpty = "Không được để trống trường dữ liệu này";
    $("#select-product").change(function () {
        $(".box-product-content").addClass("active");
        let idProduct = $("#select-product").val();
        fetch(`${localdomain}/api/asm/v1/get/product?id=${idProduct}`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            }
        }).then(res => {
            if (!res.ok) {
                if (res.status === 500) {
                    alert(msgSession);
                    clearInfo();
                }else if(res.status === 403){
                    alert(msgAuthor)
                }else if(res.status !== 200){
                    alert("Gặp vấn đề khi lấy thông tin Sản phẩm")
                }
                
            }
            return res.json();
        }).then(data => {
            $("#product-info-name").val(data.productName);
            $("#product-info-sl").val(1);
            $("#product-info-type").val("chiếc");
            $("#product-info-thue").val(10);
        }).catch(error => {
            console.log(error);
        });
    });
    checkInput = checkInputEmpty("address-info-a", "error-address-a", msgIsEmpty, checkInput);
    checkInput = checkInputEmpty("phone-info-a", "error-phone-info-a", msgIsEmpty, checkInput);
    checkInput = checkInputEmpty("mst-info-a", "error-mst-info-a", msgIsEmpty);
    checkInput = checkInputEmpty("product-info-name", "error-product-info-name", msgIsEmpty);
    checkInput = checkInputEmpty("product-info-sl", "error-product-info-sl", msgIsEmpty);
    checkInput = checkInputEmpty("product-info-type", "error-product-info-type", msgIsEmpty);
    checkInput = checkInputEmpty("product-info-thue", "error-product-info-thue", msgIsEmpty);
    checkInput = checkInputEmpty("email-info-a", "error-email-info-a", msgIsEmpty);
    checkInput = checkInputEmpty("stk-info-a", "error-stk-info-a", msgIsEmpty);
    checkInput = checkInputEmpty("address-bank-info-a", "error-address-bank-info-a", msgIsEmpty);
    checkInput = checkInputEmpty("bank-user-info-a", "error-bank-user-info-a", msgIsEmpty);
    checkInput = checkInputEmpty("bank-user-role-info-a", "error-bank-user-role-info-a", msgIsEmpty);
    checkInput = checkInputEmpty("bank-user-info-b", "error-bank-user-info-b", msgIsEmpty);
    checkInput = checkInputEmpty("bank-user-role-info-b", "error-bank-user-role-info-b", msgIsEmpty);
    checkInput = checkInputEmpty("email-info-b", "error-email-info-b", msgIsEmpty);
    checkInput = checkInputEmpty("phone-info-b", "error-phone-info-b", msgIsEmpty);
    checkInput = checkInputEmpty("more-deployment-basis", "error-more-deployment-basis", msgIsEmpty);
    checkInput = checkInputEmpty("more-deployment-basis-address", "error-more-deployment-basis-address", msgIsEmpty);
    checkInput = checkInputEmpty("more-address-cs", "error-more-address-cs", msgIsEmpty);
    
    $(".btn-sendForm").click(function (){
        let check = true;
        let productName = $("#product-info-name").val();
        let productSl =  $("#product-info-sl").val();
        let productType =  $("#product-info-type").val();
        let productThu = $("#product-info-thue").val();
        let infoAddressA = $("#address-info-a").val();
        let infoPhoneA = $("#phone-info-a").val();
        let infoMstA = $("#mst-info-a").val();
        let infoEmailA = $("#email-info-a").val();
        let infoStkA = $("#stk-info-a").val();
        let infoAddressBankA = $("#address-bank-info-a").val();
        let infoNDDA = $("#bank-user-info-a").val();
        let infoRoleA = $("#bank-user-role-info-a").val();
        let infoNDDB = $("#bank-user-info-b").val();
        let infoRoleB = $("#bank-user-role-info-b").val();
        let infoEmailB = $("#email-info-b").val();
        let infoPhoneB = $("#phone-info-b").val();
        let infoMoreTime = $("#more-time").val();
        let infoMoreDeploy = $("#more-deployment-basis").val();
        let infoMoreDeployAddress = $("#more-deployment-basis-address").val();
        let infoMoreDeployCs = $("#more-address-cs").val();
        
        check = checkFromEmpty(productName, "error-product-info-name", msgIsEmpty, check);
        check = checkFromEmpty(productSl, "error-product-info-sl", msgIsEmpty, check);
        check = checkFromEmpty(productType, "error-product-info-type", msgIsEmpty, check);
        check = checkFromEmpty(productThu, "error-product-info-thue", msgIsEmpty, check);
        check = checkFromEmpty(infoAddressA, "error-address-a", msgIsEmpty, check);
        check = checkFromEmpty(infoPhoneA, "error-phone-info-a", msgIsEmpty, check);
        check = checkFromEmpty(infoMstA, "error-mst-info-a", msgIsEmpty, check);
        check = checkFromEmpty(infoEmailA, "error-email-info-a", msgIsEmpty, check);
        check = checkFromEmpty(infoStkA, "error-stk-info-a", msgIsEmpty, check);
        check = checkFromEmpty(infoAddressBankA, "error-address-bank-info-a", msgIsEmpty, check);
        check = checkFromEmpty(infoNDDA, "error-bank-user-info-a", msgIsEmpty, check);
        check = checkFromEmpty(infoRoleA, "error-bank-user-role-info-a", msgIsEmpty, check);
        check = checkFromEmpty(infoNDDB, "error-bank-user-info-b", msgIsEmpty, check);
        check = checkFromEmpty(infoRoleB, "error-bank-user-role-info-b", msgIsEmpty, check);
        check = checkFromEmpty(infoEmailB, "error-email-info-b", msgIsEmpty, check);
        check = checkFromEmpty(infoPhoneB, "error-phone-info-b", msgIsEmpty, check);
        check = checkFromEmpty(infoMoreTime, "error-more-time", msgIsEmpty, check);
        check = checkFromEmpty(infoMoreDeploy, "error-more-deployment-basis", msgIsEmpty, check);
        check = checkFromEmpty(infoMoreDeployAddress, "error-more-deployment-basis-address", msgIsEmpty, check);
        check = checkFromEmpty(infoMoreDeployCs, "error-more-address-cs", msgIsEmpty, check);
        
        
        if(check && checkInput){
            alert("Gửi dữ liệu thành công");
        }else {
            alert("Gửi dữ liệu không thành công")
        }
        
    });
    $("#bank-user-info-b").keyup(function (){
        let data = $(this).val();
        if (data !== ""){
            $("#box-listUser").addClass("active");
            customLoadPage(`${localdomain}/admin/user/loadListUserFrom?name=${data}`, "box-listUser");
        }else {
            $("#box-listUser").removeClass("active");
        }
       
    })
    
})