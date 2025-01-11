document.addEventListener("DOMContentLoaded", function () {
    if (window.location.pathname === "/home.html") {
        const modal = document.getElementById("about-us-modal");
        const openButton = document.getElementById("about-us-button");
        const closeButton = document.querySelector(".close-button");

        if (openButton && modal && closeButton) {
            openButton.addEventListener("click", function (e) {
                e.preventDefault();
                modal.style.display = "flex";
            });

            closeButton.addEventListener("click", function () {
                modal.style.display = "none";
            });

            window.addEventListener("click", function (e) {
                if (e.target === modal) {
                    modal.style.display = "none";
                }
            });
        }
    }
});
