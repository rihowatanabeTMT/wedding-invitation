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


//スライドショー
const slides = document.querySelector('.slides');
const images = document.querySelectorAll('.slides img');
const dots = document.querySelectorAll('.dot');
const nextBtn = document.querySelector('.next');
const prevBtn = document.querySelector('.prev');

// ★ 本物の1枚目は index=1（左に複製5があるため）
let index = 1;

function updateSlider(animate = true) {
  const slideWidth = images[0].offsetWidth + 20; // 800 + gap20 = 820

  slides.style.transition = animate ? "transform 0.6s ease" : "none";
  slides.style.transform = `translateX(${-index * slideWidth}px)`;

  // ★ ドット更新（本物の1〜5に合わせる）
  dots.forEach(d => d.classList.remove('active'));
  dots[(index - 1 + 5) % 5].classList.add('active');
}

// → ボタン
nextBtn.addEventListener('click', () => {
  index++;
  updateSlider();

  // ★ 最後の複製1に来たら、本物1へ瞬間移動
  if (index === images.length - 1) {
    setTimeout(() => {
      index = 1;
      updateSlider(false);
    }, 600);
  }
});

// ← ボタン
prevBtn.addEventListener('click', () => {
  index--;
  updateSlider();

  // ★ 最初の複製5に来たら、本物5へ瞬間移動
  if (index === 0) {
    setTimeout(() => {
      index = images.length - 2;
      updateSlider(false);
    }, 600);
  }
});

// 自動スライド
setInterval(() => {
  nextBtn.click();
}, 5000);

// ★ 初期位置（本物1）へ
updateSlider(false);



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