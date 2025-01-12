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

<<<<<<< HEAD
=======
function handleFormSubmit(event) {
	event.preventDefault();
	const form = event.target;
	const formData = new FormData(form);
	const activity = Object.fromEntries(formData);
	const modalId = form.id.includes("add")
		? "activityFormPopup"
		: "activityDetailPopup";

	if (activity.id) {
		updateActivity(activity);
	} else {
		createActivity(activity);
	}
	closeModal(modalId);
}

>>>>>>> parent of 50bc43b (ada)
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
	document.getElementById("detail-time").value = activity.time;
	document.getElementById("detail-distance").value = activity.distance;
	document.getElementById("detail-type").value = activity.type;

	openModal("activityDetailPopup");
}

<<<<<<< HEAD
=======
function createActivity(activity) {
	fetch("/activity", {
		method: "POST",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(activity),
	})
		.then((response) => response.json())
		.then((data) => console.log("Activity created:", data))
		.catch((error) => console.error("Error creating activity:", error));
}

function updateActivity(activity) {
	fetch(`/activity/${activity.id}`, {
		method: "PUT",
		headers: { "Content-Type": "application/json" },
		body: JSON.stringify(activity),
	})
		.then((response) => response.json())
		.then((data) => console.log("Activity updated:", data))
		.catch((error) => console.error("Error updating activity:", error));
}

>>>>>>> parent of 50bc43b (ada)
function deleteActivity(activityId) {
	fetch(`/activity/${activityId}`, { method: "DELETE" })
		.then(() => {
			console.log("Activity deleted");
			// Optionally, remove the activity from the DOM or refresh the list
		})
		.catch((error) => console.error("Error deleting activity:", error));
}
