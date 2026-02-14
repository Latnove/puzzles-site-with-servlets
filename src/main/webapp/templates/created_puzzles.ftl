<html lang="ru">
    <#include "profile.ftl">

    <#macro scripts>
        <script defer src="${contextPath}/scripts/userPuzzlesUtils.js"></script>
        <script defer src="${contextPath}/scripts/loadCreatedPuzzles.js"></script>
    </#macro>

    <#macro content>
        <h2 class="title profile__content-title">
            История собранных пазлов
        </h2>

        <div class="profile__info">
            <div class="profile__card-list">
            </div>

            <div id="sentinel"></div>
            <div class="spinner"></div>
        </div>
    </#macro>

    <#macro modal></#macro>
</html>