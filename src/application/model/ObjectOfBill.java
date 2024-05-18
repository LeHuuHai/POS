package application.model;

public class ObjectOfBill {
	private int id_item;
	private String name_item;
	private int quantity;
	private double price;
	
	public ObjectOfBill(int id_item, String name_item, int quantity, double price) {
		super();
		this.id_item = id_item;
		this.name_item = name_item;
		this.quantity = quantity;
		this.price = price;
	}

	public int getId_item() {
		return id_item;
	}

	public void setId_item(int id_item) {
		this.id_item = id_item;
	}

	public String getName_item() {
		return name_item;
	}

	public void setName_item(String name_item) {
		this.name_item = name_item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ObjectOfBill [id_item=" + id_item + ", name_item=" + name_item + ", quantity=" + quantity + ", price="
				+ price + "]";
	}
	
}
