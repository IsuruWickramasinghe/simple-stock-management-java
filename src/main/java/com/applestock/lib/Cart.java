package com.applestock.lib;

public class Cart {
    String product_name;
    double product_price;
    int product_qty;

    public Cart(String name, double price, int qty) {
        this.product_name = name;
        this.product_price = price;
        this.product_qty = qty;
    }
    public String getProduct_name() {return product_name;}
    public double getProduct_price() {return product_price;}
    public int getProduct_qty() {return product_qty;}

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

}
