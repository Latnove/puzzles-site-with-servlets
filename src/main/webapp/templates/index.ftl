<html lang="ru">
	<head>
		<meta charset="utf-8" />
		<title>ПазлыМир</title>
		<link rel="stylesheet" href="${contextPath}/styles/reset.css" />
		<link rel="stylesheet" href="${contextPath}/styles/globals.css" />
		<link rel="stylesheet" href="${contextPath}/styles/index.css" />
		<link rel="icon" href="${contextPath}/images/site-logo.png" type="image/png"/>
		<link
			href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&amp;display=swap"
			rel="stylesheet"
		/>
		<link rel="stylesheet" href="${contextPath}/styles/fireworks.css" />

		<script>
			const contextPath = "${contextPath}"
		</script>
		<script defer src="${contextPath}/scripts/fireworks.js"></script>
		<script defer src="${contextPath}/scripts/puzzlesUtils.js"></script>
		<script defer src="${contextPath}/scripts/index.js"></script>
		<script defer src="${contextPath}/scripts/XSSUtils.js"></script>
		<script defer src="${contextPath}/scripts/catalog.js"></script>
	</head>
	<body>
		<header class="header">
			<div class="container header__container">
				<a class="logo header__logo scroll-link" href="#" data-goto="main"
					>ПазлыМир</a
				>
				<nav class="header__nav">
					<#if user?? && (user.role == "admin")>
						<a style="text-decoration: underline;" class="header__link" href="${contextPath}/panel"
						>Админ панель</a
						>
					</#if>

					<a class="header__link scroll-link" href="#" data-goto="info"
						>О нас</a
					>
					<a class="header__link scroll-link" href="#" data-goto="catalog"
						>Каталог</a
					>
					<a class="header__link scroll-link" href="#" data-goto="about"
						>Преимущества</a
					>
					<a class="header__link scroll-link" href="#" data-goto="faq">FAQ</a>

					<#if user??>
						<a class="header__link" href="${contextPath}/create">Создать пазл</a>

						<a class="header__profile" href="${contextPath}/settings">
							<img
								src="${user.image?html}"
								width="50"
								height="50"
								alt="user profile avatar"
							/>

							<div class="header__link">${user.username?html}</div>
						</a>
						<#else>
							<a class="header__link button button--primary" href="${contextPath}/login"
							>Личный кабинет</a
							>
					</#if>
				</nav>
			</div>
		</header>

		<main>
			<section class="main">
				<div class="container main__container">
					<h1 class="main__title">Добро пожаловать в мир пазлов в ПазлыМир!</h1>
					<p class="text main__text">
						Откройте для себя увлекательный мир пазлов. Собирайте, создавайте и
						соревнуйтесь с друзьями в нашем сообществе любителей головоломок.
					</p>
					<button
						class="button button--outline main__button scroll-link"
						data-goto="catalog"
					>
						Начать собирать
					</button>
				</div>
			</section>

			<section class="info">
				<div class="container info__container">
					<div class="info__content">
						<h2 class="title info__title">О пазлах</h2>
						<p class="text info__text">
							Пазлы — это удивительное сочетание игры и медитации. Они развивают
							усидчивость, внимание к деталям и логическое мышление. Процесс
							сборки увлекает, помогает отвлечься от суеты и почувствовать
							удовлетворение от проделанной работы.
						</p>
						<p class="text info__text">
							Собирая пазлы, вы тренируете мозг, развиваете творческий подход и
							учитесь видеть целое в мелочах. Это занятие подойдёт и для
							спокойного вечера в одиночестве, и для времяпрепровождения с
							семьёй или друзьями.
						</p>
						<div class="info__list">
							<div class="info__item">
								<h3 class="info__item-title">10,000+</h3>
								<p class="info__item-text">Пазлов в каталоге</p>
							</div>
							<div class="info__item">
								<h3 class="info__item-title">50,000+</h3>
								<p class="info__item-text">Активных пользователей</p>
							</div>
							<div class="info__item">
								<h3 class="info__item-title">1,000,000+</h3>
								<p class="info__item-text">Собранных пазлов</p>
							</div>
						</div>
					</div>

					<div class="info__image">
						<img
							src="${contextPath}/images/46ee296aa60023eadcfbea67a8aa92936a4a90f0.png"
							width="600px"
							alt="puzzless picture"
						/>
					</div>
				</div>
			</section>

			<section class="catalog">
				<div class="container catalog__container">
					<h2 class="title catalog__title">Каталог пазлов</h2>
					<p class="text catalog__text">
						Выберите пазл по своему вкусу и начните увлекательное путешествие
					</p>

					<div class="catalog__wrapper">
						<div class="catalog__filter filter">
							<form class="filter__form">
								<h3 class="filter__title">Фильтры</h3>

								<fieldset class="filter__item item--details">
									<h4 class="filter__item-title">Количество деталей</h4>
									<div class="filter__double-range double-range">
										<input
											name="piece"
											class="text double-range__price double-range__price--start"
											type="number"
											value="4"
										/>
										<input
											name="piece"
											class="text double-range__price double-range__price--end"
											type="number"
											value="400"
										/>
										<div class="double-range__line">
											<a
												class="double-range__slider double-range__slider--left"
												href="#"
												draggable="false"
											></a>
											<a
												class="double-range__slider double-range__slider--right"
												href="#"
												draggable="false"
											></a>
											<div class="double-range__interval-line"></div>
										</div>
									</div>
								</fieldset>

								<fieldset class="filter__item item--rating">
									<h4 class="filter__item-title">Рейтинг</h4>
									<div class="filter__rating-list">
										<button class="filter__rating-item" data-rating="1">
											★
											<input class="rating" type="checkbox" />
										</button>
										<button class="filter__rating-item" data-rating="2">
											★
											<input class="rating" type="checkbox" />
										</button>
										<button class="filter__rating-item" data-rating="3">
											★
											<input class="rating" type="checkbox" />
										</button>
										<button class="filter__rating-item" data-rating="4">
											★
											<input class="rating" type="checkbox" />
										</button>
										<button class="filter__rating-item" data-rating="5">
											★
											<input class="rating" type="checkbox" />
										</button>
									</div>
								</fieldset>

								<fieldset class="filter__item item--theme">
									<h4 class="filter__item-title">Тематика</h4>

									<div class="filter__themes">
										<#list categories![] as category>
											<label class="filter__checkbox">
												<input type="checkbox" value="${category.value}" />
												<span>${category.nameRu?html}</span>
											</label>
										</#list>
									</div>
								</fieldset>

								<button class="filter__button filter__button--primary">
									Применить
								</button>
								<button class="filter__button filter__button--secondary">
									Сбросить
								</button>
							</form>
						</div>
						<div class="catalog__card-list">
							<div class="catalog__more-button-wrapper">
								<button class="button button--primary catalog__more-button">
									Показать еще
								</button>
							</div>
						</div>
					</div>
				</div>
			</section>

			<section class="about">
				<div class="container about__container">
					<h2 class="title about__title">О нас</h2>
					<p class="text about__text">
						ПазлыМир - это больше чем просто сайт с пазлами. Это целое
						сообщество!
					</p>

					<div class="about__list">
						<div class="about__item">
							<div class="about__item-image">🧩</div>
							<h3 class="about__item-title">Создавайте свои пазлы</h3>
							<p class="about__item-text">
								Загружайте собственные изображения и превращайте их в
								увлекательные пазлы для всего сообщества
							</p>
						</div>

						<div class="about__item">
							<div class="about__item-image">🏆</div>
							<h3 class="about__item-title">Соревнуйтесь с друзьями</h3>
							<p class="about__item-text">
								Участвуйте в рейтингах, зарабатывайте достижения и соревнуйтесь
								с другими игроками в скорости сборки
							</p>
						</div>

						<div class="about__item">
							<div class="about__item-image">📊</div>
							<h3 class="about__item-title">Отслеживайте статистику</h3>
							<p class="about__item-text">
								Ведите учет всех собранных пазлов, следите за своим прогрессом и
								ставьте новые рекорды
							</p>
						</div>

						<div class="about__item">
							<div class="about__item-image">👥</div>
							<h3 class="about__item-title">Присоединяйтесь к сообществу</h3>
							<p class="about__item-text">
								Общайтесь с единомышленниками, делитесь советами и открывайте
								новых друзей через общую любовь к пазлам
							</p>
						</div>
					</div>
				</div>
			</section>

			<section class="collect">
				<div class="container">
					<h2 class="title collect__title">Пример процесса собирания пазла</h2>
					<p class="text collect__text">
						Здесь вы можете протестировать сборку пазлов
					</p>

					<div class="collect__container">
						<div class="collect__puzzle-container"></div>
						<div class="collect__all-puzzles"></div>
					</div>
				</div>
			</section>

			<section class="faq">
				<div class="container faq__container">
					<h2 class="title faq__title">Часто задаваемые вопросы</h2>
					<p class="text faq__text">
						Ответы на самые популярные вопросы наших пользователей
					</p>
					<div class="faq__list">
						<div class="faq__item">
							<button class="faq__question">
								Как начать решать пазлы?
								<span class="faq__image">+</span>
							</button>
							<div class="faq__answer">
								<p>
									Просто выберите понравившийся пазл из каталога и нажмите на
									него. Пазл откроется в новом окне, где вы сможете
									перетаскивать кусочки и собирать картинку. Никакой регистрации
									для начала не требуется!
								</p>
							</div>
						</div>

						<div class="faq__item">
							<button class="faq__question">
								Можно ли загружать свои изображения?
								<span class="faq__image">+</span>
							</button>
							<div class="faq__answer">
								<p>
									Да! Зарегистрированные пользователи могут загружать свои
									изображения и создавать из них пазлы. Ваши пазлы станут
									доступны другим пользователям сообщества.
								</p>
							</div>
						</div>

						<div class="faq__item">
							<button class="faq__question">
								Что дает регистрация на сайте?
								<span class="faq__image">+</span>
							</button>
							<div class="faq__answer">
								<p>
									Регистрация позволяет сохранять прогресс решения пазлов,
									участвовать в рейтингах, создавать собственные пазлы,
									оставлять отзывы и соревноваться с другими пользователями.
								</p>
							</div>
						</div>

						<div class="faq__item">
							<button class="faq__question">
								Как работает система рейтингов?

								<span class="faq__image">+</span>
							</button>
							<div class="faq__answer">
								<p>
									Пользователи оценивают пазлы от 1 до 5 звезд. Также ведется
									подсчет количества решений каждого пазла. Чем выше рейтинг и
									больше решений, тем популярнее пазл.
								</p>
							</div>
						</div>
					</div>
				</div>
			</section>

			<section class="auth">
				<div class="container auth__container">
					<h2 class="title auth__title">
						<#if user??>
							Создайте свой уникальный пазл за пару минут!
						<#else>
							Готовы начать свое путешествие в мир пазлов?
						</#if>
					</h2>
					<p class="text auth__text">
						<#if user??>
							Выберите любимое изображение, настройте сложность и форму элементов, а затем наслаждайтесь увлекательной игрой. Начните творить и делитесь своими пазлами с друзьями прямо сейчас!
						<#else>
							Присоединяйтесь к тысячам любителей головоломок и откройте для себя
							увлекательный мир ПазлыМир. Регистрация займет всего минуту!
						</#if>
					</p>
					<div class="auth__buttons">
						<#if user??>
							<a class="button button--primary auth__link" href="${contextPath}/create"
							>Создать пазл</a
							>
						<#else>
							<a class="button button--primary auth__link" href="${contextPath}/sign_up">
								Зарегистрироваться
							</a>
							<a class="button button--outline auth__link" href="${contextPath}/login">
								Войти
							</a>
						</#if>
					</div>
				</div>
			</section>
		</main>

		<footer class="footer">
			<div class="container footer__container">
				<div class="footer__top">
					<div class="footer__item">
						<h4 class="footer__title">ПазлыМир</h4>
						<p class="footer__text">
							Ваш путеводитель в мир увлекательных пазлов. Мы создаем сообщество
							любителей головоломок, где каждый может найти что-то по душе.
						</p>

						<div class="footer__socials">
							<a class="footer__social-link" href="https://www.google.com/" target="_blank">📘</a>
							<a class="footer__social-link" href="https://www.google.com/" target="_blank">📷</a>
							<a class="footer__social-link" href="https://www.google.com/" target="_blank">🐦</a>
							<a class="footer__social-link" href="https://www.google.com/" target="_blank">📺</a>
						</div>
					</div>

					<div class="footer__item">
						<h4 class="footer__title">Навигация</h4>
						<div class="footer__list">
							<a class="footer__text scroll-link" href="#" data-goto="info"
								>О нас</a
							>
							<a class="footer__text scroll-link" href="#" data-goto="catalog"
								>Каталог пазлов</a
							>
							<a class="footer__text scroll-link" href="#" data-goto="about"
								>Преимущества</a
							>
							<a class="footer__text scroll-link" href="#" data-goto="faq"
								>Часто задаваемые вопросы</a
							>
							<#if user??>
								<a class="footer__text" href="/${contextPath}create"
								>Создать пазл</a
								>
							</#if>
						</div>
					</div>

					<div class="footer__item">
						<h4 class="footer__title">Контакты</h4>
						<div class="footer__list">
							<p class="footer__text">📧 info@puzzleworld.ru</p>
							<p class="footer__text">📞 +7 (800) 123-45-67</p>
							<p class="footer__text">📍 Москва, Россия</p>
							<p class="footer__text">🕒 Пн-Пт: 9:00-18:00</p>
						</div>
					</div>
				</div>

				<div class="footer__bottom">
					<p class="footer__copyright">© 2025 ПазлыМир. Все права защищены.</p>
				</div>
			</div>
		</footer>
	</body>
</html>
