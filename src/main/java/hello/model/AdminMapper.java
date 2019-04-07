package hello.model;

import hello.Admin;
import hello.service.AdminService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

@Mapper
public interface AdminMapper {
    @Select("SELECT * FROM ADMIN WHERE name = #{name} LIMIT 1")
    Admin findByName(String name);

    @Select("SELECT * FROM ADMIN WHERE id = #{id}")
    Admin findById(Integer id);


    @SelectProvider(type = AdminService.class,method = "selectAdminLike")
    List<Admin> findBySearch(Admin admin);

    @SelectProvider(type = AdminService.class,method = "countAdminSearch")
    @ResultType(Integer.class)
    int countBySearch(Admin admin);

}
