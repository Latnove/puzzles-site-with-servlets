const reviewsListEl = document.querySelector('.reviews__list');
const sentinelEl = document.getElementById('sentinel');
const spinnerEl = document.querySelector('.spinner');

let page = 0;
const LIMIT = 10;
let observer;
let isLoading;

function loadMorePuzzles(createCardFn) {
    if (isLoading) return;
    isLoading = true;
    spinnerEl.classList.add('active');

    fetch(`${contextPath}/ajax/reviews?puzzleId=${PUZZLE_ID}&page=${page}&limit=${LIMIT}`, {
        method: 'GET',
        headers: { Accept: 'application/json' },
    })
        .then(response => {
            return response.json()
        })
        .then(reviews => {
            if (reviews && Array.isArray(reviews) && reviews.length > 0) {
                const newItemsHTML = reviews.map(createReviewCardHTML).join('')
                reviewsListEl.insertAdjacentHTML('beforeend', newItemsHTML)
                page++
            } else if (page === 0 && document.querySelectorAll(".review-card").length === 0) {
                reviewsListEl.insertAdjacentHTML(
                    'beforeend',
                    `<div class="text">Тут пока что пусто, вы можете быть первым, кто оценит пазл</div>`
                )
            }

            if (!reviews || reviews.length < LIMIT) {
                if (observer) {
                    observer.unobserve(sentinelEl)
                }
                if (sentinelEl) sentinelEl.remove()
                if (spinnerEl) spinnerEl.remove()
            }
        })
        .catch(error => {
            console.error('Произошла ошибка в процессе загрузки пазлов:', error)
        })
}

function createReviewCardHTML(review) {
    return `
        <div class="review-card">
			<div class="review-card__header">
			    <img class="review-card__image" src="${review.user.image}" alt="user avatar" />
				<div class="review-card__meta">
					<h4 class="review-card__username">${escapeHtml(review.user.username)}</h4>
					<div class="review-card__rating-list rating-list">
						${Array.from({ length: 5 }, (_, i) => i + 1).map(item => `<div class="rating-item ${item <= review.rating ? 'active' : ''}" data-rating="${item}">★</div>`).join('')}
					</div>
					<span class="review-card__date">${escapeHtml(review.createdAt)}</span>
			    </div>
			</div>

			<div class="review-card__text">
			    ${escapeHtml(review.reviewText)}
		    </div>
		</div>
    `;
}

if (sentinel) {
    observer = new IntersectionObserver((entries) => {
        if (entries[0].isIntersecting && !isLoading) {
            loadMorePuzzles();
        }
    }, {
        root: null,
        rootMargin: "300px"
    });

    observer.observe(sentinel);
    loadMorePuzzles();
}

const ratingContainerEl = document.querySelector('.add-review__rating-list');
const ratingInputEl = document.querySelector('.add-review__rating-input');

if (ratingContainerEl && ratingInputEl) {
    const starEls = ratingContainerEl.querySelectorAll('.add-review__rating-item')

    starEls.forEach((el) => {
    el.addEventListener('mouseover', () => {
        for (let i = 0; i < Number(el.dataset.rating); i++) {
            starEls[i].style.color = '#ffd700'
        }
    })

    el.addEventListener('mouseout', () => {
        for (let i = 0; i < starEls.length; i++) {
            starEls[i].style.color = 'var(--color-gray-300)'
        }
    })

    el.addEventListener('click', (event)  => {
        event.preventDefault()
        const clickedStar = event.target.closest('.rating');

        if (!clickedStar) {
            return;
        }

        const newRating = clickedStar.dataset.rating;

        ratingInputEl.value = newRating;

        starEls.forEach(star => {
            if (star.dataset.rating <= newRating) {
                star.classList.add('active');
            } else {
                star.classList.remove('active');
            }
        });
    })
})

ratingContainerEl.addEventListener('click', function(event) {
    const clickedStar = event.target.closest('.rating-item');
    if (!clickedStar) {
        return;
    }

    const newRating = clickedStar.dataset.rating;

    ratingInputEl.value = newRating;

    starEls.forEach(star => {
        if (star.dataset.rating <= newRating) {
            star.classList.add('active');
        } else {
            star.classList.remove('active');
        }
    });
});
}
