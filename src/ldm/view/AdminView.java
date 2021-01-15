package ldm.view;

import ldm.entity.AdminEntity;
import ldm.service.AdminService;

import java.util.List;

public class AdminView {
    public static void main(String[] args){
        AdminEntity admin=new AdminEntity();
        admin.setName("zhangsan");
        admin.setPass("zhangsan");
        if(AdminService.insert(admin)){
            System.out.println("add user success");
        }else{
            System.out.println("add user false");
        }
        List<AdminEntity> li= AdminService.select();
        for (AdminEntity user: li ) {
            System.out.println("id="+user.getId()+",name="+user.getName()+",pass="+user.getPass());
        }
    }
}
