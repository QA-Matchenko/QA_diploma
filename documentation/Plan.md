**План тестирования приложения по покупке туристического тура.**
--

Описание веб-cервиса.
--
Приложение — это веб-сервис, который предлагает купить тур по определённой цене двумя способами:

1.Обычная оплата по дебетовой карте.

2.Уникальная технология: выдача кредита по данным банковской карты.

Приложение **не обрабатывает данные по картам**, а пересылает их банковским сервисам:

-сервису платежей, далее Payment Gate;

-кредитному сервису, далее Credit Gate.

Приложение в собственной СУБД должно сохранять информацию о том, успешно ли был совершён платёж и каким способом. Данные карт при этом сохранять не допускается.


Данные для авторизации:
--
Username-app

Password-pass

Перечень автоматизируемых сценариев:
--
*Валидные требования по заполнению полей*:
1. В поле "Номер карты" вести значение "4444 4444 4444 4441" (значение, состоящее из 16 цифр).
   
2. В поле "Месяц" ввести текущую дату текущего года (двухзначное число от 01 до 12 включительно). 
3. В поле "Год" ввести данные текущего года. 
4. В поле "Имя" ввести данные на латинице не менее одной буквы. 
5. В поле "CVC/CVV" ввести трёхзначное число от 000 до 999 включительно.

>Позитивные проверки:
1. **Успешная оплата тура** "Путешествие дня" (Марракэш) при валидном заполнение полей формы "Купить" по действующей карте 4444 4444 4444 4441.

Ожидаемый результат: Вывод сообщения об успешной оплате тура. В базе данных приложения сохраняется информация об успешной транзакции (данные карт НЕ сохраняются).


2. **Успешная оплата тура** "Путешествие дня" (Марракэш) при валидном заполнение полей формы "Купить в кредит" по действующей карте 4444 4444 4444 4441.

  
Ожидаемый результат: Вывод сообщения об успешной оплате тура. В базе данных приложения сохраняется информация об успешной транзакции (данные карт НЕ сохраняются).

3. **Отказ в оплате тура** "Путешествие дня" (Марракэш) при валидном заполнение полей формы "Купить" по действующей карте 4444 4444 4444 4442.

Ожидаемый результат: Вывод сообщения об отказе оплаты тура. В базе данных приложения сохраняется информация о неудачной транзакции (данные карт НЕ сохраняются).


4. **Отказ в оплате тура** "Путешествие дня" (Марракэш) при валидном заполнение полей формы "Купить в кредит" по действующей карте 4444 4444 4444 4442.

Ожидаемый результат: Вывод сообщения об ошибке оплаты тура. В базе данных приложения сохраняется информация о неудачной транзакции (данные карт НЕ сохраняются).

5. **Позитивный статус БД после отправки информации о туре**.

Ожидаемый результат: Вывод сообщения о принятии данных.В базе данных приложения сохраняется информация о неудачной транзакции (данные карт НЕ сохраняются).

6. **Негативный статус БД после отправки информации о туре**.

Ожидаемый результат: Вывод сообщения об отклонении данных.В базе данных приложения сохраняется информация о неудачной транзакции (данные карт НЕ сохраняются).



>Нетативные проверки:

1. **Ввести 15 цифр** в поле "Номер карты". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Номер карты". Недостаточное кол-во символов.


2. **Ввести 16 любых символов** в поле "Номер карты". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Номер карты". Неверные символы.


3. **Ввести 17 цифр** в поле "Номер карты". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Номер карты". Преувеличение кол-ва символов.


4. **Оставить незаполненными данные** в поле "Номер карты". Оставшиеся поля заполнить валидными данными. 

Ожидаемый результат: Вывод сообщения об ошибке в поле "Номер карты". Недостаточное кол-во символов.


5. **Ввести число 00** в поле "Месяц". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Месяц". Неверные символы.

6. **Ввести число 13** в поле "Месяц". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Месяц". Преувеличение кол-ва символов.

7. **Оставить незаполненными данные** в поле "Месяц". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Месяц". Недостаточное кол-во символов.

8. **Ввести число 0000** в поле "Год". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Год". Неверные символы.

