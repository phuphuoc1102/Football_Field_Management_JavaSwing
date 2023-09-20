package vn.phihieuphuoc.qlsb.view;

import java.awt.Checkbox;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;
import java.awt.event.ItemListener;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import vn.phihieuphuoc.qlsb.dao.SoccerFieldBookingDao;
import vn.phihieuphuoc.qlsb.dao.TimeSoccerDao;
import vn.phihieuphuoc.qlsb.entity.SoccerFieldBooking;
import vn.phihieuphuoc.qlsb.entity.TimeSoccer;

public class BookingView extends JFrame implements ActionListener, ListSelectionListener {
	private static final long serialVersionUID = 1L;
	private TimeSoccerDao soccerDao=new TimeSoccerDao();
	private SoccerFieldBookingDao fieldBookingDao=new SoccerFieldBookingDao();
	private JButton addReserve;
	private JButton editReserveBtn;
	private JButton deleteReserveBtn;
	private JButton clearBtn;
	private JScrollPane jScrollPaneReserveTable;
	private JScrollPane jScrollPaneScheduleTable;
	private JTable reserveTable, scheduleTable;

	private JLabel dateLabel;
	private JLabel idLabel;
	private JLabel nameLabel;
	private JLabel phoneLabel;
	private JLabel fieldLabel;
	private JLabel startTimeLabel;
	private JLabel priceLabel;

	private JTextField dateField;
	private JTextField idField;
	private JTextField nameField;
	private JTextField phoneField;
	private JTextField fieldTextField;
	private JTextField startTimeField;
	private JTextField priceField;
	private Choice choiceTime;
	private Choice choiceField;
	private SoccerFieldBookingDao soccerFieldBookingDao = new SoccerFieldBookingDao();
	private String[] columnNames = new String[] { "Ngày", "STT", "Tên khách hàng", "Số điện thoại", "Sân", "Giờ",
			"Giá sân" };
	
	private Object data = new Object[][] {};
	Object[][] schedule = new Object[][] {};

	public BookingView() throws SQLException {
		initComponents();
	}

