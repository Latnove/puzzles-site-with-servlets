function createPuzzleCardHTML(puzzle) {
    const statusMap = {
        active:   { text: 'Active',   className: 'status-active' },
        pending:  { text: 'Pending',  className: 'status-pending' },
        rejected: { text: 'Rejected', className: 'status-rejected' }
    };

    const normalizedStatus = puzzle.status ? puzzle.status.toLowerCase() : 'pending';
    const statusInfo = statusMap[normalizedStatus] || statusMap.pending;

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
                        <span class="card__meta-item status ${statusInfo.className}">
                            <svg width="800px" height="800px" viewBox="0 0 12 12" xmlns="http://www.w3.org/2000/svg">
                                <path fill-rule="evenodd" clip-rule="evenodd" d="M6 12A6 6 0 106 0a6 6 0 000 12zM3 4.75A.75.75 0 013.75 4h4.5a.75.75 0 010 1.5h-4.5A.75.75 0 013 4.75zm0 2.5a.75.75 0 01.75-.75h3.5a.75.75 0 010 1.5h-3.5A.75.75 0 013 7.25z" fill="#000000"/>
                            </svg>
                            ${escapeHtml(statusInfo.text)}
                        </span>
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
            loadMorePuzzles('created_puzzles', createPuzzleCardHTML);
        }
    }, {
        root: document.querySelector(".profile__info"),
        rootMargin: "300px"
    });

    observer.observe(sentinel);
    loadMorePuzzles('created_puzzles', createPuzzleCardHTML);
}