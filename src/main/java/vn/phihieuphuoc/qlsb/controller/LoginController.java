package vn.phihieuphuoc.qlsb.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import vn.phihieuphuoc.qlsb.dao.UserDao;
import vn.phihieuphuoc.qlsb.entity.User;
import vn.phihieuphuoc.qlsb.view.BookingView;
import vn.phihieuphuoc.qlsb.view.LoginView;

public class LoginController {
	private UserDao userDao;
	private LoginView loginView;
	private BookingView bookingView;

	public LoginController(LoginView view) {
		this.loginView = view;
		this.userDao = new UserDao();
		view.addLoginListener(new LoginListener());
	}

	public void showLoginView() {
		loginView.setVisible(true);
	}
	class LoginListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			User user = loginView.getUser();
			try {
				if (userDao.checkUser(user)) {
					// nếu đăng nhập thành công, mở màn hình quản lý sinh viên
					
						bookingView = new BookingView();
						BookingController bookingController = new BookingController(bookingView);
						bookingController.showBookingView();
						loginView.setVisible(false);
				} else {
					loginView.showMessage("username hoặc password không đúng.");
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
}
