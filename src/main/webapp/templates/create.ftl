<!DOCTYPE html>
<html lang="en">
	<head>
		<meta charset="UTF-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		<link rel="icon" href="${contextPath}/images/site-logo.png" type="image/png"/>

		<style>
			<#include  "../styles/reset.css" />
			<#include "../styles/globals.css" />
			<#include "../styles/create.css" />
		</style>

		<link
			href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&amp;display=swap"
			rel="stylesheet"
		/>

		<script defer src="${contextPath}/scripts/XSSUtils.js"></script>
		<script defer src="${contextPath}/scripts/create.js"></script>
		<title>ПазлыМир</title>
	</head>
	<body>
		<a href="${contextPath}/index" class="button button-secondary back">
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
			<span>Вернуться на главную</span>
		</a>

		<main class="create">
			<div class="container create__container">
				<h1 class="create__title">Создание пазла</h1>
				<form class="create__modal" method="post" action="${contextPath}/create" enctype="multipart/form-data">
					<div class="create__upload">
						<h3 class="create__upload-title">
							<svg
								width="800px"
								height="800px"
								viewBox="0 0 24 24"
								fill="none"
								xmlns="http://www.w3.org/2000/svg"
							>
								<path
									d="M14.2639 15.9375L12.5958 14.2834C11.7909 13.4851 11.3884 13.086 10.9266 12.9401C10.5204 12.8118 10.0838 12.8165 9.68048 12.9536C9.22188 13.1095 8.82814 13.5172 8.04068 14.3326L4.04409 18.2801M14.2639 15.9375L14.6053 15.599C15.4112 14.7998 15.8141 14.4002 16.2765 14.2543C16.6831 14.126 17.12 14.1311 17.5236 14.2687C17.9824 14.4251 18.3761 14.8339 19.1634 15.6514L20 16.4934M14.2639 15.9375L18.275 19.9565M18.275 19.9565C17.9176 20 17.4543 20 16.8 20H7.2C6.07989 20 5.51984 20 5.09202 19.782C4.71569 19.5903 4.40973 19.2843 4.21799 18.908C4.12796 18.7313 4.07512 18.5321 4.04409 18.2801M18.275 19.9565C18.5293 19.9256 18.7301 19.8727 18.908 19.782C19.2843 19.5903 19.5903 19.2843 19.782 18.908C20 18.4802 20 17.9201 20 16.8V16.4934M4.04409 18.2801C4 17.9221 4 17.4575 4 16.8V7.2C4 6.0799 4 5.51984 4.21799 5.09202C4.40973 4.71569 4.71569 4.40973 5.09202 4.21799C5.51984 4 6.07989 4 7.2 4H16.8C17.9201 4 18.4802 4 18.908 4.21799C19.2843 4.40973 19.5903 4.71569 19.782 5.09202C20 5.51984 20 6.0799 20 7.2V16.4934M17 8.99989C17 10.1045 16.1046 10.9999 15 10.9999C13.8954 10.9999 13 10.1045 13 8.99989C13 7.89532 13.8954 6.99989 15 6.99989C16.1046 6.99989 17 7.89532 17 8.99989Z"
									stroke="#fff"
									stroke-width="2"
									stroke-linecap="round"
									stroke-linejoin="round"
								/>
							</svg>
							Изображение пазла
							<b>*</b>
						</h3>

						<label class="create__upload-area" for="file-input">
							<svg
								width="800px"
								height="800px"
								viewBox="0 0 24 24"
								version="1.1"
								xmlns="http://www.w3.org/2000/svg"
								xmlns:xlink="http://www.w3.org/1999/xlink"
							>
								<title>file_upload_line</title>
								<g
									id="页面-1"
									stroke="none"
									stroke-width="1"
									fill="none"
									fill-rule="evenodd"
								>
									<g
										id="File"
										transform="translate(-384.000000, -96.000000)"
										fill-rule="nonzero"
									>
										<g
											id="file_upload_line"
											transform="translate(384.000000, 96.000000)"
										>
											<path
												d="M24,0 L24,24 L0,24 L0,0 L24,0 Z M12.5934901,23.257841 L12.5819402,23.2595131 L12.5108777,23.2950439 L12.4918791,23.2987469 L12.4918791,23.2987469 L12.4767152,23.2950439 L12.4056548,23.2595131 C12.3958229,23.2563662 12.3870493,23.2590235 12.3821421,23.2649074 L12.3780323,23.275831 L12.360941,23.7031097 L12.3658947,23.7234994 L12.3769048,23.7357139 L12.4804777,23.8096931 L12.4953491,23.8136134 L12.4953491,23.8136134 L12.5071152,23.8096931 L12.6106902,23.7357139 L12.6232938,23.7196733 L12.6232938,23.7196733 L12.6266527,23.7031097 L12.609561,23.275831 C12.6075724,23.2657013 12.6010112,23.2592993 12.5934901,23.257841 L12.5934901,23.257841 Z M12.8583906,23.1452862 L12.8445485,23.1473072 L12.6598443,23.2396597 L12.6498822,23.2499052 L12.6498822,23.2499052 L12.6471943,23.2611114 L12.6650943,23.6906389 L12.6699349,23.7034178 L12.6699349,23.7034178 L12.678386,23.7104931 L12.8793402,23.8032389 C12.8914285,23.8068999 12.9022333,23.8029875 12.9078286,23.7952264 L12.9118235,23.7811639 L12.8776777,23.1665331 C12.8752882,23.1545897 12.8674102,23.1470016 12.8583906,23.1452862 L12.8583906,23.1452862 Z M12.1430473,23.1473072 C12.1332178,23.1423925 12.1221763,23.1452606 12.1156365,23.1525954 L12.1099173,23.1665331 L12.0757714,23.7811639 C12.0751323,23.7926639 12.0828099,23.8018602 12.0926481,23.8045676 L12.108256,23.8032389 L12.3092106,23.7104931 L12.3186497,23.7024347 L12.3186497,23.7024347 L12.3225043,23.6906389 L12.340401,23.2611114 L12.337245,23.2485176 L12.337245,23.2485176 L12.3277531,23.2396597 L12.1430473,23.1473072 Z"
												id="MingCute"
												fill-rule="nonzero"
											></path>
											<path
												d="M13.5858,2 C14.0572667,2 14.5115877,2.16648691 14.870172,2.46691468 L15,2.58579 L19.4142,7 C19.7476222,7.33339556 19.9511481,7.77238321 19.9922598,8.23835797 L20,8.41421 L20,20 C20,21.0543909 19.18415,21.9181678 18.1492661,21.9945144 L18,22 L6,22 C4.94563773,22 4.08183483,21.18415 4.00548573,20.1492661 L4,20 L4,4 C4,2.94563773 4.81587733,2.08183483 5.85073759,2.00548573 L6,2 L13.5858,2 Z M12,4 L6,4 L6,20 L18,20 L18,10 L13.5,10 C12.6716,10 12,9.32843 12,8.5 L12,4 Z M11.2929,11.1729 C11.6533615,10.8124385 12.2206207,10.7847107 12.6128973,11.0897166 L12.7071,11.1729 L14.8284,13.2942 C15.219,13.6847 15.219,14.3179 14.8284,14.7084 C14.4679385,15.0689538 13.9006793,15.0966888 13.5084027,14.7916047 L13.4142,14.7084 L13,14.2942 L13,17 C13,17.5523 12.5523,18 12,18 C11.48715,18 11.0644908,17.613973 11.0067275,17.1166239 L11,17 L11,14.2942 L10.5858,14.7084 C10.1953,15.099 9.5621,15.099 9.17157,14.7084 C8.81109,14.3479385 8.78336077,13.7806793 9.08838231,13.3884027 L9.17157,13.2942 L11.2929,11.1729 Z M14,4.41421 L14,8 L17.5858,8 L14,4.41421 Z"
												id="形状"
												fill="#fff"
											></path>
										</g>
									</g>
								</g>
							</svg>
							<div class="create__upload-text">Перетащите изображение сюда</div>
							<span class="create__upload-subtext">или</span>

							<div class="create__upload-button">
								<svg
									width="800px"
									height="800px"
									viewBox="0 0 24 24"
									fill="none"
									xmlns="http://www.w3.org/2000/svg"
								>
									<path
										d="M12 10V16M12 16L10 14M12 16L14 14M12.0627 6.06274L11.9373 5.93726C11.5914 5.59135 11.4184 5.4184 11.2166 5.29472C11.0376 5.18506 10.8425 5.10425 10.6385 5.05526C10.4083 5 10.1637 5 9.67452 5H6.2C5.0799 5 4.51984 5 4.09202 5.21799C3.71569 5.40973 3.40973 5.71569 3.21799 6.09202C3 6.51984 3 7.07989 3 8.2V15.8C3 16.9201 3 17.4802 3.21799 17.908C3.40973 18.2843 3.71569 18.5903 4.09202 18.782C4.51984 19 5.07989 19 6.2 19H17.8C18.9201 19 19.4802 19 19.908 18.782C20.2843 18.5903 20.5903 18.2843 20.782 17.908C21 17.4802 21 16.9201 21 15.8V10.2C21 9.0799 21 8.51984 20.782 8.09202C20.5903 7.71569 20.2843 7.40973 19.908 7.21799C19.4802 7 18.9201 7 17.8 7H14.3255C13.8363 7 13.5917 7 13.3615 6.94474C13.1575 6.89575 12.9624 6.81494 12.7834 6.70528C12.5816 6.5816 12.4086 6.40865 12.0627 6.06274Z"
										stroke="#fff"
										stroke-width="2"
										stroke-linecap="round"
										stroke-linejoin="round"
									/>
								</svg>
								Выбрать файл
							</div>

							<input
								name="file"
								type="file"
								id="file-input"
								class="create__upload-input"
								accept="image/*"
								required
							/>
						</label>

						<img class="create__preview-image" src="" alt="" />
					</div>
					<div class="create__settings">
						<fieldset class="create__item">
							<h3 class="create__item-text">
								Заголовок пазла
								<b>*</b>
							</h3>

							<input
								type="text"
								name="title"
								class="create__input"
								maxlength="200"
								placeholder="Введите название пазла"
								required
							/>
						</fieldset>

						<fieldset class="create__item">
							<h3 class="create__item-text">
								Размер сетки
								<b>*</b>
							</h3>

							<div class="create__sizes">
								<div class="create__sizes-item">
									<h4 class="create__sizes-title">Столбцы</h4>
									<div class="create__number-stepper">
										<button
											class="create__stepper-button stepper-minus"
											type="button"
											data-input="cols-input"
										>
											&mdash;
										</button>
										<input
											type="number"
											name="cols"
											class="create__input"
											id="cols-input"
											value="5"
											min="2"
											max="20"
											required
										/>
										<button
											class="create__stepper-button stepper-plus"
											type="button"
											data-input="cols-input"
										>
											+
										</button>
									</div>
								</div>

								<div class="create__sizes-item">
									<h4 class="create__sizes-title">Строки</h4>
									<div class="create__number-stepper">
										<button
											class="create__stepper-button stepper-minus"
											type="button"
											data-input="rows-input"
										>
											&mdash;
										</button>
										<input
											type="number"
											name="rows"
											class="create__input"
											id="rows-input"
											value="5"
											min="2"
											max="20"
											required
										/>
										<button
											class="create__stepper-button stepper-plus"
											type="button"
											data-input="rows-input"
										>
											+
										</button>
									</div>
								</div>
							</div>
						</fieldset>

						<fieldset class="create__item">
							<h3 class="create__item-text">
								Категории (выберите любое количество)
								<b>*</b>
							</h3>

							<div class="create__themes">
								<#list categories as category>
									<label class="create__checkbox">
										<input name="categories" type="checkbox" value="${category.value}" />
										<span>${category.nameRu?html}</span>
									</label>
								</#list>
							</div>
						</fieldset>

						<div class="status status--pending">
							<div class="status__item">
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
								На модерации
							</div>
							После создания пазл будет отправлен на модерацию
						</div>

						<div class="create__message create__error <#if errorMessage?has_content>active</#if>">${(errorMessage!"")?html}</div>

						<div class="create__message create__success <#if successMessage?has_content>active</#if>">${(successMessage!"")?html}</div>

						<div class="create__buttons">
							<button
								class="button button--secondary create__button"
								id="reset-button"
							>
								Очистить
							</button>
							<button class="button button--primary create__button">
								Создать пазл
							</button>
						</div>
					</div>
				</form>
			</div>
		</main>
	</body>
</html>
