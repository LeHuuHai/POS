package application.model;

public class Shift {
	private int id_shift;
	private int id_employee;
	private String time;
	private String infor;
	
	public Shift(int id_employee, String time, String infor) {
		super();
		this.id_employee = id_employee;
		this.time = time;
		this.infor = infor;
	}
	public Shift(int id_shift, int id_employee, String time, String infor) {
		super();
		this.id_shift = id_shift;
		this.id_employee = id_employee;
		this.time = time;
		this.infor = infor;
	}
	public int getId_employee() {
		return id_employee;
	}
	public int getId_shift() {
		return id_shift;
	}

	public void setId_shilft(int id_shift) {
		this.id_shift = id_shift;
	}

	public void setId_employee(int id_employee) {
		this.id_employee = id_employee;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getInfor() {
		return infor;
	}
	public void setInfor(String infor) {
		this.infor = infor;
	}
	
}
