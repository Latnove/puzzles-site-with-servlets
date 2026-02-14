function debounce(callback, delay = 300) {
    let timer;
    return function (...args) {
        clearTimeout(timer);
        timer = setTimeout(() => {
            callback.apply(this, args);
        }, delay);
    };
}


const passwordButtonEl = document.getElementById("password-button");
const passwordModal = document.getElementById("password-modal");

if (passwordButtonEl && passwordModal) {
    const passwordForm = passwordModal.querySelector("form");
    const closePasswordEls = passwordModal.querySelectorAll(".modal__close, .button--secondary");
    const passwordModalError = passwordModal.querySelector(".modal__error");
    const passwordEl = document.getElementById("password");
    const passwordRepeatEl = document.getElementById("repeat-password");
    const passwordCurrentEl = document.getElementById("current-password");

    const passwordErrorMessages = {
        password: '',
        passwordRepeat: '',
        passwordCurrent: ''
    };

    const displayPasswordError = () => {
        const firstError = Object.values(passwordErrorMessages).find(msg => msg);
        if (firstError) {
            passwordModalError.textContent = firstError;
            passwordModalError.classList.add('active');
        } else {
            passwordModalError.classList.remove('active');
        }
    };

    const resetPasswordForm = () => {
        passwordForm.reset();
        for (const key in passwordErrorMessages) passwordErrorMessages[key] = '';
        [passwordEl, passwordRepeatEl, passwordCurrentEl].forEach(input => {
            input.style.borderColor = 'var(--color-gray-200)';
        });
        toggleEls.forEach((el) => {el.querySelector('.toggle--close').classList.add("active")
            el.querySelector('.toggle--open').classList.remove("active")})
        displayPasswordError();
    };


    const validateNewPassword = () => {
        if (!validatePassword(passwordEl.value)) {
            passwordErrorMessages.password = 'Введенный пароль слишком простой: он должен быть не менее 8 символов, иметь заглавную и прописную буквы, содержать какой-то символ';
            passwordEl.style.borderColor = '#ef4444';
        } else {
            passwordErrorMessages.password = '';
            passwordEl.style.borderColor = '#10b981';
        }
        validateRepeatPassword();
        displayPasswordError();
    };

    const validateRepeatPassword = () => {
        if (passwordEl.value !== passwordRepeatEl.value) {
            passwordErrorMessages.passwordRepeat = 'Введенные вами пароли должны совпадать';
            passwordRepeatEl.style.borderColor = '#ef4444';
        } else {
            passwordErrorMessages.passwordRepeat = '';
            passwordRepeatEl.style.borderColor = '#10b981';
        }
        displayPasswordError();
    };

    const validateCurrentPassword = () => {
        if (passwordCurrentEl.value.trim() === '') {
            passwordErrorMessages.passwordCurrent = 'Текущий пароль не может быть пустым';
            passwordCurrentEl.style.borderColor = '#ef4444';
        } else {
            passwordErrorMessages.passwordCurrent = '';
            passwordCurrentEl.style.borderColor = '#10b981';
        }
        displayPasswordError();
    };

    passwordButtonEl.addEventListener("click", () => passwordModal.classList.add("active"));
    closePasswordEls.forEach(el => el.addEventListener("click", () => {
        passwordModal.classList.remove("active");
        resetPasswordForm();
    }));

    passwordEl.addEventListener("input", debounce(validateNewPassword));
    passwordRepeatEl.addEventListener("input", debounce(validateRepeatPassword));
    passwordCurrentEl.addEventListener("input", debounce(validateCurrentPassword));

    passwordForm.addEventListener("submit", (event) => {
        validateNewPassword();
        validateRepeatPassword();
        validateCurrentPassword();
        if (Object.values(passwordErrorMessages).some(msg => msg)) {
            event.preventDefault();
            displayPasswordError();
        }
    });
}


// --- ЛОГИКА ДЛЯ МОДАЛЬНОГО ОКНА СМЕНЫ АВАТАРА ---

const imageButtonEl = document.getElementById("image-button");
const imageModal = document.getElementById("image-modal");

if (imageButtonEl && imageModal) {
    const imageForm = imageModal.querySelector("form");
    const closeImageEls = imageModal.querySelectorAll(".modal__close, .button--secondary");
    const imageModalError = imageModal.querySelector(".modal__error");
    const imageEl = document.getElementById("image");
    const imageErrorMessage = { image: '' };

    const displayImageError = () => {
        if (imageErrorMessage.image) {
            imageModalError.textContent = imageErrorMessage.image;
            imageModalError.classList.add('active');
        } else {
            imageModalError.classList.remove('active');
        }
    };

    const resetImageForm = () => {
        imageForm.reset();
        imageErrorMessage.image = '';
        displayImageError();
    };

    imageButtonEl.addEventListener("click", () => imageModal.classList.add("active"));
    document.querySelector('.profile__image-upload').addEventListener("click", () => imageModal.classList.add("active"));
    closeImageEls.forEach(el => el.addEventListener("click", () => {
        imageModal.classList.remove("active");
        resetImageForm();
    }));

    imageForm.addEventListener("submit", (event) => {
        if (imageErrorMessage.image) {
            event.preventDefault();
            displayImageError();
        }
    });
}