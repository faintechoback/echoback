package view;

import java.util.Vector;

import javax.swing.table.AbstractTableModel;

import entity.User;

public class MyTableModel extends AbstractTableModel {//表的创建，只是简单的将原demo中分离，为了方便后续的增改操作
    Vector<User> customerVector = new Vector<User>();
    // 表的构造，需要定义当前的表格组件、表头信息等
    private String[] columnNames = transit.judgement();

    // 表格大小、长度、第几行数据、更新等方法，不再一一列取
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return customerVector.size();
    }

    public Object getValueAt(int row, int col) {
        User customer = customerVector.get(row);
        return customer.getCol(col);
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    // 更新数据
    public void updateData(Vector<User> customerVector) {
        this.customerVector = customerVector;
        if (customerVector.size() == 0) {
            customerVector = new Vector<User>();
        } else {
            fireTableRowsInserted(0, customerVector.size() - 1);
        }//如果我还想写个员工表的话可能对原本的user属性还需要多加，因为如果自己多写一个类的话，类型并不匹配
    }

}