	private void initComponents() throws SQLException {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		// khởi tạo các phím chức năng
		addReserve = new JButton("Add");
		editReserveBtn = new JButton("Edit");
		deleteReserveBtn = new JButton("Delete");
		clearBtn = new JButton("Clear");
		// khởi tạo bảng soccerFieldBooking
		jScrollPaneReserveTable = new JScrollPane();
		jScrollPaneScheduleTable = new JScrollPane();
		reserveTable = new JTable();
		scheduleTable = new JTable();
		
		choiceTime=new Choice();
		choiceTime.add("Chọn giờ");
		TimeSoccerDao timeDao=new TimeSoccerDao();
		List <TimeSoccer> listTimeSoccer=timeDao.getTimeList();
		for (TimeSoccer item: listTimeSoccer ) {
			choiceTime.add(item.getStartTime());
		}
		choiceTime.setEnabled(false);
		choiceField=new Choice();
		choiceField.add("Chọn sân");
		choiceField.add("A");
		choiceField.add("B");
		choiceField.add("C");
		choiceField.add("D");
		choiceField.add("E");
		choiceField.add("F");
		choiceField.setEnabled(false);
		
		// khởi tạo các label
		dateLabel = new JLabel("Ngày đặt sân (yyyy-MM-dd)");
		idLabel = new JLabel("Id");
		nameLabel = new JLabel("Tên khách hàng");
		phoneLabel = new JLabel("Số điện thoại");
		fieldLabel = new JLabel("Sân");
		startTimeLabel = new JLabel("Khung giờ");
		priceLabel = new JLabel("Giá sân");

		// khởi tạo các trường nhập dữ liệu cho admin
		dateField = new JTextField(15);
		idField = new JTextField(15);
		idField.setEditable(false);
		nameField = new JTextField(15);
		phoneField = new JTextField(15);
		fieldTextField = new JTextField(15);

		startTimeField = new JTextField(15);
		priceField = new JTextField(15);
		priceField.setEditable(false);
//		
		// cài đặt các cột và data cho bảng soccerFieldBooking

		reserveTable.setModel(new DefaultTableModel((Object[][]) data, columnNames));
		jScrollPaneReserveTable.setViewportView(reserveTable);
		jScrollPaneReserveTable.setPreferredSize(new Dimension(580, 400));
		jScrollPaneScheduleTable.setViewportView(scheduleTable);
		jScrollPaneScheduleTable.setPreferredSize(new Dimension(1000, 800));

		SpringLayout layout = new SpringLayout();
		// tạo đối tượng panel để chứa các thành phần của màn hình quản lý Student
		JPanel panel = new JPanel();
		panel.setSize(1000, 1000);
		panel.setLayout(layout);
		panel.add(jScrollPaneReserveTable);
		panel.add(jScrollPaneScheduleTable);

		panel.add(addReserve);
		panel.add(editReserveBtn);
		panel.add(deleteReserveBtn);
		panel.add(clearBtn);

		panel.add(dateLabel);
		panel.add(idLabel);
		panel.add(nameLabel);
		panel.add(phoneLabel);
		panel.add(fieldLabel);
		panel.add(startTimeLabel);
		panel.add(priceLabel);

		panel.add(dateField);
		panel.add(idField);
		panel.add(nameField);
		panel.add(phoneField);
//		panel.add(fieldTextField);
		panel.add(choiceField);
//		panel.add(startTimeField);
		panel.add(choiceTime);
		panel.add(priceField);

		// cài đặt vị trí các thành phần trên màn hình login
		layout.putConstraint(SpringLayout.WEST, dateLabel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, dateLabel, 40, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, idLabel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, idLabel, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, nameLabel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, nameLabel, 70, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, phoneLabel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, phoneLabel, 100, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, fieldLabel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, fieldLabel, 160, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, startTimeLabel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, startTimeLabel, 130, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, priceLabel, 10, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, priceLabel, 190, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, dateField, 170, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, dateField, 40, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, idField, 170, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, idField, 10, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, nameField, 170, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, nameField, 70, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, phoneField, 170, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, phoneField, 100, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, choiceTime, 170, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, choiceTime, 130, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, choiceField, 170, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, choiceField, 160, SpringLayout.NORTH, panel);
		
		
		layout.putConstraint(SpringLayout.WEST, priceField, 170, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, priceField, 190, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, jScrollPaneReserveTable, 500, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, jScrollPaneReserveTable, 10, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, jScrollPaneScheduleTable, 300, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, jScrollPaneScheduleTable, 450, SpringLayout.NORTH, panel);

		layout.putConstraint(SpringLayout.WEST, addReserve, 30, SpringLayout.WEST, panel);
		layout.putConstraint(SpringLayout.NORTH, addReserve, 240, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, editReserveBtn, 70, SpringLayout.WEST, addReserve);
		layout.putConstraint(SpringLayout.NORTH, editReserveBtn, 240, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, deleteReserveBtn, 70, SpringLayout.WEST, editReserveBtn);

		layout.putConstraint(SpringLayout.NORTH, clearBtn, 240, SpringLayout.NORTH, panel);
		layout.putConstraint(SpringLayout.WEST, clearBtn, 90, SpringLayout.WEST, deleteReserveBtn);

		layout.putConstraint(SpringLayout.NORTH, deleteReserveBtn, 240, SpringLayout.NORTH, panel);

		this.add(panel);
		this.pack();
		this.setTitle("Quản lý đặt sân");
		this.setSize(1600, 500);
		reserveTable.getColumnModel().getColumn(1).setPreferredWidth(10);
		editReserveBtn.setEnabled(false);
		deleteReserveBtn.setEnabled(false);
		addReserve.setEnabled(true);

	}
	public void checkField() {
		boolean isDateValid = !dateField.getText().equals("") && validateDateOfBooking();

		if (isDateValid) {
		    choiceTime.setEnabled(true);
	
		    boolean isTimeSelected = !choiceTime.getSelectedItem().equals("Chọn giờ");
		    if (isTimeSelected) {
		        List<String> fieldSoccer = fieldBookingDao.getFieldOfTimeInDate(dateField.getText(), choiceTime.getSelectedItem());
		
		        choiceField.removeAll();
		        choiceField.add("Chọn sân");
		        for (String item : fieldSoccer) {
		            choiceField.add(item);
		        }
		        choiceField.setEnabled(true);
		        priceField.setText(soccerFieldBookingDao.getPriceByTime(choiceTime.getSelectedItem()));
		    } else {
		        choiceField.setEnabled(false);
		        choiceField.select(0);
		        priceField.setText("");
		    }
		} else {
		    choiceTime.setEnabled(false);
		    choiceField.setEnabled(false);
		    choiceField.select(0);
		    choiceTime.select(0);
		    priceField.setText("");
		}
		
	}
	public void showMessage(String message) {
		JOptionPane.showMessageDialog(this, message);
	}

