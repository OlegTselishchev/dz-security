### Spring Boot Security для авторизации HTTP запросов

### Описание:

1. Для проверки Spring Security в прилложении есть окрытые и закрытые контроллеры.  

2. Открытый контроллер: 
 
   GET http://localhost:8080/api/v1/test/open

   Response: OPEN FOR NOT REGISTRY USER

3. Открытый контроллер для авторизации

   POST http://localhost:8080/api/v1/auth/register

    body {"name": "", "login": "", "password": "", "role": ""}

    role: USER/ADMIN

    Response: {"access_token": "", "refresh_token": ""}

4. Открытый контроллер для аунтификации

   POST http://localhost:8080/api/v1/auth/authenticate

   body {"login": "", "password": ""}

   Response: {"access_token": "", "refresh_token": ""}

5. Для проверки авторизации тестовый контроллер: хост:порт/api/v1/test/**

   GET http://localhost:8080/api/v1/test/welcome - люболй авторизированный пользователь

   GET http://localhost:8080/api/v1/test/admin - авторизированый пользователь с ролью ADMIN

   GET http://localhost:8080/api/v1/test/user - авторизированый пользователь с ролью USER

6. Для проверки авторизации c пермишинами:

    GET POST PUT DELETE http://localhost:8080/api/v1/admin

    GET POST PUT DELETE http://localhost:8080/api/v1/user

### 🏄 Стек:
Java 21, SpringBoot 3, Maven, Interceptors
