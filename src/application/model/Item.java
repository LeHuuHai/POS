
package application.model;

public class Item {
    private int id;
    private String name;
    private String type;
    private double price;
	private String img_path;
    private String state;

    public Item() {
    }

    public Item(int id) {
        this.id = id;
    }

    public Item(int id, String name, double price, String type, String img_path, String state) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.price = price;
		this.img_path = img_path;
		this.state = state;
	}

    public Item(String name, double price, String type, String img_path, String state) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.img_path = img_path;
		this.state = state;
	}
    
    public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public String getImg_path() {
        return img_path;
    }

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", price=" + price + ", img_path=" + img_path + ", state=" + state
				+ "]";
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

    
    
}
