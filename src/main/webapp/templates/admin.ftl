<html lang="ru">
<head>
    <meta charset="utf-8" />
    <title>ПазлыМир</title>
    <link rel="icon" href="${contextPath}/images/site-logo.png" type="image/png"/>

    <link rel="stylesheet" href="${contextPath}/styles/reset.css" />
    <link rel="stylesheet" href="${contextPath}/styles/globals.css" />
    <link rel="stylesheet" href="${contextPath}/styles/admin.css" />
    <link
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&amp;display=swap"
            rel="stylesheet"
    />

    <script>
        const contextPath = "${contextPath}"
    </script>
    <script defer src="${contextPath}/scripts/XSSUtils.js"></script>
    <script defer src="${contextPath}/scripts/admin.js"></script>
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
<main class="panel">
    <div class="container panel__container">
        <h1 class="panel__title">Админ панель</h1>

        <div class="panel__info-list">
            <div class="panel__info-item">
                <div class="panel__info-image panel__info-image--pending">⏳</div>
                <div class="panel__info-meta">
                    <p class="panel__info-status">На модерации</p>
                    <span class="panel__info-count">${(puzzleCounts.pendingCount!0)?html}</span>
                </div>
            </div>

            <div class="panel__info-item">
                <div class="panel__info-image panel__info-image--active">✓</div>
                <div class="panel__info-meta">
                    <p class="panel__info-status">Активные</p>
                    <span class="panel__info-count">${(puzzleCounts.activeCount!0)?html}</span>
                </div>
            </div>

            <div class="panel__info-item">
                <div class="panel__info-image panel__info-image--rejected">✗</div>
                <div class="panel__info-meta">
                    <p class="panel__info-status">Отклонённые</p>
                    <span class="panel__info-count">${(puzzleCounts.rejectedCount!0)?html}</span>
                </div>
            </div>
        </div>

        <nav class="panel__nav">
            <a class="panel__link active" id="all" href="#">
                <div class="panel__link-image">
                    <svg
                            width="800px"
                            height="800px"
                            viewBox="0 0 24 24"
                            fill="none"
                            xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                                fill="none"
                                d="M22 11V9C22 4 20 2 15 2H9C4 2 2 4 2 9V15C2 20 4 22 9 22H10"
                                stroke="#292D32"
                                stroke-width="1.5"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                        />
                        <path
                                fill="none"
                                d="M2.03003 8.5H22"
                                stroke="#292D32"
                                stroke-width="1.5"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                        />
                        <path
                                fill="none"
                                d="M2.03003 15.5H12"
                                stroke="#292D32"
                                stroke-width="1.5"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                        />
                        <path
                                fill="none"
                                d="M8.51001 21.99V2.01001"
                                stroke="#292D32"
                                stroke-width="1.5"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                        />
                        <path
                                fill="none"
                                d="M15.51 11.99V2.01001"
                                stroke="#292D32"
                                stroke-width="1.5"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                        />
                        <path
                                fill="none"
                                d="M18.73 14.6701L14.58 18.82C14.42 18.98 14.27 19.29 14.23 19.51L14 21.1C13.92 21.67 14.32 22.08 14.89 21.99L16.48 21.76C16.7 21.73 17.01 21.5701 17.17 21.4101L21.32 17.26C22.03 16.55 22.37 15.7101 21.32 14.6601C20.28 13.6201 19.45 13.9501 18.73 14.6701Z"
                                stroke="#292D32"
                                stroke-width="1.5"
                                stroke-miterlimit="10"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                        />
                        <path
                                fill="none"
                                d="M18.14 15.26C18.49 16.52 19.48 17.5 20.74 17.86"
                                stroke="#292D32"
                                stroke-width="1.5"
                                stroke-miterlimit="10"
                                stroke-linecap="round"
                                stroke-linejoin="round"
                        />
                    </svg>
                </div>
                Все (${(puzzleCounts.totalCount!0)?html})
            </a>

            <a class="panel__link" id="pending" href="#">
                <div class="panel__link-image">
                    <svg
                            fill="inherit !important"
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
                </div>
                На модерации (${(puzzleCounts.pendingCount!0)?html})
            </a>

            <a class="panel__link" id="active" href="#">
                <div class="panel__link-image">
                    <svg
                            fill="inherit !important"
                            width="800px"
                            height="800px"
                            viewBox="0 0 24 24"
                            xmlns="http://www.w3.org/2000/svg"
                    >
                        <path
                                d="m0 12c0-6.627 5.373-12 12-12s12 5.373 12 12-5.373 12-12 12c-6.624-.008-11.992-5.376-12-11.999zm2.4 0c0 5.302 4.298 9.6 9.6 9.6s9.6-4.298 9.6-9.6-4.298-9.6-9.6-9.6c-5.299.006-9.594 4.301-9.6 9.599v.001zm4 0c0-3.093 2.507-5.6 5.6-5.6s5.6 2.507 5.6 5.6-2.507 5.6-5.6 5.6c-3.093 0-5.6-2.507-5.6-5.6z"
                        />
                    </svg>
                </div>
                Активные (${(puzzleCounts.activeCount!0)?html})
            </a>

            <a class="panel__link" id="rejected" href="#">
                <div class="panel__link-image">
                    <svg
                            fill="inherit !important"
                            height="800px"
                            width="800px"
                            version="1.1"
                            id="Capa_1"
                            xmlns="http://www.w3.org/2000/svg"
                            xmlns:xlink="http://www.w3.org/1999/xlink"
                            viewBox="0 0 329.328 329.328"
                            xml:space="preserve"
                    >
								<path
                                        d="M164.666,0C73.871,0,0.004,73.871,0.004,164.672c0.009,90.792,73.876,164.656,164.662,164.656
	c90.793,0,164.658-73.865,164.658-164.658C329.324,73.871,255.459,0,164.666,0z M164.666,30c31.734,0,60.933,11.042,83.975,29.477
	L59.478,248.638c-18.431-23.04-29.471-52.237-29.474-83.967C30.004,90.413,90.413,30,164.666,30z M164.666,299.328
	c-31.733,0-60.934-11.042-83.977-29.477L269.854,80.691c18.431,23.043,29.471,52.244,29.471,83.979
	C299.324,238.921,238.917,299.328,164.666,299.328z"
                                />
							</svg>
                </div>
                Отклоненные (${(puzzleCounts.rejectedCount!0)?html})
            </a>
        </nav>

        <div class="panel__card-list">
        </div>
        <div id="sentinel"></div>
        <div class="spinner"></div>
    </div>
</main>
</body>
</html>
