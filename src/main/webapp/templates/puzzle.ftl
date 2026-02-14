<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="icon" href="/images/site-logo.png" type="image/png"/>



		<style>
			<#include "../styles/reset.css" />
			<#include "../styles/globals.css" />
			<#include "../styles/puzzle.css" />
		</style>

		<link
			href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&amp;display=swap"
			rel="stylesheet"
		/>

		<script>
			const PUZZLE_ID = ${(puzzle.id)};
			const contextPath = "${contextPath}"
		</script>

		<script defer src="${contextPath}/scripts/XSSUtils.js"></script>
		<script defer src="${contextPath}/scripts/puzzle.js"></script>

		<#if user?? && user.role == "admin">
			<script defer src="${contextPath}/scripts/puzzleAdmin.js"></script>
		</#if>

		<title>ПазлыМир</title>
	</head>
	<body>
		<a <#if user?? && user.role == "admin">href="${contextPath}/panel"
				<#else>href="${contextPath}/index"
				</#if>
				class="button button-secondary back">
			<div class="back__arrow">
				<svg
					xmlns="http://www.w3.org/2000/svg"
					version="1.0"
					viewBox="0 0 1280.000000 640.000000"
				>
					<metadata>
						Created by potrace 1.15, written by Peter Selinger 2001-2017
					</metadata>
					<g
						transform="translate(0.000000,640.000000) scale(0.100000,-0.100000)"
						fill="#000000"
						stroke="none"
					>
						<path
							d="M9280 5934 c-106 -21 -223 -80 -293 -150 -99 -97 -148 -196 -168 -336 -10 -72 -9 -97 5 -164 22 -108 75 -212 144 -282 33 -33 391 -297 851 -627 l794 -570 -5084 -5 c-4763 -5 -5087 -6 -5132 -22 -146 -52 -265 -152 -330 -275 -114 -217 -77 -472 93 -644 70 -71 126 -108 217 -142 l58 -22 5078 -5 5078 -5 -752 -615 c-414 -338 -776 -638 -804 -667 -29 -29 -68 -84 -89 -125 -112 -224 -73 -470 105 -649 104 -105 233 -159 382 -159 99 0 186 22 270 68 70 39 2847 2303 2942 2399 160 162 199 422 93 633 -46 94 -119 163 -324 311 -1086 782 -2701 1940 -2747 1970 -83 54 -166 80 -272 84 -49 2 -101 1 -115 -1z"
						/>
					</g>
				</svg>
			</div>
			<span>
				<#if user?? && user.role == "admin">
				Вернуться обратно
				<#else>
				Вернуться на главную
				</#if>
			</span>
		</a>

		<main class="puzzle">
			<div class="container puzzle__container">
				<div class="puzzle__wrapper">
					<div class="puzzle__image">
						<img
							src="${puzzle.imageUrl?html}"
							alt="puzzle image"
						/>
					</div>
					<div class="puzzle__content">
						<#if userRole?? && userRole?has_content && userRole == "admin">
							<form class="puzzle__title-wrapper" method="post" action="${contextPath}/remove_puzzle?puzzleId=${puzzle.id!}">
								<h1 class="puzzle__title">${(puzzle.title!"")?html}</h1>

								<button class="puzzle__delete-button button button--outline">Remove</button>
							</form>

							<#else>
							<h1 class="puzzle__title">${(puzzle.title!"")?html}</h1>
						</#if>
						<div class="puzzle__params">
							<div class="puzzle__params-item">
								<svg
									width="800px"
									height="800px"
									viewBox="0 0 16 16"
									fill="none"
									xmlns="http://www.w3.org/2000/svg"
								>
									<path
										d="M8 7C9.65685 7 11 5.65685 11 4C11 2.34315 9.65685 1 8 1C6.34315 1 5 2.34315 5 4C5 5.65685 6.34315 7 8 7Z"
										fill="#fff"
									/>
									<path
										d="M14 12C14 10.3431 12.6569 9 11 9H5C3.34315 9 2 10.3431 2 12V15H14V12Z"
										fill="#fff"
									/>
								</svg>
								<span> Автор: <b> @${(creatorUser.username!"")?html} </b> </span>
							</div>
							<div class="puzzle__params-item">
								<svg
									width="800px"
									height="800px"
									viewBox="0 0 24 24"
									fill="none"
									xmlns="http://www.w3.org/2000/svg"
								>
									<path
										d="M22 11V9C22 4 20 2 15 2H9C4 2 2 4 2 9V15C2 20 4 22 9 22H10"
										stroke="#292D32"
										stroke-width="1.5"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
									<path
										d="M2.03003 8.5H22"
										stroke="#292D32"
										stroke-width="1.5"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
									<path
										d="M2.03003 15.5H12"
										stroke="#292D32"
										stroke-width="1.5"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
									<path
										d="M8.51001 21.99V2.01001"
										stroke="#292D32"
										stroke-width="1.5"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
									<path
										d="M15.51 11.99V2.01001"
										stroke="#292D32"
										stroke-width="1.5"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
									<path
										d="M18.73 14.6701L14.58 18.82C14.42 18.98 14.27 19.29 14.23 19.51L14 21.1C13.92 21.67 14.32 22.08 14.89 21.99L16.48 21.76C16.7 21.73 17.01 21.5701 17.17 21.4101L21.32 17.26C22.03 16.55 22.37 15.7101 21.32 14.6601C20.28 13.6201 19.45 13.9501 18.73 14.6701Z"
										stroke="#292D32"
										stroke-width="1.5"
										stroke-miterlimit="10"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
									<path
										d="M18.14 15.26C18.49 16.52 19.48 17.5 20.74 17.86"
										stroke="#292D32"
										stroke-width="1.5"
										stroke-miterlimit="10"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
								</svg>
								<span> Сетка: ${(puzzle.pieceCols?html)!}×${(puzzle.pieceRows?html)!} (${(puzzle.pieceCols)! * (puzzle.pieceRows)!} элементов) </span>
							</div>
							<div class="puzzle__params-item">
								<svg width="800px" height="800px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
									<path d="M14.0037 4H9.9963C6.21809 4 4.32899 4 3.15525 5.17157C2.27661 6.04858 2.0557 7.32572 2.00016 9.49444C1.99304 9.77248 2.22121 9.99467 2.49076 10.0652C3.35074 10.2901 3.98521 11.0711 3.98521 12C3.98521 12.9289 3.35074 13.7099 2.49076 13.9348C2.22121 14.0053 1.99304 14.2275 2.00016 14.5056C2.0557 16.6743 2.27661 17.9514 3.15525 18.8284C4.32899 20 6.21809 20 9.9963 20H14.0037C17.7819 20 19.671 20 20.8448 18.8284C21.7234 17.9514 21.9443 16.6743 21.9998 14.5056C22.007 14.2275 21.7788 14.0053 21.5092 13.9348C20.6493 13.7099 20.0148 12.9289 20.0148 12C20.0148 11.0711 20.6493 10.2901 21.5092 10.0652C21.7788 9.99467 22.007 9.77248 21.9998 9.49444C21.9443 7.32572 21.7234 6.04858 20.8448 5.17157C19.671 4 17.7819 4 14.0037 4Z" stroke="#1C274C" stroke-width="1.5"/>
									<path d="M11.1459 10.0225C11.5259 9.34084 11.7159 9 12 9C12.2841 9 12.4741 9.34084 12.8541 10.0225L12.9524 10.1989C13.0603 10.3926 13.1143 10.4894 13.1985 10.5533C13.2827 10.6172 13.3875 10.641 13.5972 10.6884L13.7881 10.7316C14.526 10.8986 14.895 10.982 14.9828 11.2643C15.0706 11.5466 14.819 11.8407 14.316 12.429L14.1858 12.5812C14.0429 12.7483 13.9714 12.8319 13.9392 12.9353C13.9071 13.0387 13.9179 13.1502 13.9395 13.3733L13.9592 13.5763C14.0352 14.3612 14.0733 14.7536 13.8435 14.9281C13.6136 15.1025 13.2682 14.9435 12.5773 14.6254L12.3986 14.5431C12.2022 14.4527 12.1041 14.4075 12 14.4075C11.8959 14.4075 11.7978 14.4527 11.6014 14.5431L11.4227 14.6254C10.7318 14.9435 10.3864 15.1025 10.1565 14.9281C9.92674 14.7536 9.96476 14.3612 10.0408 13.5763L10.0605 13.3733C10.0821 13.1502 10.0929 13.0387 10.0608 12.9353C10.0286 12.8319 9.95713 12.7483 9.81418 12.5812L9.68403 12.429C9.18097 11.8407 8.92945 11.5466 9.01723 11.2643C9.10501 10.982 9.47396 10.8986 10.2119 10.7316L10.4028 10.6884C10.6125 10.641 10.7173 10.6172 10.8015 10.5533C10.8857 10.4894 10.9397 10.3926 11.0476 10.1989L11.1459 10.0225Z" stroke="#1C274C" stroke-width="1.5"/>
								</svg>

								<span class="puzzle__rating">Рейтинг: <span class="rating-list">
										<#list 1..5 as item>
											<div class="rating-item <#if item <= puzzle.rating!>active</#if>" data-rating="${item}">★</div>
										</#list>
									</span><b>${(puzzle.rating!"")?html}</b>
								</span>
							</div>
							<div class="puzzle__params-item">
								<svg
									width="800px"
									height="800px"
									viewBox="0 0 24 24"
									fill="none"
									xmlns="http://www.w3.org/2000/svg"
								>
									<path
										d="M3 9H21M7 3V5M17 3V5M6 13H8M6 17H8M11 13H13M11 17H13M16 13H18M16 17H18M6.2 21H17.8C18.9201 21 19.4802 21 19.908 20.782C20.2843 20.5903 20.5903 20.2843 20.782 19.908C21 19.4802 21 18.9201 21 17.8V8.2C21 7.07989 21 6.51984 20.782 6.09202C20.5903 5.71569 20.2843 5.40973 19.908 5.21799C19.4802 5 18.9201 5 17.8 5H6.2C5.0799 5 4.51984 5 4.09202 5.21799C3.71569 5.40973 3.40973 5.71569 3.21799 6.09202C3 6.51984 3 7.07989 3 8.2V17.8C3 18.9201 3 19.4802 3.21799 19.908C3.40973 20.2843 3.71569 20.5903 4.09202 20.782C4.51984 21 5.07989 21 6.2 21Z"
										stroke="#000000"
										stroke-width="2"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
								</svg>
								<span> Создан: ${(puzzle.createdAt!"")?html} </span>
							</div>
						</div>

						<#if userRole?? && userRole?has_content && userRole == "admin">
							<div class="puzzle__status-list">
								<#list allStatuses as status>
									<#if status == puzzle.status>
										<div class="puzzle__status status__item status__item--${puzzle.status!}" data-status="${puzzle.status!}">
											<svg
													fill="#000000"
													width="800px"
													height="800px"
													viewBox="0 0 32 32"
													version="1.1"
													xmlns="http://www.w3.org/2000/svg"
											>
												<title>time</title>
												<path
														d="M0 16q0-3.232 1.28-6.208t3.392-5.12 5.12-3.392 6.208-1.28q3.264 0 6.24 1.28t5.088 3.392 3.392 5.12 1.28 6.208q0 3.264-1.28 6.208t-3.392 5.12-5.12 3.424-6.208 1.248-6.208-1.248-5.12-3.424-3.392-5.12-1.28-6.208zM4 16q0 3.264 1.6 6.048t4.384 4.352 6.016 1.6 6.016-1.6 4.384-4.352 1.6-6.048-1.6-6.016-4.384-4.352-6.016-1.632-6.016 1.632-4.384 4.352-1.6 6.016zM14.016 16v-5.984q0-0.832 0.576-1.408t1.408-0.608 1.408 0.608 0.608 1.408v4h4q0.8 0 1.408 0.576t0.576 1.408-0.576 1.44-1.408 0.576h-6.016q-0.832 0-1.408-0.576t-0.576-1.44z"
												></path>
											</svg>
											${(puzzle.status!"")?html}
										</div>

										<#else>
											<div class="puzzle__status status__item status__item--${status} puzzle__status--edit" data-status="${status?html}">
												<svg
														fill="#000000"
														width="800px"
														height="800px"
														viewBox="0 0 32 32"
														version="1.1"
														xmlns="http://www.w3.org/2000/svg"
												>
													<title>time</title>
													<path
															d="M0 16q0-3.232 1.28-6.208t3.392-5.12 5.12-3.392 6.208-1.28q3.264 0 6.24 1.28t5.088 3.392 3.392 5.12 1.28 6.208q0 3.264-1.28 6.208t-3.392 5.12-5.12 3.424-6.208 1.248-6.208-1.248-5.12-3.424-3.392-5.12-1.28-6.208zM4 16q0 3.264 1.6 6.048t4.384 4.352 6.016 1.6 6.016-1.6 4.384-4.352 1.6-6.048-1.6-6.016-4.384-4.352-6.016-1.632-6.016 1.632-4.384 4.352-1.6 6.016zM14.016 16v-5.984q0-0.832 0.576-1.408t1.408-0.608 1.408 0.608 0.608 1.408v4h4q0.8 0 1.408 0.576t0.576 1.408-0.576 1.44-1.408 0.576h-6.016q-0.832 0-1.408-0.576t-0.576-1.44z"
													></path>
												</svg>
												${status?html}
											</div>
									</#if>
								</#list>
							</div>

							<#else>
								<div class="puzzle__status status__item status__item--${puzzle.status!}">
									<svg
											fill="#000000"
											width="800px"
											height="800px"
											viewBox="0 0 32 32"
											version="1.1"
											xmlns="http://www.w3.org/2000/svg"
									>
										<title>time</title>
										<path
												d="M0 16q0-3.232 1.28-6.208t3.392-5.12 5.12-3.392 6.208-1.28q3.264 0 6.24 1.28t5.088 3.392 3.392 5.12 1.28 6.208q0 3.264-1.28 6.208t-3.392 5.12-5.12 3.424-6.208 1.248-6.208-1.248-5.12-3.424-3.392-5.12-1.28-6.208zM4 16q0 3.264 1.6 6.048t4.384 4.352 6.016 1.6 6.016-1.6 4.384-4.352 1.6-6.048-1.6-6.016-4.384-4.352-6.016-1.632-6.016 1.632-4.384 4.352-1.6 6.016zM14.016 16v-5.984q0-0.832 0.576-1.408t1.408-0.608 1.408 0.608 0.608 1.408v4h4q0.8 0 1.408 0.576t0.576 1.408-0.576 1.44-1.408 0.576h-6.016q-0.832 0-1.408-0.576t-0.576-1.44z"
										></path>
									</svg>
									${(puzzle.status!"")?html}
								</div>
						</#if>
						<div class="puzzle__categories">
							<p class="puzzle__categories-title puzzle__params-item">
								<svg
									width="800px"
									height="800px"
									viewBox="0 0 24 24"
									fill="none"
									xmlns="http://www.w3.org/2000/svg"
								>
									<g clip-path="url(#clip0_429_11052)">
										<circle
											cx="17"
											cy="7"
											r="3"
											stroke="#292929"
											stroke-width="2.5"
											stroke-linecap="round"
											stroke-linejoin="round"
										/>
										<circle
											cx="7"
											cy="17"
											r="3"
											stroke="#292929"
											stroke-width="2.5"
											stroke-linecap="round"
											stroke-linejoin="round"
										/>
										<path
											d="M14 14H20V19C20 19.5523 19.5523 20 19 20H15C14.4477 20 14 19.5523 14 19V14Z"
											stroke="#292929"
											stroke-width="2.5"
											stroke-linecap="round"
											stroke-linejoin="round"
										/>
										<path
											d="M4 4H10V9C10 9.55228 9.55228 10 9 10H5C4.44772 10 4 9.55228 4 9V4Z"
											stroke="#292929"
											stroke-width="2.5"
											stroke-linecap="round"
											stroke-linejoin="round"
										/>
									</g>
									<defs>
										<clipPath id="clip0_429_11052">
											<rect width="24" height="24" fill="white" />
										</clipPath>
									</defs>
								</svg>
								<span> Категории пазла: </span>
							</p>
							<#list puzzle.categories! as category>
								<div class="puzzle__categories-item">${category.nameRu?html}</div>
							</#list>
						</div>
						<#if puzzle.status == "active" || (user?? && user.role == "admin")>
							<a class="puzzle__link button button--primary" href="${contextPath}/play/${puzzle.id}"
							><svg
										width="800px"
										height="800px"
										viewBox="0 0 24 24"
										xmlns="http://www.w3.org/2000/svg"
										fill="none"
								>
									<path
											fill="#fff"
											stroke="#fff"
											stroke-linecap="round"
											stroke-linejoin="round"
											stroke-width="2"
											d="M16.75 6H20a1 1 0 0 1 1 1v3.25a.75.75 0 0 1-.75.75H20a2 2 0 1 0 0 4h.25a.75.75 0 0 1 .75.75V20a1 1 0 0 1-1 1h-3.25a.75.75 0 0 1-.75-.75V20a2 2 0 1 0-4 0v.25a.75.75 0 0 1-.75.75H7a1 1 0 0 1-1-1v-4.25a.75.75 0 0 0-.75-.75H5a2 2 0 1 1 0-4h.25a.75.75 0 0 0 .75-.75V7a1 1 0 0 1 1-1h4.25a.75.75 0 0 0 .75-.75V5a2 2 0 1 1 4 0v.25c0 .414.336.75.75.75z"
									/>
								</svg>
								Начать собирать пазл</a
							>
							<#else>
							<div class="text">Сборка пазла пока недоступна</div>
						</#if>
					</div>
				</div>
				<div class="reviews puzzle__reviews">
					<#if !review??>
						<form class="reviews__add-review add-review" method="post">
						<h3 class="add-review__title">
							<svg
								width="800px"
								height="800px"
								viewBox="0 0 24 24"
								fill="none"
								xmlns="http://www.w3.org/2000/svg"
							>
								<path
									d="M11.4001 18.1612L11.4001 18.1612L18.796 10.7653C17.7894 10.3464 16.5972 9.6582 15.4697 8.53068C14.342 7.40298 13.6537 6.21058 13.2348 5.2039L5.83882 12.5999L5.83879 12.5999C5.26166 13.1771 4.97307 13.4657 4.7249 13.7838C4.43213 14.1592 4.18114 14.5653 3.97634 14.995C3.80273 15.3593 3.67368 15.7465 3.41556 16.5208L2.05445 20.6042C1.92743 20.9852 2.0266 21.4053 2.31063 21.6894C2.59466 21.9734 3.01478 22.0726 3.39584 21.9456L7.47918 20.5844C8.25351 20.3263 8.6407 20.1973 9.00498 20.0237C9.43469 19.8189 9.84082 19.5679 10.2162 19.2751C10.5343 19.0269 10.823 18.7383 11.4001 18.1612Z"
									fill="#fff"
								/>
								<path
									d="M20.8482 8.71306C22.3839 7.17735 22.3839 4.68748 20.8482 3.15178C19.3125 1.61607 16.8226 1.61607 15.2869 3.15178L14.3999 4.03882C14.4121 4.0755 14.4246 4.11268 14.4377 4.15035C14.7628 5.0875 15.3763 6.31601 16.5303 7.47002C17.6843 8.62403 18.9128 9.23749 19.85 9.56262C19.8875 9.57563 19.9245 9.58817 19.961 9.60026L20.8482 8.71306Z"
									fill="#fff"
								/>
							</svg>
							Оставить отзыв
						</h3>
						<div class="rating-list add-review__rating-list">
							<#list 1..5 as item>
									<button class="rating-item add-review__rating-item <#if rating?? && item <= rating!0>active</#if>" data-rating="${item}">
										<input class="rating" type="radio" id="star${item}" value="${item}" <#if rating?? && item == rating!0>checked</#if>/>
										★
									</button>
							</#list>
							<input name="rating" class="add-review__rating-input" <#if rating??>value="#{rating}"</#if> required/>
						</div>
						<textarea
							id="review-textarea"
							name="text"
							class="add-review__textarea"
							minlength="3"
							required
							placeholder="Напишите ваш отзыв о пазле...
							
