$(document).ready(function () {
    $(".menu-user").click(function () {
        customLoadPage(`${localdomain}/admin/user/get/list-user`, "content");
    });
    $(".menu-product").click(function () {
        customLoadPage(`${localdomain}/web/list-product`, "content");
    });
    if (fullName !== "undefined" && fullName !== null) {
        $("#box_login").html(`
            <div class="d-flex flex-column">
            <b><i class="fa fa-user" aria-hidden="true" style="padding-right: 6px;"></i>${fullName}</b>
            <a href="/logout" class="btn-logout" style="margin: 0; padding: 0; font-size: 12px; cursor: pointer;">Đăng xuất <i class="fa fa-sign-out" aria-hidden="true"></i></a>
            </div>
        `);
        $("#box_login-phone").html(`
            <div class="d-flex flex-column">
            <b><i class="fa fa-user" aria-hidden="true" style="padding-right: 6px;"></i>${fullName}</b>
            <a href="/logout" class="btn-logout" style="margin: 0; padding: 0; font-size: 12px; cursor: pointer;">Đăng xuất <i class="fa fa-sign-out" aria-hidden="true"></i></a>
            </div>
        `);
        $(".btn-logout").click(function () {
            clearInfo()
        })
    }
})