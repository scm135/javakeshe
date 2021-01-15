package ldm.view;

import ldm.entity.AdminEntity;
import ldm.service.AdminService;

import javax.swing.table.AbstractTableModel;
import java.util.List;
import java.util.Vector;

public class AdminTableModel extends AbstractTableModel {
    Vector col;//存放数据表列标题，要根据数据库数据表的字段决定的
    Vector rowData;//存放数据表的数据行，是根据数据表记录数决定
    Vector row;//存放数据表中的某一行数据

    public AdminTableModel(){
        initCol();
        initRowData();
    }

    private void  initCol(){
        col=new Vector();
        col.add("id");
        col.add("name");
        col.add("pass");
        col.add("phone");
    }

    private void initRowData() {
        //通过数据表users的业务类UsersService.getData()方法获取数据表的所有记录
        List<AdminEntity> li = AdminService.select();
        //创建数据表数据行的对象
        rowData = new Vector();
        //利用遍历方式把数据表中记录逐条读取出来放到row对象中，然后再把放到rowData对象中构成二维表
        for (AdminEntity ue : li) {
            row = new Vector();
            row.add(ue.getId());
            row.add(ue.getName());
            row.add(ue.getPass());
            row.add(ue.getPhone());
            rowData.add(row);
        }
    }



        @Override
    public int getRowCount() {
        return rowData.size();
    }

    @Override
    public int getColumnCount() {
        return col.size();
    }

    @Override
    public Object getValueAt(int i, int i1) {
        return ((Vector)rowData.get(i)).get(i1);
    }

    @Override
    public String getColumnName(int column) {
        return col.get(column).toString();
    }
}
