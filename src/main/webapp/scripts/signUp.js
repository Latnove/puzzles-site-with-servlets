const formEl = document.querySelector('.auth__form')
const loginEl = document.getElementById('login')
const errorEl = document.querySelector('.auth__error')
const emailEl = document.getElementById('email')
const passwordEl = document.getElementById('password')
const passwordRepeatEl = document.getElementById('password-repeat')
const agreeEl = document.getElementById('agree')

let isError = true

const errorMessages = {
	login: '',
	email: '',
	password: '',
	passwordRepeat: '',
	agree: '',
}

async function fetchData(url) {
	try {
		const response = await fetch(url, {
			method: 'GET',
			headers: {
				'Accept': 'text/plain'
			}
		});

		if (!response.ok) {
			throw new Error(`Ошибка HTTP: ${response.status}`);
		}

		return await response.text();

	} catch (error) {
		console.error('Ошибка запроса:', error);
	}
}

function debounce(callback) {
	let timer
	return function () {
		clearTimeout(timer)
		timer = setTimeout(() => {
			callback()
		}, 300)
	}
}

const showErrorMessage = () => {
	for (let key in errorMessages) {
		if (errorMessages[key].length > 0) {
			errorEl.classList.add('active')
			errorEl.textContent = errorMessages[key]
			isError = true
			return
		}
	}
	isError = false
	errorEl.classList.remove('active')
}

const handleInputLogin =  debounce(async () => {
	if (!validateLogin(loginEl.value)) {
		loginEl.style.borderColor = '#ef4444'
		errorMessages.login =
			'Введенный login некорректный: он должен быть длиной более 4 и содержать только буквы, цифры и символ _'
	} else {
		errorMessages.login = ''
		loginEl.style.borderColor = '#10b981'

		const resp = await fetchData(`${contextPath}/ajax/sign_up?login=${loginEl.value}`)
		if (resp !== "") {
			loginEl.style.borderColor = '#ef4444'
			errorMessages.login = resp;
		}
	}

	showErrorMessage()
})

const handleInputEmail = debounce(async () => {
	if (!validateEmail(emailEl.value)) {
		emailEl.style.borderColor = '#ef4444'
		errorMessages.email = 'Введите корректную почту'
	} else {
		errorMessages.email = ''
		emailEl.style.borderColor = '#10b981'

		const resp = await fetchData(`${contextPath}/ajax/sign_up?email=${encodeURIComponent(emailEl.value)}`)
		if (resp !== "") {
			emailEl.style.borderColor = '#ef4444'
			errorMessages.email = resp;
		}
	}

	showErrorMessage()
})

const handleInputPassword = debounce(() => {
	if (!validatePassword(passwordEl.value)) {
		passwordEl.style.borderColor = '#ef4444'
		errorMessages.password =
			'Введенный пароль слишком простой: он должен быть не менее 8 символов, иметь заглавную и прописную буквы, содержать какой-то символ'
	} else {
		errorMessages.password = ''
		passwordEl.style.borderColor = '#10b981'
	}
	showErrorMessage()
})

const handleInputPasswordRepeat = debounce(() => {
	if (passwordEl.value !== passwordRepeatEl.value) {
		passwordRepeatEl.style.borderColor = '#ef4444'
		errorMessages.passwordRepeat = 'Введенные вами пароли должны совпадать'
	} else {
		errorMessages.passwordRepeat = ''
		passwordRepeatEl.style.borderColor = '#10b981'
	}
	showErrorMessage()
})

loginEl.addEventListener('input', handleInputLogin)
emailEl.addEventListener('input', handleInputEmail)
passwordEl.addEventListener('input', handleInputPassword)
passwordRepeatEl.addEventListener('input', handleInputPasswordRepeat)
agreeEl.addEventListener('change', () => {
	if (agreeEl.checked) {
		errorMessages.agree = ''
	} else {
		errorMessages.agree = 'Для регистрации нужно согласиться с условиями'
	}
	showErrorMessage()
})

formEl.querySelector('.form__button').addEventListener('click', event => {
	if (!agreeEl.checked) {
		event.preventDefault()
		errorMessages.agree = 'Для регистрации нужно согласиться с условиями'
		showErrorMessage()
		return
	}
	if (isError) {
		event.preventDefault()
		showErrorMessage()
		return
	}
})