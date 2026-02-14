const listContainerEl = document.querySelector('.panel__card-list');
let sentinelEl = document.getElementById('sentinel');
let spinnerEl = document.querySelector('.spinner');

let page = 0;
const LIMIT = 10;

let observer;
let isLoading;
let statuses = ["pending", "active", "rejected"]

async function loadMorePuzzles(statuses, createCardFn) {
    if (isLoading) return;
    isLoading = true;
    spinnerEl.classList.add('active');
    fetch(`${contextPath}/ajax/puzzles_status?status=${statuses.join('&status=')}&page=${page}&limit=${LIMIT}`, {
        method: 'GET',
        headers: { 'Accept': 'application/json' }
    }).then(response => {
        return response.json()
    }).then(puzzles => {
        if (puzzles && Array.isArray(puzzles) && puzzles.length > 0) {
            const newItemsHTML = puzzles.map(createCardFn).join('');
            listContainerEl.insertAdjacentHTML('beforeend', newItemsHTML);
            page++;
        } else if (page === 0) {
            listContainerEl.insertAdjacentHTML('beforeend', `<div class="text panel__message">Тут пока что пусто</div>`);
        }

        if (!puzzles || puzzles.length < LIMIT) {
            if (observer) {
                observer.unobserve(sentinelEl);
            }
            if (sentinelEl) sentinelEl.remove();
            if (spinnerEl) spinnerEl.remove();
        }
    }).catch(error => {
        console.error('Ошибка при выполнении fetch-запроса:', error);
    }).finally(() => {
        isLoading = false;
        spinnerEl.classList.remove("active");
    })
}

function createPuzzleCardHTML(puzzle) {
    const formattedDate = new Date(puzzle.createdAt).toLocaleString('ru-RU', {
        day: '2-digit', month: '2-digit', year: 'numeric',
        hour: '2-digit', minute: '2-digit'
    });

    return `
        <a class="panel__card card" href="${contextPath}/puzzle/${puzzle.id}">
            <div class="card__image">
                <img src="${puzzle.imageUrl}" alt="puzzle image" />
                    <div class="status__item status__item--${puzzle.status} card__status">
                        ${escapeHtml(puzzle.status)}
                    </div>
                </div>
            <div class="card__content">
                <h3 class="card__title">${escapeHtml(puzzle.title)}</h3>
                    <div class="card__meta">
                        <div class="card__meta-item">
                            <svg
                                    width="800px"
                                    height="800px"
                                    viewBox="0 0 16 16"
                                    fill="none"
                                    xmlns="http://www.w3.org/2000/svg"
                            >
                                <path
                                        d="M8 7C9.65685 7 11 5.65685 11 4C11 2.34315 9.65685 1 8 1C6.34315 1 5 2.34315 5 4C5 5.65685 6.34315 7 8 7Z"
                                        fill="#fff"
                                />
                                <path
                                        d="M14 12C14 10.3431 12.6569 9 11 9H5C3.34315 9 2 10.3431 2 12V15H14V12Z"
                                        fill="#fff"
                                />
                            </svg>
                            @${escapeHtml(puzzle.user.username)}
                        </div>

                        <div class="card__meta-item">
                            <svg
                                    width="800px"
                                    height="800px"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    xmlns="http://www.w3.org/2000/svg"
                            >
                                <path
                                        d="M22 11V9C22 4 20 2 15 2H9C4 2 2 4 2 9V15C2 20 4 22 9 22H10"
                                        stroke="#292D32"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                                <path
                                        d="M2.03003 8.5H22"
                                        stroke="#292D32"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                                <path
                                        d="M2.03003 15.5H12"
                                        stroke="#292D32"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                                <path
                                        d="M8.51001 21.99V2.01001"
                                        stroke="#292D32"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                                <path
                                        d="M15.51 11.99V2.01001"
                                        stroke="#292D32"
                                        stroke-width="1.5"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                                <path
                                        d="M18.73 14.6701L14.58 18.82C14.42 18.98 14.27 19.29 14.23 19.51L14 21.1C13.92 21.67 14.32 22.08 14.89 21.99L16.48 21.76C16.7 21.73 17.01 21.5701 17.17 21.4101L21.32 17.26C22.03 16.55 22.37 15.7101 21.32 14.6601C20.28 13.6201 19.45 13.9501 18.73 14.6701Z"
                                        stroke="#292D32"
                                        stroke-width="1.5"
                                        stroke-miterlimit="10"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                                <path
                                        d="M18.14 15.26C18.49 16.52 19.48 17.5 20.74 17.86"
                                        stroke="#292D32"
                                        stroke-width="1.5"
                                        stroke-miterlimit="10"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                            </svg>

                            ${escapeHtml(puzzle.pieceCols)}×${escapeHtml(puzzle.pieceRows)} (${escapeHtml(puzzle.pieceCols * puzzle.pieceRows)} частей)
                        </div>

                        <div class="card__meta-item">
                            <svg
                                    width="800px"
                                    height="800px"
                                    viewBox="0 0 24 24"
                                    fill="none"
                                    xmlns="http://www.w3.org/2000/svg"
                            >
                                <path
                                        d="M3 9H21M7 3V5M17 3V5M6 13H8M6 17H8M11 13H13M11 17H13M16 13H18M16 17H18M6.2 21H17.8C18.9201 21 19.4802 21 19.908 20.782C20.2843 20.5903 20.5903 20.2843 20.782 19.908C21 19.4802 21 18.9201 21 17.8V8.2C21 7.07989 21 6.51984 20.782 6.09202C20.5903 5.71569 20.2843 5.40973 19.908 5.21799C19.4802 5 18.9201 5 17.8 5H6.2C5.0799 5 4.51984 5 4.09202 5.21799C3.71569 5.40973 3.40973 5.71569 3.21799 6.09202C3 6.51984 3 7.07989 3 8.2V17.8C3 18.9201 3 19.4802 3.21799 19.908C3.40973 20.2843 3.71569 20.5903 4.09202 20.782C4.51984 21 5.07989 21 6.2 21Z"
                                        stroke="#000000"
                                        stroke-width="2"
                                        stroke-linecap="round"
                                        stroke-linejoin="round"
                                />
                            </svg>
                            ${formattedDate}
                        </div>
                    </div>

                    <div class="card__categories">
                        ${puzzle.categories.map(el => `<div class="card__category">${escapeHtml(el.nameRu)}</div>`).join("")}
                    </div>
                </div>
                
                <div class="card__action"></div>
            </a>
    `;
}

