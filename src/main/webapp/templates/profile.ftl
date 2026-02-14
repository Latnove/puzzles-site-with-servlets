<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <title>ПазлыМир</title>
    <link rel="stylesheet" href="${contextPath}/styles/reset.css" />
    <link rel="stylesheet" href="${contextPath}/styles/globals.css" />
    <link rel="stylesheet" href="${contextPath}/styles/profile.css" />
    <link rel="icon" href="${contextPath}/images/site-logo.png" type="image/png"/>

    <link
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&amp;display=swap"
            rel="stylesheet"
    />

    <script>
        const contextPath = "${contextPath}"
    </script>
    <script defer src="${contextPath}/scripts/XSSUtils.js"></script>
    <@scripts></@scripts>
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
<main class="profile">
    <div class="container profile__container">
        <h1 class="profile__title">Мой профиль</h1>

        <div class="profile__modal">
            <nav class="profile__nav">
                <a href="${contextPath}/settings" class="profile__link <#if isSettings??>active</#if>">
                    <svg width="800px" height="800px" viewBox="0 0 16 16" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M8 7C9.65685 7 11 5.65685 11 4C11 2.34315 9.65685 1 8 1C6.34315 1 5 2.34315 5 4C5 5.65685 6.34315 7 8 7Z" fill="#fff"/>
                        <path d="M14 12C14 10.3431 12.6569 9 11 9H5C3.34315 9 2 10.3431 2 12V15H14V12Z" fill="#fff"/>
                    </svg>
                    <span>Профиль</span>
                </a>

                <a href="${contextPath}/collect_puzzles" class="profile__link <#if isCollect??>active</#if>">
                    <svg width="800px" height="800px" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg" fill="none"><path fill="#fff" stroke="#fff" stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M16.75 6H20a1 1 0 0 1 1 1v3.25a.75.75 0 0 1-.75.75H20a2 2 0 1 0 0 4h.25a.75.75 0 0 1 .75.75V20a1 1 0 0 1-1 1h-3.25a.75.75 0 0 1-.75-.75V20a2 2 0 1 0-4 0v.25a.75.75 0 0 1-.75.75H7a1 1 0 0 1-1-1v-4.25a.75.75 0 0 0-.75-.75H5a2 2 0 1 1 0-4h.25a.75.75 0 0 0 .75-.75V7a1 1 0 0 1 1-1h4.25a.75.75 0 0 0 .75-.75V5a2 2 0 1 1 4 0v.25c0 .414.336.75.75.75z"/></svg>

                    <span>Собранные</span>
                </a>

                <a href="${contextPath}/created_puzzles" class="profile__link <#if isCreated??>active</#if>">
                    <svg width="800px" height="800px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <path d="M11.4001 18.1612L11.4001 18.1612L18.796 10.7653C17.7894 10.3464 16.5972 9.6582 15.4697 8.53068C14.342 7.40298 13.6537 6.21058 13.2348 5.2039L5.83882 12.5999L5.83879 12.5999C5.26166 13.1771 4.97307 13.4657 4.7249 13.7838C4.43213 14.1592 4.18114 14.5653 3.97634 14.995C3.80273 15.3593 3.67368 15.7465 3.41556 16.5208L2.05445 20.6042C1.92743 20.9852 2.0266 21.4053 2.31063 21.6894C2.59466 21.9734 3.01478 22.0726 3.39584 21.9456L7.47918 20.5844C8.25351 20.3263 8.6407 20.1973 9.00498 20.0237C9.43469 19.8189 9.84082 19.5679 10.2162 19.2751C10.5343 19.0269 10.823 18.7383 11.4001 18.1612Z" fill="#fff"/>
                        <path d="M20.8482 8.71306C22.3839 7.17735 22.3839 4.68748 20.8482 3.15178C19.3125 1.61607 16.8226 1.61607 15.2869 3.15178L14.3999 4.03882C14.4121 4.0755 14.4246 4.11268 14.4377 4.15035C14.7628 5.0875 15.3763 6.31601 16.5303 7.47002C17.6843 8.62403 18.9128 9.23749 19.85 9.56262C19.8875 9.57563 19.9245 9.58817 19.961 9.60026L20.8482 8.71306Z" fill="#fff"/>
                    </svg>

                    <span>Созданные</span>
                </a>

                <a href="${contextPath}/logout" class="profile__link profile__link--logout">
                    <svg width="800px" height="800px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                        <g id="Interface / Log_Out">
                            <path id="Vector" d="M12 15L15 12M15 12L12 9M15 12H4M9 7.24859V7.2002C9 6.08009 9 5.51962 9.21799 5.0918C9.40973 4.71547 9.71547 4.40973 10.0918 4.21799C10.5196 4 11.0801 4 12.2002 4H16.8002C17.9203 4 18.4796 4 18.9074 4.21799C19.2837 4.40973 19.5905 4.71547 19.7822 5.0918C20 5.5192 20 6.07899 20 7.19691V16.8036C20 17.9215 20 18.4805 19.7822 18.9079C19.5905 19.2842 19.2837 19.5905 18.9074 19.7822C18.48 20 17.921 20 16.8031 20H12.1969C11.079 20 10.5192 20 10.0918 19.7822C9.71547 19.5905 9.40973 19.2839 9.21799 18.9076C9 18.4798 9 17.9201 9 16.8V16.75" stroke="rgba(255, 193, 193, 0.9)" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                        </g>
                    </svg>
                    <span>Выход</span>
                </a>
            </nav>

            <div class="profile__content">
                <@content></@content>
            </div>
        </div>
    </div>
</main>

<@modal></@modal>
</body>
</html>