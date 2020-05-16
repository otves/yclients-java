# Yclients API Client

Создан на Spring Boot 2. В основном для получения callback по записям, для своего Sms Server.
Если кому-то будет интересно развивать проект, то можете писать otveska[at]mail.ru с темой "Yclients API Client.

### License 
Apache License, Version 2.0 (http://www.apache.org/licenses/LICENSE-2.0)

### Ссылки

* [YClients REST API](https://yclients.docs.apiary.io) По факту там не всегда актаульная информация. Я менял модели, приходящие от API, 
и они отличались от доки.
* [Git](https://git-scm.com/)
* [Apache Maven](https://maven.apache.org/) 
* [Spring Boot Reference Guide](https://docs.spring.io/spring-boot/docs/2.1.6.RELEASE/reference/htmlsingle/)
* [Project Lombok](https://projectlombok.org/)

### Что требуется 
GIT (https://habr.com/ru/post/174467)

Как проверить, что установлен: 
```
git --version
```

JDK 8 (https://jdk.java.net/8/)

Как проверить, что установлена:
``` 
java -version
```

Maven 3 (https://habr.com/ru/post/77382/)

Как проверить, что установлена:
``` 
mvn -v
```


### Как запустить
```
git clone git@gitlab.com:otveska/yclients-java.git

cd yclients-java

mvn clean package

java -jar target/yclients-java-0.0.1-SNAPSHOT.jar
```

Пример запуска на Windows run-yclients-client.cmd

Можете проверять. GET [localhost:8888/callback/test](localhost:8888/callback/test)