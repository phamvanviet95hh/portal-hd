$(document).ready(function (){
    loadContentHome();
    function loadContentHome(){
        customLoadPage(`${localdomain}/web/content-home-menu`, "sidebar-menu");
        customLoadPage(`${localdomain}/web/content-home`, "content_box");
    }
})