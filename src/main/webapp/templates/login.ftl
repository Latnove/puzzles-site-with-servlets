<html lang="ru">
<#include "auth.ftl">
<#macro scripts>
	<script defer src="${contextPath}/scripts/togglePassword.js"></script>
</#macro>
<#macro headerLinks>
	<a class="auth__link auth__link--primary" href="#">Вход</a>
	<a class="auth__link" href="${contextPath}/sign_up">Регистрация</a>
</#macro>
<#macro title>
	Добро пожаловать!
</#macro>
<#macro text>
	Войдите в свой аккаунт, чтобы продолжить собирать пазлы
</#macro>
<#macro error>
	<div class="auth__error<#if errorMessage?? && errorMessage?has_content> active</#if>">
		${(errorMessage!"")?html}
	</div>
</#macro>
<#macro form>
	<form class="auth__form" method="post" action="${contextPath}/login">
		<input
				type="text"
				class="auth__input"
				name="login"
				id="login"
				placeholder="Имя пользователя"
				value="${(username!"")?html}"
				autocomplete="nickname"
				required
		/>

		<div class="password-container">
			<input
					name="password"
					type="password"
					class="auth__input"
					placeholder="Пароль"
					id="password"
					value="${(password!"")?html}"
					required
					autocomplete="current-password"
			/>

			<button class="password-container__toggle">
				<svg
						class="toggle toggle--close active"
						width="800px"
						height="800px"
						viewBox="0 0 24 24"
						fill="none"
						xmlns="http://www.w3.org/2000/svg"
				>
					<path
							d="M1 12C1 12 5 4 12 4C19 4 23 12 23 12"
							stroke="#1f2937"
							stroke-width="2"
							stroke-linecap="round"
							stroke-linejoin="round"
					/>
					<path
							d="M1 12C1 12 5 20 12 20C19 20 23 12 23 12"
							stroke="#1f2937"
							stroke-width="2"
							stroke-linecap="round"
							stroke-linejoin="round"
					/>
					<circle
							cx="12"
							cy="12"
							r="3"
							stroke="#1f2937"
							stroke-width="2"
							stroke-linecap="round"
							stroke-linejoin="round"
					/>
				</svg>

				<svg
						class="toggle toggle--open"
						width="800px"
						height="800px"
						viewBox="0 0 24 24"
						fill="none"
						xmlns="http://www.w3.org/2000/svg"
				>
					<path
							d="M2 2L22 22"
							stroke="#1f2937"
							stroke-width="2"
							stroke-linecap="round"
							stroke-linejoin="round"
					/>
					<path
							d="M6.71277 6.7226C3.66479 8.79527 2 12 2 12C2 12 5.63636 19 12 19C14.0503 19 15.8174 18.2734 17.2711 17.2884M11 5.05822C11.3254 5.02013 11.6588 5 12 5C18.3636 5 22 12 22 12C22 12 21.3082 13.3317 20 14.8335"
							stroke="#1f2937"
							stroke-width="2"
							stroke-linecap="round"
							stroke-linejoin="round"
					/>
					<path
							d="M14 14.2362C13.4692 14.7112 12.7684 15.0001 12 15.0001C10.3431 15.0001 9 13.657 9 12.0001C9 11.1764 9.33193 10.4303 9.86932 9.88818"
							stroke="#1f2937"
							stroke-width="2"
							stroke-linecap="round"
							stroke-linejoin="round"
					/>
				</svg>
			</button>
		</div>

		<label for="remember" class="auth__checkbox">
			<input type="checkbox" id="remember" />
			Запомнить меня
		</label>

		<button type="submit" class="button button--primary form__button">Войти</button>
	</form>
</#macro>
<#macro bottom>
	Нет аккаунта? &#8203;
	<a class="auth__bottom-link" href="${contextPath}/sign_up">Зарегистрироваться</a>
</#macro>
</html>