const initObserver = () => {
    sentinelEl = document.getElementById('sentinel');

    if (!sentinelEl) {
        document.querySelector(".panel__container").insertAdjacentHTML("beforeend", '<div id="sentinel"></div><div class="spinner"></div>')

        sentinelEl = document.getElementById('sentinel');
        spinnerEl = document.querySelector('.spinner');
    }

    listContainerEl.innerHTML = ""
    page = 0

    observer = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && !isLoading) {
                loadMorePuzzles(statuses, createPuzzleCardHTML);
        }

        }, {
            root: null,
            rootMargin: "300px"
        });

    observer.observe(sentinelEl);

    loadMorePuzzles(statuses, createPuzzleCardHTML);
}

window.addEventListener("DOMContentLoaded", () => {
    initObserver()
})

const allButtonEl = document.getElementById("all")
const pendingButtonEl = document.getElementById("pending")
const activeButtonEl = document.getElementById("active")
const rejectedButtonEl = document.getElementById("rejected")
const navButtonEls = document.querySelectorAll(".panel__link")

navButtonEls.forEach(el => el.addEventListener("click", (event) => {
    event.preventDefault()
    navButtonEls.forEach(button => {
        button.classList.remove('active');
    });

    event.target.classList.add("active")
}))

allButtonEl.addEventListener("click", (event) => {
    statuses = ["pending", "active", "rejected"]
    initObserver()
})

pendingButtonEl.addEventListener("click", (event) => {
    statuses = ["pending"]
    initObserver()

})

activeButtonEl.addEventListener("click", (event) => {
    statuses = ["active"]
    initObserver()

})

rejectedButtonEl.addEventListener("click", (event) => {
    statuses = ["rejected"]
    initObserver()

})