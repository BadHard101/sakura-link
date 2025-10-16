## Веб-приложение туристического справочника по Японии 🌸 "SakuraLink"
Добро пожаловать в репозиторий проекта "SakuraLink"!  

![SakuraLink](https://i.pinimg.com/originals/58/31/59/5831595354177b00eef935f7deb13cd3.jpg)

### О проекте  
Веб-приложение «SakuraLink» ориентировано на русскоязычных пользователей, планирующих поездку в Японию. Проект направлен на упрощение планирования маршрутов и получение справочной информа-ции о туристических локациях, культурных особенностях и практических нюансах посещения.  
В основе архитектуры лежит модель MVC (Model View Controller), реализуемая средствами Spring Boot и ReactJS.  

---  

### Технологичксий стек
**Frontend:** React, Vite, React Router, Redux Toolkit (+ thunk), Axios, MUI, Tailwind CSS, Formik + Yup.  
**Backend:** Java, Spring Boot (Web, Security, Data JPA), Hibernate/JPA, JWT, Lombok, WebSocket.  
**База данных:** MySQL.
### Технологические особенности
- **Роли и доступ**: гость, пользователь, модератор, администратор.  
- **Безопасность**: JWT‑аутентификация, BCrypt, разграничение доступа.  
- **UI**: SPA на React, Redux Toolkit, Material‑UI + Tailwind, адаптивная вёрстка.

---  


### Роли и права
- **Гость** — просмотр публичного контента.  
- **Пользователь** — публикации, комментарии, оценки, избранное, жалобы.  
- **Модератор** — модерация контента и жалоб.  
- **Администратор** — глобальные настройки, роли, аналитика.
### Ключевые возможности
- **Справочник городов и достопримечательностей**: карточки мест, избранное, рейтинги, отзывы с фото и оценкой, ссылки на короткие видео.  
- **Статьи и гайды**: категории, поиск, пагинация, рейтинги, комментарии, избранное.  
- **Форум и Q&A**: темы/сообщения, отметка «решено», «горячие» темы, модерация контента.  
- **Чаты и уведомления** в реальном времени через WebSocket.  

---  

## Скриншоты
![Лента профиля](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Лента.png)
<p align="center"><em>Лента профиля с постами</em></p>  

![Страница профиля](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Профиль.png)
<p align="center"><em>Страница профиля пользователя</em></p>  

![Страница городов](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Города.png)
<p align="center"><em>Страница городов</em></p>  

![Страница достопримечательности](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Страница%20достоприм.png)
<p align="center"><em>Страница достопримечательности</em></p>  

![Окно отзыва на достопримечательность](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Отзыв.png)
<p align="center"><em>Окно отзыва на достопримечательность</em></p>  

![Страница статей](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Статьи.png)
<p align="center"><em>Страница статей</em></p>  

![Страница выбранной статьи](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Статья.png)
<p align="center"><em>Страница выбранной статьи</em></p>  

![Страница форума](https://github.com/BadHard101/sakura-link/blob/master/src/main/resources/Форум.png)
<p align="center"><em>Страница форума</em></p>  

---  

## Контакты
Если у вас есть вопросы или предложения, не стесняйтесь связаться по электронной почте: [BadHard101@yandex.ru](mailto:BadHard101@yandex.ru).
