/*
 * 数据库操作类，进行数据库底层操作
 * 配置信息在Config.properties文件中
 * Made By:coolszy
 * 2009.07.07
 */

package dao;

import javax.swing.*;
import java.sql.*;
import java.util.PropertyResourceBundle;

public class SqlManager
{
	//定义常量
	private final int PSTM_TYPE=0;
	private final int CALL_TYPE=1;

	private static SqlManager manager = null; //静态成员变量，支持单态模式
	private PropertyResourceBundle bundle; //配置资源文件
	private static String jdbcDrive = null; //JDBC驱动类型
	private String DBhost = ""; //数据库主机地址
	private String DBname = ""; //数据库名
	private String DBprot = ""; //数据库端口
	private String DBuser = ""; //数据库用户名
	private String DBpasswd = ""; //数据库密码
	private String strcon = null; //连接字符串

	private Connection conn = null; //连接对象
	private PreparedStatement pstm = null;
	private CallableStatement cstm = null;

	/**
	 *   私有构造函数,不可实例化
	 */
	private SqlManager()
	{
		try
		{
			// 读取配置文件
			bundle = new PropertyResourceBundle(SqlManager.class
					.getResourceAsStream("db.properties"));
			this.DBhost = getString("DBhost"); //读取主机名
			this.DBname = getString("DBname"); //读取用户名
			this.DBprot = getString("DBport"); //读取端口
			this.DBuser = getString("DBuser"); //读取用户
			this.DBpasswd = getString("DBpassword"); //读取密码
			String databese_type = getString("database-type"); //读取数据库类型
			if (databese_type != null) //如果类型不为空
			{
				if (databese_type.toLowerCase().equals("mysql"))
				{ // 设置mysql数据库的驱动程序和连接字符
					jdbcDrive = "com.mysql.jdbc.Driver";
					strcon = "jdbc:mysql://" + DBhost + ":" + DBprot + "/"
							+ DBname;
				}
				else
				if (databese_type.toLowerCase().equals("oracle"))
				{ // 设置oracle数据库的驱动程序和连接字符
					jdbcDrive = "oracle.jdbc.driver.OracleDrive";
					strcon = "jdbc:oracle:thin:@" + DBhost + ":" + DBprot
							+ ":" + DBname;
				}
				else
				if (databese_type.toLowerCase().equals("sqlserver2000"))
				{ // 设置sqlserver2000数据库的驱动程序和连接字符
					jdbcDrive = "com.microsoft.jdbc.sqlserver.SQLServerDriver";
					strcon = "jdbc:micorsoft:sqlserver://" + DBhost
							+ ":" + DBprot + ";DatabaseName=" + DBname;
				}
				else
				if (databese_type.toLowerCase().equals(
						"sqlserver2005"))
				{ // 设置sqlserver2005数据库的驱动程序和连接字符
					jdbcDrive = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
					strcon = "jdbc:sqlserver://" + DBhost + ":"
							+ DBprot + ";DatabaseName=" + DBname;
				}
			}
		}
		catch (Exception e)
		{
			System.err.println("SqlManager Error!" + e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 *  读取配置文件中的值
	 * @param key 配置文件的key
	 * @return  key对应的值
	 */
	private String getString(String key)
	{
		return this.bundle.getString(key);
	}

	/**
	 * 单态模式获取实例
	 * @return SqlManager对象
	 */
	public static SqlManager createInstance()
	{
		if (manager == null)
		{
			manager = new SqlManager();
			manager.initDB();
		}
		return manager;
	}

	/**
	 *  初始化连接参数，由指定的DBType生成
	 */
	public void initDB()
	{
		try
		{
			Class.forName(jdbcDrive);
		}
		catch (Exception e)
		{
			System.err.println("initDB Error!" + e.getMessage());
			e.printStackTrace();
		}
		System.out.println("成功加载驱动类！");
	}

	/**
	 *  连接数据库
	 */
	public void connectDB()
	{
		try
		{
			conn = DriverManager.getConnection(strcon, DBuser, DBpasswd); // 获取连接
			conn.setAutoCommit(false); //设置自动提交为false
		}
		catch (SQLException e)
		{
			if(e.getErrorCode() == 0){
				JOptionPane.showMessageDialog(null, "数据库未启动或驱动未加载！","警告",JOptionPane.WARNING_MESSAGE);
			}
			if(e.getErrorCode() == 1045){
				JOptionPane.showMessageDialog(null, "数据库用户名密码错误！","警告",JOptionPane.WARNING_MESSAGE);
			}
			if(e.getErrorCode() == 1049){
				JOptionPane.showMessageDialog(null, "数据库名称错误！","警告",JOptionPane.WARNING_MESSAGE);
			}
			System.err.println(e.getErrorCode()+"connectDB Error!" + e.getMessage());
			e.printStackTrace();
			return;
		}
		System.out.println("DB connect success！");
	}

	/**
	 *  断开数据库
	 */
	public void closeDB()
	{
		try
		{
			if (pstm != null)
			{
				pstm.close();
			}
			if (cstm != null)
			{
				cstm.close();
			}
			if (conn != null)
			{
				conn.close();
			}
		}
		catch (Exception e)
		{
			System.err.println("closeDB Error!" + e.getMessage());
			e.printStackTrace();
			return;
		}
		finally
		{
			pstm = null;
			cstm = null;
			conn = null;
		}
		System.out.println("db close success！");
	}

	/**
	 * 设置PrepareStatement对象中Sql语句中的参数
	 * @param sql sql语句
	 * @param params 参数列表
	 */
	private void setPrepareStatementParams(String sql, Object[] params)
	{
		try
		{
			pstm = conn.prepareStatement(sql); //获取对象
			if (params != null)
			{
				for (int i = 0; i < params.length; i++) // 遍历参数列表填充参数
				{
					pstm.setObject(i + 1, params[i]);
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("setPrepareStatementParams Error!"+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 设置CallableStatementParams对象中sql语句中的参数
	 * @param sql sql语句
	 * @param params 参数列表
	 */
	private void setCallableStatementParams(String sql, Object[] params)
	{
		try
		{
			cstm = conn.prepareCall(sql); //获取CallableStatement对象
			if (params != null)
			{
				for (int i = 0; i < params.length; i++) // 遍历数组填充参数
				{
					cstm.setObject(i + 1, params[i]);
				}
			}
		}
		catch (SQLException e)
		{
			System.err.println("setCallableStatementParams Error!"+ e.getMessage());
			e.printStackTrace();
		}
	}

	/**
	 * 执行带参数查询
	 * @param sql sql语句
	 * @param params 参数列表
	 */
	public ResultSet executeQueryArg(String sql, Object[] params){
		return this.executeQuery(sql,params,PSTM_TYPE);
	}

	//删除操作
	public boolean deleteData(String sql, Object[] params) {
		return executeUpdate(sql, params, PSTM_TYPE);
	}
	//添加操作，后续可全删减至update操作部分
	public boolean insertData(String sql, Object[] params) {
		return executeUpdate(sql, params, PSTM_TYPE);
	}
	//更新操作，需要嵌套单个查询
	public boolean updateData(String sql, Object[] params) {
		return executeUpdate(sql, params, PSTM_TYPE);
	}
	public boolean getData(String sql, Object[] params) {
		return executeUpdate(sql, params, PSTM_TYPE);
	}

	/**
	 * 执行查询
	 * @param sql sql语句
	 * @param params 参数列表
	 * @param type sql语句的类型
	 * @return 返回ResultSet类型的查询结果
	 */
	public ResultSet executeQuery(String sql, Object[] params, int type)
	{ // 执行查询数据库接口
		ResultSet rs = null;
		try
		{
			switch (type)
			//判断是PrepareStatement还是CallableStatement
			{
				case PSTM_TYPE:
					manager.setPrepareStatementParams(sql, params); //填充参数
					rs = pstm.executeQuery(); // 执行查询操作
					break;
				case CALL_TYPE:
					manager.setCallableStatementParams(sql, params); //填充参数
					rs = cstm.executeQuery(); //执行查询
					break;
				default:
					throw new Exception("不存在该选项");
			}
		}
		catch (Exception e)
		{
			System.err.println("executeQuery Error!" + e.getMessage());
			e.printStackTrace();
		}
		return rs;
	}

	/**
	 * 更新数据库操作
	 * @param sql sql语句
	 * @param params 参数列表
	 * @param type sql语句的类型
	 * @return 执行操作的结果
	 */
	public boolean executeUpdate(String sql, Object[] params, int type) // 执行无返回数据的数据查询，返回值是被改变的书库的数据库项数
	{
		boolean result=false;
		try
		{
			switch (type)
			//判断是PrepareStatement还是CallableStatement
			{
				case PSTM_TYPE:
					manager.setPrepareStatementParams(sql, params); //填充参数
					pstm.executeUpdate(); // 执行更新
					manager.commitChange();
					result=true;
					break;
				case CALL_TYPE:
					manager.setCallableStatementParams(sql, params); //填充参数
					cstm.executeUpdate(); // 执行更新
					manager.commitChange();
					result=true;
					break;
				default:
					throw new Exception("不存在该选项");
			}
		}
		catch (Exception e)
		{
			System.err.println("executeUpdate Error!:" + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 提交信息到数据库
	 * @throws SQLException
	 */
	private void commitChange() throws SQLException
	{
		try
		{
			conn.commit();
			System.out.println("data submit success！");
		}
		catch (Exception e)
		{
			System.out.println("CommitChange Error!" + e.getMessage());
			e.printStackTrace();
		}
	}
	public Object[] getSingleResultAsArray(String sql, Object[] params) {
		ResultSet rs = null;
		Object[] result = null;
		try {
			// 执行查询，获取ResultSet
			rs = this.executeQuery(sql, params, PSTM_TYPE);
			
			if (rs != null && rs.next()) {
				// 获取列数
				int columnCount = rs.getMetaData().getColumnCount();
				
				// 创建一个与列数相同大小的Object数组
				result = new Object[columnCount];
				
				// 将每一列的数据存入数组
				for (int i = 1; i <= columnCount; i++) {
					result[i - 1] = rs.getObject(i); // rs的列索引从1开始，所以要减1来填充数组
				}
			}
		} catch (SQLException e) {
			System.err.println("getSingleResultAsArray Error! " + e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
}
