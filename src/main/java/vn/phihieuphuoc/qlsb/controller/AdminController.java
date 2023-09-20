package vn.phihieuphuoc.qlsb.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.phihieuphuoc.qlsb.dao.SoccerFieldBookingDao;
import vn.phihieuphuoc.qlsb.entity.SoccerFieldBooking;
import vn.phihieuphuoc.qlsb.view.BookingView;

public class AdminController {
	private SoccerFieldBookingDao soccerFieldBookingDao;
	private BookingView bookingView;
	List<String> list = soccerFieldBookingDao.getListDate();
	public AdminController(BookingView view) throws SQLException {
		this.bookingView = view;


		soccerFieldBookingDao = new SoccerFieldBookingDao();
		view.addAddBookingListener(new AddStudentListener());
		view.addEditBookingListener(new EditBookingListener());
		view.addDeleteBookingListener(new DeleteBookingListener());
		view.addClearListener(new ClearStudentListener());
		view.addListBookingSelectionListener(new ListStudentSelectionListener());
	}

	public void showBookingView() throws SQLException {
		List<SoccerFieldBooking> listBookings = soccerFieldBookingDao.getListBookings();
		bookingView.setVisible(true);
		bookingView.showListBookings(listBookings);
		bookingView.showSchedule(list);
	}
	class AddStudentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SoccerFieldBooking soccerFieldBooking = bookingView.getBookingInfo();
			if (soccerFieldBooking != null) {
				try {
					soccerFieldBookingDao.add(soccerFieldBooking);
					bookingView.showMessage("Thêm thành công!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				//bookingView.showBooking(null);
				try {
					
					bookingView.showListBookings(soccerFieldBookingDao.getListBookings());
					bookingView.showSchedule(list);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
	class EditBookingListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SoccerFieldBooking soccerFieldBooking = bookingView.getBookingInfo();
			if (soccerFieldBooking != null) {
				try {
					int count = soccerFieldBookingDao.edit(soccerFieldBooking);
					bookingView.showBooking(soccerFieldBooking);
					bookingView.showListBookings(soccerFieldBookingDao.getListBookings());
					bookingView.showSchedule(list);
					bookingView.showMessage("Cập nhật thành công!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
	class DeleteBookingListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			SoccerFieldBooking soccerFieldBooking = bookingView.getBookingInfo();
			if (soccerFieldBooking != null) {
				try {
					int count = soccerFieldBookingDao.delete(soccerFieldBooking);
					bookingView.clearBookingInfo();
					bookingView.showListBookings(soccerFieldBookingDao.getListBookings());
					bookingView.showSchedule(list);
					bookingView.showMessage("Xóa thành công!");
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}
		}
	}
	class ClearStudentListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			bookingView.clearBookingInfo();
		}
	}

	class ListStudentSelectionListener implements ListSelectionListener {
		public void valueChanged(ListSelectionEvent e) {
			bookingView.fillBookingFromSelectedRow();
		}
	}
}
