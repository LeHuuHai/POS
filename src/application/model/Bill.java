package application.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Bill {
    private int id;
//    private ArrayList<BillItem> list;
    private String time;
    private double discount;
    private double VAT;
    private double total;

    public Bill(int id, String time, double discount, double VAT, double total) {
        this.id = id;
        this.time = time;
        this.discount = discount;
        this.VAT = VAT;
        this.total = total;
    }

    public Bill(int id) {
        this.id = id;
    }

//    public Bill(int id, ArrayList<BillItem> list, double discount, double VAT) {
//        this.id = id;
//        this.list = list;
//        this.discount = discount;
//        this.VAT = VAT;
//    }
    
    public Bill(double discount, double VAT, double total) {
        this.discount = discount;
        this.VAT = VAT;
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.time = currentDateTime.format(formatter);
        this.total = total;
    }

    public int getId() {
        return id;
    }

//    public ArrayList<BillItem> getList() {
//        return list;
//    }

    public String getTime() {
        return time;
    }

    public double getDiscount() {
        return discount;
    }

    public double getVAT() {
        return VAT;
    }

    public double getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Bill{" + "id=" + id + ", time=" + time + ", discount=" + discount + ", VAT=" + VAT + ", total=" + total + '}';
    }

}
