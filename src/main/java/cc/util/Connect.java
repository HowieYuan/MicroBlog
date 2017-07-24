package cc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

//进行数据库连接
public class Connect {
	public static Connection getCon() throws SQLException, ClassNotFoundException{
		String name="com.mysql.jdbc.Driver";
		Class.forName(name); 
		Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/db_vip?useUnicode=true&characterEncoding=UTF-8&serverTimezone=GMT", "root", "980509");
		return con;
	}
	
	//关闭数据库连接
	public static void closeCon(PreparedStatement pstmt,Connection con) throws Exception{
		if (pstmt != null) {
			pstmt.close();
			if (con != null) {
				con.close();
			}
		}
	}
}
