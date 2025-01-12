document.addEventListener("DOMContentLoaded", () => {
	setupEventListeners();
});

function setupEventListeners() {
	const activityList = document.getElementById("activityList");
	const addActivityBtn = document.getElementById("addActivityBtn");
	const activityForm = document.getElementById("activityForm");
	const cancelActivityBtn = document.getElementById("cancelActivity");
	const typeSelect = document.getElementById("type");
	const raceDetails = document.getElementById("raceDetails");

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
		} else if (event.target.classList.contains("editBtn")) {
			const activityId = event.target.dataset.activityId;
			editActivity(activityId);
		} else if (event.target.classList.contains("deleteBtn")) {
			const activityId = event.target.dataset.activityId;
			deleteActivity(activityId);
		}
	});

	activityForm.addEventListener("submit", handleFormSubmit);

	typeSelect.addEventListener("change", () => {
		if (typeSelect.value === "race") {
			raceDetails.style.display = "block";
		} else {
			raceDetails.style.display = "none";
		}
	});
}


function handleFormSubmit(event) {
	event.preventDefault();
	const formData = new FormData(event.target);
	const activity = Object.fromEntries(formData);
	if (activity.id) {
		updateActivity(activity);
	} else {
		createActivity(activity);
	}
	closeModal("activityFormPopup");
}

function openModal(modalId) {
	document.getElementById(modalId).style.display = "flex";
	resetForm();
}

function closeModal(modalId) {
	document.getElementById(modalId).style.display = "none";
}

function resetForm() {
	document.getElementById("activityForm").reset();
	document.getElementById("raceDetails").style.display = "none";
}

function showDetail(activityId) {
	fetch(`/activity/${activityId}`)
		.then((response) => response.json())
		.then((activity) => populateDetailPopup(activity))
		.catch((error) => console.error("Error fetching activity details:", error));
}

function editActivity(activityId) {
	fetch(`/activity/${activityId}`)
		.then((response) => response.json())
		.then((activity) => {
			populateForm(activity);
			openModal("activityFormPopup");
		})
		.catch((error) =>
			console.error("Error fetching activity for edit:", error)
		);
}

function deleteActivity(activityId) {
	fetch(`/activity/${activityId}`, { method: "DELETE" })
		.then(() => {
			console.log("Activity deleted");
			// Optionally, remove the activity from the DOM or refresh the list
		})
		.catch((error) => console.error("Error deleting activity:", error));
}

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

function populateDetailPopup(activity) {
	document.getElementById("detailDate").textContent = activity.date;
	document.getElementById("detailTitle").textContent = activity.title;
	document.getElementById("detailDescription").textContent =
		activity.description;
	document.getElementById("detailTime").textContent = activity.time;
	document.getElementById("detailDistance").textContent = activity.distance;
	document.getElementById("detailType").textContent = activity.type;

	if (activity.type === "race") {
		document.getElementById("detailRaceName").textContent = activity.raceName;
		document.getElementById("detailRank").textContent = activity.rank;
		document.getElementById("detailRace").style.display = "block";
	} else {
		document.getElementById("detailRace").style.display = "none";
	}

	openModal("activityDetailPopup");
}

function populateForm(activity) {
	document.getElementById("title").value = activity.title;
	document.getElementById("date").value = activity.date;
	document.getElementById("description").value = activity.description;
	document.getElementById("time").value = activity.time;
	document.getElementById("distance").value = activity.distance;
	document.getElementById("type").value = activity.type;

	if (activity.type === "race") {
		document.getElementById("raceDetails").style.display = "block";
		document.getElementById("raceName").value = activity.raceName;
		document.getElementById("rank").value = activity.rank;
	} else {
		document.getElementById("raceDetails").style.display = "none";
	}
}
