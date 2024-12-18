$(document).ready(function () {
    
    let idUser = $("#partId").val();
    $(".dm-remove").click(function (e){
        e.preventDefault();
        let checkRemove = confirm(" Bạn có muốn xóa đầu mối này không!!! ");
        let idClue = $(this).attr("data-id");
        if(checkRemove){
            fetch(`${localdomain}/api/partner-to-clue/delete?id=${idClue}&idPart=${idUser}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                }
            }).then(res => {
                if (!res.ok) {
                    if (res.status === 500) {
                        alertGloable("Time out", "false");
                    }
                    throw new Error('Network response was not ok')
                }
                return res.json();
            }).then(data => {
                console.log(data)
                if (data.success) {
                    checkDelete = true;
                    alertGloable(data.message, "success");
                }else {
                    checkDelete = false;
                    alertGloable(data.message, "false");
                }
            }).catch(err => {
                console.error('There has been a problem with your fetch operation:', err);
            })
            setTimeout(loadListUser, 1000)
        }
    });
    
    $(".btn-updatePartnerRegister").click(function () {
        let idPart = $(this).attr("data-id");
        idUser = idPart;
        let chek = true;
        let nameCompany = $("#partner-register-input-company").val();
        let nameNDD = $("#partner-register-input-fullName").val();
        let position = $("#partner-register-input-position").val();
        let gender = $("#partner-register-input-gender").val();
        let phone = $("#partner-register-input-phone").val();
        let email = $("#partner-register-input-email").val();
        let mst = $("#partner-register-input-mst").val();
        let stk = $("#partner-register-input-stk").val();
        let nameBank = $("#partner-register-input-bank").val();
        let idTinh = $("#form_tinh-register").val();
        let idHuyen = $("#form_quan-register").val();
        let idXa = $("#form_xa-register").val();
        
        chek = checkFromEmpty(nameCompany, "form-error-partner-register-company", msgEmpty, chek);
        chek = checkFromEmpty(nameNDD, "form-error-partner-register-fullName", msgEmpty, chek);
        chek = checkFromEmpty(position, "form-error-register-position", msgEmpty, chek);
        chek = checkFromEmpty(gender, "form-error-register-gender", msgEmpty, chek);
        chek = checkFromEmpty(phone, "form-error-partner-register-input-phone", msgEmpty, chek);
        chek = checkFromEmpty(email, "form-partner-register-input-email", msgEmpty, chek);
        chek = checkFromEmpty(mst, "form-error-partner-register-mst", msgEmpty, chek);
        chek = checkFromEmpty(stk, "form-error-partner-register-stk", msgEmpty, chek);
        chek = checkFromEmpty(nameBank, "form-error-partner-register-bank", msgEmpty, chek);
        chek = checkFromEmpty(idXa, "form-error-register-address", msgEmpty, chek);
        
        if (chek) {
            var dataBody = JSON.stringify({
                nameCompany : nameCompany,
                nameNDD : nameNDD,
                position : position,
                gender : gender,
                phone : phone,
                email : email,
                mst : mst,
                stk : stk,
                nameBank : nameBank,
                idTinh : idTinh,
                idHuyen : idHuyen,
                idXa : idXa
            });
            
            fetch(`${localdomain}/user/v1/updatePartner`, {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${token}`
                },
                body: dataBody // Dữ liệu gửi đi
            }).then(response => {
                if (!response.ok) {
                    if (response.status === 500) {
                        console.log("Connect to service timeout");
                    }
                    throw new Error('Network response was not ok');
                }
                return response.json();
            }).then(data => {
                if (data.success) {
                    alertGloable(data.message, "success");
                    setTimeout(loadListUser, 1000);
                } else {
                    alertGloable(data.message, "false");
                }
            })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
        } else {
            alertGloable("Gặp lỗi kiểm tra lại những trường thông tin đã nhập", "false");
        }
        
    });
    function loadListUser(){
        
        customLoadPage(`${localdomain}/web/partner/editPartner?id=${idUser}`, "content_box")
    }
    $("#info-topLeft-prev").click(function () {
        let partnerId = $(this).attr("data-id");
        $("#content_box").load(`/admin/user/inforUser?idUser=${partnerId}`);
    });
    $(".btn-addPartnerBack").click(function () {
        let partnerId = $(this).attr("data-id");
        $("#content_box").load(`/admin/user/inforUser?idUser=${partnerId}`);
    });
    let district1 = $("#form_quan-edit");
    let ward = $("#form_xa-edit");
    
    $("#form_tinh-edit").change(function (){
        const selectedOption = this.options[this.selectedIndex];
        let idProvince = selectedOption.getAttribute("value");
        fetch(`${localdomain}/web/district/getDistrict?provincesId=${idProvince}`, {
            method : "GET",
            headers : {
                "Content-Type" : "application/json",
                "Authorization": `Bearer ${token}`
            }
        }).then(res => {
            if (!res.ok){
                throw new Error('Network response was not ok')
            }
            return res.json();
        }).then(data => {
            if(data.success){
                let contentDistrict = ""
                
                data.data.map(value => {
                    contentDistrict += `
                        <option value="${value.code}"> ${value.fullName} </option>
                    `;
                })
                district1.html(contentDistrict);
                
            }
        }).catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
        
    })
    district1.change(function (){
        const selectedOption = this.options[this.selectedIndex];
        let idProvince = selectedOption.getAttribute("value");
        fetch(`${localdomain}/web/ward/getList?districtId=${idProvince}`, {
            method : "GET",
            headers : {
                "Content-Type" : "application/json"
            }
        }).then(res => {
            if (!res.ok){
                throw new Error('Network response was not ok')
            }
            return res.json();
        }).then(data => {
            if(data.success){
                let contentWard = ""
                data.data.map(value => {
                    contentWard += `
                        <option value="${value.code}"> ${value.fullName} </option>
                    `;
                })
                ward.html(contentWard);
            }
        }).catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
    });
    $(".btn-loadListDm").click(function (){
        $("#box-loadDm").addClass("active");
        let idPart = $(this).val();
        customLoadPage(`${localdomain}/web/daumoi/get/dm-list?id=${idPart}`, "loadDmContent-box")
    });
    
})