// ▼ MENU 開閉
document.addEventListener("DOMContentLoaded", () => {
    const menuBtn = document.getElementById("js-menu-btn");
    const nav = document.getElementById("js-global-nav");
    const infoToggle = document.querySelector(".info-toggle");
    const infoSub = document.querySelector(".info-sub");

    if (menuBtn) {
        menuBtn.addEventListener("click", () => {
            nav.classList.toggle("open");
        });
    }

    if (infoToggle) {
        infoToggle.addEventListener("click", () => {
            infoSub.classList.toggle("open");
        });
    }
});
(function() {
    const params = new URLSearchParams(window.location.search);
    const from = params.get("from");

    // TOP に来たときだけ保存
    if (from) {
        localStorage.setItem("from", from);
    }

    // 保存されている from を取得
    const savedFrom = localStorage.getItem("from");
    if (!savedFrom) return;

    // TOP のリンクだけ書き換える
    document.querySelectorAll('a[href="/top"]').forEach(link => {
        link.href = `/top?from=${savedFrom}`;
    });
})();