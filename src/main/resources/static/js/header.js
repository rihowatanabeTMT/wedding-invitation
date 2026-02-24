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

    // ▼▼ ここからが “from を全ページで保持する” ための本命 ▼▼

    const params = new URLSearchParams(window.location.search);
    const from = params.get("from");

    if (from) {
        // すべての内部リンクに from を付与
        document.querySelectorAll('a[href^="/"]').forEach(link => {
            const url = new URL(link.href, location.origin);

            // すでに from が付いていなければ付与
            if (!url.searchParams.get("from")) {
                url.searchParams.set("from", from);
                link.href = url.pathname + "?" + url.searchParams.toString();
            }
        });

        // onclick で /top を使っているボタンも書き換える
        document.querySelectorAll('[onclick*="/top"]').forEach(btn => {
            btn.setAttribute("onclick", `location.href='/top?from=${from}'`);
        });
    }
});