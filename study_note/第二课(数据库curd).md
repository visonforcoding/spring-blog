## 课程目标

1. 完成与mysql的curd操作

## 课程计划

1. 完成博客后台管理员的增删改查操作

## 学习笔记

1、 遇到模板引擎找不到css文件

加入配置spring.mvc.static-path-pattern 即可

```ymal
spring:
  jpa:
    hibernate:
      ddl-auto: update
  datasource:
    url: jdbc:mysql://localhost:3306/db_sptest?useSSL=false
    username: mysqluser
    password: ***
  mvc:
    static-path-pattern: /static/**
```

2、 如何实现jqgrid的列表数据格式

```java

```


## 课程成果

1. 完成jqgrid 列表展示
2. 学会了thymeleaf基础使用和layout
3. 对于查询条件组合的处理还不知道
4. 在考虑jpa和mybatis或querydsl哪种更适合中大企业级业务开发