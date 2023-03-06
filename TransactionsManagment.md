# Transaction Management in Spring Boot



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
