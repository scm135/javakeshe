package ldm.data;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class DataConnetion<T> {
    private static final String DBDRIVER="ore.gjt.mm.mysql.Driver";
    private static final String DBURL="jdbc:mysql://localhost6/dc";
    private static final String DBUSER="root";
    private static final String DBPASS="admin";
    private static Connection conn=null;

    public static Connection getConn(){
        try {
            Class.forName(DBDRIVER);
            conn= DriverManager.getConnection(DBURL,DBUSER,DBPASS);
            return conn;
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /*
     * @param rs 保存从数据表取加的数据集
     * @param psta 执行sql命令的对象
     * @param conn 连接数据对象
     */
    public static void close(ResultSet rs, PreparedStatement psta,Connection conn){
        try {
            if (rs!=null){
                rs.close();
            }
            if (psta!=null){
                psta.close();
            }
            if (conn!=null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    /*
     * 执行insert、update和delete命令的方法
     * @param sqlcomm sql命令字符串
     * @param para sql命令中参数参数所对应的值
     * @return 执行命令成功返回true，否则返回false
     */
    public static boolean request(String sqlcomm,Object ... para) {
        boolean flag = false;
        try {
            Connection conn = getConn();
            PreparedStatement psta = conn.prepareStatement(sqlcomm);
            if (para==null){
                para=new Object[0];
            }
            for (int i=0;i<para.length;i++){
                psta.setObject(i+1,para[i]);
            }
            int i=psta.executeUpdate();
            if (i>0){
                flag=true;
            }
            close(null,psta,conn);
            return flag;
        } catch (SQLException e) {
            return false;
        }
    }
    /*
     * @param cls 数据表所对应的实体类
     * @param sql 要执行的sql查询命令
     * @param para 要执行的sql命令中参数所对应的值，若没有则为null
     * @return 返回数据集，数据集中的第一个元素都是cls的对象
     */
    public List<T> query(Class<T> cls,String sql,Object ... para){
        List<T> list=new ArrayList<>();
        try {
            conn=getConn();
            PreparedStatement psta=conn.prepareStatement(sql);
            if (para==null){
                para=new Object[0];
            }
            //遍历赋值
            for (int i=0;i<para.length;i++){
                psta.setObject(i+1,para[i]);
            }
            ResultSet rs=psta.executeQuery();
            ResultSetMetaData rsmd=rs.getMetaData();
            while (rs.next()){
                T obj=cls.newInstance();//创建该类的新实例
                int col=rsmd.getColumnCount();//获取列数据，就是属性个数
                for (int i=1;i<=col;i++){
                    String DBField=rsmd.getColumnLabel(i);//取第i列名称
                    Field field=cls.getDeclaredField(DBField);
                    field.setAccessible(true);//私有可见
                    field.set(obj,rs.getObject(i));//第i个列的值赋给field
                }
                list.add(obj);
            }
            close(rs,psta,conn);
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }

}
