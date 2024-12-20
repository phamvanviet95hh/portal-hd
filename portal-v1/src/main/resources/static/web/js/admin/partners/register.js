$(document).ready(function(){
    
    checkInputEmpty("partner-register-input-company", "form-error-partner-register-company", msgEmpty);
    checkInputEmpty("partner-register-input-fullName", "form-error-partner-register-fullName", msgEmpty);
    checkInputEmpty("partner-register-input-position", "form-error-register-position", msgEmpty);
    checkInputEmpty("partner-register-input-gender", "form-error-register-gender", msgEmpty);
    checkInputEmpty("partner-register-input-phone", "form-error-partner-register-input-phone", msgEmpty);
    let district = $("#form_quan-register");
    let ward = $("#form_xa-register");
    
    $(".btn-registerSendPartner").click(function(){
        let endPoint = `${localdomain}/api/partner/add`;
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
        
        
        if(chek){
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
            
           fetch(endPoint, {
               method: "POST",
               headers: {
                   'Content-Type': 'application/json',
                   'Authorization': `Bearer ${token}`
               },
               body : dataBody
           }).then(response => {
               if (!response.ok) {
                   console.log(response.body);
               }
               return response.json();
           }).then(data => {
               console.log(data)
           })
           .catch(error => {
               console.log(error)
           });
            
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
})