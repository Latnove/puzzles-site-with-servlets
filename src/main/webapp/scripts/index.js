const img = new Image()
img.src = `${contextPath}/images/dd83dfeb-3115-4f0b-9cdf-a3b8b4d92935.png`

const rows = 4
const cols = 4
const containerEl = document.querySelector('.collect__container')
const puzzleContainerEl = document.querySelector('.collect__puzzle-container')
const allPuzzlesEl = document.querySelector('.collect__all-puzzles')

const isAllPuzzlesEl = event => {
	const allPuzzlesRect = allPuzzlesEl.getBoundingClientRect()

	if (
		allPuzzlesRect.left <= event.clientX &&
		event.clientX <= allPuzzlesRect.left + allPuzzlesEl.clientWidth &&
		allPuzzlesRect.top <= event.clientY &&
		event.clientY <= allPuzzlesRect.top + allPuzzlesEl.clientHeight
	) {
		return true
	}

	return false
}

const handleIsCollect = () => {
	allPuzzlesEl.classList.add('hidden')
	launchRandomFireworks()
}

img.onload = () =>
	loadImage(
		img,
		rows,
		cols,
		containerEl,
		puzzleContainerEl,
		allPuzzlesEl,
		isAllPuzzlesEl,
		handleIsCollect,
		() => {}
	)

const headerEl = document.querySelector('.header')

document.querySelectorAll('.scroll-link').forEach(el =>
	el.addEventListener('click', event => {
		event.preventDefault()
		if (el.dataset.goto && document.querySelector(`.${el.dataset.goto}`)) {
			const gotoBlockEl = document.querySelector(`.${el.dataset.goto}`)
			const gotoBlockValue =
				gotoBlockEl.getBoundingClientRect().top +
				1 +
				pageYOffset -
				headerEl.offsetHeight

			window.scrollTo({
				top: gotoBlockValue,
				behavior: 'smooth',
			})
		}
	})
)

document.querySelectorAll('.faq__item').forEach(el =>
	el.addEventListener('click', () => {
		if (el.classList.contains('active')) {
			el.classList.remove('active')
		} else {
			el.classList.add('active')
		}
	})
)