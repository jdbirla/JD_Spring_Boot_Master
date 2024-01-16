# Transaction Management in Spring Boot

# Table of contents

- [Transaction Management in Spring Boot](#transaction-management-in-spring-boot)
  - [Transaction Management in Spring framwork](#transaction-management-in-spring-framwork)
    - [There are 2 types of transaction management namely](#there-are-2-types-of-transaction-management-namely)
    - [1. Declarative Transaction Management](#1-declarative-transaction-management)
    - [2. Programmatic Transaction Management](#2-programmatic-transaction-management)
  - [Spring Data JPA transaction managment](#spring-data-jpa-transaction-managment)
  - [```](#)
  - [- https://www.youtube.com/watch?v=b7Pev6i8fso&list=PLyHJZXNdCXsdC-p2186C6NO4FpadnCC_q&index=8&ab_channel=CodeDecode](#--httpswwwyoutubecomwatchvb7pev6i8fsolistplyhjzxndcxsdc-p2186c6no4fpadncc_qindex8ab_channelcodedecode)
  - [@Modifying](#modifying)
  - [Multiple Data Source in Spring Boot](#multiple-data-source-in-spring-boot)
 
## Transaction Management in Spring framwork 
  - [https://medium.com/javarevisited/transaction-management-in-spring-boot-eb01e20b21fe]
  - [https://www.baeldung.com/spring-transactional-propagation-isolation]
  - [https://www.javainuse.com/spring/springtrans]
### There are 2 types of transaction management namely
1. Declarative
2. Programmatic

### 1. Declarative Transaction Management
- This can be achieved in 2 ways ie. through XML configurations or through annotations
- Now, let us focus only on the annotation part.
- We need to include 2 annotations for this to take effect
  - 1. **@EnableTransactionManagement** annotation is usually on the main class. Adding @EnableTransactionManagement annotation creates a PlatformTransactionManager for you — with its JDBC auto-configurations.
```java
 @SpringBootApplication
@EnableTransactionManagement
public class Application {
  public static void main(String[] args) {
    SpringApplication.run(Application.class, args);
  }
}
```
2. **@Transactional** annotation on a service class or a method
- We can put the annotation on definitions of interfaces, classes, or directly on methods.  They override each other according to the priority order; from lowest to highest - - we have: interface, superclass, class, interface method, superclass method, and class method.
- Spring applies the class-level annotation to all public methods of this class that we did not annotate with @Transactional.
- However, if we put the annotation on a private or protected method, Spring will ignore it without an error.
- Usually it's not recommended to set @Transactional on the interface; however, it is acceptable for cases like @Repository with Spring Data. We can put the annotation on a class definition to override the transaction setting of the interface/superclass:


```java
@Transactional
public interface TransferService {
    void transfer(String user1, String user2, double val);
}
```
- Override the transactional annotation on implementation class
```java
@Service
@Transactional
public class TransferServiceImpl implements TransferService {
    @Override
    public void transfer(String user1, String user2, double val) {
        // ...
    }
}
```
- Now let's override it by setting the annotation directly on the method:
```java
@Transactional
public void transfer(String user1, String user2, double val) {
    // ...
}
```

```java
@Transactional
public class UserService {
   
    public User getUser(String id) {
       // DB call
        
    }    public User saveUser(User user) {
       // DB call
        
    }
```
- Using Transactional annotation on a method also marks that class as transactional
```java
public class UserService {
    @Transactional
    public User getUser(String id) {
       // DB call
        
    }
}
```

- The annotation supports further configuration as well:

1. the Propagation Type of the transaction
2. the Isolation Level of the transaction
3. a Timeout for the operation wrapped by the transaction
4. a readOnly flag – a hint for the persistence provider that the transaction should be read only
5. the Rollback rules for the transaction

---
- https://www.javainuse.com/spring/springtrans
#### Propagation Type of the transaction
  - Transaction Propagation indicates if any component or service will or will not participate in transaction and how will it behave if the calling calling component/service already has or does not have a transaction created already.
  - Propagations
      - REQUIRED (Default)
      - SUPPORTS
      - NOT_SUPPORTED
      - REQUIRES_NEW
      - NEVER
      - MANDATORY
- REQUIRED (Default)
     ![image](https://user-images.githubusercontent.com/69948118/223617183-5ff2acbe-161d-46e5-a424-5aa030327c86.png)
- SUPPORTS
    ![image](https://user-images.githubusercontent.com/69948118/223617230-ef442e06-521e-46ca-936e-5b5179ef5dfc.png)
- NOT_SUPPORTED
    ![image](https://user-images.githubusercontent.com/69948118/223617302-e0f23f73-5ab3-4dc5-8439-15a5c18c3e0c.png)
- REQUIRES_NEW
    ![image](https://user-images.githubusercontent.com/69948118/223617355-ae8f490a-8a35-43fd-8194-b11b793a8d83.png)
    
- NEVER

  ![image](https://user-images.githubusercontent.com/69948118/223617464-b30de1d2-c2de-458d-8f17-e082262ceeb4.png)
  
- MANDATORY
 ![image](https://user-images.githubusercontent.com/69948118/223617514-dc8ee2c7-7e40-4a41-8122-8ec86b51e644.png)
 
 - Summary 
 ![image](https://user-images.githubusercontent.com/69948118/223617680-937686c1-90fb-4a5c-b36f-f21909b953ca.png)

#### Rollback the transaction
- Now, let us talk about the rollback process. If the code segment inside a Transactional method throws a Runtime exception or error, the transaction is automatically rolled back by the framework.
- In the below code snippet, a NullPointerException is thrown by the code which is a Runtime exception and hence the transaction is rolled back
```java
public class UserService {
@Autowired
UserRespository userRepository;
    @Transactional
    public User getUser(String id) throws   UserNotEligibleToDriveException{
        User user = userRepository.get(id);//Assume user object is null
//We get NullPointerException on below lineuser.setMobile("12345678");//below line not executeduserRepository.save(user)}
}
```
- However, the transaction is not rolled back for the Checked Exceptions.
- Consider the following code snippet. In the case of the checked exception below, the user mobile number is set to 12345678 but the transaction is not rolled back because the code throws a Checked Exception.
```java
public class UserService {
@Autowired
UserRespository userRepository;
   @Transactional
    public User getUser(String id) throws   UserNotEligibleToDriveException{
        User user = userRepository.get(id);        user.setMobile("12345678");       //Checked Exception
        if(user.age < 18)
          throw UserNotEligibleToDriveException();        //below line not executed        userRepository.save(user)               
    }
}
```
- Because the user is a persistent entity, hibernate persists the user object to DB and the new phone number is updated, even though the save method is not executed. But this is not the ideal scenario as we should not save any of the info here and fail the transaction.

- We definitely need to roll back the transaction for the above scenario. we can achieve this using rollbackFor attribute.
```java
@Transactional(rollbackFor=UserNotEligibleToDriveException.class)
    public User getUser(String id) throws   UserNotEligibleToDriveException{  //code here}
```
- We can also specify rollbackFor=Exception.class to inform Spring Boot to rollback the transaction for any checked exceptions. In this way, we dont need to specify all the checked exceptions thrown by this method
- Another way is to extend the RunTimeException and we don’t need to specify rollbackFor attribute

#### Transaction Isolation
- As we know, in order to maintain consistency in a database, it follows ACID properties. Among these four properties (Atomicity, Consistency, Isolation, and Durability) Isolation determines how transaction integrity is visible to other users and systems. It means that a transaction should take place in a system in such a way that it is the only transaction that is accessing the resources in a database system. 
- Isolation levels define the degree to which a transaction must be isolated from the data modifications made by any other transaction in the database system. A transaction isolation level is defined by the following phenomena: 
- Transaction Isolation defines the database state when two transactions concurrently act on the same database entity. It involves locking of database records. So it describes the behaviour or state of the database when one transaction is working on database entity and then some other concurrent transaction tries to simultaneously access/edit the same database entity.
- **Dirty Reads** -
- A Dirty read is a situation when a transaction reads data that has not yet been committed. For example, Let’s say transaction 1 updates a row and leaves it uncommitted, meanwhile, Transaction 2 reads the updated row. If transaction 1 rolls back the change, transaction 2 will have read data that is considered never to have existed.
- Suppose two transactions - Transaction A and Transaction B are running concurrently. If Transaction A modifies a record but not commits it. Transaction B reads this record but then Transaction A again rollbacks the changes for the record and commits it. So Transaction B has a wrong value.
- **Non-Repeatable Reads** - Non Repeatable read occurs when a transaction reads the same row twice and gets a different value each time. For example, suppose transaction T1 reads data. Due to concurrency, another transaction T2 updates the same data and commit, Now if transaction T1 rereads the same data, it will retrieve a different value
- **Phantom Reads** -
- Phantom Read occurs when two same queries are executed, but the rows retrieved by the two, are different. For example, suppose transaction T1 retrieves a set of rows that satisfy some search criteria. Now, Transaction T2 generates some new rows that match the search criteria for transaction T1. If transaction T1 re-executes the statement that reads the rows, it gets a different set of rows this time.
-   Suppose two transactions - Transaction A and Transaction B are running concurrently. If Transaction A reads some records. Transaction B adds more such records before transaction A has been committed. So if Transaction A again reads there will be more records than the previous select statement. So same select statements result in different number records to be displayed as new records also get added.
  
- Based on these phenomena, The SQL standard defines four isolation levels:  
   - **READ UNCOMMITTED**: Read Uncommitted is the lowest isolation level. In this level, one transaction may read not yet committed changes made by other transactions, thereby allowing dirty reads. At this level, transactions are not isolated from each other. This can result in dirty reads, non-repeatable reads, and phantom reads.
   - **READ COMMITTED**:  This isolation level guarantees that any data read is committed at the moment it is read. Thus it does not allow dirty read. The transaction holds a read or write lock on the current row, and thus prevents other transactions from reading, updating, or deleting it.
   -  In this isolation level, a transaction can only see changes made by other committed transactions. This eliminates dirty reads but can still result in non-repeatable reads and phantom reads.
   - **REPEATABLE READ**: This is the most restrictive isolation level. The transaction holds read locks on all rows it references and writes locks on referenced rows for update and delete actions. Since other transactions cannot read, update or delete these rows, consequently it avoids non-repeatable read.
   - This isolation level guarantees that a transaction will see the same data throughout its duration, even if other transactions commit changes to the data. However, phantom reads are still possible.
   - **SERIALIZABLE**:
   - This is the highest isolation level. A serializable execution is guaranteed to be serializable. Serializable execution is defined to be an execution of operations in which concurrently executing transactions appears to be serially executing.
   - This is the highest isolation level where a transaction is executed as if it were the only transaction in the system. All transactions must be executed sequentially, which ensures that there are no dirty reads, non-repeatable reads, or phantom reads.
- The choice of isolation level depends on the specific requirements of the application. Higher isolation levels offer stronger data consistency but can also result in longer lock times and increased contention, leading to decreased concurrency and performance. Lower isolation levels provide more concurrency but can result in data inconsistencies.

![image](https://github.com/jdbirla/JD_Spring_Boot_Master/assets/69948118/ca7df2d5-233f-4534-b3cb-66574a5a8a23)

![image](https://user-images.githubusercontent.com/69948118/223618179-5ec21308-a606-4a7d-8277-804ec16436b9.png)

### Lock in DBMS
- Shared Lock: Allows multiple transactions to read a resource simultaneously but prevents any of them from writing to it.
- Exclusive Lock: Grants exclusive access to a resource, preventing other transactions from either reading or writing to it.
- Read Lock: Similar to a shared lock, it allows multiple transactions to read a resource simultaneously but prevents any of them from acquiring a write lock.
- Write Lock: Grants exclusive access to a resource for writing, preventing other transactions from both reading and writing to it.
#### Optimistic vs pessimistic locking
This is an interesting concept. Remember how I mentioned above that all the parallel processes that want to do database transactions will have to sequentially execute them in order to maintain data integrity?

But that can slow things down quite a lot, especially when the transactions involve multiple steps or updates.

So there are two ways of going ahead with it.
**optimistic locking** - One approach is to let the processes update the same records in parallel. Only when one process successfully commits its changes, the other process is told that there exists a conflict due to which the other process will have to attempt the transaction again.
**pessimistic locking** - Another approach is to lock the row as soon as one process attempts to modify it (or delete it) and ask the other processes to wait before doing anything.

### 2. Programmatic Transaction Management
- the Programmatic Transaction Management where developers draw the transaction boundaries inside the service class methods.
- Let us consider the following code snippet. We make some DB calls, then we call external API and then again we make DB calls and then we make an internal API Call.
```java
@Transactional 
public void completeOrder(OrderRequest request) { 
    generateOrder(request); // DB call
    generateInvoice(request); // DB call
    paymentApi(request); // external API call
    savePaymentInfo(request); // DB call
    createSubscription(request) // DB call
    sendInvoiceEmail() // internal API call
}
```
- The @Transactional aspect creates a new EntityManager and starts a new transaction by borrowing one Connection from the connection pool (HikariCP is the default in Spring Boot)

1. The Connection is opened and it starts generating the orders until all the code snippet of the completeOrder method is executed.
2. If there is any delay in the payment API, the connection is on hold and it is not returned to the connection pool
3 .If we have concurrent users doing complete orders, the application will run out of Database Connections resulting in the application lag
4. Hence we should not mix the DB and I/O calls inside a Transactional method
- This is a very good use case for Programmatic Transaction Management and we use TransactionTemplate for this purpose
```java
@Autowired private TransactionTemplate template;
public void completeOrder(OrderRequest request) { template.execute(
    status -> {
       generateOrder(request); // DB call
       generateInvoice(request); // DB call       return request;
    });
   
    paymentApi(request); // external API calltemplate.execute(
    status -> {
       savePaymentInfo(request); // DB call
       createSubscription(request) // DB call        return request;
    });    
    
    sendInvoiceEmail() // internal API call
}
```

- In the above snippet, we did not use @Transactional but we have used TransactionTemplate twice and wrapped the DB operations inside. The following steps are performed

1. The Template starts a new transaction with a borrowed connection and executes generateOrder and generateInvoice in a single transaction.
2. Then the transaction is closed and connection is returned to the connection pool
3. payment API call is made
4. Then again the template starts a new transaction with a borrowed connection and executes savePaymentInfo and createSubscription in a single transaction.
5. Then the transaction is closed and the connection is returned to the connection pool
6. Then the internal API call is made and the response is sent to the user.




---

## Spring Data JPA transaction managment 
- In spring data JPA transaction mangment done by implementation class of JPARepository , which has annotation @Transactional
```java
@Repository
@Transactional(readOnly = true)
public class SimpleJpaRepository<T, ID> implements JpaRepositoryImplementation<T, ID> {
```
---
- https://www.youtube.com/watch?v=b7Pev6i8fso&list=PLyHJZXNdCXsdC-p2186C6NO4FpadnCC_q&index=8&ab_channel=CodeDecode
---
## @Modifying 
`@Modifying` and `@Transactional` are both annotations used in Spring Data JPA, but they serve different purposes.

**@Modifying:**

`@Modifying` is used in Spring Data JPA to indicate that a method is intended to modify the database. It is often used in conjunction with a custom query method or a JPQL (Java Persistence Query Language) query method in a repository interface. When you annotate a method with `@Modifying`, Spring Data JPA understands that this method is not just for querying data but for performing updates, inserts, or deletes. 

Here's an example:

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    
    @Modifying
    @Query("UPDATE User u SET u.active = true WHERE u.id = :userId")
    void activateUser(@Param("userId") Long userId);
}
```

In this example, the `activateUser` method is annotated with `@Modifying`, and it uses a JPQL query to update a user's `active` status in the database. The method will modify data in the database, and the `@Modifying` annotation indicates this to Spring Data JPA.

**@Transactional:**

`@Transactional` is a more general annotation used for managing transactions in Spring. It can be applied to a method or class to specify that the annotated method or all methods in the annotated class should be executed within a transaction. A transaction ensures that a series of database operations are atomic, consistent, and isolated.

Here's an example:

```java
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void activateUser(Long userId) {
        userRepository.activateUser(userId);
        // Other database operations or business logic can go here
    }
}
```

In this example, the `activateUser` method of the `UserService` class is annotated with `@Transactional`. This means that all database operations, including the call to `userRepository.activateUser(userId)`, will be executed within a single transaction. If any part of the transaction fails, all changes will be rolled back, ensuring data consistency.

**When to Use Which:**

- Use `@Modifying` when you have a repository method that directly modifies the database, such as update or delete operations without using the default `save()` method provided by `curdrepository`

- Use `@Transactional` when you want to manage transactions around a set of operations, which may include multiple database operations and other business logic. `@Transactional` ensures that the entire set of operations is atomic.

In some cases, you might use both annotations together. For example, you could have a service method annotated with `@Transactional` that calls a repository method annotated with `@Modifying` to update data and manage the transaction around it.

Remember that `@Transactional` can also be used at the class level, so all methods within the class will have a transaction applied, whereas `@Modifying` is specific to individual methods that perform write operations.

- If you use an update query with `@Query` in Spring Data JPA without the `@Modifying` annotation, it may compile and execute successfully, but it will not update the database as expected. Here's what will happen:

1. Compilation: The code will compile without any issues. Spring Data JPA allows you to define custom queries with `@Query`, even for update or delete operations.

2. Execution: When you call the `activateUser` method, the JPQL query is executed, and it will modify the database in memory, but the changes won't be flushed to the database. In other words, the changes will not be persisted to the underlying database.

3. Transactional Behavior: If this method is called within a transaction (e.g., if the calling method or service method is annotated with `@Transactional`), the changes made by the query will be treated as part of that transaction, but they won't be saved to the database. In the context of a transaction, you might not see the immediate effects of the query, and any changes made will be rolled back when the transaction is committed or rolled back.

In summary, without the `@Modifying` annotation, the update query will not have the desired effect of persisting the changes to the database. To ensure that the changes are actually committed to the database, you should use `@Modifying` along with `@Query` to explicitly inform Spring Data JPA that this method performs a database modification, and the changes should be flushed to the database.

---
## Multiple Data Source in Spring Boot
![image](https://user-images.githubusercontent.com/69948118/224540691-999a5fea-7b41-4887-9bf0-b5bbe4ff8eca.png)
![image](https://user-images.githubusercontent.com/69948118/224541001-29a3471c-cf93-4988-a7f9-c3beb465dbd2.png)
![image](https://user-images.githubusercontent.com/69948118/224541147-a4051988-76ea-4ba0-95b0-a7ace0620a82.png)
![image](https://user-images.githubusercontent.com/69948118/224541225-fba16c50-c97d-4a60-87cb-ab8c650b6fab.png)
![image](https://user-images.githubusercontent.com/69948118/224541317-cb3fdc98-a5bd-46be-86b7-4bd42cdabf2c.png)
![image](https://user-images.githubusercontent.com/69948118/224541342-29fa6a6a-420c-42f9-8bb6-eeb7ee0bbdc6.png)
![image](https://user-images.githubusercontent.com/69948118/224541353-11ebbdbc-c98e-4e6e-b048-5f877b8f3d7e.png)
![image](https://user-images.githubusercontent.com/69948118/224541378-587bb2fc-b965-4aa8-bda4-211ee5fead53.png)
![image](https://user-images.githubusercontent.com/69948118/224541407-c9280d9f-5603-4f48-bdcf-d4701e35bd38.png)
![image](https://user-images.githubusercontent.com/69948118/224541433-1981ceb8-af66-44d3-b2bf-ddde1672e653.png)
![image](https://user-images.githubusercontent.com/69948118/224541498-746ce5b2-2850-4ee0-86dc-ee495ba81b37.png)
![image](https://user-images.githubusercontent.com/69948118/224541792-9262b1a3-68cf-4f41-990a-fa082c4c3a7f.png)
![image](https://user-images.githubusercontent.com/69948118/224541868-105ddc63-01b9-4f78-a31f-73a44a2df51b.png)
![image](https://user-images.githubusercontent.com/69948118/224541995-a1c4f165-4900-4c82-af00-de44028315e0.png)
![image](https://user-images.githubusercontent.com/69948118/224542049-04c9d423-adc5-4f28-b181-0fcb3815a71b.png)
![image](https://user-images.githubusercontent.com/69948118/224542091-0216b620-6b2f-424d-8632-84008576c740.png)

---
## Spring Boot transaction management
In a Spring Boot project, if you don't explicitly use `@Transactional` annotations on your methods, Spring Boot still provides transaction management through the default settings and configurations provided by the Spring framework. This default behavior relies on the underlying Spring framework's support for declarative transaction management.

Here's an explanation of how transaction management works in a Spring Boot project without explicit `@Transactional` annotations:

1. **Declarative Transaction Management:**
   - Spring supports declarative transaction management, where you can define transactional behavior using annotations or XML configurations.
   - In the absence of explicit `@Transactional` annotations, Spring Boot relies on the default transaction management settings defined in the Spring framework.

2. **Default Propagation and Isolation:**
   - Spring Boot uses default propagation behavior (e.g., `Propagation.REQUIRED`) and isolation level (e.g., `Isolation.DEFAULT`) for transactions if not explicitly specified.
   - These defaults ensure that methods run within a transaction, and if a transactional context already exists, the method will join the existing transaction; otherwise, a new transaction will be created.

3. **Rollback Rules:**
   - By default, a runtime exception (unchecked exception) triggers a rollback of the transaction. Checked exceptions do not trigger automatic rollback unless specified.
   - You can customize rollback behavior by using the `@Transactional(rollbackFor = Exception.class)` annotation.

4. **TransactionManager and DataSource Configuration:**
   - Spring Boot automatically configures a `DataSource` bean if a suitable one is found in the classpath. The `DataSource` provides the connection to the database.
   - Spring Boot also configures a `PlatformTransactionManager` bean, typically an instance of `DataSourceTransactionManager`.
   - These configurations can be further customized in the `application.properties` or `application.yml` files.

5. **Transaction Interceptors:**
   - Spring Boot uses AOP (Aspect-Oriented Programming) to intercept method calls annotated with `@Transactional`.
   - When a method annotated with `@Transactional` is called, a transactional aspect is applied, and it begins/commits or rolls back the transaction as needed.

6. **Transaction Proxying:**
   - Spring Boot creates proxy objects around your Spring beans (classes annotated with `@Service`, `@Repository`, etc.).
   - These proxy objects intercept method calls and apply transactional behavior when necessary.

7. **@EnableTransactionManagement:**
   - In a Spring Boot project, `@EnableTransactionManagement` annotation is often included in the main application class or a configuration class. However, it's not mandatory, as Spring Boot enables transaction management by default.

Here's a simple example to illustrate the default transaction management in a Spring Boot project:

```java
@Service
public class MyService {

    @Autowired
    private MyRepository myRepository;

    public void performTransactionalOperation() {
        // This method runs within a transaction due to the default behavior.
        // Changes made to the database will be committed if this method completes successfully.
        myRepository.saveEntity(new MyEntity());
    }
}
```

In summary, Spring Boot provides default transaction management based on the underlying Spring framework. If you don't explicitly use `@Transactional` annotations, the framework uses default settings for transaction propagation, isolation, rollback rules, and other aspects. You can further customize these settings through properties in the `application.properties` or `application.yml` files if needed.

Spring Boot relies on various components from the Spring Framework to implement transaction management. Below are some of the key classes and annotations used internally for transaction management in Spring Boot:

1. **`@Transactional` Annotation:**
   - The `@Transactional` annotation is used at the method level to specify that a method should run within a transactional context. It can be applied to service methods, DAO methods, or any other methods that interact with the database.

2. **`TransactionInterceptor`:**
   - The `TransactionInterceptor` is part of the Spring AOP framework and is responsible for intercepting method calls annotated with `@Transactional`. It applies the necessary transactional behavior, such as beginning, committing, or rolling back transactions.

3. **`PlatformTransactionManager`:**
   - The `PlatformTransactionManager` is an interface in the Spring Framework that defines the methods required for transaction management. In Spring Boot, the default implementation is often `DataSourceTransactionManager` when working with a JDBC DataSource.

4. **`ProxyTransactionManagementConfiguration`:**
   - The `ProxyTransactionManagementConfiguration` class is part of the Spring Framework's configuration for transaction management using proxies. It configures the necessary beans, such as the `TransactionInterceptor` and `PlatformTransactionManager`, to enable transaction management.

5. **`TransactionAspectSupport`:**
   - The `TransactionAspectSupport` class provides support for managing transactions within aspects. It contains methods for beginning, committing, and rolling back transactions.

6. **`TransactionSynchronizationManager`:**
   - The `TransactionSynchronizationManager` is a central helper class that manages transaction synchronization for the current thread. It keeps track of the current transaction status and registered synchronization callbacks.

These classes work together to provide declarative transaction management in Spring Boot. When a method annotated with `@Transactional` is called, the `TransactionInterceptor` intercepts the method invocation, and based on the configured `PlatformTransactionManager`, it manages the transaction lifecycle, including beginning, committing, or rolling back transactions.

While these classes are essential components of the transaction management system, it's important to note that the Spring Framework's transaction management is quite modular and extensible. Depending on the configuration and environment, Spring Boot might use additional classes or different implementations for specific scenarios, such as JTA-based transactions or other transaction manager implementations. The classes mentioned here represent the core components used for the default case with JDBC transactions.

