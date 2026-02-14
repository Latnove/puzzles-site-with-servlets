<html lang="ru">
    <#include "profile.ftl">

    <#macro scripts>
        <script defer src="${contextPath}/scripts/validators.js"></script>
        <script defer src="${contextPath}/scripts/settings.js"></script>
        <script defer src="${contextPath}/scripts/togglePassword.js"></script>
    </#macro>
    <#macro content>
        <h2 class="title profile__content-title">Ваша учетная запись</h2>

                <div class="profile__info">
                    <div class="profile__top">
                        <div class="profile__image">
                            <img
                                src="${user.image}"
                                alt="profile avatar"
                            />

                            <button class="profile__image-upload">
                                <svg width="800px" height="800px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                                    <path fill-rule="evenodd" clip-rule="evenodd" d="M9.77778 21H14.2222C17.3433 21 18.9038 21 20.0248 20.2646C20.51 19.9462 20.9267 19.5371 21.251 19.0607C22 17.9601 22 16.4279 22 13.3636C22 10.2994 22 8.76721 21.251 7.6666C20.9267 7.19014 20.51 6.78104 20.0248 6.46268C19.3044 5.99013 18.4027 5.82123 17.022 5.76086C16.3631 5.76086 15.7959 5.27068 15.6667 4.63636C15.4728 3.68489 14.6219 3 13.6337 3H10.3663C9.37805 3 8.52715 3.68489 8.33333 4.63636C8.20412 5.27068 7.63685 5.76086 6.978 5.76086C5.59733 5.82123 4.69555 5.99013 3.97524 6.46268C3.48995 6.78104 3.07328 7.19014 2.74902 7.6666C2 8.76721 2 10.2994 2 13.3636C2 16.4279 2 17.9601 2.74902 19.0607C3.07328 19.5371 3.48995 19.9462 3.97524 20.2646C5.09624 21 6.65675 21 9.77778 21ZM12 9.27273C9.69881 9.27273 7.83333 11.1043 7.83333 13.3636C7.83333 15.623 9.69881 17.4545 12 17.4545C14.3012 17.4545 16.1667 15.623 16.1667 13.3636C16.1667 11.1043 14.3012 9.27273 12 9.27273ZM12 10.9091C10.6193 10.9091 9.5 12.008 9.5 13.3636C9.5 14.7192 10.6193 15.8182 12 15.8182C13.3807 15.8182 14.5 14.7192 14.5 13.3636C14.5 12.008 13.3807 10.9091 12 10.9091ZM16.7222 10.0909C16.7222 9.63904 17.0953 9.27273 17.5556 9.27273H18.6667C19.1269 9.27273 19.5 9.63904 19.5 10.0909C19.5 10.5428 19.1269 10.9091 18.6667 10.9091H17.5556C17.0953 10.9091 16.7222 10.5428 16.7222 10.0909Z" fill="#fff"/>
                                </svg>
                            </button>
                        </div>

                        <div>
                            <h3 class="profile__username">${user.username?html}</h3>
                            <p class="profile__created-at">Создан ${user.createdAt?html}а</p>
                        </div>
                    </div>

                    <div class="profile__list">
                        <div class="profile__item">
                            <span class="profile__item-name">Логин:</span>

                            <p class="profile__item-text">${user.username?html}</p>
                        </div>

                        <div class="profile__item">
                            <span class="profile__item-name">Почта: </span>

                            <p class="profile__item-text">${user.email?html}</p>
                        </div>

                        <div class="profile__item profile__item--edit">
                            <span class="profile__item-name">Пароль:</span>

                            <p class="profile__item-text">••••••••</p>

                            <button class="button button--primary profile__button" id="password-button">
                                Изменить
                            </button>
                        </div>

                        <div class="profile__item profile__item--edit">
                            <span class="profile__item-name">Аватар: </span>

                            <p class="profile__item-text">Фотография профиля</p>

                            <button class="button button--primary profile__button" id="image-button">
                                Изменить фото
                            </button>
                        </div>
                    </div>
                </div>
    </#macro>

    <#macro modal>
        <div class="modal <#if passwordError??>active</#if>" id="password-modal">
            <div class="modal__content">
                <div class="modal__header">
                    <h3 class="modal__title">Изменить пароль</h3>
                    <button class="modal__close">
                        <svg width="800px" height="800px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <g id="Menu / Close_MD">
                                <path id="Vector" d="M18 18L12 12M12 12L6 6M12 12L18 6M12 12L6 18" stroke="#6B7280" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            </g>
                        </svg>
                    </button>
                </div>

                <div class="modal__error <#if passwordError?? && passwordError?has_content>active</#if>">
                    <#if passwordError??>
                        ${passwordError?html}
                    </#if>
                </div>

                <form class="modal__body" method="post" action="${contextPath}/change_password">
                    <div class="modal__item">
                        <label class="modal__label">Текущий пароль:</label>
                        <div class="password-container">
                            <input
                                    id="current-password"
                                    type="password"
                                    name="oldPassword"
                                    class="modal__input"
                                    placeholder="Введите текущий пароль"
                                    value="${(oldPassword!"")?html}"
                                    autocomplete="cur-password"
                                    required
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
                    </div>
                    <div class="modal__item">
                        <label class="modal__label">Новый пароль:</label>
                        <div class="password-container">
                            <input
                                    id="password"
                                    name="password"
                                    type="password"
                                    class="modal__input"
                                    placeholder="Введите новый пароль"
                                    required
                                    autocomplete="new-password"
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
                    </div>
                    <div class="modal__item">
                        <label class="modal__label">Подтвердите пароль:</label>
                        <div class="password-container">
                            <input
                                    id="repeat-password"
                                    type="password"
                                    class="modal__input"
                                    placeholder="Подтвердите новый пароль"
                                    autocomplete="new-password"
                                    required
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
                    </div>
                <div class="modal__buttons">
                    <button type="button" class="button button--secondary profile__button">
                        Отмена
                    </button>
                    <button type="submit" class="button button--primary profile__button">
                        Сохранить
                    </button>
                </div>
                </form>

            </div>


        </div>

        <div class="modal <#if imageError??>active</#if>" id="image-modal">
            <div class="modal__content">
                <div class="modal__header">
                    <h3 class="modal__title">Изменить аватарку</h3>
                    <button class="modal__close">
                        <svg width="800px" height="800px" viewBox="0 0 24 24" fill="none" xmlns="http://www.w3.org/2000/svg">
                            <g id="Menu / Close_MD">
                                <path id="Vector" d="M18 18L12 12M12 12L6 6M12 12L18 6M12 12L6 18" stroke="#6B7280" stroke-width="2" stroke-linecap="round" stroke-linejoin="round"/>
                            </g>
                        </svg>
                    </button>
                </div>

                <div class="modal__error <#if imageError?? && imageError?has_content>active</#if>">
                    <#if imageError??>
                        ${imageError?html}
                    </#if>
                </div>

                <form class="modal__body" method="post" action="${contextPath}/change_avatar" enctype="multipart/form-data">
                    <div class="modal__item">
                        <label class="modal__label">Выберите новое фото:</label>
                        <input type="file" name="image" class="modal__input" id="image" accept="image/*" required/>
                    </div>

                    <div class="modal__buttons">
                        <button type="button" class="button button--secondary profile__button">
                            Отмена
                        </button>
                        <button type="submit" class="button button--primary profile__button">
                            Сохранить
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </#macro>
</html>