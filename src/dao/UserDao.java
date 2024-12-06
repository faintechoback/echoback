package dao;

import entity.User;
import utils.ErrorManager;
import view.transit;

import java.sql.ResultSet;
import java.util.Vector;

public class UserDao {
    SqlManager manage = null;

    public UserDao() {
        // 初始化连接
        manage = SqlManager.createInstance();
        // 连接数据库
        manage.connectDB();
    }

    // 下为登陆方面。不管
    public boolean loginCheck(String username, String password) {
        boolean result = false;
        try {
            String sql = "SELECT * FROM `demo_user` where name=? and password=?";
            // 构造传输的参数
            Object[] params = new Object[] { username, password };
            // 使用jdbc中的查询方法，送入sql和参数，拿到查询的返回值
            ResultSet rs = manage.executeQueryArg(sql, params);
            // 如果返回值中存在数据，返回成功True
            while (rs.next())
                result = true;
            // 关闭数据库
            manage.closeDB();
        } catch (Exception e) {
            // 出现错误时，返回一个错误，描述出错位置和错误本身
            ErrorManager.printError("UserDAO.loginCheck", e);
        }
        return result;
    }

    /*
     * 不论是查询全部还是查询单个，以及删除，此阶段都是直接根据transit内的返回来
     * 实现sql语句的拼接，user对象的创建，可一定上简便一些
     */
    // 查询全部，
    public Vector<User> getAllinfo() {
        Vector<User> result = new Vector<User>();
        try {
            String sql = "SELECT * FROM lb_tabl_" + transit.tablname();
            ResultSet rs = manage.executeQueryArg(sql, null);
            while (rs.next()) {
                
                User person = transit.getUser(rs);
                result.add(person);
            }
        } catch (Exception e) {
            // ErrorManager.printError("UserDAO.getAllUser", e);
            System.out.println("aa");
        }

        return result;
    }

    /* 添加数据 */
    public void increaseUser(Object[] value) {// 此处的参数列表或许有,号的间隔问题，参数个数问题
        String sql = "insert into " + transit.getsql();
        try {
            // 后续可能需要考虑类型不匹配的问题，暂时不用考虑
            boolean affectedRows = manage.insertData(sql, value);
            if (affectedRows) {
                System.out.println("添加成功");
            } else {
                System.out.println("添加失败，请检查输入");
            }
            manage.closeDB();// 关闭连接
        } catch (Exception e) {
            ErrorManager.printError("UserDAO.increaseUser", e);
        }
    }

    /*类似查询部分，但是将结果直接返回，而不是放在user里面*/
    public Object[] getobject() {
        String sql = "SELECT * FROM lb_tabl_" + transit.tablname() +" where id= ? ";
        Object[] params = new Object[]{transit.getPitch()};  // 缺陷，只能找id
        Object[] result = SqlManager.createInstance().getSingleResultAsArray(sql, params);
        if (result != null) {
            return result;
    } else {
        System.out.println("未找到数据");//未找到返回null
    }
        /* String sql = "SELECT * FROM lb_tabl_" + transit.tablname() +" where id= ? ";
        try {
            Object[] params = new Object[] { 1 };
            ResultSet rs = manage.executeQuery(sql, params, 1);
            Object []value = new Object[rs.getMetaData().getColumnCount()];
            //r.next();//因为序号部分不改，所以数组长度始终减一,后移一位
            System.out.println(value.length);
            for (int i = 0; i <= value.length; i++) {
                value[i ] = rs.getObject(i+1); // rs的列索引从1开始，所以要减1来填充数组
            }
            return value;
        } catch (Exception e) {
            ErrorManager.printError("UserDAO.getobject", e);
        }*/
        return null; 
    }
/* 更新数据 */
    public void updateUser(Object[] value) {
        String sql1 =transit.update()+" where id= "+transit.getPitch();
        try {
            if (manage.updateData(sql1, value)) {
                System.out.println("修改成功");
            }
            else{
                System.out.println("修改失败");
            }
        } catch (Exception e) {
            ErrorManager.printError("UserDAO.updateUser", e);
        }
    }

    /* 删除 */
    public void deleteUser(String field, String value) {
        // 依旧是根据参数来识别哪个表
        String sql = "DELETE FROM lb_tabl_" + transit.tablname() + " WHERE " + field + "= ?";
        try {
            // 后续可能需要考虑类型不匹配的问题，暂时不用考虑
            Object[] params = new Object[] { value };
            boolean affectedRows = manage.deleteData(sql, params);// 识别收影响行数
            if (affectedRows) {
                System.out.println("删除成功");
            } else {
                System.out.println("删除失败，未找到指定目标");
            }
            manage.closeDB();// 关闭连接
        } catch (Exception e) {
            ErrorManager.printError("UserDAO.deleteUser", e);
        }
    }

    /**
     * 查询数据库中满足条件的客户
     * 
     * @param field 查询的字段
     * @param value 满足的值
     * @return 查询结果
     *         下面为定值查询部分
     */
    public Vector<User> searchUser(String field, String value) {
        Vector<User> result = new Vector<User>();
        try {
            String sql = "{call  search_" + transit.tablname() + " (?,?)}";
            Object[] params = new Object[] { field, value };
            ResultSet rs = manage.executeQuery(sql, params, 1);
            while (rs.next()) {
                User person = transit.getUser(rs);
                result.add(person);
            }
        } catch (Exception e) {
            ErrorManager.printError("UserDAO.searchUser", e);
        }
        return result;
    }
}
