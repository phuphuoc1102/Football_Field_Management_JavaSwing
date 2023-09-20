package vn.phihieuphuoc.qlsb.dao;

import javax.swing.table.DefaultTableModel;

public class BaseDao {
	static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/projectjava";
	static final String USER = "root";
	static final String PASS = "";
	DefaultTableModel model;
}
