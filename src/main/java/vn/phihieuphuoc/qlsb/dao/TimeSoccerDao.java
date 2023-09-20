package vn.phihieuphuoc.qlsb.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import vn.phihieuphuoc.qlsb.entity.TimeSoccer;

public class TimeSoccerDao extends BaseDao{
		public List<TimeSoccer> getTimeList(){
			List<TimeSoccer> listTime=new ArrayList<TimeSoccer>();
			 
			String sql = "SELECT * FROM time_soccer ORDER BY startTime ASC";
			try {
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while (rs.next()) {
					String startTime=rs.getString("startTime");
					Long price=rs.getLong("price");
					String shift=rs.getString("shift");
					TimeSoccer time=new TimeSoccer(startTime, price, shift);
					listTime.add(time);
				}
				conn.close();
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return listTime;
		}
		public List<String> getTimeValidOfDay(String dateOfBooking){
			List<String> list=new ArrayList<String>();
			String sql = "SELECT startTime FROM time_soccer"
					+ " WHERE startTime NOT IN (SELECT startTime FROM soccerfieldbooking WHERE dateofbooking="+"'"+dateOfBooking+"')"
					+ " ORDER BY startTime asc";
			try {
				System.out.println(sql);
				Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
				Statement stmt = conn.createStatement();
				ResultSet rs=stmt.executeQuery(sql);
				while (rs.next()) {
					String startTime=rs.getString("startTime");
					list.add(startTime);
				}
				conn.close();
				stmt.close();
				for (String item:list) {
					System.out.println(item);
				}
				return list;
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return null;
			}
			
		}
}
