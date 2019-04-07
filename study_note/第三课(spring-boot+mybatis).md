## 课程目标

1. 完成与spring boot 与mybatis的集成处理数据curd

## 课程计划

1. 使用mybatis完成博客后台管理员列表的jqgird搜索

## 课程分析

> 想要完成列表的搜索，就必须对sql按提交搜索条件进行逻辑判断组织sql,也就是动态sql

## 步骤

### 1.加入依赖

```groovy
// build.gradle
dependencies {
    compile("org.springframework.boot:spring-boot-starter-web")
    compile("org.springframework.boot:spring-boot-starter-thymeleaf")
    compile("org.springframework.boot:spring-boot-devtools")
    // JPA Data (We are going to use Repositories, Entities, Hibernate, etc...)
    compile 'org.springframework.boot:spring-boot-starter-data-jpa'
    // Use MySQL Connector-J
    compile 'mysql:mysql-connector-java'
    // 使用mybatis
    compile("org.mybatis.spring.boot:mybatis-spring-boot-starter:1.3.2")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testCompile("junit:junit")
}
```

### 2. 配置mybatis

```yaml
spring:
  jpa:
    hibernate:
      ddl-auto: update
  datasource:
    url: jdbc:mysql://localhost:3306/db_sptest?useSSL=false
    username: mysqluser
    password: mysqlpwd
  mvc:
    static-path-pattern: /static/**
mybatis:
  type-aliases-package: hello.model
```

### 3. 使用mybatis mapper

```java
// model/AdminMapper
@Mapper
public interface AdminMapper {
    //使用注解方式
    @Select("SELECT * FROM ADMIN WHERE name = #{name} LIMIT 1")
    Admin findByName(String name);

    @Select("SELECT * FROM ADMIN WHERE id = #{id}")
    Admin findById(Integer id);

    //动态sql
    @SelectProvider(type = AdminService.class,method = "selectAdminLike")
    List<Admin> findBySearch(Admin admin);

    //动态sql 返回行数
    @SelectProvider(type = AdminService.class,method = "countAdminSearch")
    @ResultType(Integer.class)
    int countBySearch(Admin admin);

}
```

### 4.编写动态sql语句

```java
// service/AdminService
import org.apache.ibatis.jdbc.SQL;

public class AdminService {

    // With conditionals (note the final parameters, required for the anonymous inner class to access them)
    public String selectAdminLike(Admin admin) {
        return new SQL() {{
            SELECT("A.name,A.email,A.id");
            FROM("ADMIN A");
            if (admin.getName() != null) {
                WHERE("A.name = '" + admin.getName() + "'");
            }
            if (admin.getEmail() != null) {
                WHERE("A.email = " + admin.getEmail());
            }
        }}.toString();
    }

    public String countAdminSearch(Admin admin){
        return new SQL() {{
            SELECT("count(*)");
            FROM("ADMIN A");
            if (admin.getName() != null) {
                WHERE("A.name = '" + admin.getName() + "'");
            }
            if (admin.getEmail() != null) {
                WHERE("A.email = " + admin.getEmail());
            }
        }}.toString();
    }
}
```

### 5.使用mybatis查询方法

```java
    // AdminController
    @GetMapping(path = "get_list")
    @ResponseBody
    public DataResponse<Admin> getAdminList(
            Admin adminReq,
            @RequestParam(defaultValue = "1", value = "page") String page,
            @RequestParam(defaultValue = "10", value = "rows") String rows) {
        String total; //页数
        List<Admin> admin_list;
        int records;
        records = adminMapper.countBySearch(adminReq);
        int pageSize = Integer.valueOf(rows);
        total = Integer.toString((records + pageSize - 1) / pageSize);
        DataResponse<Admin> response = new DataResponse<>();
        admin_list = adminMapper.findBySearch(adminReq);
        response.setTotal(total);
        response.setRows(admin_list);
        response.setPage(page);
        response.setRecords(records);
        return response;
    }

```

## 课程成果

![](http://img.rc5j.cn/blog20190407114858.png)

1. 完成使用jqgrid+spring boot+mybatis 的数据列表搜索
2. 遗留page 和 pageSize 的传参控制，下节课对代码进行稍微的改动就可以支持
3. 目前使用了jpa的Hibernate entity 这么用合理么？