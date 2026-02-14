let editButtonEls = document.querySelectorAll(".puzzle__status[data-status]")

editButtonEls.forEach(el => {
    el.addEventListener("click", () => {
        if (!el.classList.contains("puzzle__status--edit")) {
            return;
        }

        fetch(`${contextPath}/ajax/update_puzzle_status?status=${el.dataset.status}&puzzleId=${PUZZLE_ID}`, { method: "POST" })
            .then(response => {
                if (!response.ok) {
                    throw new Error(`HTTP error! status: ${response.status}`);
                }

                location.reload()
            })
            .catch((error) => {
                console.log(error)
            });
    });
});