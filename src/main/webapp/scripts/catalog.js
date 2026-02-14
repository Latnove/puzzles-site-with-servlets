const doubleRangeEl = document.querySelector('.double-range')
const doubleRangeLine = doubleRangeEl.querySelector('.double-range__line')
const doubleRangeIntervalLineEL = doubleRangeEl.querySelector(
	'.double-range__interval-line'
)
const leftInputEl = doubleRangeEl.querySelector('.double-range__price--start')
const rightInputEl = doubleRangeEl.querySelector('.double-range__price--end')
const leftSliderEl = doubleRangeEl.querySelector('.double-range__slider--left')
const rightSliderEl = doubleRangeEl.querySelector(
	'.double-range__slider--right'
)
const MIN_PIECE_VALUE = 4
const MAX_PIECE_VALUE = 400
const LIMIT = 6;
let minPieceValue = MIN_PIECE_VALUE
let maxPieceValue = MAX_PIECE_VALUE
let typingTimer
let page = 0
let isLoading = false

leftInputEl.addEventListener('input', e => {
	clearTimeout(typingTimer)
	typingTimer = setTimeout(() => {
		const currentValue = parseFloat(e.target.value)

		if (isNaN(currentValue)) {
			e.target.value = minPieceValue
		}

		if (currentValue < minPieceValue) {
			e.target.value = minPieceValue
		}

		if (currentValue > parseFloat(rightInputEl.value)) {
			e.target.value = parseFloat(rightInputEl.value)
		}

		const rightEdge =
			rightSliderEl.getBoundingClientRect().left -
			doubleRangeLine.getBoundingClientRect().left -
			rightSliderEl.offsetWidth +
			1

		let newleft =
			doubleRangeLine.offsetWidth *
			((parseFloat(e.target.value) - minPieceValue) /
				(maxPieceValue - minPieceValue))

		if (newleft > rightEdge) {
			newleft = rightEdge
		}

		leftSliderEl.style.left = newleft + 'px'
		resizeIntervalLine()
	}, 1000)
})

rightInputEl.addEventListener('input', e => {
	clearTimeout(typingTimer)
	typingTimer = setTimeout(() => {
		const currentValue = parseFloat(e.target.value)

		if (isNaN(currentValue)) {
			e.target.value = maxPieceValue
		}

		if (currentValue < parseFloat(leftInputEl.value)) {
			e.target.value = parseFloat(leftInputEl.value)
		}

		if (currentValue > maxPieceValue) {
			e.target.value = maxPieceValue
		}

		const leftEdge =
			leftSliderEl.getBoundingClientRect().left -
			doubleRangeLine.getBoundingClientRect().left +
			leftSliderEl.offsetWidth -
			1

		let newleft =
			doubleRangeLine.offsetWidth *
				((parseFloat(e.target.value) - minPieceValue) /
					(maxPieceValue - minPieceValue)) -
			rightSliderEl.offsetWidth

		if (newleft < leftEdge) {
			newleft = leftEdge
		}

		rightSliderEl.style.left = newleft + 'px'
		resizeIntervalLine()
	}, 1000)
})

leftSliderEl.addEventListener('mousedown', e => {
	let shiftX = e.clientX - leftSliderEl.getBoundingClientRect().left

	document.addEventListener('mousemove', onMouseMove)
	document.addEventListener('mouseup', onMouseUp)

	function onMouseMove(event) {
		const leftEdge = -1

		const rightEdge =
			rightSliderEl.getBoundingClientRect().left -
			doubleRangeLine.getBoundingClientRect().left -
			rightSliderEl.offsetWidth +
			1

		handleDragDoubleRangeSlider(
			leftSliderEl,
			event,
			shiftX,
			leftEdge,
			rightEdge,
			leftInputEl
		)
	}

	function onMouseUp(event) {
		event.preventDefault()
		document.removeEventListener('mouseup', onMouseUp)
		document.removeEventListener('mousemove', onMouseMove)
	}
})

