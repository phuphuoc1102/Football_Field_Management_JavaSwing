package vn.phihieuphuoc.qlsb.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import vn.phihieuphuoc.qlsb.dao.SoccerFieldBookingDao;
import vn.phihieuphuoc.qlsb.entity.SoccerFieldBooking;
import vn.phihieuphuoc.qlsb.view.BookingView;

public class BookingController {
    private SoccerFieldBookingDao soccerFieldBookingDao;
    private BookingView bookingView;

    public BookingController(BookingView view) {
        this.bookingView = view;
        soccerFieldBookingDao = new SoccerFieldBookingDao();
        
        view.checkDateFocusListener(new checkDateFocusListener());
        view.checkTimeItemListener(new checkTimeItemListener());
       // view.checkFieldItemListener(new checkFieldItemListener());
        view.addAddBookingListener(new AddBookingListener());
        view.addEditBookingListener(new EditBookingListener());
        view.addDeleteBookingListener(new DeleteBookingListener());
        view.addClearListener(new ClearStudentListener());
        view.addListBookingSelectionListener(new ListBookingSelectionListener());
    }

    public void showBookingView() throws SQLException {
        List<SoccerFieldBooking> bookingLists = soccerFieldBookingDao.getListBookings();
        System.out.println(bookingLists.size());

        bookingView.setVisible(true);
        bookingView.showListBookings(bookingLists);
        List<String> list = new LinkedList<String>();
		bookingView.showSchedule(soccerFieldBookingDao.listDateOfBooking(list));
		//bookingView.check();
    }

    class AddBookingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SoccerFieldBooking soccerFieldBooking = bookingView.getBookingInfo();
            if (soccerFieldBooking != null) {
                try {
					soccerFieldBookingDao.add(soccerFieldBooking);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
                bookingView.showBooking(soccerFieldBooking);
                try {
                	bookingView.showListBookings(soccerFieldBookingDao.getListBookings());
                	
                	List<String> list = new LinkedList<String>();
					bookingView.showSchedule(soccerFieldBookingDao.listDateOfBooking(list));
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				};
				bookingView.showMessage("Thêm thành công!");
            }
        }
    }
    class EditBookingListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            SoccerFieldBooking soccerFieldBooking = bookingView.getBookingInfo();
            if (soccerFieldBooking != null) {
                try {
					soccerFieldBookingDao.edit(soccerFieldBooking);
					bookingView.showBooking(soccerFieldBooking); 
					bookingView.showListBookings(soccerFieldBookingDao.getListBookings());
					List<String> list = new LinkedList<String>();
					bookingView.showSchedule(soccerFieldBookingDao.listDateOfBooking(list));
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
					soccerFieldBookingDao.delete(soccerFieldBooking);
					bookingView.clearBookingInfo();
					bookingView.showListBookings(soccerFieldBookingDao.getListBookings());
					List<String> list = new LinkedList<String>();
					bookingView.showSchedule(soccerFieldBookingDao.listDateOfBooking(list));
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
    class ListBookingSelectionListener implements ListSelectionListener {
        public void valueChanged(ListSelectionEvent e) {
        	bookingView.fillBookingFromSelectedRow();
        }
    }
    class checkDateFocusListener implements FocusListener{


		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			
		}

	
		public void focusLost(FocusEvent e) {
			bookingView.checkField();
		}
    	
    }
    class checkTimeItemListener implements ItemListener{

	
		public void itemStateChanged(ItemEvent e) {
			bookingView.checkField();
			
		}

    }
    class checkFieldItemListener implements ItemListener{


		public void itemStateChanged(ItemEvent e) {
			bookingView.checkField();
			
		}

    }
}
