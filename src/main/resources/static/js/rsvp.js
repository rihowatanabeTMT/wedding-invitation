/**
 * アレルギー
 */
const allergyRadios = document.getElementsByName('allergy');
const allergyDetail = document.getElementById('allergy-detail');

// ラジオ変更時
allergyRadios.forEach(radio => {
  radio.addEventListener('change', () => {
    allergyDetail.style.display = (radio.value === 'yes') ? 'block' : 'none';
  });
});

// ★ 初期表示（戻る時に必要）
const checkedAllergy = document.querySelector('input[name="allergy"]:checked');
if (checkedAllergy && checkedAllergy.value === 'yes') {
  allergyDetail.style.display = 'block';
}


/**
 * 同伴者
 */
const plusoneRadios = document.getElementsByName('plusone_flag');
const plusoneArea = document.getElementById('plusone-area');
const plusoneList = document.getElementById('plusone-list');
const addBtn = document.getElementById('add-plusone');

let guestIndex = 0;
const maxGuest = 4;

/**
 * 同伴者ブロック生成
 */
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


/**
 * 同伴者ラジオ変更時
 */
plusoneRadios.forEach(radio => {
  radio.addEventListener('change', () => {

    if (radio.value === 'yes') {
      plusoneArea.style.display = 'block';

      // ★ 既にサーバ描画された同伴者がある場合は初期化しない
      if (plusoneList.children.length === 0) {
        guestIndex = 0;
        plusoneList.appendChild(createGuestBlock(guestIndex));
      }

    } else {
      plusoneArea.style.display = 'none';
      plusoneList.innerHTML = '';
      guestIndex = 0;
    }
  });
});


/**
 * 同伴者追加
 */
addBtn.addEventListener('click', () => {
  if (guestIndex >= maxGuest - 1) return;

  guestIndex++;
  plusoneList.appendChild(createGuestBlock(guestIndex));
});


/**
 * 同伴者削除
 */
plusoneList.addEventListener('click', (e) => {
  if (e.target.classList.contains('remove-btn')) {
    const index = e.target.getAttribute('data-index');
    const block = document.getElementById(`guest_block_${index}`);
    block.remove();
    renumberGuests();
  }
});


/**
 * 削除後の番号振り直し
 */
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


/**
 * ★ 初期表示（戻る時に必要）
 * サーバ側で plusones が描画されている場合はそのまま表示
 */
if (plusoneList.children.length > 0) {
  plusoneArea.style.display = 'block';
  document.querySelector('input[name="plusone_flag"][value="yes"]').checked = true;
  guestIndex = plusoneList.querySelectorAll('.form-group').length - 1;
}