package ldm.view;

import javax.swing.*;
import java.awt.*;

public class AdminWindow {
    JFrame jf=null;
    JTable jTable=null;
    AdminTableModel adminTableModel=new AdminTableModel();

    public AdminWindow(){
        jf=new JFrame("人事管理系统");
        jf.setSize(400,400);
        jf.setLocationRelativeTo(null);

        jTable=new JTable();
        JScrollPane js=new JScrollPane(jTable);
        JPanel jp=new JPanel(new BorderLayout());
        jp.add(js);

        jf.setContentPane(jp);
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
}
