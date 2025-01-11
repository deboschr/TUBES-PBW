document.addEventListener("DOMContentLoaded", () => {
    const activityList = document.getElementById("activityList");
    const addActivityBtn = document.getElementById("addActivityBtn");
    const activityFormPopup = document.getElementById("activityFormPopup");
    const activityDetailPopup = document.getElementById("activityDetailPopup");
    const cancelActivity = document.getElementById("cancelActivity");
    const saveActivity = document.getElementById("saveActivity");
    const typeSelect = document.getElementById("type");
    const raceDetails = document.getElementById("raceDetails");
    const closeDetailPopup = document.getElementById("closeDetailPopup");

    let activities = [];

    // Fungsi untuk membuka pop-up tambah/edit aktivitas
    addActivityBtn.addEventListener("click", () => {
        openFormPopup();
    });

    // Fungsi untuk menyembunyikan pop-up form
    cancelActivity.addEventListener("click", () => {
        closeFormPopup();
    });

    // Menampilkan/menyembunyikan detail race
    if (typeSelect && raceDetails) {
        typeSelect.addEventListener("change", () => {
            if (typeSelect.value === "race") {
                raceDetails.style.display = "block";
            } else {
                raceDetails.style.display = "none";
            }
        });
    } else {
        console.warn("Elemen 'typeSelect' atau 'raceDetails' tidak ditemukan di DOM.");
    }

    // Fungsi untuk menyimpan aktivitas
    saveActivity.addEventListener("click", (e) => {
        e.preventDefault();
        const activity = getFormData();
        if (activity) {
            activities.push(activity);
            renderActivityList();
            closeFormPopup();
        }
    });

    // Fungsi untuk menampilkan daftar aktivitas
    function renderActivityList() {
        activityList.innerHTML = "";
        activities.forEach((activity, index) => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${activity.title}</td>
                <td>${activity.date}</td>
                <td>${activity.time}</td>
                <td>${activity.distance} km</td>
                <td>${activity.type}</td>
                <td>
                    <button onclick="showDetail(${index})">Detail</button>
                </td>
            `;

            activityList.appendChild(row);
        });
    }

    // Fungsi untuk mendapatkan data dari form
    function getFormData() {
        const date = document.getElementById("date").value;
        const title = document.getElementById("title").value;
        const description = document.getElementById("description").value;
        const time = document.getElementById("time").value;
        const distance = document.getElementById("distance").value;
        const type = document.getElementById("type").value;
        const photos = document.getElementById("photo").files;
        const raceName = document.getElementById("raceName").value;
        const rank = document.getElementById("rank").value;

        if (!date || !title || !time || !distance || !type) {
            alert("Harap isi semua bidang yang wajib!");
            return null;
        }

        return {
            date,
            title,
            description,
            time,
            distance,
            type,
            photos: Array.from(photos),
            raceName: type === "race" ? raceName : null,
            rank: type === "race" ? rank : null,
        };
    }

    // Fungsi untuk menampilkan detail aktivitas
    window.showDetail = function (index) {
        const activity = activities[index];
        document.getElementById("detailDate").textContent = activity.date;
        document.getElementById("detailTitle").textContent = activity.title;
        document.getElementById("detailDescription").textContent = activity.description;
        document.getElementById("detailTime").textContent = activity.time;
        document.getElementById("detailDistance").textContent = `${activity.distance} km`;
        document.getElementById("detailType").textContent = activity.type;

        if (activity.type === "race") {
            document.getElementById("detailRace").style.display = "block";
            document.getElementById("detailRaceName").textContent = activity.raceName;
            document.getElementById("detailRank").textContent = activity.rank;
        } else {
            document.getElementById("detailRace").style.display = "none";
        }

        // Tampilkan foto (jika ada)
        const detailPhoto = document.getElementById("detailPhoto");
        if (activity.photos.length > 0) {
            detailPhoto.src = URL.createObjectURL(activity.photos[0]);
            detailPhoto.style.display = "block";
        } else {
            detailPhoto.style.display = "none";
        }

        activityDetailPopup.style.display = "flex";
    };

    // Fungsi untuk menutup form pop-up
    function closeFormPopup() {
        activityFormPopup.style.display = "none";
    }

    // Fungsi untuk membuka form pop-up
    function openFormPopup() {
        activityFormPopup.style.display = "flex";
        resetForm();
    }

    // Fungsi untuk mereset form
    function resetForm() {
        document.getElementById("activityForm").reset();
        raceDetails.style.display = "none";
    }

        if (closeDetailPopup) {
        closeDetailPopup.addEventListener("click", () => {
            activityDetailPopup.style.display = "none"; // Tutup modal
        });
    }
    
    const editActivityBtn = document.getElementById("editActivity");

    // Fungsi untuk membuka form edit dengan data aktivitas
    if (editActivityBtn) {
        editActivityBtn.addEventListener("click", () => {
            activityDetailPopup.style.display = "none"; // Tutup modal detail
            openEditForm();
        });
    }

    function openEditForm() {
        const index = parseInt(activityDetailPopup.dataset.currentIndex, 10); // Ambil indeks aktivitas
        const activity = activities[index];

        if (activity) {
            // Isi form dengan data aktivitas
            document.getElementById("date").value = activity.date;
            document.getElementById("title").value = activity.title;
            document.getElementById("description").value = activity.description;
            document.getElementById("time").value = activity.time;
            document.getElementById("distance").value = activity.distance;
            document.getElementById("type").value = activity.type;

            // Tampilkan bagian race jika jenis aktivitas adalah "race"
            if (activity.type === "race") {
                document.getElementById("raceDetails").style.display = "block";
                document.getElementById("raceName").value = activity.raceName || "";
                document.getElementById("rank").value = activity.rank || "";
            } else {
                document.getElementById("raceDetails").style.display = "none";
            }

            // Simpan indeks ke dalam atribut form
            activityFormPopup.dataset.currentIndex = index;

            // Tampilkan modal form
            activityFormPopup.style.display = "flex";
        }
    }
});
