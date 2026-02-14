const listContainer = document.querySelector('.profile__card-list');
const sentinel = document.getElementById('sentinel');
const spinner = document.querySelector('.spinner');

let page = 0;
const LIMIT = 10;
let observer;
let isLoading;

async function fetchPuzzles(url, requestName, page = 0, limit = 10) {
    try {
        const response = await fetch(`${url}?requestName=${requestName}&page=${page}&limit=${limit}`, {
            method: 'GET',
            headers: { 'Accept': 'application/json' }
        });
        if (!response.ok) {
            throw new Error(`Сетевой ответ не был успешным: ${response.status}`);
        }
        return await response.json();
    } catch (error) {
        console.error('Ошибка при выполнении fetch-запроса:', error);
        return null;
    }
}

async function loadMorePuzzles(requestName, createCardFn) {
    if (isLoading) return;
    isLoading = true;
    spinner.classList.add('active');

    const url = `${contextPath}/ajax/user_puzzles`

    try {
        const puzzles = await fetchPuzzles(url, requestName, page, LIMIT);
        if (puzzles && Array.isArray(puzzles) && puzzles.length > 0) {
            const newItemsHTML = puzzles.map(createCardFn).join('');
            listContainer.insertAdjacentHTML('beforeend', newItemsHTML);
            page++;
        } else if (page === 0) {
            listContainer.insertAdjacentHTML('beforeend', `<div class="text puzzles__message">Тут пока что пусто, проявите активность</div>`);
        }

        if (!puzzles || puzzles.length < LIMIT) {
            if (observer) {
                observer.unobserve(sentinel);
            }
            if (sentinel) sentinel.remove();
            if (spinner) spinner.remove();
        }
    } catch (error) {
        console.error("Произошла ошибка в процессе загрузки пазлов:", error);
    } finally {
        isLoading = false;
        spinner.classList.remove("active");
    }
}