Поделитесь впечатлениями: что вам понравилось, была ли сложность оптимальной, понравилась ли картинка?"
						>${text!""}</textarea>
							<#if errorMessage?? && errorMessage?has_content>
								<div class="text error-message">${errorMessage?html}</div>
							</#if>
						<button
							class="puzzle__link button button--primary add-review__button"
						>
							Отправить отзыв
						</button>
					</form>
					</#if>

					<h2 class="reviews__title">
						<svg
							width="800px"
							height="800px"
							viewBox="0 0 24 24"
							fill="none"
							xmlns="http://www.w3.org/2000/svg"
						>
							<path
								d="M4 8C4 5.17157 4 3.75736 4.87868 2.87868C5.75736 2 7.17157 2 10 2H14C16.8284 2 18.2426 2 19.1213 2.87868C20 3.75736 20 5.17157 20 8V16C20 18.8284 20 20.2426 19.1213 21.1213C18.2426 22 16.8284 22 14 22H10C7.17157 22 5.75736 22 4.87868 21.1213C4 20.2426 4 18.8284 4 16V8Z"
								stroke="#1C274D"
								stroke-width="1.5"
							/>
							<path
								d="M19.8978 16H7.89778C6.96781 16 6.50282 16 6.12132 16.1022C5.08604 16.3796 4.2774 17.1883 4 18.2235"
								stroke="#1C274D"
								stroke-width="1.5"
							/>
							<path
								d="M8 7H16"
								stroke="#1C274D"
								stroke-width="1.5"
								stroke-linecap="round"
							/>
							<path
								d="M8 10.5H13"
								stroke="#1C274D"
								stroke-width="1.5"
								stroke-linecap="round"
							/>
							<path
								d="M19.5 19H8"
								stroke="#1C274D"
								stroke-width="1.5"
								stroke-linecap="round"
							/>
						</svg>
						Отзывы (${(reviewsLength!0)?html})
					</h2>

					<div class="reviews__list">
						<#if (reviewsLength! > 0) >
							<#if review?? && review?has_content>
								<div class="review-card">
									<div class="review-card__header">
										<img class="review-card__image" src="${(review.user.image!"")?html}" alt="user avatar" />
										<div class="review-card__meta">
											<h4 class="review-card__username">${(review.user.username!"")?html}</h4>
											<div class="review-card__rating-list rating-list">
												<#list 1..5 as item>
													<div class="rating-item <#if item <= review.rating>active</#if>" data-rating="${item}">★</div>
												</#list>
											</div>
											<span class="review-card__date">${(review.createdAt!"")?html}</span>
										</div>

										<form method="post" action="${contextPath}/remove_review/${review.id}" class="review-card__remove">
											<button class="button button--outline" type="submit">Удалить отзыв</button>
										</form>
									</div>

									<div class="review-card__text">
										${(review.reviewText!"")?html}
									</div>
								</div>
							</#if>
						</#if>
					</div>

					<div class="spinner"></div>
					<div id="sentinel"></div>
				</div>
			</div>
		</main>
	</body>
</html>
