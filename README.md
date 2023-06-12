# servisimo-frontend  

## servisimo-frontend (Backend + API)  

Simple Java demo app to simulate backend and API.  
Main functionality is to get tickets from DB and provide parametrized search  

Viewer backend is available at https://github.com/smonko/servisimo-viewer  
Write backend is available at https://github.com/smonko/servisimo-putter  
Catalog backend is available at https://github.com/smonko/servisimo-catalog  

### Build with:  
- SpringBoot 3.0.6  
- Java JDK 17  

### How to use  

Build with maven  
```
$ mvn clean install -U 
```

Run  
```
java -jar servisimo-frontend-0.0.1-SNAPSHOT.jar
```

Run with config  
```
java -jar servisimo-frontend-0.0.1-SNAPSHOT.jar --spring.config.location=../config/application.yml
```

### Feature flags  
Configuration options to simulate featurea flags and toogle some functionality  

```
features:
  canaryflag: true
  reportticket: true
  advancefilter: false
  cataloglist: true
```

**canaryflag** - no real function. Just to demonstrate canary deployment. Check */feature-flags*  
**advancefilter** - activate advanced filtering for tickets  
**reportticket** - activate ticket reporting form  
**cataloglist** - activate catalog browser  

### Service Discovery  
By default application will register to consul agent. To disable this function modify application.yml:  
```
spring:
  ...
  cloud:
    consul:
      enabled: false
      ...
      discovery:
        enebled: false
        ...
```


### Copyrights  
(C) Stefan Monko (stefan@stefanmonko.sk / monkos@posam.sk)  