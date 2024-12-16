$(document).ready(function () {
    
    let selectedFile = null;
    const imageInput = document.getElementById('imageInput');
    const imagePreview = document.getElementById('content-productCategoryImg-span');
    let idUser = null;
    $(".btn-selectImg").click(function () {
        $("#imageInput").click();
    });
    imageInput.addEventListener('change', function (event) {
        const file = event.target.files[0]; // Lấy file được chọn
        if (file) {
            const reader = new FileReader();
            selectedFile = file;
            reader.addEventListener('load', function () {
                imagePreview.innerHTML = `<img src="${reader.result}" id="imgProductCategoryUpload" alt="Ảnh xem trước">`;
            });
            
            reader.readAsDataURL(file); // Đọc file dưới dạng URL
            
        } else {
            imagePreview.innerHTML = '<span>Chưa có ảnh</span>';
        }
        
    });
    
    $(".btn-updatePartnerRegister").click(function () {
        let idPart = $(this).attr("data-id");
        idUser = idPart;
        let checkFrom = true;
        let email = $("#email").val();
        var dataBody;
        let header = {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
        let fullNameRegister = $("#fullNameRegister").val();
        let phoneRegister = $("#phoneRegister").val();
        let tinhRegister = $("#form_tinh-edit").val();
        let huyenRegister = $("#form_quan-edit").val();
        let xaRegister = $("#form_xa-edit").val();
        let username = $("#username").val();
        
        checkFrom = checkFromEmpty(fullNameRegister, "errorFullName", msgEmpty, checkFrom);
        checkFrom = checkFromEmpty(email, "errorEmail", msgEmpty, checkFrom);
        checkFrom = checkFromEmpty(phoneRegister, "errorPhone", msgEmpty, checkFrom);
        checkFrom = checkFromEmpty(xaRegister, "form-error-edit-address", msgEmpty, checkFrom);
        checkFrom = checkFromEmpty(username, "errorUsername", msgEmpty, checkFrom);
        
        if (checkFrom) {
            $("#errorUsername").html("");
            $("#errorRePasswords").html("");
            $("#errorCodePartner").html("");
            const formData = new FormData();
            dataBody = JSON.stringify({
                id: idPart,
                userName: username,
                tinh: tinhRegister,
                email: email,
                fullName: fullNameRegister,
                phone: phoneRegister,
                huyen: huyenRegister,
                xa: xaRegister
            });
            if (selectedFile == null) {
                alert("Chọn ảnh trước khi thay đổi thông tin");
                return;
            }
            formData.append("dataForm", dataBody.toString());
            formData.append("file", selectedFile); // Thay bằng file thực tế
            console.log(formData.get("dataForm"));
            fetch(`${localdomain}/user/v1/updatePartner`, {
                method: "POST",
                headers: {
                    "Authorization": `Bearer ${token}`
                },
                body: formData // Dữ liệu gửi đi
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
                    $("#logo-dv").attr("src", `data:image/png;base64,${data.data.avatar}`);
                    sessionStorage.setItem("logo", data.data.avatar);
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
        customLoadPage(`${localdomain}/admin/user/inforUser?idUser=${idUser}`, "content_box");
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
    })
    
})