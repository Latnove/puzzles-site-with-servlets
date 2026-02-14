<html>
<head>
    <meta charset="utf-8" />
    <title>ПазлыМир</title>
    <link rel="stylesheet" href="${contextPath}/styles/reset.css" />
    <link rel="stylesheet" href="${contextPath}/styles/globals.css" />
    <link rel="stylesheet" href="${contextPath}/styles/error.css" />
    <link rel="icon" href="${contextPath}/images/site-logo.png" type="image/png"/>
    <link
            href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;600;700&amp;display=swap"
            rel="stylesheet"
    />
</head>
<body>
<div class="container error__container">
    <div class="error">
        <span class="error__icon">🧩</span>
        <h2 class="error__code">
            ${statusCode!}
        </h2>
        <h1 class="error__title">
            ${pageTitle!}
        </h1>
        <p class="error__text">
            ${errorMessage!}
        </p>
        <a class="button button--primary error__link" href="${contextPath}/index">На главную</a>
    </div>
</div>
</body>