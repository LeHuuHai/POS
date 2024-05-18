package application.model;

public class Employee {
	private int id;
	private String name;
	private String gender;
	private String dob;
	private String phone;
	private String address;
	private String idcard;
	private double salary;
	private String state;
	
	
	public Employee(int id) {
		this.id = id;
	}
	public Employee(int id, String name, String gender, String dob, String phone, String address, String idcard,
			double salary, String state) {
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.idcard = idcard;
		this.salary = salary;
		this.state = state;
	}
	public Employee(String name, String gender, String dob, String phone, String address, String idcard, double salary,
			String state) {
		super();
		this.name = name;
		this.gender = gender;
		this.dob = dob;
		this.phone = phone;
		this.address = address;
		this.idcard = idcard;
		this.salary = salary;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getDob() {
		return dob;
	}
	public void setDob(String dob) {
		this.dob = dob;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getIdcard() {
		return idcard;
	}
	public void setIdcard(String idcard) {
		this.idcard = idcard;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", gender=" + gender + ", dob=" + dob + ", phone=" + phone
				+ ", address=" + address + ", idcard=" + idcard + ", salary=" + salary + ", state=" + state + "]";
	}

}
