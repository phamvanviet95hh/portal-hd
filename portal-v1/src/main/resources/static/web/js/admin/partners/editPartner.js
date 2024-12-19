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
        let nameCompany = $("#partner-edit-input-company").val();
        let nameNDD = $("#partner-edit-input-fullName").val();
        let position = $("#partner-edit-input-position").val();
        let gender = $("#partner-edit-input-gender").val();
        let phone = $("#partner-edit-input-phone").val();
        let email = $("#partner-edit-input-email").val();
        let mst = $("#partner-edit-input-mst").val();
        let stk = $("#partner-edit-input-stk").val();
        let nameBank = $("#partner-edit-input-bank").val();
        let idTinh = $("#partner-edit-input-province").val();
        let idHuyen = $("#partner-edit-input-district").val();
        let idXa = $("#partner-edit-input-ward").val();
        
        chek = checkFromEmpty(nameCompany, "error-partner-edit-input-company", msgEmpty, chek);
        chek = checkFromEmpty(nameNDD, "error-partner-edit-input-fullName", msgEmpty, chek);
        chek = checkFromEmpty(position, "error-partner-edit-input-position", msgEmpty, chek);
        chek = checkFromEmpty(gender, "error-partner-edit-input-gender", msgEmpty, chek);
        chek = checkFromEmpty(phone, "error-partner-edit-input-input-phone", msgEmpty, chek);
        chek = checkFromEmpty(email, "error-partner-edit-input-email", msgEmpty, chek);
        chek = checkFromEmpty(mst, "error-partner-edit-input-mst", msgEmpty, chek);
        chek = checkFromEmpty(stk, "error-partner-edit-input-stk", msgEmpty, chek);
        chek = checkFromEmpty(nameBank, "error-partner-edit-input-bank", msgEmpty, chek);
        chek = checkFromEmpty(idXa, "error-partner-edit-input-address", msgEmpty, chek);
        
        if (chek) {
            var dataBody = JSON.stringify({
                partnerId : idPart,
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
            
            fetch(`${localdomain}/api/partner/update`, {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
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
                console.log(data);
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