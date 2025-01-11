const editButton = document.getElementById("editButton");
        const deleteButton = document.getElementById("deleteButton");
        const logoutButton = document.getElementById("logoutButton");
        const editModal = document.getElementById("editModal");
        const closeModal = document.getElementById("closeModal");
        const saveEdit = document.getElementById("saveEdit");
        const cancelEdit = document.getElementById("cancelEdit");

        editButton.onclick = () => {
            editModal.style.display = "flex";
        };

        closeModal.onclick = () => {
            editModal.style.display = "none";
        };

        cancelEdit.onclick = () => {
            editModal.style.display = "none";
        };

        saveEdit.onclick = () => {
            const newName = document.getElementById("editName").value;
            const newEmail = document.getElementById("editEmail").value;

            document.getElementById("profileName").innerText = `Nama: ${newName}`;
            document.getElementById("profileEmail").innerText = `Email: ${newEmail}`;

            const newPicture = document.getElementById("editPicture").files[0];
            if (newPicture) {
                const reader = new FileReader();
                reader.onload = () => {
                    document.getElementById("profilePicture").src = reader.result;
                };
                reader.readAsDataURL(newPicture);
            }

            editModal.style.display = "none";
        };

        deleteButton.onclick = () => {
            if (confirm("Apakah Anda yakin ingin menghapus akun?")) {
                alert("Akun berhasil dihapus.");
                // Redirect ke halaman lain atau logout
            }
        };

        logoutButton.onclick = () => {
            if (confirm("Apakah Anda yakin ingin log out?")) {
                alert("Berhasil log out.");
                // Redirect ke halaman login
            }
        };