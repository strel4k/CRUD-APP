## Консольное CRUD-приложение на Java </a> 

- Описание проекта

Данное приложение реализует CRUD (Create, Read, Update, Delete) операции для управления сущностями Writer, Post и Label. Данные хранятся в JSON-файлах (writers.json, posts.json, labels.json) с использованием библиотеки Gson.

- Структура проекта

Проект разделен на несколько слоев:

model – POJO классы (Writer, Post, Label, PostStatus)

repository – работа с JSON-файлами через Gson (GsonWriterRepositoryImpl, GsonPostRepositoryImpl, GsonLabelRepositoryImpl)

controller – логика управления (WriterController, PostController, LabelController)

view – взаимодействие с пользователем через консоль (WriterView, PostView, LabelView)

main – точка входа (Main)

- Используемые технологии

Java 17+

Gson (для работы с JSON)

Maven (для управления зависимостями)

- Возможности

Добавление, редактирование, удаление и просмотр Writer

Добавление, редактирование, удаление и просмотр Post

Добавление, редактирование, удаление и просмотр Label

Удаление осуществляется путем изменения статуса на DELETED