rightSliderEl.addEventListener('mousedown', e => {
	let shiftX = e.clientX - rightSliderEl.getBoundingClientRect().left

	document.addEventListener('mousemove', onMouseMove)
	document.addEventListener('mouseup', onMouseUp)

	function onMouseMove(event) {
		const leftEdge =
			leftSliderEl.getBoundingClientRect().left -
			doubleRangeLine.getBoundingClientRect().left +
			leftSliderEl.offsetWidth -
			1

		const rightEdge = doubleRangeEl.offsetWidth - rightSliderEl.offsetWidth

		handleDragDoubleRangeSlider(
			rightSliderEl,
			event,
			shiftX,
			leftEdge,
			rightEdge,
			rightInputEl
		)
	}

	function onMouseUp(event) {
		event.preventDefault()
		document.removeEventListener('mouseup', onMouseUp)
		document.removeEventListener('mousemove', onMouseMove)
	}
})

const resizeIntervalLine = () => {
	const width =
		rightSliderEl.getBoundingClientRect().left -
		leftSliderEl.getBoundingClientRect().left +
		rightSliderEl.offsetWidth

	doubleRangeIntervalLineEL.style.width = width + 'px'
	doubleRangeIntervalLineEL.style.left =
		window.getComputedStyle(leftSliderEl).left
}

const handleDragDoubleRangeSlider = (
	element,
	event,
	shiftX,
	leftEdge,
	rightEdge,
	inputEl
) => {
	let newLeft =
		event.clientX - shiftX - doubleRangeEl.getBoundingClientRect().left
	let value = null

	if (newLeft < leftEdge) {
		newLeft = leftEdge
		if (newLeft <= -1) value = minPieceValue
		else value = leftInputEl.value
	}

	if (newLeft > rightEdge) {
		newLeft = rightEdge
		if (newLeft >= doubleRangeEl.offsetWidth - rightSliderEl.offsetWidth)
			value = maxPieceValue
		else value = rightInputEl.value
	}

	element.style.left = newLeft + 'px'

	if (value === null)
		value =
			minPieceValue +
			Math.ceil(
				((maxPieceValue - minPieceValue) * Math.ceil(newLeft)) /
					doubleRangeEl.offsetWidth
			)

	inputEl.value = value

	resizeIntervalLine()
}

leftSliderEl.addEventListener('click', e => {
	e.preventDefault()
})
rightSliderEl.addEventListener('click', e => {
	e.preventDefault()
})

const filterRatingEls = document.querySelectorAll('.filter__rating-item')
filterRatingEls.forEach(el => {
	el.addEventListener('mouseover', () => {
		for (let i = 0; i < Number(el.dataset.rating); i++) {
			filterRatingEls[i].style.color = '#ffd700'
		}
	})

	el.addEventListener('mouseout', () => {
		for (let i = 0; i < filterRatingEls.length; i++) {
			filterRatingEls[i].style.color = 'var(--color-gray-300)'
		}
	})

	el.addEventListener('click', event => {
		event.preventDefault()

		for (let i = 0; i < Number(el.dataset.rating); i++) {
			filterRatingEls[i].classList.add('active')
			filterRatingEls[i].querySelector('.rating').checked = true
		}

		for (let i = Number(el.dataset.rating); i < filterRatingEls.length; i++) {
			filterRatingEls[i].classList.remove('active')
			filterRatingEls[i].querySelector('.rating').checked = false
		}
	})
})

const filterEl = document.querySelector('.filter')
const catalogWrapperEl = document.querySelector(".catalog__card-list")
const moreButtonWrapperEl = document.querySelector(".catalog__more-button-wrapper")
const moreButtonEl = moreButtonWrapperEl.querySelector(".catalog__more-button")

