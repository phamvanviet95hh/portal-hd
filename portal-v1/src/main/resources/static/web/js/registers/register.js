$(document).ready(function (){
    $('.alert').alert();
    $(".register-form-group input").click(function (){
        $(this).parent().find("label").addClass("hien")
        $(this).attr("placeholder","")
    });
    
    $(document).ready(function (){
        let district = $("#form_quan-register");
        let ward = $("#form_xa-register");
        let msg = "Không được để trống trường này...";
        $(".btn-backToHome").click(function (){
            window.location.href = `${localdomain}/index`;
        });
        $(".btn-registerSend").click(function (){
            let check = true;
            let fullName = $("#register-input-fullName").val();
            let userName = $("#register-input-userName").val();
            let password = $("#register-input-password").val();
            let rePassword = $("#register-input-rePassword").val();
            let phoneNumber = $("#register-input-phone").val();
            let email = $("#register-input-email").val();
            let idTinh = $("#form_tinh-register").val();
            let idHuyen = $("#form_quan-register").val();
            let idXa = $("#form_xa-register").val();
            
            check = checkFromEmpty(fullName, "form-error-register-fullName", msg, check);
            check = checkFromEmpty(userName, "form-error-register-userName", msg, check);
            check = checkFromEmpty(password, "form-error-register-password", msg, check);
            check = checkFromEmpty(rePassword, "form-error-register-rePassword", msg, check);
            check = checkFromEmpty(phoneNumber, "form-error-register-phone", msg, check);
            check = checkFromEmpty(email, "form-register-input-email", msg, check);
            check = checkFromEmpty(idXa, "form-error-register-address", msg, check);
            
            if (password !== rePassword){
                check=false;
                $("#form-error-register-rePassword").html("Nhập mật khẩu không khớp...");
            }else {
                $("#form-error-register-rePassword").html("");
            }
            
            if(check){
                let itemThongBao = document.getElementById("thongbao");
                let endpointLogin = `${localdomain}/user/v1/add`
                var bodyData = JSON.stringify({ userName: userName , fullName : fullName, password : password, provinceId : idTinh, districtId : idHuyen, wardId : idXa, email : email, phone: phoneNumber, detail: "Partner", status: "1", role: "USER"});
                fetch(endpointLogin, {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json', // Thiết lập header
                    },
                    body: bodyData // Dữ liệu gửi đi
                })
                    .then(response => {
                        if (!response.ok) {
                            throw new Error('Network response was not ok');
                        }
                        return response.json();
                    })
                    .then(data => {
                        console.log(data)
                        if(data.success){
                            console.log(itemThongBao)
                            let thongbao = `<div>
                        <i class="fa fa-check-circle-o" aria-hidden="true"></i>
                    </div>
                    <div class="mb-5">Đăng ký tài khoản thành công</div>`;
                            itemThongBao.innerHTML = thongbao;
                            
                            
                        }
                    })
                    .catch(error => {
                        console.error('There has been a problem with your fetch operation:', error);
                    });
            }else {
                console.log("sjdbashgdvashdvashdvsgh")
            }
            
        });
        
        $("#form_tinh-register").change(function (){
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
                    district.html(contentDistrict);
                    
                }
            }).catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
            
        })
        district.change(function (){
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
    });
    
    
});