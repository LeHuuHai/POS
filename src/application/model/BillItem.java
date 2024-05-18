package application.model;

public class BillItem {
    private int id_bill;
    private int id_item;
    private int quantity;

    public BillItem(int id_item, int quantity) {
        this.id_item = id_item;
        this.quantity = quantity;
    }
    
    public BillItem(int id_bill, int id_item, int quantity) {
        this.id_bill = id_bill;
        this.id_item = id_item;
        this.quantity = quantity;
    }

    public int getId_bill() {
        return id_bill;
    }

    public int getId_item() {
        return id_item;
    }

    public int getQuantity() {
        return quantity;
    }

	@Override
	public String toString() {
		return "BillItem [id_bill=" + id_bill + ", id_item=" + id_item + ", quantity=" + quantity + "]";
	}
    
}
