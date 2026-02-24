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

// ▼ from を全リンクに付与（DOMContentLoaded の外で実行）
(function() {
    const params = new URLSearchParams(window.location.search);
    const from = params.get("from");

    if (!from) return;

    // すべての内部リンクに from を付与
    document.querySelectorAll('a[href^="/"]').forEach(link => {
        const url = new URL(link.href, location.origin);

        if (!url.searchParams.get("from")) {
            url.searchParams.set("from", from);
            link.href = url.pathname + "?" + url.searchParams.toString();
        }
    });

    // onclick の /top も書き換え
    document.querySelectorAll('[onclick*="/top"]').forEach(btn => {
        btn.setAttribute("onclick", `location.href='/top?from=${from}'`);
    });
})();