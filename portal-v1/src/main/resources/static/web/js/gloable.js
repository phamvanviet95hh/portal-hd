// let localdomain = "http://" + window.location.hostname + ":" + window.location.port;
let localdomain = "http://" + window.location.hostname + ":" + window.location.port;
let token = localStorage.getItem("token") !== null ? localStorage.getItem("token") : sessionStorage.getItem("token");
let fullName = localStorage.getItem("fullName") !== null ? localStorage.getItem("fullName") : sessionStorage.getItem("fullName");
let username = localStorage.getItem("username") !== null ? localStorage.getItem("username") : sessionStorage.getItem("username");
let userId = localStorage.getItem("userId") !== null ? localStorage.getItem("userId") : sessionStorage.getItem("userId");
let role = localStorage.getItem("role") !== null ? localStorage.getItem("role") : sessionStorage.getItem("role");
let msgSession = "Hết phiên đăng nhập hoặc chưa đăng nhập vui lòng đăng nhập trước";
let msgAuthor = "Bạn không có quyền truy cập web này";
let msgEmpty = "Không được để trống trường này";

function clearInfo() {
    localStorage.removeItem("username");
    localStorage.removeItem("userId");
    localStorage.removeItem("fullName");
    localStorage.removeItem("role");
    localStorage.removeItem("token");
    localStorage.removeItem("phone");
    localStorage.removeItem("email");
    localStorage.removeItem("address");
    localStorage.removeItem("cart");
    
    sessionStorage.removeItem("username");
    sessionStorage.removeItem("userId");
    sessionStorage.removeItem("fullName");
    sessionStorage.removeItem("role");
    sessionStorage.removeItem("token");
    sessionStorage.removeItem("phone");
    sessionStorage.removeItem("email");
    sessionStorage.removeItem("address");
    sessionStorage.removeItem("cart");
    window.location.href = `${localdomain}/index`;
}

function checkFromEmpty( data ,idElement, msg, check){
    if (data === ""){
        check = false;
        $("#"+idElement).html(msg);
    }else {
        $("#"+idElement).html("")
    }
    return check;
}

function checkInputEmpty(input, idElement, msg, checkInput){
    $("#"+input).keyup(function (){
        if($(this).val() !== ""){
            $("#"+idElement).html("");
        }else {
            checkInput = false;
            $("#"+idElement).html(msg);
        }
    });
    return checkInput;
}

function alertGloable(message, type) {
    if (type === "success") {
        $(".alert-success").addClass("opa");
        $('#myAlert').fadeIn();
        $("#contentAlert").html(message);
    } else if (type === "false") {
        $('#myAlertError').fadeIn();
        $("#contentAlertError").html(message);
        $(".alert-danger").addClass("opa");
    }
    setTimeout(function () {
        if (type === "success") {
            $('#myAlert').fadeOut();
            $(".alert-success").removeClass("opa");
        } else if (type === "false") {
            $('#myAlertError').fadeOut();
            $(".alert-danger").removeClass("opa");
        }
    }, 3000);
}

function post(url, bodyData) {
    fetch(url, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        },
        body: bodyData // Dữ liệu gửi đi
    }).then(response => {
        if (!response.ok) {
            throw new Error('Network response was not ok');
        }
        return response.json();
    }).then(data => {
        console.log(data)
        if (data.success) {
            alertGloable(data.success);
        } else {
            alert(data.message);
        }
    })
        .catch(error => {
            console.error('There has been a problem with your fetch operation:', error);
        });
}

function xoa(url, id) {
    fetch(`${url}?id=${id}`, {
        method: "DELETE",
        headers: {
            "Content-Type": "application/json",
            "Authorization": `Bearer ${token}`
        }
    }).then(res => {
        if (!res.ok) {
            if(res.status === 403){
                alert(msgAuthor);
            }else if(res.status === 500){
                alert(msgSession);
                clearInfo();
            }
        }
        return res.json();
    }).then(data => {
        if (data.success) {
            alertGloable(data.message, "success");
        }else {
            alertGloable(data.message, "false");
        }
    }).catch(err => {
        console.error('There has been a problem with your fetch operation:', err);
    })
}

