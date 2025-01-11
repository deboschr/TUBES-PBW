document.addEventListener("DOMContentLoaded", () => {
    const detailButtons = document.querySelectorAll(".detailButton");
    const modal = document.getElementById("activityModal");
    const closeModal = document.getElementById("closeModal");

    const detailDate = document.getElementById("detailDate");
    const detailTitle = document.getElementById("detailTitle");
    const detailDescription = document.getElementById("detailDescription");
    const detailTime = document.getElementById("detailTime");
    const detailDistance = document.getElementById("detailDistance");
    const detailType = document.getElementById("detailType");

    const activities = [
        {
            title: "Marathon 5K",
            date: "2025-01-05",
            description: "Berlomba di marathon lokal",
            time: 1,
            distance: 5,
            type: "Race",
        },
    ];

    detailButtons.forEach((button, index) => {
        button.addEventListener("click", () => {
            const activity = activities[index];

            detailDate.value = activity.date;
            detailTitle.value = activity.title;
            detailDescription.value = activity.description;
            detailTime.value = activity.time;
            detailDistance.value = activity.distance;
            detailType.value = activity.type;

            modal.style.display = "flex";
        });
    });

    closeModal.addEventListener("click", () => {
        modal.style.display = "none";
    });

    modal.addEventListener("click", (event) => {
        if (event.target === modal) {
            modal.style.display = "none";
        }
    });
});
