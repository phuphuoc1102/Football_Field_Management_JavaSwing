package vn.phihieuphuoc.qlsb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.table.DefaultTableModel;

import vn.phihieuphuoc.qlsb.entity.User;

public class UserDao extends BaseDao{
    public boolean checkUser(User user) throws SQLException {
    	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM user WHERE username='"+user.getUserName()+"' AND password='" + user.getPassword()+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while(rs.next())
		{
			return true;
		}
//        if (user != null) {
//            if ("admin".equals(user.getUserName()) 
//                    && "admin".equals(user.getPassword())) {
//                return true;
//            }
//        }
        return false;
    }
}
