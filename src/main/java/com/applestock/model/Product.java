package com.applestock.model;

public class Product {
    private final String product_slug;
    private final String product_name;
    private final double  product_price;
    private final int  product_qty;
    private final String product_desc;
    private final String product_cat;

    public Product(String product_slug, String product_name, double product_price, int product_qty, String product_desc, String product_cat) {
        this.product_slug = product_slug;
        this.product_name = product_name;
        this.product_price = product_price;
        this.product_qty = product_qty;
        this.product_desc = product_desc;
        this.product_cat = product_cat;
    }
    public String getProductSlug(){return product_slug;}
    public String getProductName(){return product_name;}
    public double getProductPrice(){return product_price;}
    public int getProductQty(){return product_qty;}
    public String getProductDesc(){return product_desc;}
    public String getProductCat(){return product_cat;}
}
