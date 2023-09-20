package vn.phihieuphuoc.qlsb.entity;

public class TimeSoccer {
		private String startTime;
		private long price;
		private String shift;
		
		public TimeSoccer(String startTime, long price, String buoi) {
			super();
			this.startTime = startTime;
			this.price = price;
			this.shift = buoi;
		}
		public String getStartTime() {
			return startTime;
		}
		public void setStartTime(String startTime) {
			this.startTime = startTime;
		}
		public double getPrice() {
			return price;
		}
		public void setPrice(long price) {
			this.price = price;
		}
		public String getBuoi() {
			return shift;
		}
		public void setBuoi(String buoi) {
			this.shift = buoi;
		}
}
