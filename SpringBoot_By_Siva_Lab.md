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

---
## Spring Boot Tips
- https://www.youtube.com/watch?v=2dPon1G5S-M&list=PLuNxlOYbv61jFFX2ARQKnBgkMF6DvEEic&index=1&ab_channel=SivaLabs

### Dependecy Managment
- Dependecymanagment tag : we can exclude any transative dependency and we can define in parent project as POM 

### Managing application configuration
- Default Values ex. applicartion.properties
- Profile specific overrides ex. application-{profie}.properties
- environment variables ex.  SERVER_PORT=9090

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
