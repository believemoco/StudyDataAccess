package MyJDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HelloJDBC {
	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static String DB_URL = "JDBC:mysql://127.0.0.1:3306/cloud_study";
	static final String USER = "root";
	static final String PASSWORD = "123";

	public static void helloworld() throws ClassNotFoundException {
		Connection conn = null;
		Statement stmt = null;
		// PreparedStatement ptmt=null;
		ResultSet rs = null;

		// 1.首先我们要装载驱动程序
		Class.forName(JDBC_DRIVER);
		// 2.建立到后端的数据库的连接
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);

			// 3.执行SQL语句
			stmt = conn.createStatement();// 在javaweb中要执行SQL语句首先 要创建Statement对象
			
			rs = stmt.executeQuery("select * from course where UserName='ZhangSan'");

			// 4.获取执行结果
			while (rs.next()) {

				System.out.println("学生" + rs.getString("UserName") + "所学课程：" + rs.getString("CourseName"));

			}

		} catch (SQLException e) {
			// 异常处理：
			e.printStackTrace();// 打印捕获到的异常
		} finally {
			// 5.清理资源
			try {
				if (conn != null)
					conn.close();
				if (stmt != null)
					stmt.close();
				if (rs != null)
					rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public static void main(String[] args) throws ClassNotFoundException {
		helloworld();
	}

}