9. **Ввести число 2024** в поле "Год". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Год". Неверные символы.

10. **Ввести число 2026** в поле "Год". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Год". Неверные символы.


11. **Оставить незаполненными данные** в поле "Год". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Год". Недостаточное кол-во символов.

12. **Ввести имя на кириллице** в поле "Имя". Оставшиеся поля заполнить валидными данными. 

Ожидаемый результат: Вывод сообщения об ошибке в поле "Имя". Неверные символы.

13. **Ввести любые символы**в поле "Имя". Оставшиеся поля заполнить валидными данными. 

Ожидаемый результат: Вывод сообщения об ошибке в поле "Имя". Неверные символы.

14. **Оставить незаполненными данные** в поле "Имя". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "Имя". Недостаточное кол-во символов.

15. **Ввести число 1000** в поле "CVC/CVV". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "CVC/CVV". Преувеличение кол-ва символов.

16. **Ввести любое четырехзначное число** в поле "CVC/CVV". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "CVC/CVV". Преувеличение кол-ва символов.

17. **Оставить незаполненными данные** в поле "CVC/CVV". Оставшиеся поля заполнить валидными данными.

Ожидаемый результат: Вывод сообщения об ошибке в поле "CVC/CVV". Недостаточное кол-во символов.



Перечень используемых инструментов с обоснованием выбора.
--
**IntelliJ IDEA** — интегрированная среда разработки (комплекс программных средств, который используется для написания, исполнения, отладки и оптимизации кода) для Java, JavaScript, Python и других языков программирования. Отличается обширным набором инструментов для рефакторинга (перепроектирования) и оптимизации кода.

**Java** — язык написания авто-тестов. Это мультифункциональный объектно-ориентированный язык со строгой типизацией.

**JUnit 5** - последняя версия для написания тестов. Он предоставляет средство запуска консоли для запуска платформы из командной строки и создания подключаемых модулей для Gradle и Maven.

**Selenide** — фреймворк для автоматизированного тестирования веб-приложений на основе Selenium WebDriver. В Selenium имеется отличная библиотека для работы с веб-браузером, то есть он является инструментом автоматизации веб-браузера. С его помощью мы можем открывать браузер и копировать (имитировать) действия пользователя. Обладает низкоуровневыми API.

**Java Faker** - Простой инструмент для генерации тестовых данных.

**Lombok** - фреймворк для автогенерации кода с целью улучшить читаемость тестов.

**Git и GitHub** удобен для контроля версий проекта.

**Allure** - фреймворк, позволит создать визуально наглядную картину выполнения тестов.

**Docker** — платформа с открытым исходным кодом для автоматизации разработки, доставки и развертывания приложений. Ее основная идея — создание стандартного и предсказуемого окружения, где приложения могут работать независимо от операционной системы или инфраструктуры. Должен присутствовать на проекте  и удобен для работы с контейнерами.

**DBeaver**- приложение, предназначенное для управления базами данных. Выполняет функции: управление скриптами SQL, внесение результатов, обработка таблиц, экспорт и импорт данных.

**Google Chrome**- браузер, программа для просмотра интернет-страниц. 



Перечень и описание возможных рисков при автоматизации.
--
1. Возможны баги, падение автотестов при выпуске нового релиза. 
2. При частых релизах возможны дополнительные траты на отладку автотестов.
3. Возможны сложности с установкой/настройкой приложения и БД при тестировании.

Интервальная оценка с учётом рисков в часах.
--
1. Подготовка окружения для тестирования, получение БД - 20 часов.
2. Написание автотестов, тестирование, корректировка -45 часов.
3. Написание баг-репортов, issue после прогона тестов -6 часов.
4. Создание отчета по завершению тестирования- 4 часа.

План сдачи работ: когда будут готовы автотесты, результаты их прогона.
--
1. Подготовка окружения для тестирования, получение БД- 12.05.2025- 14.05.2025
2. Написание автотестов, тестирование, корректировка - 15.05.2025-23.05.2025
3. Написание баг-репортов, issue после прогона тестов - 26.05.2025- 27.05.2025
4. Создание отчета по завершению тестирования- 28.05-30.05.2025


