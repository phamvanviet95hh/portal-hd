$(document).ready(function(){
    let arr = [];
    
    let id;
    $(".btn-addDmToPartner").click(function() {
        let partnerId = $(this).val();
        id = partnerId
        getCheckedValues();
        var dataBody = JSON.stringify(
            {
                data : arr,
                idPart : partnerId
            }
        );
        
        fetch(`${localdomain}/api/partner-to-clue/add`, {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
                "Authorization": `Bearer ${token}`
            },
            body: dataBody // Dữ liệu gửi đi
        }).then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        }).then(data => {
            if (data.success) {
                alertGloable(data.success);
                setTimeout(loadListUser, 1000);
            } else {
                alert(data.message);
            }
        })
            .catch(error => {
                console.error('There has been a problem with your fetch operation:', error);
            });
       
    })
    function getCheckedValues() {
        const checkboxes = document.querySelectorAll('input[name="userDm"]:checked');
        let checkedValues = [];
        checkboxes.forEach((checkbox) => {
            checkedValues.push(checkbox.value);
        });
        
        arr = checkedValues;
        
    }
    
    function loadListUser(){
        customLoadPage(`${localdomain}/web/partner/editPartner?id=${id}`, "content_box")
    }
    
    $(".fa-times-circle").click(function () {
        $("#box-loadDm").removeClass("active");
    });
})
