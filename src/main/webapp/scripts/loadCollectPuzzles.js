function createPuzzleCardHTML(puzzle) {
    const ratingStarsHTML = Array.from({ length: 5 }, (_, i) => {
        const starColor = i < puzzle.rating ? '#ffd700' : 'var(--color-gray-300)';
        return `<div style="color: ${starColor}">★</div>`;
    }).join('');
    const formattedDate = new Date(puzzle.createdAt).toLocaleString('ru-RU', {
        day: '2-digit', month: '2-digit', year: 'numeric',
        hour: '2-digit', minute: '2-digit'
    });
    return `
        <div class="profile__card card">
            <a href="${contextPath}/puzzle/${puzzle.id}" class="card__link">
                <img class="card__image" src="${puzzle.imageUrl}" alt="puzzle image" />
                <div class="card__info">
                    <div class="card__title">${escapeHtml(puzzle.title)}</div>
                    <div class="card__meta">
                        <span class="card__meta-item">cols: ${escapeHtml(puzzle.pieceCols)}</span>
                        <span class="card__meta-item">rows: ${escapeHtml(puzzle.pieceRows)}</span>
                        <span class="card__meta-item rating">${ratingStarsHTML}</span>
                        <span class="card__meta-item">${formattedDate}</span>
                    </div>
                </div>
            </a>
        </div>
    `;
}

if (sentinel) {
    observer = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && !isLoading) {
            loadMorePuzzles('collect_puzzles', createPuzzleCardHTML);
        }
    }, {
        root: document.querySelector(".profile__info"),
        rootMargin: "300px"
    });

    observer.observe(sentinel);
    loadMorePuzzles('collect_puzzles', createPuzzleCardHTML);
}