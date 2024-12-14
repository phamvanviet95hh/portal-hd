$(document).ready(function (){
    console.log("Connect to Login");
    let domain = window.location.hostname;
    let port = window.location.port;
    $("#form-login").on("submit", function (event) {
        event.preventDefault();
        let username = $("#username").val();
        let password = $("#password").val();
        let endpointLogin = `http://${domain}:${port}/login`
        var bodyData = JSON.stringify({ username: username , password : password });
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
                    if(data.success){
                        // Lưu thông tin đăng nhập vào local storage
                        if ($("#form_checkboxLogin").is(':checked')){
                            localStorage.setItem("username", data.data.userName);
                            localStorage.setItem("userId", data.data.id);
                            localStorage.setItem("token", data.token);
                            localStorage.setItem("fullName", data.data.fullName);
                        }else {
                            sessionStorage.setItem("username", data.data.userName);
                            sessionStorage.setItem("userId", data.data.id);
                            sessionStorage.setItem("token", data.token);
                            sessionStorage.setItem("fullName", data.data.fullName);
                        }
                        window.location.href = `${localdomain}/dashboard`;
                    }else {
                        alert(data.message);
                    }
                })
                .catch(error => {
                    console.error('There has been a problem with your fetch operation:', error);
                });
    })
    
})