package view;

import entity.User;
import java.sql.ResultSet;

public class transit {
    private static int selection = 1;// 默认为1
    private static int pitch;
    private static int function;// 1为修改，0为删除,暂时pass
    private static String employeename;//后续或者可以直接在user里面写属性，因为可能涉及到员工表
    private static String key="faintechoback";//密匙识别是否为boss权限
    private static boolean boss=false;//默认不是boss权限

    ///
    /// 
    /// 
    /// 
    public static boolean isBoss() {
        return boss;
    }

    public static void setBoss(boolean boss) {
        transit.boss = boss;
    }

    //private
    //
    public static int getPitch() {
        return pitch;
    }

    public static void setPitch(int pitch) {
        transit.pitch = pitch;
    }

    public static String getKey() {
        return key;
    }
   
    public static void setEmployeename(String employeename) {
        transit.employeename = employeename;
    }

    public static String getEmployeename() {
        return employeename;
    }

    public static int getSelection() {
        return selection;
    }

    public static int getFunction() {
        return function;
    }

    public static void setFunction(int function) {
        transit.function = function;
    }

    public static String update() {
        switch (getSelection()) {
            case 1:
                return "UPDATE lb_tabl_imp SET sort = ?,`Product_name`= ?,`Amount`=?,`Unite`=?,`Allcost`=?,`Deliver_date`=?,`Deliver_order`=?,`Import_date`=?,`Product_sale`=? ";
            case 2:
                return "UPDATE lb_tabl_deliver SET `Supply_id`=?,`Deliver_order`=?,sort=?,`Product_name`=?,`Amount`=?,`Unite`=?,`Allcost`=?,`Deliver_date`=?";
            case 3:
                return "UPDATE lb_tabl_deal SET `Deliver_order`=?,sort=?,`Product_name`=?,`Amount`=?,`Unite`=?,`Allcost`=?,`Deliver_order`=?,`Deal_date`=?";
            case 4:
                return "UPDATE lb_tabl_client SET `Client_id`=?,`Client_name`=?,`Client_place`=?";
            case 5:
                return "UPDATE lb_tabl_sup SET `Supply_id`=?,`Supply_name`=?,`Supply_place`=?";
            default:
                return null;
        }
    }

    public static User getUser(ResultSet rs) {
        try {

            switch (getSelection()) {
                case 1: {
                    User person = new User(
                            Integer.parseInt(rs.getString(1)),
                            rs.getString(2),
                            rs.getString(3),
                            Integer.parseInt(rs.getString(4)),
                            Integer.parseInt(rs.getString(5)),
                            Integer.parseInt(rs.getString(6)),
                            rs.getDate(7),
                            Integer.parseInt(rs.getString(8)),
                            rs.getDate(9),
                            rs.getBoolean(10));
                    return person;
                }
                case 2: {
                    User person = new User(
                            Integer.parseInt(rs.getString(1)),
                            Integer.parseInt(rs.getString(2)),
                            Integer.parseInt(rs.getString(3)),
                            rs.getString(4),
                            rs.getString(5),
                            Integer.parseInt(rs.getString(6)),
                            Integer.parseInt(rs.getString(7)),
                            Integer.parseInt(rs.getString(8)),
                            rs.getDate(9));
                    return person;
                }
                case 3: {
                    User person = new User(
                            Integer.parseInt(rs.getString(1)),
                            Integer.parseInt(rs.getString(2)),
                            rs.getString(3),
                            rs.getString(4),
                            Integer.parseInt(rs.getString(5)),
                            Integer.parseInt(rs.getString(6)),
                            Integer.parseInt(rs.getString(7)),
                            Integer.parseInt(rs.getString(8)),
                            rs.getDate(9));
                    return person;
                }
                case 4: {
                    User person = new User(
                            Integer.parseInt(rs.getString(1)),
                            Integer.parseInt(rs.getString(2)),
                            rs.getString(3),
                            rs.getString(4));
                    return person;
                }
                case 5: {
                    User person = new User(
                            Integer.parseInt(rs.getString(1)),
                            Integer.parseInt(rs.getString(2)),
                            rs.getString(3),
                            rs.getString(4));
                    return person;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void setSelection(int selection) {
        transit.selection = selection;
        System.out.println(selection);
    }

    public static String tablname() {
        switch (getSelection()) {
            case 1:
                return "imp ";// 此时需要注意空格，sql内tabl部分和where部分都是存在空格，其他地方同理
            case 2:
                return "deliver ";
            case 3:
                return "deal ";
            case 4:
                return "client ";
            case 5:
                return "sup ";
            default:
                return null;
        }
    }

    /* 各个表对应的字段名 */
    public static String[] judgement() {
        switch (getSelection()) {
            case 1: {
                String[] columnNames1 = { "序号", "商品种类", "商品名称", "数量", "单价", "总价", "购入时间", "订单号", "入库时间", "当前状态" };
                return columnNames1;
            }
            case 2: {
                String[] columnNames2 = { "序号", "供货商编号", "订单号", "商品种类", "商品名称", "数量", "单价", "总价", "购入时间" };

                return columnNames2;
            }
            case 3: {
                String[] columnNames3 = { "序号", "售出订单", "商品种类", "商品名称", "数量", "单价", "总价", "购入时间", "售出时间" };

                return columnNames3;
            }
            case 4: {
                String[] columnNames4 = { "序号", "客户编号", "客户名称", "收货地址" };

                return columnNames4;
            }
            case 5: {
                String[] columnNames5 = { "序号", "供货商编号", "供货商名称", "发货地址" };

                return columnNames5;
            }
            default:
                return null;
        }

    }

    public static String getsql() {
        switch (getSelection()) {
            case 1:
                return " lb_tabl_imp(sort,`Product_name`,`Amount`,`Unite`,`Allcost`,`Deliver_date`,`Deliver_order`,`Import_date`,`Product_sale`)"
                        + "values(?,?,?,?,?,?,?,?,?)";
            case 2:
                return " lb_tabl_deliver(Supply_id,Deliver_order,sort,Product_name,Amount,Unite,Allocst,De;iver_date)"
                        + "values(?,?,?,?,?,?,?,?)";
            case 3:
                return " lb_tabl_deal(Deal_order,sort,Product_name,Amount,Unite,Allocst,Deliver_order,Deal_date)"
                        + "values(?,?,?,?,?,?,?,?)";
            case 4:
                return " lb_tabl_client(Client_id,Client_name,Client_place)" + "values(?,?,?)";
            case 5:
                return " lb_tabl_sup(Supply_id,Supply_name,Supply_place)" + "values(?,?,?)";
            default:
                return null;
        }
    }

    public static String[] choice() {
        switch (getSelection()) {
            case 1: {
                String[] columnNames = { "id", "sort", "Product_name", "Deliver_order", "Product_sale" };
                return columnNames;
            }
            case 2: {
                String[] columnNames = { "id", "Supply_id", "Deliver_order", "sort", "Product_name" };
                return columnNames;
            }
            case 3: {
                String[] columnNames = { "id", "Deal_order", "sort", "Product_name", "Product_sale", "Deliver_order" };
                return columnNames;
            }
            case 4: {
                String[] columnNames = { "id", "Client_id", "Client_name", "Client_place" };
                return columnNames;
            }
            case 5: {
                String[] columnNames = { "id", "Supply_id", "Supply_name", "Supply_place" };
                return columnNames;
            }
            default:
                return null;

        }
    }
}
