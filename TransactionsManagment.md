# Transaction Management in Spring Boot

## Transaction Management in Spring framwork 
[https://medium.com/javarevisited/transaction-management-in-spring-boot-eb01e20b21fe]
[https://www.baeldung.com/spring-transactional-propagation-isolation]
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

#### Rollback the transaction
- Now, let us talk about the rollback process. If the code segment inside a Transactional method throws a Runtime exception or error, the transaction is automatically rolled back by the framework.
- In the below code snippet, a NullPointerException is thrown by the code which is a Runtime exception and hence the transaction is rolled back
```java
public class UserService {@Autowired
UserRespository userRepository;@Transactional
    public User getUser(String id) throws   UserNotEligibleToDriveException{
        User user = userRepository.get(id);//Assume user object is null
//We get NullPointerException on below lineuser.setMobile("12345678");//below line not executeduserRepository.save(user)}
}
```
- However, the transaction is not rolled back for the Checked Exceptions.
- Consider the following code snippet. In the case of the checked exception below, the user mobile number is set to 12345678 but the transaction is not rolled back because the code throws a Checked Exception.
```java
public class UserService {@Autowired
UserRespository userRepository;@Transactional
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

## JDBC
```java
Class.forName("com.mysql.jdbc.Driver");  
Connection con=DriverManager.getConnection( "jdbc:mysql://localhost:3306/sonoo","root","root");  //here sonoo is database name, root is username and password  
Statement stmt=con.createStatement();  
ResultSet rs=stmt.executeQuery("select * from emp");  
while(rs.next())  
System.out.println(rs.getInt(1)+"  "+rs.getString(2)+"  "+rs.getString(3));  
con.close();  
```

## Spring JDBC template
```java
int result = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM EMPLOYEE", Integer.class);

```

## JPA
```java
EntityManagerFactory factory = Persistence.createEntityManagerFactory("UsersDB");     
EntityManager entityManager = factory.createEntityManager();           
entityManager.getTransaction().begin();                  
User newUser = new User();    
newUser.setEmail("billjoy@gmail.com");    
newUser.setFullname("bill Joy");      
newUser.setPassword("billi");            
entityManager.persist(newUser);             
entityManager.getTransaction().commit();             
entityManager.close();       
factory.close();

```

## Hibernate
```java
StandardServiceRegistry ssr = new StandardServiceRegistryBuilder().configure("hibernate.cfg.xml").build();    
Metadata meta = new MetadataSources(ssr).getMetadataBuilder().build();  
SessionFactory factory = meta.getSessionFactoryBuilder().build(); 
Session session = factory.openSession();  
Transaction t = session.beginTransaction();            
Employee e1=new Employee();    
e1.setId(101);       
e1.setFirstName("Gaurav");       
e1.setLastName("Chawla");             
session.save(e1);     
t.commit();     
System.out.println("successfully saved");   
factory.close();    
session.close();   

```

## Spring Data JPA
```java
public interface CustomerRepository extends CrudRepository<Customer, Long>
{     
List<Customer> findByLastName(String lastName); 
} 

private CustomerRepository repository;     
public void test() {  
// Save a new customer       
Customer newCustomer = new Customer();      
newCustomer.setFirstName("John");       
newCustomer.setLastName("Smith");               
repository.save(newCustomer);              
// Find a customer by ID        
Optional<Customer> result = repository.findById(1L);  
result.ifPresent(customer -> System.out.println(customer));            
// Find customers by last name     
List<Customer> customers = repository.findByLastName("Smith");      
customers.forEach(customer -> System.out.println(customer));            
// List all customers         
Iterable<Customer> iterator = repository.findAll();     
iterator.forEach(customer -> System.out.println(customer));           
// Count number of customer    
long count = repository.count();    
System.out.println("Number of customers: " + count);
```
