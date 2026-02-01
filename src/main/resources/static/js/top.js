/**
 * countdown
 */
const wDate = new Date('2026-05-09T10:30:00');

function countDown(){
	const now = new Date();
	const diff = wDate - now;
	
	if(diff <= 0){
		document.getElementById('days').textContent = '0';
		document.getElementById('hours').textContent = '0';
		document.getElementById('minutes').textContent = '0';
		document.getElementById('seconds').textContent = '0';
		// もう更新しない
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
/**
* Swiper　画像スライド
*/
	new Swiper('.photo-slider', {
	  loop: true,
	  autoplay: { delay: 2500 },
	  pagination: { el: '.swiper-pagination' },
	  effect: 'slide',
	  speed: 600,
	});
	
	