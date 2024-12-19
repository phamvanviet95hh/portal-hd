$(document).ready(function(){
    $(".dm-remove").click(function(){
        let check = confirm("Bạn có chắc muốn xóa đầu mối này!!!")
        let idClue = $(this).attr("data-idClue");
        if(check){
            fetch(`${localdomain}/api/clue/delete?id=${idClue}`, {
                method: "DELETE",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`
                }
            }).then(res => {
                if (!res.ok) {
                    console.log(res);
                }
                return res.json();
            }).then(data => {
                if(data.success){
                    alertGloable(data.message, "success");
                    setTimeout(loadListDm, 1000)
                }else {
                    alertGloable(data.message, "false");
                }
            }).catch(error => {
                console.log(error);
            })
        }
    });
    function loadListDm(){
        customLoadPage(`${localdomain}/web/daumoi/list-daumoi`, "content_box");
    }
    $(".btn-addClue").click(function(){
        customLoadPage(`${localdomain}/web/daumoi/add-clue`, "content_box");
    });
    $(".dm-edit").click(function(){
        let idClue = $(this).attr("data-id");
        customLoadPage(`${localdomain}/web/daumoi/edit-clue?id=${idClue}`, "content_box");
    })
})