# Spring Boot - The Missing Guide and Spring boot Tips
- [The Missing Guide](#spring-boot-the-missing-guide)
- [Spring Boot Tips](#spring-boot-tips)


## Spring Boot The Missing Guide
- https://www.youtube.com/watch?v=3tTS8rwV7zQ&list=PLuNxlOYbv61jZL1IiciTgWezZoqEp4WXh&index=1&ab_channel=SivaLabs


![image](https://user-images.githubusercontent.com/69948118/228121911-356eed30-a461-4f21-b60f-d9850adfdd4e.png)
![image](https://user-images.githubusercontent.com/69948118/228122653-54279153-f7dc-449a-b7e1-25675b235c6a.png)
![image](https://user-images.githubusercontent.com/69948118/228123223-85b1ddbc-ee3b-48d0-929d-f8f8a6a60da4.png)
![image](https://user-images.githubusercontent.com/69948118/228123240-26f62d41-9e7e-43c0-aa12-54a2b2acf34c.png)
![image](https://user-images.githubusercontent.com/69948118/228123856-e9d325dd-d274-4e2e-852a-6fcb7e267341.png)
![image](https://user-images.githubusercontent.com/69948118/228123886-adf225ec-d56c-46d3-b741-e4f436309d32.png)
![image](https://user-images.githubusercontent.com/69948118/228124056-e8a3033c-cd20-4b74-8fbd-7455e1ca1137.png)
![image](https://user-images.githubusercontent.com/69948118/228138190-79e84217-814e-48da-a9c9-bacfd2267b28.png)
## Template Framework used by Spring
![image](https://user-images.githubusercontent.com/69948118/228138333-b9bb4bf9-9587-44df-a108-aec53fecd931.png)
![image](https://user-images.githubusercontent.com/69948118/228138838-61e6ca52-dee7-45d2-8e26-bb004366077a.png)
## Spring handles transactions & security using AOP & proxy design
![image](https://user-images.githubusercontent.com/69948118/228139057-ac1c5b0d-643a-4eec-b614-c1a743d4ceb5.png)
![image](https://user-images.githubusercontent.com/69948118/228142731-15a4269e-48a9-4b8d-8d48-3199060a57d5.png)
![image](https://user-images.githubusercontent.com/69948118/228143396-475b7ff3-15ba-4dab-96e4-365781741e7b.png)
## Bean Configuration
## AutoConfiguration Works
![image](https://user-images.githubusercontent.com/69948118/236757652-33bb7784-ac06-4fa5-b645-0380dd0c403f.png)
![image](https://user-images.githubusercontent.com/69948118/236758155-10e4fba9-8db8-4b3c-943a-92570a850cf4.png)
![image](https://user-images.githubusercontent.com/69948118/236759029-524bca04-cd69-46f5-bc65-012be7fac43c.png)
![image](https://user-images.githubusercontent.com/69948118/236759064-de7ec436-e35a-47f3-893f-0ab02b4bc357.png)
![image](https://user-images.githubusercontent.com/69948118/236759323-914567cc-e0db-4889-a76d-5cbd909c7665.png)


### Autoconfiguration
- https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/#features.external-config
![image](https://user-images.githubusercontent.com/69948118/228201235-922a73a3-3a38-45c7-9681-9731e9f45fc6.png)

- @Value
- @ConfigurationProperties(prefix = "app")
- we can create internal static class in main class of property configuration and we can access
- ![image](https://user-images.githubusercontent.com/69948118/228847353-40848010-e67d-43d4-a6b8-71f64da4a92a.png)

### Validation Configuration Properties using JavaBeans validation API
- We can use Java Bean API @NonEmpty etc on fields od property config class
![image](https://user-images.githubusercontent.com/69948118/228848020-14567241-0466-4395-a176-5bdc825866a2.png)
![image](https://user-images.githubusercontent.com/69948118/228848128-5a312d6b-57ba-4407-a399-2c3bcb154b4d.png)

### Binding Properties to Immutable Objects using Contructor Binding
- Property coniguration should be read only meaning no one can update those properties values later , for that we have to make that prperty class as Immutable
- Remove all setters , fields shoud be final and create constructor
- @ConstructorBinding use this annotation for configuration using constructor
- ![image](https://user-images.githubusercontent.com/69948118/228849809-3d1982f3-53c7-4bd7-a2d2-9626d187b672.png)

### Binding Properties to Java Records for Java14+


---
## Spring Boot Tips
- https://www.youtube.com/watch?v=2dPon1G5S-M&list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic&index=1&ab_channel=SivaLabs
- https://github.com/sivaprasadreddy/sivalabs-youtube-code-samples
### Dependecy Managment
- Dependecymanagment tag : we can exclude any transative dependency and we can define in parent project as POM 

### Managing application configuration
- Default Values ex. applicartion.properties
- Profile specific overrides ex. application-{profie}.properties
- environment variables ex.  SERVER_PORT=9090
- we can validate property file fields using @Validate
- We can bind propertiy files with Record from jdk16+

### Logging Tips
- https://github.com/sivaprasadreddy/sivalabs-youtube-code-samples/tree/main/spring-boot-logging
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/4aad686d-3cb2-4f88-ab8d-13ee539ca279)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/3d1b331c-1ece-4ba3-9b28-deb2fd669095)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/ffd79c35-e082-40a3-a51b-bd663a05f373)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/e8244d02-b255-4b02-807e-1d37acbc36ef)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/5ce9a1bb-e87c-4915-9ae8-558854f8ec72)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/4b6f61b7-aec7-4e11-ac19-4ab2275af3d1)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/23b30ab6-e39a-48c0-ba5f-664b31750a29)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/17dc8ce1-3d7f-41f3-8dd7-fb78b3c075fe)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/22cbef17-33c5-4e71-aaaa-020005188780)
#### Mapped Daignostic COntext
- By using the MDC, you can enrich log statements with contextual information such as user identifiers, request IDs, session IDs, transaction IDs, or any other relevant data that can help in understanding the log events in the context of the application's execution flow.
- https://howtodoinjava.com/logback/logging-with-mapped-diagnostic-context
```xml
<appender name="CONSOLE" class="ch.qos.logback.core.ConsoleAppender"> 
  <layout>
    <Pattern>%d{DATE} %p %X{sessionId} %X{userId} %c - %m%n</Pattern>
  </layout> 
</appender>
 ```
 ```
#logging.level.root=INFO
#logging.level.com.sivalabs=TRACE
#logging.pattern.console=%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID:- }){magenta} %clr(UserId:%X{UserId:-Guest}){magenta} %clr(Context:%X){magenta}  %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n
#logging.file.name=myapp.log
 ```
```xml
<configuration>
    <springProperty scope="context" name="region" source="app.region" defaultValue="us-east-1"/>
    <property name="LOG_FILE" value="${region}-myapp.log"/>
    <property name="CONSOLE_LOG_PATTERN" value="%clr(%d{yyyy-MM-dd HH:mm:ss.SSS}){faint} %clr(%5p) %clr(${PID:- }){magenta} %clr(UserId:%X{UserId:-Guest}){magenta} %clr(Context:%X){magenta}  %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n ${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}"/>
    <property name="FILE_LOG_PATTERN" value="${FILE_LOG_PATTERN:-%d{${LOG_DATEFORMAT_PATTERN:-yyyy-MM-dd HH:mm:ss.SSS}} ${LOG_LEVEL_PATTERN:-%5p} ${PID:- } [UserId:%X{UserId:-Guest}] [Context:%X] --- [%t] %-40.40logger{39} : %m%n${LOG_EXCEPTION_CONVERSION_WORD:-%wEx}}"/>

    <include resource="org/springframework/boot/logging/logback/defaults.xml" />
    <include resource="org/springframework/boot/logging/logback/console-appender.xml" />
    <include resource="org/springframework/boot/logging/logback/file-appender.xml" />

    <springProfile name="local">
        <root level="INFO">
            <appender-ref ref="CONSOLE" />
        </root>
    </springProfile>

    <springProfile name="!local">
        <root level="INFO">
            <appender-ref ref="CONSOLE" />
            <appender-ref ref="FILE" />
        </root>
    </springProfile>

    <logger name="com.sivalabs" level="DEBUG"/>
    <logger name="org.springframework" level="INFO"/>
</configuration>
```
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/9a254218-58d6-46de-93e6-3b5b71ac6d76)

### Testing
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/784c1191-45e9-4169-b8ea-fbc7a11ed13f)

- @WebMvcTest
```java
@WebMvcTest(controllers = ProductController.class)
class ProductControllerTest {

    @MockBean
    private ProductService productService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnActiveProducts() throws Exception {
        BDDMockito.given(productService.getAllProducts()).willReturn(List.of());

        mockMvc.perform(get("/api/products"))
                .andExpect(status().isOk());
    }
}
```

### IT using TestContainers

### Exception Handling in spring boot
- https://github.com/sivaprasadreddy/sivalabs-youtube-code-samples/tree/main/spring-boot-exception-handling
- Web project exception handling
- Customize white error page using template
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/b3ab8c4c-2ef4-4126-8059-1a189e0d7038)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/bc042250-a55b-44ac-b734-1092582b6eb4)
- Rest exception handling
- Customize error response payload using @ExceptionHandler
- @ControllerAdvice or @RestControllerAdvice

### Exception Handling using problem-spring-web
- https://github.com/zalando/problem-spring-web
- When add this starter dependecy in project will get exception handling by default with proper http status code and message
- Internally usinbg ProblemAutoconfiguration
```java
@RestControllerAdvice
class ExceptionHandling implements ProblemHandling {

    @ExceptionHandler
    public ResponseEntity<Problem> handle(CustomerNotFoundException e, NativeWebRequest request) {
        ThrowableProblem problem = Problem.builder().withStatus(Status.NOT_FOUND)
                .withTitle("Customer Not Found")
                .withDetail(e.getMessage())
                .build();
        return create(problem, request);
    }
}
```
### Acuator
```
spring.boot.admin.client.url=http://localhost:9090

management.endpoints.web.exposure.include=*
management.endpoint.health.show-components=always
management.endpoint.health.show-details=always

management.info.env.enabled=true
management.info.java.enabled=true
management.info.os.enabled=true

info.myapp.name=ActuatorDemo
info.myapp.version=1.0.1
info.myapp.author=SivaLabs
info.myapp.website=https://sivalabs.in

info.app.encoding=@project.build.sourceEncoding@
info.app.java.source=@java.version@
info.app.java.target=@java.version@
```

### Monitoring Spring Boot Application by Spring Boot Admin
- https://github.com/sivaprasadreddy/sivalabs-youtube-code-samples/tree/main/spring-boot-admin-server
- https://github.com/codecentric/spring-boot-admin
- Add Spring Boot Admin Server starter to your dependencies:
```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-server</artifactId>
    <version>3.0.4</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-web</artifactId>
</dependency>
```
```java
@SpringBootApplication
@EnableAdminServer
public class SpringBootAdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAdminServerApplication.class, args);
    }

}
```
- Add spring-boot-admin-starter-client to your dependencies:

```xml
<dependency>
    <groupId>de.codecentric</groupId>
    <artifactId>spring-boot-admin-starter-client</artifactId>
    <version>3.0.4</version>
</dependency>
<dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-security</artifactId>
</dependency>
 ```
 ```
 spring.boot.admin.client.url=http://localhost:8080  (1)
management.endpoints.web.exposure.include=*  (2)
management.info.env.enabled=true (3)
```
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/cc2edcd7-86f1-41c3-a1c5-2bc27d3f2989)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/16f06751-d04b-4696-95af-0e91c918380d)

### Generating Swagger docs for SpringBoot REST APIs
- https://github.com/sivaprasadreddy/sivalabs-youtube-code-samples/tree/main/spring-boot-openapi-swagger
- swagger docs can be generate by two libs sprningFox and OpenApi
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/15f5a781-8569-473e-ba25-a9538ebb62f3)
![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/0aa6c14d-b2af-48a7-acf3-ece5c28597b8)
```
 <dependency>
            <groupId>org.springdoc</groupId>
            <artifactId>springdoc-openapi-ui</artifactId>
            <version>1.6.9</version>
        </dependency>
 ```
 - For secutiry authorization at global
 ```java
 @Configuration
@OpenAPIDefinition(info = @Info(title = "SpringBoot Tips API", version = "v1"))
@SecurityScheme(
        name = "jwtBearerAuth",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        scheme = "bearer"
)
public class SwaggerConfig {
}
```
```java

@RestController
@RequestMapping("/api/bookmarks")
@RequiredArgsConstructor
@SecurityRequirement(name = "jwtBearerAuth")
@Tag(name = "Bookmarks")
public class BookmarkController {
    private final BookmarkService bookmarkService;

    @GetMapping
    public List<Bookmark> getAllBookmarks() {
        return bookmarkService.getAllBookmarks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable Long id) {
        Bookmark bookmark = bookmarkService.getBookmarkById(id);
        return ResponseEntity.ok(bookmark);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    //@Operation(description = "Create a new bookmark", security = @SecurityRequirement(name = "jwtBearerAuth"))
    @ApiResponses(
            value = {
                    @ApiResponse(description = "Successfully created new bookmark", responseCode = "201"),
                    @ApiResponse(description = "Forbidden", responseCode = "403"),
            }
    )
    public Bookmark createBookmark(@RequestBody @Valid Bookmark bookmark) {
        return bookmarkService.createBookmark(bookmark);
    }

}
 ```








