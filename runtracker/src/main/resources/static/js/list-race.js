const upcomingRaces = [
            { name: "Jakarta Marathon", date: "2025-02-15", location: "Jakarta", description: "Event tahunan di Jakarta." },
            { name: "Bali Ultra Run", date: "2025-03-01", location: "Bali", description: "Perlombaan lari dengan pemandangan Bali." }
        ];

        const historyRaces = [
            { name: "Bandung 10K", date: "2025-01-10", location: "Bandung", distance: 10, time: 1, rank: 2, runners: ["John", "Sarah", "Mike", "Anna", "David"] },
            { name: "Surabaya Half Marathon", date: "2025-01-20", location: "Surabaya", distance: 21, time: 2.5, rank: 5, runners: ["Alex", "Jane", "Chris", "Emily", "Robert"] }
        ];

        const raceTable = document.getElementById("raceTable");
        const raceModal = document.getElementById("raceModal");
        const closeModal = document.getElementById("closeModal");

        function loadRaces(races, isUpcoming) {
            const tbody = raceTable.querySelector("tbody");
            tbody.innerHTML = "";

            races.forEach((race, index) => {
                const row = document.createElement("tr");
                row.innerHTML = `
                    <td>${race.name}</td>
                    <td>${race.date}</td>
                    <td>${race.location}</td>
                    <td><button class="button-detail" onclick="showRaceDetails(${index}, ${isUpcoming})">Detail</button></td>
                `;
                tbody.appendChild(row);
            });
        }

        function showRaceDetails(index, isUpcoming) {
            const race = isUpcoming ? upcomingRaces[index] : historyRaces[index];

            document.getElementById("raceName").innerText = race.name;
            document.getElementById("raceDate").innerText = race.date;
            document.getElementById("raceLocation").innerText = race.location;

            if (isUpcoming) {
                document.getElementById("raceDescriptionSection").style.display = "block";
                document.getElementById("raceDescription").innerText = race.description;
                document.getElementById("registerButton").style.display = "block";
                document.getElementById("raceDetailsSection").style.display = "none";
            } else {
                document.getElementById("raceDescriptionSection").style.display = "none";
                document.getElementById("registerButton").style.display = "none";
                document.getElementById("raceDetailsSection").style.display = "block";
                document.getElementById("raceDistance").innerText = race.distance;
                document.getElementById("raceTime").innerText = race.time;
                document.getElementById("raceRank").innerText = race.rank;

                const runnersList = document.getElementById("topRunners");
                runnersList.innerHTML = "";
                race.runners.forEach(runner => {
                    const li = document.createElement("li");
                    li.innerText = runner;
                    runnersList.appendChild(li);
                });
            }

            raceModal.style.display = "flex";
        }

        closeModal.onclick = () => {
            raceModal.style.display = "none";
        };

        document.getElementById("upcomingTab").onclick = () => {
            document.getElementById("upcomingTab").classList.add("active");
            document.getElementById("historyTab").classList.remove("active");
            loadRaces(upcomingRaces, true);
        };

        document.getElementById("historyTab").onclick = () => {
            document.getElementById("historyTab").classList.add("active");
            document.getElementById("upcomingTab").classList.remove("active");
            loadRaces(historyRaces, false);
        };

        document.addEventListener("DOMContentLoaded", () => loadRaces(upcomingRaces, true));