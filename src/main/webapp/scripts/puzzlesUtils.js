function loadImage(
	img,
	rows,
	cols,
	containerEl,
	puzzleContainerEl,
	allPuzzlesEl,
	isAllPuzzlesEl,
	handleIsCollect,
	handleIsNotCollect
) {
	const puzzleSize = 220
	const pieceWidth = img.width / cols
	const pieceHeight = img.height / rows
	const tab = Math.min(pieceWidth, pieceHeight) / 4
	const coefficient = puzzleSize / (pieceWidth + 2 * tab)
	const correctPuzzles = []

	function edgeFor(row, col, rows, cols) {
		return {
			top: row === 0 ? null : (row + col) % 2 === 0,
			right: col === cols - 1 ? null : (row + col + 1) % 2 === 0,
			bottom: row === rows - 1 ? null : (row + col + 1) % 2 === 1,
			left: col === 0 ? null : (row + col) % 2 === 1,
		}
	}

	function createPiecePath(ctx, x, y, width, height, edges) {
		const tab = Math.min(width, height) / 4
		ctx.beginPath()
		ctx.moveTo(x, y)

		if (edges.top === null) {
			ctx.lineTo(x + width, y)
		} else {
			ctx.lineTo(x + width / 3, y)
			const sign = edges.top ? -1 : 1
			ctx.bezierCurveTo(
				x + width / 3 + tab / 2,
				y + sign * tab,
				x + (2 * width) / 3 - tab / 2,
				y + sign * tab,
				x + (2 * width) / 3,
				y
			)
			ctx.lineTo(x + width, y)
		}

		if (edges.right === null) {
			ctx.lineTo(x + width, y + height)
		} else {
			ctx.lineTo(x + width, y + height / 3)
			const sign = edges.right ? 1 : -1
			ctx.bezierCurveTo(
				x + width + sign * tab,
				y + height / 3 + tab / 2,
				x + width + sign * tab,
				y + (2 * height) / 3 - tab / 2,
				x + width,
				y + (2 * height) / 3
			)
			ctx.lineTo(x + width, y + height)
		}

		if (edges.bottom === null) {
			ctx.lineTo(x, y + height)
		} else {
			ctx.lineTo(x + (2 * width) / 3, y + height)
			const sign = edges.bottom ? 1 : -1
			ctx.bezierCurveTo(
				x + (2 * width) / 3 - tab / 2,
				y + height + sign * tab,
				x + width / 3 + tab / 2,
				y + height + sign * tab,
				x + width / 3,
				y + height
			)
			ctx.lineTo(x, y + height)
		}

		if (edges.left === null) {
			ctx.lineTo(x, y)
		} else {
			ctx.lineTo(x, y + (2 * height) / 3)
			const sign = edges.left ? 1 : -1
			ctx.bezierCurveTo(
				x - sign * tab,
				y + (2 * height) / 3 - tab / 2,
				x - sign * tab,
				y + height / 3 + tab / 2,
				x,
				y + height / 3
			)
			ctx.lineTo(x, y)
		}
		ctx.closePath()
	}

	for (let row = 0; row < rows; row++) {
		for (let col = 0; col < cols; col++) {
			const pieceCanvas = document.createElement('canvas')

			const canvasWidth = pieceWidth + tab * 2
			const canvasHeight = pieceHeight + tab * 2

			pieceCanvas.width = canvasWidth
			pieceCanvas.height = canvasHeight

			const ctxPiece = pieceCanvas.getContext('2d')
			const edges = edgeFor(row, col, rows, cols)

			createPiecePath(ctxPiece, tab, tab, pieceWidth, pieceHeight, edges)

			ctxPiece.strokeStyle = 'rgba(0,0,0,0.8)'
			ctxPiece.stroke()
			ctxPiece.clip()

			let sx = col * pieceWidth - tab
			let sy = row * pieceHeight - tab
			let sWidth = pieceWidth + 2 * tab
			let sHeight = pieceHeight + 2 * tab
			let dx = 0,
				dy = 0

			if (sx < 0) {
				dx = -sx
				sWidth += sx
				sx = 0
			}
			if (sy < 0) {
				dy = -sy
				sHeight += sy
				sy = 0
			}
			if (sx + sWidth > img.width) sWidth = img.width - sx
			if (sy + sHeight > img.height) sHeight = img.height - sy

			ctxPiece.drawImage(img, sx, sy, sWidth, sHeight, dx, dy, sWidth, sHeight)

			const gridEl = document.createElement('div')
			const puzzleBorderEl = document.createElement('div')
			gridEl.classList.add('puzzle')
			puzzleBorderEl.classList.add('puzzle-border')
			pieceCanvas.classList.add('puzzle-item')
			puzzleContainerEl.appendChild(gridEl)
			gridEl.appendChild(puzzleBorderEl)
			allPuzzlesEl.appendChild(pieceCanvas)

			puzzleContainerEl.style.display = 'grid'
			puzzleContainerEl.style.gridTemplateColumns = `repeat(${cols}, ${
				pieceWidth * coefficient
			}px)`
			puzzleContainerEl.style.gridTemplateRows = `repeat(${rows}, ${
				pieceHeight * coefficient
			}px)`

			allPuzzlesEl.style.width = puzzleContainerEl.clientWidth + 'px'
			const maxLeft =
				allPuzzlesEl.clientWidth - pieceCanvas.offsetWidth * coefficient
			const maxTop =
				allPuzzlesEl.clientHeight - pieceCanvas.offsetHeight * coefficient
			const left = Math.random() * Math.max(0, maxLeft)
			const top = Math.random() * Math.max(0, maxTop)
			pieceCanvas.style.left = `${left}px`
			pieceCanvas.style.top = `${top}px`
			pieceCanvas.style.zIndex = Math.round(Math.random() * 3)
			pieceCanvas.style.width = `${puzzleSize}px`

			pieceCanvas.dataset.top = edges.top
			pieceCanvas.dataset.right = edges.right
			pieceCanvas.dataset.bottom = edges.bottom
			pieceCanvas.dataset.left = edges.left
			gridEl.dataset.row = row
			gridEl.dataset.col = col

			correctPuzzles.push(pieceCanvas)
		}
	}

	const getAlphaParams = (el, event, ctx) => {
		const rect = el.getBoundingClientRect()

		const scaleX = el.width / rect.width
		const scaleY = el.height / rect.height

		const x = Math.floor((event.clientX - rect.left) * scaleX)
		const y = Math.floor((event.clientY - rect.top) * scaleY)

		return ctx.getImageData(x, y, 1, 1).data[3]
	}

	const isCorrectPosition = (puzzleEl, canvasEl) => {
		const row = Number(puzzleEl.dataset.row)
		const col = Number(puzzleEl.dataset.col)

		const topPuzzleEl = puzzleContainerEl.querySelector(
			`.puzzle[data-row="${row - 1}"][data-col="${col}"]`
		)

		const bottomPuzzleEl = puzzleContainerEl.querySelector(
			`.puzzle[data-row="${row + 1}"][data-col="${col}"]`
		)

		const leftPuzzleEl = puzzleContainerEl.querySelector(
			`.puzzle[data-row="${row}"][data-col="${col - 1}"]`
		)

		const rightPuzzleEl = puzzleContainerEl.querySelector(
			`.puzzle[data-row="${row}"][data-col="${col + 1}"]`
		)

		const data = [
			{ pos: 'top', checkForm: 'bottom', el: topPuzzleEl, forms: [] },
			{ pos: 'bottom', checkForm: 'top', el: bottomPuzzleEl, forms: [] },
			{ pos: 'left', checkForm: 'right', el: leftPuzzleEl, forms: [] },
			{ pos: 'right', checkForm: 'left', el: rightPuzzleEl, forms: [] },
		]

		for (let i = 0; i < data.length; i++) {
			if (data[i].el === null) {
				data[i].forms = ['null', 'false']
			} else {
				const childCanvasEl = data[i].el.querySelector('.puzzle-item')

				if (childCanvasEl === null) {
					data[i].forms = ['null', 'false', 'true']
				} else {
					switch (childCanvasEl.dataset[data[i].checkForm]) {
						case 'false':
							data[i].forms = ['null', 'false', 'true']
							break
						case 'true':
							data[i].forms = ['false']
							break
						case 'null':
							data[i].forms = ['null', 'false']
							break
					}
				}
			}

			if (!data[i].forms.includes(canvasEl.dataset[data[i].pos])) {
				return false
			}
		}

		return true
	}

	const getCurrentPuzzle = event => {
		let puzzleEl = null
		containerEl.querySelectorAll('.puzzle').forEach(el => {
			const puzzleRect = el.getBoundingClientRect()
			if (
				puzzleRect.left <= event.clientX &&
				event.clientX <= puzzleRect.left + el.clientWidth &&
				puzzleRect.top <= event.clientY &&
				event.clientY <= puzzleRect.top + el.clientHeight
			) {
				puzzleEl = el
			}
		})

		return puzzleEl
	}

	const isCollectPuzzles = () => {
		const puzzleItems = containerEl.querySelectorAll('.puzzle-item')
		for (let i = 0; i < puzzleItems.length; i++) {
			if (correctPuzzles[i] !== puzzleItems[i]) {
				allPuzzlesEl.appendChild(puzzleItems[i])
				puzzleItems[i].style.left = tab * coefficient + 'px'
				puzzleItems[i].style.top = tab * coefficient + 'px'
				return false
			}
		}

		return true
	}

	document.querySelectorAll('.puzzle-item').forEach(el => {
		const ctx = el.getContext('2d', { willReadFrequently: true })

		el.addEventListener('mousemove', event => {
			if (getAlphaParams(el, event, ctx) > 0) {
				el.style.cursor = 'grab'
			} else {
				el.style.cursor = 'initial'
			}
		})

		el.addEventListener('mousedown', event => {
			const lastParentEl = el.parentElement
			const lastLeftPos = el.offsetLeft
			const lastTopPos = el.offsetTop

			if (getAlphaParams(el, event, ctx) > 0) {
				const shiftX = event.clientX - el.getBoundingClientRect().left
				const shiftY = event.clientY - el.getBoundingClientRect().top
				containerEl.appendChild(el)

				mouseMove(event)

				function mouseMove(event) {
					const puzzleEl = getCurrentPuzzle(event)
					let puzzleBorderEl = null

					el.style.left =
						event.clientX -
						containerEl.getBoundingClientRect().left -
						shiftX +
						'px'
					el.style.top =
						event.clientY -
						containerEl.getBoundingClientRect().top -
						shiftY +
						'px'
					el.style.zIndex = 6

					if (puzzleEl === null) return

					const swappedPuzzleEl = puzzleEl.querySelector('.puzzle-item')

					if (swappedPuzzleEl === null && isCorrectPosition(puzzleEl, el)) {
						puzzleBorderEl = puzzleEl.querySelector('.puzzle-border')
						puzzleBorderEl.classList.add('puzzle-border--active')
					} else {
						if (
							isCorrectPosition(puzzleEl, el) &&
							(lastParentEl === allPuzzlesEl ||
								isCorrectPosition(lastParentEl, swappedPuzzleEl))
						) {
							puzzleBorderEl = puzzleEl.querySelector('.puzzle-border')
							puzzleBorderEl.classList.add('puzzle-border--active')
						}
					}

					puzzleContainerEl
						.querySelectorAll('.puzzle-border--active')
						.forEach(el => {
							if (el !== puzzleBorderEl) {
								el.classList.remove('puzzle-border--active')
							}
						})
				}

				document.addEventListener('mousemove', mouseMove)
				el.addEventListener('mouseup', mouseUp)

				function mouseUp(event) {
					document.removeEventListener('mousemove', mouseMove)
					el.removeEventListener('mouseup', mouseUp)

					let left = lastLeftPos
					let top = lastTopPos
					let parrent = lastParentEl

					puzzleContainerEl
						.querySelectorAll('.puzzle-border--active')
						.forEach(el => {
							el.classList.remove('puzzle-border--active')
						})

					const puzzleEl = getCurrentPuzzle(event)
					el.style.zIndex = 3

					if (isAllPuzzlesEl(event)) {
						const allPuzzlesRect = allPuzzlesEl.getBoundingClientRect()

						allPuzzlesEl.appendChild(el)

						left = event.clientX - allPuzzlesRect.left - shiftX
						top = event.clientY - allPuzzlesRect.top - shiftY
						el.style.left = `${left}px`
						el.style.top = `${top}px`
						return
					}
					if (puzzleEl === null) {
						lastParentEl.appendChild(el)
						el.style.left = `${left}px`
						el.style.top = `${top}px`
						return
					}

					const swappedPuzzleEl = puzzleEl.querySelector('.puzzle-item')

					if (swappedPuzzleEl) {
						if (
							isCorrectPosition(puzzleEl, el) &&
							(lastParentEl === allPuzzlesEl ||
								isCorrectPosition(lastParentEl, swappedPuzzleEl))
						) {
							lastParentEl.appendChild(swappedPuzzleEl)
							swappedPuzzleEl.style.left = `${lastLeftPos}px`
							swappedPuzzleEl.style.top = `${lastTopPos}px`

							parrent = puzzleEl
							left = -tab * coefficient
							top = -tab * coefficient
						}
					} else {
						if (isCorrectPosition(puzzleEl, el)) {
							parrent = puzzleEl
							left = -tab * coefficient
							top = -tab * coefficient
						}
					}

					parrent.appendChild(el)
					el.style.left = `${left}px`
					el.style.top = `${top}px`

					if (allPuzzlesEl.querySelectorAll('.puzzle-item').length === 0) {
						if (isCollectPuzzles()) {
							handleIsCollect()
						} else {
							handleIsNotCollect()
						}
					}
				}
			}
		})
	})
}
