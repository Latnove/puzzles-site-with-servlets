const img = new Image()
img.crossOrigin = "Anonymous";
img.src = IMAGE_URL

const rows = ROWS;
const cols = COLS;
const containerEl = document.querySelector('.container')
const puzzleContainerEl = document.querySelector('.puzzle-container')
const allPuzzlesEl = document.querySelector('.all-puzzles')
const modalEl = document.querySelector('.modal-overlay')
const messageEl = modalEl.querySelector('.modal-message')
const stickerEl = modalEl.querySelector('.modal-sticker')
const modalButtonEl = modalEl.querySelector('.modal-button')

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

const handleClick = (event) => {
    event.preventDefault()
    modalEl.classList.remove("show")
}

const handleIsCollect = () => {
    modalEl.classList.add('show')
    allPuzzlesEl.classList.add('hidden')
    modalButtonEl.removeEventListener('click', handleClick)
    messageEl.textContent = 'Вы успешно собрали пазл!'
    stickerEl.textContent = '🎉✨'
}

const handleIsNotCollect = () => {
    messageEl.textContent = 'Вы неправильно собрали пазл'
    stickerEl.textContent = '❌'
    modalButtonEl.removeEventListener('click', handleClick)
    modalButtonEl.addEventListener("click", handleClick)
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
        handleIsNotCollect
    )