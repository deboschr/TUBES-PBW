document.getElementById("saveButton").addEventListener("click", function () {
    const date = document.getElementById("activityDate").value;
    const title = document.getElementById("activityTitle").value;
    const description = document.getElementById("activityDescription").value;
    const time = document.getElementById("activityTime").value;
    const distance = document.getElementById("activityDistance").value;
    const photos = document.getElementById("activityPhotos").files;

    if (!date || !title || !description || !time || !distance) {
        alert("Harap isi semua field yang wajib!");
        return;
    }

    if (photos.length > 5) {
        alert("Anda hanya dapat mengunggah maksimal 5 foto.");
        return;
    }

    alert("Aktivitas berhasil disimpan!");
    document.getElementById("activityForm").reset();
});

document.getElementById("cancelButton").addEventListener("click", function () {
    if (confirm("Apakah Anda yakin ingin membatalkan?")) {
        document.getElementById("activityForm").reset();
    }
});
