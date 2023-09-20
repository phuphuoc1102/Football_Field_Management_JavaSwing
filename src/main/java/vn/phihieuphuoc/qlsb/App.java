package vn.phihieuphuoc.qlsb;

import java.awt.EventQueue;

import vn.phihieuphuoc.qlsb.controller.LoginController;
import vn.phihieuphuoc.qlsb.dao.TimeSoccerDao;
import vn.phihieuphuoc.qlsb.view.LoginView;

public class App {
    public static void main(String[] args) {
    	
        EventQueue.invokeLater(new Runnable() {
            public void run() {
            	
                LoginView view = new LoginView();
                LoginController controller = new LoginController(view);
                // hiển thị màn hình login
                controller.showLoginView();
            }
        });
    }
}