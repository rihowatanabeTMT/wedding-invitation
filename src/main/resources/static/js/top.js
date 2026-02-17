const wDate = new Date('2026-05-09T10:30:00');

function countDown(){
    const now = new Date();
    const diff = wDate - now;

    if(diff <= 0){
        document.getElementById('days').textContent = '0';
        document.getElementById('hours').textContent = '0';
        document.getElementById('minutes').textContent = '0';
        document.getElementById('seconds').textContent = '0';
        clearInterval(timerId);
        return;
    }

    const totalSeconds = Math.floor(diff / 1000);
    const days = Math.floor(totalSeconds / (60 * 60 * 24));
    const hours = Math.floor((totalSeconds % (60 * 60 * 24)) / (60 * 60));
    const minutes = Math.floor((totalSeconds % (60 * 60)) / 60);
    const seconds = totalSeconds % 60;

    document.getElementById('days').textContent = days;
    document.getElementById('hours').textContent = String(hours).padStart(2, '0');
    document.getElementById('minutes').textContent = String(minutes).padStart(2, '0');
    document.getElementById('seconds').textContent = String(seconds).padStart(2, '0');
}

const timerId = setInterval(countDown, 1000);
countDown();


// フェードイン
const fadeElems = document.querySelectorAll('.fadein');

function fadeInOnScroll() {
  fadeElems.forEach(el => {
    const rect = el.getBoundingClientRect().top;
    const trigger = window.innerHeight * 0.85;
    if (rect < trigger) {
      el.classList.add('show');
    }
  });
}

window.addEventListener('scroll', fadeInOnScroll);
fadeInOnScroll();

//背景戻し

window.addEventListener('load', () => {
  const card = document.querySelector('.hero-card');

  // Invitation（大）のアニメ開始と同時に背景を薄くする
  setTimeout(() => {
    card.classList.add('hero-fade-bg');
  }, 0.1);

  // Invitation（大）が消えたら背景を元に戻す（3.2秒後）
  setTimeout(() => {
    card.classList.remove('hero-fade-bg');
  }, 3200);
});

//バーコード
JsBarcode("#barcode", "2026.05.09-Kota & Riho Wedding Invitation", {
  format: "code128",
  lineColor: "#000",
  width: 2,
  height: 60,
  displayValue: false
});


//スマホ縮小
function adjustHeroScale() {
  const cardWidth = 900;
  const screenWidth = window.innerWidth;

  if (screenWidth < 900) {
    const scale = screenWidth / cardWidth;
    document.documentElement.style.setProperty('--hero-scale', scale);
  } else {
    document.documentElement.style.setProperty('--hero-scale', 1);
  }
}

window.addEventListener('load', adjustHeroScale);
window.addEventListener('resize', adjustHeroScale);