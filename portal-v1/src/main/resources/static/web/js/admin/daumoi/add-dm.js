$(document).ready(function(){
    let nameClueForm = $("#add-content-item-fullName");
    let phoneClueForm = $("#add-content-item-phone");
    let emailClueForm = $("#add-content-item-email");
    let positionClueForm = $("#add-content-item-position");
    let checkInput = true;
    
    checkInput = checkInputEmpty("add-content-item-fullName", "error-add-content-item-fullName", msgEmpty, checkInput);
    checkInput = checkInputEmpty("add-content-item-phone", "error-add-content-item-phone", msgEmpty, checkInput);
    checkInput = checkInputEmpty("add-content-item-email", "error-add-content-item-email", msgEmpty, checkInput);
    
    
    $(".btn-addNewClue").click(function (){
        let nameClue = nameClueForm.val();
        let phoneClue = phoneClueForm.val();
        let emailClue = emailClueForm.val();
        let positionClue = positionClueForm.val();
        let check = true;
        
        check = checkFromEmpty(nameClue, "error-add-content-item-fullName", msgEmpty, check);
        check = checkFromEmpty(phoneClue, "error-add-content-item-phone", msgEmpty, check);
        check = checkFromEmpty(emailClue, "error-add-content-item-email", msgEmpty, check);
        check = checkFromEmpty(positionClue, "error-add-content-item-position", msgEmpty, check);
        
        if(check && checkInput){
            fetch(`${localdomain}/api/clue/add`,{
                method: "POST",
                headers:{"Content-Type":"application/json", "Authorization":`Bearer ${token}`},
                body:JSON.stringify({
                    clueName : nameClue,
                    phoneClue : phoneClue,
                    emailClue : emailClue,
                    positionClue : positionClue
                })
            }).then(response => {
                if (!response.ok){
                    console.log(response);
                }
                return response.json();
            }).then(data => {
                if(data.success){
                    alertGloable(data.message, "success");
                    setTimeout(loadListDm, 1000)
                }else {
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