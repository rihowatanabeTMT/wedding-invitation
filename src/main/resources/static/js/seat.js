// ==============================
// Build merged guest list
// ==============================

const allGuests = [
    ...groomGuests.map(g => ({ ...g, side: "groom" })),
    ...brideGuests.map(g => ({ ...g, side: "bride" }))
];

// ==============================
// Build table → guest mapping
// ==============================

const tables = {};

function buildTables() {
    const tableIds = ["A","B","C","D","E","F","G","H","I","J"];
    tableIds.forEach(t => tables[t] = []);

    allGuests.forEach(g => {
        if (!g.table) return;
        tables[g.table].push(g);
    });

    // Sort by positionNo
    Object.keys(tables).forEach(t => {
        tables[t].sort((a, b) => (a.positionNo || 0) - (b.positionNo || 0));
    });
}

buildTables();

// ==============================
// DOM elements
// ==============================

const tableButtons = document.querySelectorAll("[data-table]");
const selectedTableName = document.getElementById("selectedTableName");

const leftSeats = [
    document.getElementById("left1"),
    document.getElementById("left2"),
    document.getElementById("left3"),
    document.getElementById("left4")
];

const rightSeats = [
    document.getElementById("right1"),
    document.getElementById("right2"),
    document.getElementById("right3"),
    document.getElementById("right4")
];

const sideSelect = document.getElementById("sideSelect");
const guestSelect = document.getElementById("guestSelect");
const searchSeatBtn = document.getElementById("searchSeatBtn");

let highlightedGuestId = null;

// ==============================
// Show table seats
// ==============================

function showTable(tableId) {

    // ★ 最初は非表示 → 卓を押したら表示
    document.querySelector(".selected-table-area").style.display = "block";

    const guests = tables[tableId] || [];
    const total = guests.length;

    selectedTableName.textContent = tableId;

    // Clear all seats
    [...leftSeats, ...rightSeats].forEach(el => {
        el.innerHTML = "";
        el.classList.remove("highlight");
    });

    if (total === 0) return;

    const leftCount = Math.ceil(total / 2);

    guests.forEach((g, i) => {

        // ★ seat-block（relation + name）を作る
        const wrapper = document.createElement("div");
        wrapper.classList.add("seat-block");

        const rel = document.createElement("div");
        rel.classList.add("relation");
        rel.textContent = g.fullRelation || "";

		const nm = document.createElement("div");
		nm.classList.add("name");

		nm.innerHTML = `
		    <span class="lastname">${g.lastName}</span>
		    <span class="firstname">${g.firstName}</span>
		    <span class="sama">様</span>
		`;	

        wrapper.appendChild(rel);
        wrapper.appendChild(nm);

        // Highlight
        if (g.id === highlightedGuestId) {
            wrapper.classList.add("highlight");
        }

        // 左右に配置
        if (i < leftCount) {
            leftSeats[i].appendChild(wrapper);
        } else {
            const idx = i - leftCount;
            rightSeats[idx].appendChild(wrapper);
        }
    });
}

// ==============================
// Highlight table button
// ==============================

function highlightTableButton(tableId) {
    tableButtons.forEach(btn => {
        if (btn.dataset.table === tableId) {
            btn.classList.add("table-selected");
        } else {
            btn.classList.remove("table-selected");
        }
    });
}

// ==============================
// Table button click
// ==============================

tableButtons.forEach(btn => {
    btn.addEventListener("click", () => {
        const tableId = btn.dataset.table;
        highlightedGuestId = null;
        showTable(tableId);
        highlightTableButton(tableId);
    });
});

// ==============================
// Side selection → update guest list
// ==============================

sideSelect.addEventListener("change", () => {
    const side = sideSelect.value;

    guestSelect.innerHTML = "";

    if (!side) {
        guestSelect.innerHTML = `<option value="">先に新郎/新婦を選んでください</option>`;
        return;
    }

    const list = allGuests.filter(g => g.side === side);

    guestSelect.innerHTML = `<option value="">名前を選択</option>`;
    list.forEach(g => {
        const opt = document.createElement("option");
        opt.value = g.id;
        opt.textContent = g.lastName + g.firstName;
        guestSelect.appendChild(opt);
    });
});

// ==============================
// Search → jump to table
// ==============================

searchSeatBtn.addEventListener("click", () => {
    const guestId = Number(guestSelect.value);
    if (!guestId) {
        alert("名前を選択してください");
        return;
    }

    const guest = allGuests.find(g => g.id === guestId);
    if (!guest) {
        alert("該当するゲストが見つかりません");
        return;
    }

    highlightedGuestId = guest.id;

    const tableId = guest.table;
    showTable(tableId);
    highlightTableButton(tableId);
});