function getCurrentDate() {
    const now = new Date();
    const year = now.getFullYear();
    const month = String(now.getMonth() + 1).padStart(2, '0'); // Tháng bắt đầu từ 0 nên cần +1
    const day = String(now.getDate()).padStart(2, '0');

    return `${year}-${month}-${day}`;
}
function getFirstDayOfMonth() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth();
    
    
    const firstDay = new Date(year, month, 1);
    
    
    const yearString = firstDay.getFullYear();
    const monthString = String(firstDay.getMonth() + 1).padStart(2, '0');
    const dayString = String(firstDay.getDate()).padStart(2, '0');
    const hoursString = String(firstDay.getHours()).padStart(2, '0');
    const minutesString = String(firstDay.getMinutes()).padStart(2, '0');
    const secondsString = String(firstDay.getSeconds()).padStart(2, '0');
    
    
    const formattedDate = `${yearString}-${monthString}-${dayString}T${hoursString}:${minutesString}:${secondsString}`;
    
    return formattedDate;
}

function getLastDayOfMonth() {
    const now = new Date();
    const year = now.getFullYear();
    const month = now.getMonth();
    
    
    const firstDayNextMonth = new Date(year, month + 1, 1);
    
    
    const lastDay = new Date(firstDayNextMonth - 1);
    
    const yearString = lastDay.getFullYear();
    const monthString = String(lastDay.getMonth() + 1).padStart(2, '0');
    const dayString = String(lastDay.getDate()).padStart(2, '0');
    const hoursString = String(lastDay.getHours()).padStart(2, '0');
    const minutesString = String(lastDay.getMinutes()).padStart(2, '0');
    const secondsString = String(lastDay.getSeconds()).padStart(2, '0');
    
    const formattedDate = `${yearString}-${monthString}-${dayString}T${hoursString}:${minutesString}:${secondsString}`;
    
    return formattedDate;
}

function customLoadPage(url, idElement){
    $.ajax(url, {
        method: "GET",
        headers : {
            "Content-Type" : "html",
            "Authorization": `Bearer ${token}`
        },
        success : function(data, textStatus, jqXHR){
            console.log(jqXHR.status);
            if(jqXHR.status === 200){
                $("#"+idElement).html(data);
            }
        },
        error : function (xhr, textStatus, errorThrown) {
            if(xhr.status === 403){
                alert(msgAuthor);
            }else if(xhr.status === 500){
                alert(msgSession);
                clearInfo();
            }
        }
    })
}

function getNowDateTimeTypeLocalDateTime(){
    const localDate = new Date();
    const year = localDate.getFullYear();
    const month = String(localDate.getMonth() + 1).padStart(2, '0');
    const day = String(localDate.getDate()).padStart(2, '0');
    const hours = String(localDate.getHours()).padStart(2, '0');
    const minutes = String(localDate.getMinutes()).padStart(2, '0');
    const seconds = String(localDate.getSeconds()).padStart(2, '0');

    const localDateTime = `${year}-${month}-${day} ${hours}:${minutes}:${seconds}`;
    return localDateTime;
}
let checkDelete ;
function customDelete(url, id) {
    fetch(`${url}?id=${id}`, {
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
}
$(document).ready(function () {
    function updateClock() {
        const now = new Date();
        const hours = String(now.getHours()).padStart(2, '0');
        const minutes = String(now.getMinutes()).padStart(2, '0');
        const seconds = String(now.getSeconds()).padStart(2, '0');
        document.getElementById('clock').textContent = `${hours}:${minutes}:${seconds}`;
    }
    
    setInterval(updateClock, 1000);
    updateClock();
    const today = new Date();
    
    const day = String(today.getDate()).padStart(2, '0');
    const month = String(today.getMonth() + 1).padStart(2, '0');
    const year = today.getFullYear();
    
    const currentDate = `${day}/${month}/${year}`;
    document.getElementById('dateTime').textContent = `${currentDate}`;
    const daysOfWeek = ["Chủ nhật", "Thứ 2", "Thứ 3", "Thứ 4", "Thứ 5", "Thứ 6", "Thứ 7"];
    
    const currentDay = daysOfWeek[today.getDay()];
    document.getElementById('dateThu').textContent = `${currentDay}`;
});