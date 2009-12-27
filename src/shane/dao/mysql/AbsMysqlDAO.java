package shane.dao.mysql;

import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import shane.common.PropertiesManager;

public abstract class AbsMysqlDAO {

	public Connection getConnection() {
		Connection conn = null;
		Properties props = null;
		try {
			props = PropertiesManager.getProperties(
			"shane.properties.mysqlserver");
		} catch (FileNotFoundException ex) {
			System.out.println(ex);
		}
		try {
			Class.forName(props.getProperty("DRIVER"));
			conn = DriverManager.getConnection(props.getProperty("URL"),
                                         props.getProperty("USER_NAME"),
                                         props.getProperty("PASSWORD"));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.println("属性文件处理异常 ");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;

	}

	public void close(ResultSet rs,Statement stm,Connection conn) {
		try {
			if(null!=rs)
				rs.close();
			if(null!=stm)
				stm.close();
			if(null!=conn)
				conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}
	
}
