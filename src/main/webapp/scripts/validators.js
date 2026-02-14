const validateLogin = str => {
	const regex = /^[a-zA-Z][a-zA-Z0-9_]{4,}$/

	return regex.test(str)
}

const validatePassword = str => {
	const regex = /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).{8,}$/;	return regex.test(str)
}

const validateEmail = str => {
	const regex = /^[\w.-]+@[\w-]+\.[a-z]{2,6}$/i
	return regex.test(str)
}
