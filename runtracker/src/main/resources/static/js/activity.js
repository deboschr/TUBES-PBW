document.addEventListener("DOMContentLoaded", () => {
	setupEventListeners();
});

function setupEventListeners() {
	const addActivityBtn = document.getElementById("addActivityBtn");
	const cancelActivityBtn = document.getElementById("cancelActivity");
	const activityList = document.getElementById("activityList");

	addActivityBtn.addEventListener("click", () =>
		openModal("activityFormPopup")
	);
	cancelActivityBtn.addEventListener("click", () =>
		closeModal("activityFormPopup")
	);

	activityList.addEventListener("click", (event) => {
		if (event.target.classList.contains("detailBtn")) {
			const activityId = event.target.dataset.activityId;
			showDetail(activityId);
		}
	});

	const deleteDetailActivityBtn = document.getElementById(
		"deleteDetailActivity"
	);
	deleteDetailActivityBtn.addEventListener("click", () => {
		const activityId = document.getElementById("detail-activityForm").dataset
			.activityId;
		if (confirm("Are you sure you want to delete this activity?")) {
			deleteActivity(activityId);
			closeModal("activityDetailPopup");
		}
	});
}

function openModal(modalId) {
	document.getElementById(modalId).style.display = "flex";
}

function closeModal(modalId) {
	document.getElementById(modalId).style.display = "none";
}

function showDetail(activityId) {
	fetch(`/activity/${activityId}`)
		.then((response) => response.json())
		.then((activity) => populateFormDetail(activity))
		.catch((error) => console.error("Error fetching activity details:", error));
}

function populateFormDetail(activity) {
	document.getElementById("detail-title").value = activity.title;
	document.getElementById("detail-date").value = activity.date;
	document.getElementById("detail-description").value = activity.description;
	document.getElementById("detail-duration").value = activity.duration;
	document.getElementById("detail-distance").value = activity.distance;

	openModal("activityDetailPopup");
}

function deleteActivity(activityId) {
	fetch(`/activity/${activityId}`, { method: "DELETE" })
		.then(() => {
			console.log("Activity deleted");
			// Optionally, remove the activity from the DOM or refresh the list
		})
		.catch((error) => console.error("Error deleting activity:", error));
}
