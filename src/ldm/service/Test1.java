package ldm.service;

import ldm.entity.AdminEntity;

import java.util.List;

public class Test1 {
    public static void main(String [] args){
       AdminEntity admin=new AdminEntity();
        admin.setName("xx");
        admin.setPass("987");
        if (AdminService.insert(admin)){
            System.out.println("添加成功");
        }else {
            System.out.println("添加失败");
        }
        List<AdminEntity> list=AdminService.select();
        for (AdminEntity us:list){
            System.out.println("id="+us.getId()+"name="+us.getName()+"pass="+us.getPass());
        }

    }

}
