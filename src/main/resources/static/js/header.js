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


// URL パラメータ取得
const params = new URLSearchParams(window.location.search);
const from = params.get("from");

// すべての /top リンクを書き換える
if (from) {
  document.querySelectorAll('a[href="/top"]').forEach(link => {
    link.href = `/top?from=${from}`;
  });

  // onclick で /top を使っているボタンも書き換える
  document.querySelectorAll('[onclick*="/top"]').forEach(btn => {
    btn.setAttribute("onclick", `location.href='/top?from=${from}'`);
  });
}