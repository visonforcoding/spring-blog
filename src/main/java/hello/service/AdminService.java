package hello.service;

import hello.Admin;
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
