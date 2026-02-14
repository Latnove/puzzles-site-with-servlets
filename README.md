# 🧩 Puzzle Creator

Сайт, где **любой может создать свой пазл**, **модераторы одобряют**, а пользователи **собирают и оставляют отзывы**!

## ✨ Особенности

- 🔐 Регистрация/авторизация с аватарками
- 🧩 Создание пазлов из своих изображений  
- ✅ Модерация (pending → active)
- 🎮 Сборка пазлов (drag & drop)
- ⭐ Отзывы и комментарии
- 💾 PostgreSQL + HikariCP
- ⚡ Java Servlets + FreeMarker + Canvas API
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/0e63ff9d-27b2-4dcc-b897-d99c20030472" />
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/24c4830a-4be7-4272-8f12-bcbc660b2c2d" />
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/bc61daae-149c-4952-8dd3-aa9330f8e63e" />

## 🔐 Аутентификация
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/e8bbf10a-6ded-4bc9-afd4-c8d6d69d2128" />
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/21c604b5-6479-48e1-a80c-2d33a274fb98" />

## 💾 Профиль
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/43618df5-168f-4a5e-9a15-e4497adad7e0" />

## 🧩 Пазлы
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/14c272be-26b3-4248-b6a9-08c111737fed" />

## ✅ Админ-панель
<img width="1512" height="829" alt="image" src="https://github.com/user-attachments/assets/cd30b9fe-5ceb-4ae5-80a0-ecf614e8ba2b" />

## 🚀 Быстрый старт

```bash
git clone <repo>
cd puzzles

# 1. Копируй пример
cp .env.example .env

# 2. Создай базу данных по .sql

# 3. Заполни секреты в .env

# 4. Запуск
docker compose up -d --build
