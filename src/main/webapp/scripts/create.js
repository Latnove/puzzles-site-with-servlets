const formEl = document.querySelector('.create__modal')
const uploadEl = document.querySelector('.create__upload-area')
const resetEl = document.getElementById('reset-button')
const errorMessageEl = document.querySelector('.create__error')
const successMessageEl = document.querySelector('.create__success')
const imageInputEl = document.getElementById('file-input')
const previewImageEl = document.querySelector('.create__preview-image')
const checkboxEls = formEl.querySelectorAll('input[name="categories"]')

imageInputEl.addEventListener('change', event => {
	const file = event.target.files[0]
	previewImageEl.classList.remove('active')
	previewImageEl.src = ''

	if (file) {
		uploadEl.classList.add('disable')
		const reader = new FileReader()

		reader.onload = function (e) {
			previewImageEl.src = e.target.result
			previewImageEl.classList.add('active')
		}

		reader.readAsDataURL(file)
	}
})

checkboxEls.forEach(el =>
	el.addEventListener('change', event => {
		errorMessageEl.textContent = ''
		errorMessageEl.classList.remove('active')
	})
)

formEl.addEventListener('change', event => {
	if (successMessageEl) {
		successMessageEl.textContent = ''
		successMessageEl.classList.remove('active')
	}
})

formEl.addEventListener('submit', event => {
	const isChecked = Array.from(checkboxEls).some(checkbox => checkbox.checked)

	if (!isChecked) {
		errorMessageEl.textContent = 'Выберите хотя бы одну категорию'
		errorMessageEl.classList.add('active')
		event.preventDefault()
	}
})

resetEl.addEventListener('click', event => {
	if (successMessageEl) {
		successMessageEl.textContent = ''
		successMessageEl.classList.remove('active')
	}

	errorMessageEl.textContent = ''
	uploadEl.classList.remove('disable')
	errorMessageEl.classList.remove('active')
	previewImageEl.classList.remove('active')
	previewImageEl.src = ''
	formEl.reset()
	event.preventDefault()
})

document.querySelectorAll('.stepper-plus').forEach(el =>
	el.addEventListener('click', () => {
		const inputEl = document.getElementById(el.dataset.input)

		const currentValue = Number(inputEl.value)
		const max = Number(inputEl.max)

		if (currentValue < max) {
			inputEl.value = currentValue + 1
		}
	})
)

document.querySelectorAll('.stepper-minus').forEach(el =>
	el.addEventListener('click', () => {
		const inputEl = document.getElementById(el.dataset.input)

		const currentValue = Number(inputEl.value)
		const min = Number(inputEl.min)

		if (currentValue > min) {
			inputEl.value = currentValue - 1
		}
	})
)