	/**
	 * hiển thị list soccerFieldBooking vào bảng studentTable
	 * 
	 * @param list
	 */
	public void showListBookings(List<SoccerFieldBooking> list) {
		int size = list.size();

		Object[][] soccerFieldBooking = new Object[size][7];
		for (int i = 0; i < size; i++) {
			soccerFieldBooking[i][0] = list.get(i).getDateOfBooking();
			soccerFieldBooking[i][1] = list.get(i).getId();
			soccerFieldBooking[i][2] = list.get(i).getCustomerName();
			soccerFieldBooking[i][3] = list.get(i).getCustomerPhoneNumber();
			soccerFieldBooking[i][4] = list.get(i).getField();
			soccerFieldBooking[i][5] = list.get(i).getTimeToStart();
			soccerFieldBooking[i][6] = list.get(i).getPrice();
		}

		reserveTable.setModel(new DefaultTableModel(soccerFieldBooking, columnNames));
		reserveTable.getColumnModel().getColumn(0).setPreferredWidth(25);
		reserveTable.getColumnModel().getColumn(1).setPreferredWidth(7);
		reserveTable.getColumnModel().getColumn(4).setPreferredWidth(7);
		reserveTable.getColumnModel().getColumn(5).setPreferredWidth(8);
		reserveTable.getColumnModel().getColumn(6).setPreferredWidth(25);
	}

	public void showSchedule(List<String> list) throws SQLException {
		List<TimeSoccer> listTime=soccerDao.getTimeList();
		Object[][] soccerFieldBooking = new Object[list.size()][listTime.size()];
		for (int i = 0; i < list.size(); i++) {
			
			soccerFieldBooking[i][0] = list.get(i);
			
			for (int j=1;j<listTime.size();j++)
			{ 
			soccerFieldBooking[i][j] = soccerFieldBookingDao.getDataFieldByDateAndTime(list.get(i),listTime.get(j-1).getStartTime() ).toString();
			}
		}
		 String[] columnNames2 = new String[listTime.size()];
		 columnNames2[0]="Ngày/Giờ";
		 for (int i=1;i<listTime.size();i++) columnNames2[i]=listTime.get(i-1).getStartTime();
		scheduleTable.setModel(new DefaultTableModel(soccerFieldBooking, columnNames2));
		scheduleTable.getColumnModel().getColumn(0).setPreferredWidth(125);
		scheduleTable.setVisible(true);
		
	}

	/**
	 * điền thông tin của hàng được chọn từ bảng soccerFieldBooking vào các trường
	 * tương ứng của soccerFieldBooking.
	 */
	public void fillBookingFromSelectedRow() {
		// lấy chỉ số của hàng được chọn
		int row = reserveTable.getSelectedRow();
		String dateOfBooking = reserveTable.getModel().getValueAt(row, 0).toString();
		if (LocalDate.now().isBefore(LocalDate.parse(dateOfBooking)) || LocalDate.now().isEqual(LocalDate.parse(dateOfBooking))) {
			dateField.setText(reserveTable.getModel().getValueAt(row, 0).toString());
			idField.setText(reserveTable.getModel().getValueAt(row, 1).toString());
			nameField.setText(reserveTable.getModel().getValueAt(row, 2).toString());
			phoneField.setText(reserveTable.getModel().getValueAt(row, 3).toString());
			choiceField.select(reserveTable.getModel().getValueAt(row, 4).toString());
			choiceTime.select(reserveTable.getModel().getValueAt(row, 5).toString());
			priceField.setText(reserveTable.getModel().getValueAt(row, 6).toString());
			choiceTime.setEnabled(true);
			choiceField.setEnabled(true);
			editReserveBtn.setEnabled(true);
			deleteReserveBtn.setEnabled(true);
			// disable Add button
			addReserve.setEnabled(false);
			} else {
			showMessage("Không thể chỉnh sửa vì đã hết thời hạn");
			}
	}

	/**
	 * xóa thông tin soccerFieldBooking
	 */
	public void clearBookingInfo() {
		dateField.setText("");
		idField.setText("");
		nameField.setText("");
		phoneField.setText("");
		choiceTime.select(0);
		choiceField.select(0);
		editReserveBtn.setEnabled(false);
		deleteReserveBtn.setEnabled(false);
		priceField.setText("");
		// enable Add button
		addReserve.setEnabled(true);
	}

