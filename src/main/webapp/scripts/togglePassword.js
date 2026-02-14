const toggleEls = document.querySelectorAll('.password-container__toggle')

toggleEls.forEach(el =>
	el.addEventListener('click', event => {
		event.preventDefault()
		const toggleOpenEl = el.querySelector('.toggle--open')
		const toggleCloseEl = el.querySelector('.toggle--close')
		const inputEl = el.parentElement.querySelector('input')

		if (toggleOpenEl.classList.contains('active')) {
			toggleOpenEl.classList.remove('active')
			toggleCloseEl.classList.add('active')
			inputEl.type = 'password'
		} else {
			toggleOpenEl.classList.add('active')
			toggleCloseEl.classList.remove('active')
			inputEl.type = 'text'
		}
	})
)
