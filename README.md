# java-common-utils
java common utils library

一个简单的Java通用工具类，目前的设想，包括简化异常处理工具、简易限流处理工具等

ExceptionHandler, 目标简化try catch的代码冗余度

```java
try {
  // dosomething...
} catch (Exception e) {
  log.error(e);
}
```
简化为
```java
ExceptionHandler.ignoreExceptionWithErrorMsg(() -> {
  // dosomething....            
}, "exec error");
```

```java
try {
  // dosomething...
} catch (Exception e) {
  log.error(e);
  throws new MyException("执行错误");
}
```
简化为
```java
ExceptionHandler.runtimeException(() -> {
   // dosomething...
}, "exec error");
```

```java
try {
  // dosomething...
} catch (Exception e) {
  log.error(e);
  // if exception happen, then exec something...
  // dosomething
  log.error("exec error");
}
```
简化为
```java
ThrowsHandler.run(() -> {
    // dosomething...
   })
   .whenExceptionThen(() -> //dosomethings...)
   .logThrowable("exec error");
```

```java
try {
  // dosomething...
} catch (Exception e) {
  log.error(e);
  // if exception happen, then exec something...
  // dosomething
  log.error("exec error");
  throws new MyException("exec error");
}
```
简化为
```java
ThrowsHandler.run(() -> {
    // dosomething...
   })
   .whenExceptionThen(() -> //dosomethings...)
   .runtimeExp("exec error");
```