const createPuzzleCardHTML = (puzzle) => {
	const ratingStarsHTML = Array.from({ length: 5 }, (_, i) => {
		const starColor = i <= puzzle.rating - 1 ? '#ffd700' : 'var(--color-gray-300)';
		return `<span style="color: ${starColor}">★</span>`;
	}).join('');
	return `
			<a class="catalog__item card" href="${contextPath}/puzzle/${puzzle.id}">
			<div class="card__image-wrapper">
				<img
					class="card__image"
					src="${puzzle.imageUrl}"
					alt="card image"
					/>
					<h4 class="card__image-title">${escapeHtml(puzzle.title)}</h4>
				</div>
				<div class="card__info">
				<h3 class="card__title">${escapeHtml(puzzle.title)}</h3>
				<div class="card__info-wrapper">
					<div class="card__details">
						Детали: <span class="card__value">${escapeHtml(puzzle.pieceCols * puzzle.pieceRows)}</span>шт
					</div>
					<div class="card__count">
					Собрано: <span class="card__value">${escapeHtml(puzzle.completionsCount)}</span> раз
					</div>
						<div class="card__rating">
							<div class="card__rating-list">
								${ratingStarsHTML}
								<span class="card__value">${escapeHtml(puzzle.rating)}</span>
							</div>
						</div>
					</div>
				</div>
			</a>
		</div>
    `;
}

const loadCards = () => {
	const pieceStart = filterEl.querySelector('.double-range__price--start').value
	const pieceEnd = filterEl.querySelector('.double-range__price--end').value

	const ratingEls = filterEl.querySelectorAll('input.rating:checked')
	const categoriesEls = filterEl.querySelectorAll('.filter__checkbox input:checked')

	const minRating = ratingEls.length

	const categories =
		categoriesEls.length === 0
			? Array.from(filterEl.querySelectorAll('.filter__checkbox input')).map(
					el => el.value
			  )
			: Array.from(categoriesEls).map(el => el.value)

	const params = new URLSearchParams();
	categories.forEach(el => {
		params.append('categories', el)
	});

	if (isLoading) {
		return
	}

	isLoading = true
	// AJAX запрос
	fetch(`${contextPath}/catalog?pieceStart=${pieceStart}&pieceEnd=${pieceEnd}&minRating=${minRating}&${params.toString()}&limit=${LIMIT}&offset=${page}`, {method: "GET"}).then(response => {
		if (response.ok) {
			return response.json()
		}
	}).then(data => {
		if (data && Array.isArray(data) && data.length > 0) {
			const newItemsHTML = data.map(createPuzzleCardHTML).join('');
			catalogWrapperEl.insertAdjacentHTML('beforeend', newItemsHTML);
			page++;
		} else if (page === 0) {
			catalogWrapperEl.insertAdjacentHTML('beforeend', `<div class="text catalog__message">Пазлов с такими параметрами пока не найдено :(</div>`);
		}

		if (data.length < LIMIT) {
			moreButtonWrapperEl.classList.remove("active")
		} else {
			moreButtonWrapperEl.classList.add("active")
		}

		isLoading = false
	})
}

filterEl
	.querySelector('.filter__button--primary')
	.addEventListener('click', event => {
		event.preventDefault()
		catalogWrapperEl.querySelectorAll(".card, .catalog__message").forEach(el => el.remove())
		page = 0
		loadCards()
	})

filterEl
	.querySelector('.filter__button--secondary')
	.addEventListener('click', event => {
		event.preventDefault()

		filterEl.querySelector('.double-range__price--start').value =
			MIN_PIECE_VALUE

		filterEl.querySelector('.double-range__price--end').value = MAX_PIECE_VALUE

		filterEl.querySelectorAll('input.rating').forEach(el => {
			el.checked = false
			el.parentElement.classList.remove('active')
		})

		filterEl.querySelectorAll('.filter__checkbox input').forEach(el => {
			el.checked = false
		})

		leftSliderEl.style.left = -1 + 'px'

		rightSliderEl.style.left =
			doubleRangeLine.offsetWidth - rightSliderEl.offsetWidth + 1 + 'px'
		page = 0

		resizeIntervalLine()

		catalogWrapperEl.querySelectorAll(".card, .catalog__message").forEach(el => el.remove())
		loadCards()
	})

moreButtonEl.addEventListener("click", () => {
	loadCards()
})

document.addEventListener("DOMContentLoaded", () => {
	loadCards()
})
