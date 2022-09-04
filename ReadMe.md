## Selenium Waits

There are 2 kind of wait 
- Implicit wait 
  They are added on global/driver level 
```java
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
```
- Explicit wait 