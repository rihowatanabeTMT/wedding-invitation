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

//バーコード
JsBarcode("#barcode", "2026.05.09-Kota & Riho Wedding Invitation", {
  format: "code128",
  lineColor: "#000",
  width: 2,
  height: 60,
  displayValue: false
});
	
window.addEventListener('load', () => {
  const blur = document.querySelector('.hero-blur-overlay');
  const inv = document.querySelector('.hero-photo-invitation');

  // Invitation 出現（横から）
  inv.classList.add('show');
  blur.style.opacity = 1;

  // Invitation が消えるタイミング
  setTimeout(() => {
    inv.classList.remove('show');
    inv.classList.add('hide'); // ← ふわっと消える
    blur.style.opacity = 0;
  }, 2000);
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