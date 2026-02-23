document.addEventListener("DOMContentLoaded", () => {
    const menuBtn = document.getElementById("js-menu-btn");
    const nav = document.getElementById("js-global-nav");
    const infoToggle = document.querySelector(".info-toggle");
    const infoSub = document.querySelector(".info-sub");

    // MENU 開閉
    menuBtn.addEventListener("click", () => {
        nav.classList.toggle("open");
    });

    // INFOMATION 開閉
    infoToggle.addEventListener("click", () => {
        infoSub.classList.toggle("open");
    });
});