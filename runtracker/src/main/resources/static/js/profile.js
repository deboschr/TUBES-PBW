
document.addEventListener("DOMContentLoaded", () => {
    const editButton = document.getElementById("edit-button");
    const popup = document.getElementById("popup-form");
    const cancelButton = document.getElementById("cancel");
    const form = document.getElementById("edit-form");
    const nameField = document.getElementById("name");
    const emailField = document.getElementById("email");
    const profileName = document.getElementById("profile-name");
    const profileEmail = document.getElementById("profile-email");

    // Show popup
    editButton.addEventListener("click", () => {
        popup.style.display = "flex";
    });

    // Close popup
    cancelButton.addEventListener("click", () => {
        popup.style.display = "none";
    });

    // Handle form submission
    form.addEventListener("submit", (event) => {
        event.preventDefault();
        const newName = nameField.value;
        const newEmail = emailField.value;

        if (newName && newEmail) {
            profileName.textContent = newName;
            profileEmail.textContent = newEmail;
            alert("Profile updated successfully!");
            popup.style.display = "none";
        } else {
            alert("Please fill in all fields!");
        }
    });
});
