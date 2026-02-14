function createFirework(x, y) {
	const particleCount = 100
	const colors = [
		'#FFD700',
		'#FF4500',
		'#ADFF2F',
		'#00FFFF',
		'#FF1493',
		'#FFFFFF',
		'#FFA500',
	]

	for (let i = 0; i < particleCount; i++) {
		const particleEl = document.createElement('div')
		// ИСПРАВЛЕНО: Убедитесь, что этот класс ('particle') соответствует вашему CSS
		particleEl.classList.add('particle')

		// Устанавливаем начальную позицию взрыва
		particleEl.style.left = `${x}px`
		particleEl.style.top = `${y}px`

		// Выбираем случайный цвет для частицы
		const color = colors[Math.floor(Math.random() * colors.length)]
		particleEl.style.setProperty('--color', color)

		// Рассчитываем случайную конечную точку для анимации
		const angle = Math.random() * Math.PI * 2
		const distance = Math.random() * 150 + 50
		const destX = Math.cos(angle) * distance
		const destY = Math.sin(angle) * distance

		// Передаем конечные координаты в CSS через переменные
		particleEl.style.setProperty('--x', `${destX}px`)
		particleEl.style.setProperty('--y', `${destY}px`)

		// Добавляем частицу напрямую в body
		document.body.appendChild(particleEl)

		// Планируем удаление частицы после завершения ее анимации
		setTimeout(() => {
			particleEl.remove()
		}, 1200) // Это время должно совпадать с длительностью анимации в CSS
	}
}

function launchRandomFireworks() {
	const particleContainerEl = document.createElement('div')
	particleContainerEl.classList.add('particle-container')
	document.body.appendChild(particleContainerEl)

	const fireworkCount = 20
	const duration = 2000 // Запускать в течение 3 секунд

	for (let i = 0; i < fireworkCount; i++) {
		// Запускаем каждый фейерверк со случайной задержкой
		setTimeout(() => {
			const x = Math.random() * window.innerWidth
			const y = Math.random() * window.innerHeight
			createFirework(x, y)
		}, Math.random() * duration)
	}

	setTimeout(() => {
		particleContainerEl.remove()
	}, 2000)
}
