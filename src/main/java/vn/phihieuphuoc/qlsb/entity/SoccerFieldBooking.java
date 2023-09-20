package vn.phihieuphuoc.qlsb.entity;

import java.io.Serializable;
import java.sql.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "student")
@XmlAccessorType(XmlAccessType.FIELD)
public class SoccerFieldBooking implements Serializable {
    private static final long serialVersionUID = 1L;
    private Date dateOfBooking;
    private int id;
    private String customerName;
    private String customerPhoneNumber;
    private String field;
    private long price;
    
    /* điểm trung bình của sinh viên */
    private String timeToStart;

	public Date getDateOfBooking() {
		return dateOfBooking;
	}

	public void setDateOfBooking(Date dateOfBooking) {
		this.dateOfBooking = dateOfBooking;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerPhoneNumber() {
		return customerPhoneNumber;
	}

	public void setCustomerPhoneNumber(String customerPhoneNumber) {
		this.customerPhoneNumber = customerPhoneNumber;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public long getPrice() {
		return price;
	}

	public void setPrice(long price) {
		this.price = price;
	}

	public String getTimeToStart() {
		return timeToStart;
	}

	public void setTimeToStart(String timeToStart) {
		this.timeToStart = timeToStart;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public SoccerFieldBooking(Date dateOfBooking, int id, String customerName, String customerPhoneNumber, String field,
			String timeToStart,long price) {
		super();
		this.dateOfBooking = dateOfBooking;
		this.id = id;
		this.customerName = customerName;
		this.customerPhoneNumber = customerPhoneNumber;
		this.field = field;
		this.price = price;
		this.timeToStart = timeToStart;
		this.price = price;
	}

	public SoccerFieldBooking() {
		super();
	}

    
}