	/**
	 * hiện thị thông tin soccerFieldBooking
	 * 
	 * @param soccerFieldBooking
	 */
	public void showBooking(SoccerFieldBooking soccerFieldBooking) {
		dateField.setText("" + soccerFieldBooking.getDateOfBooking());
		idField.setText("" + soccerFieldBooking.getId());
		nameField.setText(soccerFieldBooking.getCustomerName());
		phoneField.setText("" + soccerFieldBooking.getCustomerPhoneNumber());
//		fieldTextField.setText(soccerFieldBooking.getField() + "");
//		startTimeField.setText("" + soccerFieldBooking.getTimeToStart());
		priceField.setText("" + soccerFieldBooking.getPrice());
		// enable Edit and Delete buttons
		editReserveBtn.setEnabled(true);
		deleteReserveBtn.setEnabled(true);
		// disable Add button
		addReserve.setEnabled(false);
	}

	/**
	 * lấy thông tin soccerFieldBooking
	 * 
	 * @return
	 */
	public SoccerFieldBooking getBookingInfo() {
		// validate soccerFieldBooking
		if (!validateName() || !validatePhone() || !validateDateOfBooking()) {
			return null;
		}
		try {
			
			SoccerFieldBooking soccerFieldBooking = new SoccerFieldBooking();
			if (idField.getText() != null && !"".equals(idField.getText())) {
				soccerFieldBooking.setId(Integer.parseInt(idField.getText()));
			}
			soccerFieldBooking.setDateOfBooking(Date.valueOf(dateField.getText().trim()));
			soccerFieldBooking.setCustomerName(nameField.getText().trim());
			soccerFieldBooking.setCustomerPhoneNumber(phoneField.getText().trim());
			soccerFieldBooking.setField(choiceField.getSelectedItem());
			soccerFieldBooking.setTimeToStart(choiceTime.getSelectedItem());
			soccerFieldBooking.setPrice(Integer.parseInt(priceField.getText().trim()));
			return soccerFieldBooking;
		} catch (Exception e) {
			showMessage(e.getMessage());
		}
		return null;
	}
	
	private boolean validateName() {
		String name = nameField.getText();
		if (name == null || "".equals(name.trim())) {
			nameField.requestFocus();
			showMessage("Name không được trống.");
			return false;
		}
		return true;
	}


	private boolean validatePhone() {
		try {
			String phone = phoneField.getText().trim();
			if (phone == null || "".equals(phone.trim())) {
				phoneField.requestFocus();
				showMessage("Số điện thoại không hợp lệ");
				return false;
			}
		} catch (Exception e) {
			phoneField.requestFocus();
			showMessage("Số điện thoại không hợp lệ!");
			return false;
		}
		return true;
	}


	private boolean validateDateOfBooking() {
		try {
			String dateOfBooking = dateField.getText().trim();
			if (LocalDate.now().isEqual(LocalDate.parse(dateOfBooking)) == false) {
				if (LocalDate.now().isAfter(LocalDate.parse(dateOfBooking)) == true) {
					startTimeField.requestFocus();
					showMessage("Ngày đặt sân không hợp lệ, ngày đặt sân không thể nào là ngày trước ngày hôm nay");
					return false;
				}
			}
		} catch (Exception e) {
			startTimeField.requestFocus();
			showMessage("Thời gian đặt sân không hợp lệ!");
			return false;
		}
		return true;
	}

	public void actionPerformed(ActionEvent e) {
	}

	public void valueChanged(ListSelectionEvent e) {
	}

	public void addAddBookingListener(ActionListener listener) {
		addReserve.addActionListener(listener);
	}

	public void addEditBookingListener(ActionListener listener) {
		editReserveBtn.addActionListener(listener);
	}

	public void addDeleteBookingListener(ActionListener listener) {
		deleteReserveBtn.addActionListener(listener);
	}

	public void addClearListener(ActionListener listener) {
		clearBtn.addActionListener(listener);
	}

	public void addListBookingSelectionListener(ListSelectionListener listener) {
		reserveTable.getSelectionModel().addListSelectionListener(listener);
	}
	public void checkDateFocusListener(FocusListener listener) {
		dateField.addFocusListener(listener);
	}
	public void checkTimeItemListener(ItemListener listener) {
		choiceTime.addItemListener(listener);
	}
	public void checkFieldItemListener(ItemListener listener) {
		choiceField.addItemListener(listener);
	}
}
