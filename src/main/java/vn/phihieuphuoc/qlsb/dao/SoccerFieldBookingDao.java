package vn.phihieuphuoc.qlsb.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.table.DefaultTableModel;

import vn.phihieuphuoc.qlsb.entity.SoccerFieldBooking;


public class SoccerFieldBookingDao extends BaseDao{
	private static final String STUDENT_FILE_NAME = "soccerFieldBooking.xml";
	private List<SoccerFieldBooking> listBookings;
	public List<String> listDate = new LinkedList<String>();;
	public List<String> getListDate() throws SQLException {
		listDateOfBooking(listDate);
		return listDate;
	}

	public void setListDate(List<String> listDate) {
		this.listDate = listDate;
	}
	
	public SoccerFieldBookingDao() {

	}

	public int add(SoccerFieldBooking soccerFieldBooking) throws SQLException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM soccerFieldBooking";
		ResultSet rs = stmt.executeQuery(sql);
		int idArray[] = new int[100];
		int i = 0, max = 0;
		while (rs.next()) {
			idArray[i] = rs.getInt("Id");
			if (idArray[i] > max)
				max = idArray[i];
			i++;
		}

		int id = max + 1;
		soccerFieldBooking.setId(id);
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append("INSERT INTO soccerFieldBooking");
		sqlInsert.append(" (");
		sqlInsert.append("dateOfBooking,");
		sqlInsert.append("id,");
		sqlInsert.append("customerName,");
		sqlInsert.append("customerPhoneNumber,");
		sqlInsert.append("fieldBooking,");
		sqlInsert.append("startTime,");
		sqlInsert.append("price");
		sqlInsert.append(")");
		sqlInsert.append(" VALUES ('");
		sqlInsert.append(soccerFieldBooking.getDateOfBooking() + "',");
		sqlInsert.append(soccerFieldBooking.getId() + ",");
		sqlInsert.append("'" + soccerFieldBooking.getCustomerName() + "',");
		sqlInsert.append("'" + soccerFieldBooking.getCustomerPhoneNumber() + "',");
		sqlInsert.append("'" + soccerFieldBooking.getField() + "',");
		sqlInsert.append("'" + soccerFieldBooking.getTimeToStart() + "',");
		sqlInsert.append(soccerFieldBooking.getPrice() + "");
		sqlInsert.append(")");
		int count = stmt.executeUpdate(sqlInsert.toString());
		conn.close();
		return count;
	}

	public List<SoccerFieldBooking> showListBookings() throws SQLException {
		//int x = updateBookingListByDay();
//		listDate = listDateOfBooking(listDate);
//		System.out.println(listDate.size());
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql = "SELECT * FROM soccerFieldBooking ORDER BY dateofbooking ASC";
		ResultSet rs = stmt.executeQuery(sql);
		listBookings = new LinkedList<SoccerFieldBooking>();
		Date tmpDate = null;
		while (rs.next()) {
			Date dateOfBooking = rs.getDate("dateOfBooking");
//			if(tmpDate == dateOfBooking)
//			tmpDate = rs.getDate("dateOfBooking");
			int id = rs.getInt("ID");
			String customerName = rs.getString("customerName");
			String customerPhoneNumber = rs.getString("customerPhoneNumber");
			String field = rs.getString("fieldBooking");
			String timeToStart = rs.getString("startTime");
			long price = rs.getLong("price");

			SoccerFieldBooking soccerFieldBooking = new SoccerFieldBooking(dateOfBooking,id, customerName, customerPhoneNumber, field,
					timeToStart,price);
			listBookings.add(soccerFieldBooking);

		}
		conn.close();
		return listBookings;

	}

	

	public List<SoccerFieldBooking> getListBookings() throws SQLException {
		showListBookings();
		return listBookings;
	}

	/**
	 * cập nhật soccerFieldBooking vào listBookings và lưu listBookings vào file
	 * 
	 * @param soccerFieldBooking
	 * @throws SQLException 
	 */
    public int edit(SoccerFieldBooking soccerFieldBooking) throws SQLException {
    	Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
        StringBuilder sqlInsert = new StringBuilder();
        sqlInsert.append("UPDATE soccerFieldBooking");
        sqlInsert.append(" SET ");
        sqlInsert.append(" dateofbooking = '" + soccerFieldBooking.getDateOfBooking()+"',");
        sqlInsert.append(" customerName ='" + soccerFieldBooking.getCustomerName()+"',");
        sqlInsert.append(" customerPhoneNumber ='" + soccerFieldBooking.getCustomerPhoneNumber()+"',");
        sqlInsert.append(" fieldBooking ='" + soccerFieldBooking.getField()+"',");
        sqlInsert.append(" startTime='" + soccerFieldBooking.getTimeToStart()+ "',");
        sqlInsert.append(" price = " + soccerFieldBooking.getPrice());
        sqlInsert.append(" WHERE ");
        sqlInsert.append(" id = "+soccerFieldBooking.getId());
        int count = stmt.executeUpdate(sqlInsert.toString()); 
        conn.close();
        return count;
    }

	public void setListBookings(List<SoccerFieldBooking> listBookings) {
		this.listBookings = listBookings;
	}

	/**
	 * xóa soccerFieldBooking từ listBookings và lưu listBookings vào file
	 * 
	 * @param soccerFieldBooking
	 * @throws SQLException
	 */
	public int delete(SoccerFieldBooking soccerFieldBooking) throws SQLException {
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append("DELETE FROM soccerFieldBooking");
		sqlInsert.append(" WHERE");
		sqlInsert.append(" id = ");
		sqlInsert.append(soccerFieldBooking.getId() + "");
		int count = stmt.executeUpdate(sqlInsert.toString());
		conn.close();
		return count;
	}
	public int updateBookingListByDay() throws SQLException
	{
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		StringBuilder sqlInsert = new StringBuilder();
		sqlInsert.append("DELETE FROM soccerFieldBooking ");
		sqlInsert.append(" WHERE");
		sqlInsert.append(" dateofbooking < '");
		sqlInsert.append(LocalDate.now() + "'");
		int count = stmt.executeUpdate(sqlInsert.toString());
		conn.close();
		return count;
	}
	public List<String> listDateOfBooking(List<String>listDate) throws SQLException
	{
		
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql = "SELECT dateofbooking FROM soccerFieldBooking GROUP BY dateofbooking";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			listDate.add(rs.getDate("dateofbooking").toString());
			

		}
		conn.close();
		return listDate;
	}
	public List<String> getDataFieldByDateAndTime(String date,String startTime ) throws SQLException
	{
		List<String> listFieldBooking = new LinkedList<String>();
		Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
		Statement stmt = conn.createStatement();
		String sql = "SELECT fieldBooking FROM soccerFieldBooking WHERE dateofbooking= '"+date+"' AND starttime='" + startTime+"'";
		ResultSet rs = stmt.executeQuery(sql);
		while (rs.next()) {
			
			listFieldBooking.add(rs.getString("fieldBooking").toString());

		}
		conn.close();
		return listFieldBooking;
	}
	public List<String> getFieldOfTimeInDate(String dateOfBooking,String timeStart){
		List<String> list=new ArrayList<String>();
		String sql = "SELECT soccerField FROM soccer_field WHERE soccerField "
				+ "NOT IN (SELECT fieldBooking FROM soccerfieldbooking WHERE dateOfBooking="+"'"+dateOfBooking+"' AND starttime="+"'"+timeStart+"') ORDER BY soccerField ASC";
		try {
			
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			while (rs.next()) {
				String soccerField=rs.getString("soccerField");
				list.add(soccerField);
			}
			conn.close();
			stmt.close();
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	public String getPriceByTime(String startTime) {
		String sql="SELECT price FROM time_soccer WHERE startTime="+"'"+startTime+"'";
     try {
			
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			Statement stmt = conn.createStatement();
			ResultSet rs=stmt.executeQuery(sql);
			long price = 0;
			while(rs.next()) {
			 price=rs.getLong("price");
			}
			conn.close();
			stmt.close();
			return Long.toString(price) ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}