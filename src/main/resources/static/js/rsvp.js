/**
 * アレルギー
 */
const allergyRadios = document.getElementsByName('allergy');
const allergyDetail = document.getElementById('allergy-detail');

allergyRadios.forEach(radio => {
  radio.addEventListener('change', () => {
    allergyDetail.style.display = (radio.value === 'yes') ? 'block' : 'none';
  });
});

/**
 * 郵便番号と住所
 */
const postInput = document.getElementById('post');

postInput.addEventListener('input', function () {
  let value = this.value.replace(/[^0-9]/g, '');

  if (value.length > 3) {
    value = value.slice(0, 3) + '-' + value.slice(3, 7);
  }

  this.value = value;
});

document.getElementById('post').addEventListener('blur', function(){
  const zipcode = this.value.replace(/[^0-9]/g,'');

  if(zipcode.length !== 7) return;

  fetch(`https://zipcloud.ibsnet.co.jp/api/search?zipcode=${zipcode}`)
    .then(response => response.json())
    .then(data =>{
      if(data.results){
        const r = data.results[0];
        document.getElementById('prefecture').value = r.address1;
        document.getElementById('address').value = r.address2 + r.address3;
      }
    });
});

/**
 * 同伴者
 */
const plusoneRadios = document.getElementsByName('plusone_flag');
const plusoneArea = document.getElementById('plusone-area');
const plusoneList = document.getElementById('plusone-list');
const addBtn = document.getElementById('add-plusone');

let guestIndex = 0;
const maxGuest = 4;

function createGuestBlock(index) {
  const block = document.createElement('div');
  block.classList.add('form-group');
  block.setAttribute('id', `guest_block_${index}`);

  block.innerHTML = `
    <h4>同伴者 ${index + 1}</h4>

    <label>お名前</label>
    <input type="text" name="plusones[${index}].plusOneName" required>

    <label>ふりがな</label>
    <input type="text" name="plusones[${index}].plusOneFurigana" required>

    <label>アレルギー</label>
    <textarea name="plusones[${index}].plusOneAllergy" rows="2"></textarea>

    <button type="button" class="remove-btn" data-index="${index}">削除</button>
  `;

  return block;
}

plusoneRadios.forEach(radio => {
  radio.addEventListener('change', () => {
    if (radio.value === 'yes') {
      plusoneArea.style.display = 'block';
      plusoneList.innerHTML = '';
      guestIndex = 0;
      plusoneList.appendChild(createGuestBlock(guestIndex));
    } else {
      plusoneArea.style.display = 'none';
      plusoneList.innerHTML = '';
      guestIndex = 0;
    }
  });
});

addBtn.addEventListener('click', () => {
  if (guestIndex >= maxGuest - 1) return;

  guestIndex++;
  plusoneList.appendChild(createGuestBlock(guestIndex));
});

plusoneList.addEventListener('click', (e) => {
  if (e.target.classList.contains('remove-btn')) {
    const index = e.target.getAttribute('data-index');
    const block = document.getElementById(`guest_block_${index}`);
    block.remove();
    renumberGuests();
  }
});

function renumberGuests() {
  const blocks = plusoneList.querySelectorAll('.form-group');
  let num = 0;

  blocks.forEach(block => {
    block.id = `guest_block_${num}`;

    const title = block.querySelector('h4');
    title.textContent = `同伴者 ${num + 1}`;

    const nameInput = block.querySelector(`[name*="plusOneName"]`);
    nameInput.name = `plusones[${num}].plusOneName`;

    const furiInput = block.querySelector(`[name*="plusOneFurigana"]`);
    furiInput.name = `plusones[${num}].plusOneFurigana`;

    const allergyInput = block.querySelector(`[name*="plusOneAllergy"]`);
    allergyInput.name = `plusones[${num}].plusOneAllergy`;

    const removeBtn = block.querySelector('.remove-btn');
    removeBtn.setAttribute('data-index', num);

    num++;
  });

  guestIndex = blocks.length - 1;

  if (guestIndex < 0) {
    document.querySelector('input[name="plusone_flag"][value="no"]').checked = true;
    plusoneArea.style.display = 'none';
  }
}