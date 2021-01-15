package ldm.service;

import ldm.data.DataConnetion;
import ldm.entity.AdminEntity;

import java.util.List;

public class AdminService {
    public static boolean insert(AdminEntity admin1){
        String sql="insert into admin(name,pass,phone) values(?,?,?)";
        Object[] para=new Object[]{new String(admin1.getName()), admin1.getPass(),new String(admin1.getPhone())};
        return DataConnetion.request(sql,para);
    }

    public static boolean update(AdminEntity admin){
        String sql="update admin set name=?,pass=?,phone=? where id=?";
        Object[] para=new Object[]{new String(admin.getName()),new String(admin.getPass()),new String(admin.getPhone()),new Long(admin.getId())};
        return DataConnetion.request(sql,para);
    }

    public static boolean del(long id){
        String sql="delete from admin  where id=?";
        Object[] para=new Object[]{new Long(id)};
        return DataConnetion.request(sql,para);
    }

    public static List<AdminEntity> select(){
        String sql="select id,name,pass,phone from admin";
        AdminEntity admin=new AdminEntity();
        return new DataConnetion().query(admin.getClass(),sql,null);
    }
}
