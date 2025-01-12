document.addEventListener("DOMContentLoaded", () => {
	setupEventListeners();
});

function setupEventListeners() {
	const addActivityBtn = document.getElementById("addActivityBtn");
	const addActivityForm = document.getElementById("add-activityForm");
	const cancelActivityBtn = document.getElementById("cancelActivity");
	const activityList = document.getElementById("activityList");
	const typeSelect = document.getElementById("add-type"); // assuming you have a type field for race details
	const raceDetails = document.getElementById("add-raceDetails");

	addActivityBtn.addEventListener("click", () =>
		openModal("activityFormPopup")
	);
	cancelActivityBtn.addEventListener("click", () =>
		closeModal("activityFormPopup")
	);
	addActivityForm.addEventListener("submit", handleFormSubmit);

	typeSelect.addEventListener("change", () => {
		raceDetails.style.display = typeSelect.value === "race" ? "block" : "none";
	});

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
			.activityId; // Ensure this dataset is set when populating the form
		if (confirm("Are you sure you want to delete this activity?")) {
			deleteActivity(activityId);
			closeModal("activityDetailPopup");
		}
	});
}

function handleFormSubmit(event) {
	event.preventDefault();
	const form = event.target;
	const formData = new FormData(form);

	// Pastikan ID tidak ada atau null untuk kasus create
	if (!formData.get("id")) {
		createActivity(formData);
	} else {
		updateActivity(formData);
	}
	closeModal(form.dataset.modalId);
}

function openModal(modalId) {
	document.getElementById(modalId).style.display = "flex";
	if (modalId === "activityFormPopup") {
		document.getElementById("add-activityForm").reset();
		document.getElementById("add-raceDetails").style.display = "none";
	}
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

function createActivity(formData) {
	fetch("/activity", {
		method: "POST",
		body: formData,
	})
		.then((response) => response.json())
		.then((data) => {
			console.log("Activity created:", data);
			closeModal("activityFormPopup");
		})
		.catch((error) => console.error("Error creating activity:", error));
}

function updateActivity(formData) {
	const activityId = formData.get("id"); // Asumsikan id ada di dalam FormData
	fetch(`/activity/${activityId}`, {
		method: "PUT",
		body: formData,
	})
		.then((response) => response.json())
		.then((data) => {
			console.log("Activity updated:", data);
		})
		.catch((error) => console.error("Error updating activity:", error));
}

function deleteActivity(activityId) {
	fetch(`/activity/${activityId}`, { method: "DELETE" })
		.then(() => {
			console.log("Activity deleted");
			// Optionally, remove the activity from the DOM or refresh the list
		})
		.catch((error) => console.error("Error deleting activity:", error));
}
