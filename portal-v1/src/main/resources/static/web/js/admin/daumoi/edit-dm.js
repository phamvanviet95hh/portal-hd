$(document).ready(function () {
    let nameClueForm = $("#edit-content-item-fullName");
    let phoneClueForm = $("#edit-content-item-phone");
    let emailClueForm = $("#edit-content-item-email");
    let positionClueForm = $("#edit-content-item-position");
    let checkInput = true;
    
    checkInput = checkInputEmpty("edit-content-item-fullName", "error-edit-content-item-fullName", msgEmpty, checkInput);
    checkInput = checkInputEmpty("edit-content-item-phone", "error-edit-content-item-phone", msgEmpty, checkInput);
    checkInput = checkInputEmpty("edit-content-item-email", "error-edit-content-item-email", msgEmpty, checkInput);
    
    $(".btn-addEditClue").click(function (){
        let nameClue = nameClueForm.val();
        let phoneClue = phoneClueForm.val();
        let emailClue = emailClueForm.val();
        let positionClue = positionClueForm.val();
        let check = true;
        
        check = checkFromEmpty(nameClue, "error-edit-content-item-fullName", msgEmpty, check);
        check = checkFromEmpty(phoneClue, "error-edit-content-item-phone", msgEmpty, check);
        check = checkFromEmpty(emailClue, "error-edit-content-item-email", msgEmpty, check);
        check = checkFromEmpty(positionClue, "error-edit-content-item-position", msgEmpty, check);
        
        if(check && checkInput) {
            fetch(`${localdomain}/api/clue/update`, {
                method: "POST",
                headers: {"Content-Type": "application/json", "Authorization": `Bearer ${token}`},
                body: JSON.stringify({
                    clueName: nameClue,
                    phoneClue: phoneClue,
                    emailClue: emailClue,
                    positionClue: positionClue
                })
            }).then(response => {
                if (!response.ok) {
                    console.log(response);
                }
                return response.json();
            }).then(data => {
                if (data.success) {
                    alertGloable(data.message, "success");
                    setTimeout(loadListDm, 1000)
                } else {
                    alertGloable(data.message, "false");
                }
            }).catch(error => {
                console.log(error)
            })
        }
    });
    function loadListDm(){
        customLoadPage(`${localdomain}/web/daumoi/list-daumoi`, "content_box");
    }
    $(".btn-backListDm").click(function (){
        customLoadPage(`${localdomain}/web/daumoi/list-daumoi`, "content_box");
    });
})