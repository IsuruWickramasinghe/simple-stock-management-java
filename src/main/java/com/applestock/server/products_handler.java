package com.applestock.server;

import com.applestock.db.db_connection;
import com.applestock.lib.Product;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class products_handler extends db_connection {

    public List<Product> getProducts(){
        List<Product> productList = new ArrayList<>();
        try{
            String getProductsQuery = "select * from product";
            PreparedStatement getProductStatement = getConnection().prepareStatement(getProductsQuery);
            ResultSet getProductResult = getProductStatement.executeQuery();
            while (getProductResult.next()){
                String product_slug = getProductResult.getString("product_slug");
                String product_name = getProductResult.getString("product_name");
                double product_price = getProductResult.getDouble("product_price");
                int product_qty = getProductResult.getInt("product_qty");
                String product_desc = getProductResult.getString("product_desc");
                String product_cat = getProductResult.getString("product_cat");

                productList.add(new Product(product_slug, product_name, product_price, product_qty, product_desc, product_cat));
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return productList;
    }

    public void refreshProductTable(@NotNull JTable table) {

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (Product product : getProducts()) {
            tableModel.addRow(new Object[]{
                    product.getProductSlug(),
                    product.getProductName(),
                    product.getProductPrice(),
                    product.getProductQty(),
                    product.getProductDesc(),
                    product.getProductCat(),
            });
        }
    }

    public void refreshHomeTable(@NotNull JTable table) {

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (Product product : getProducts()) {
            tableModel.addRow(new Object[]{
                    product.getProductName(),
                    product.getProductPrice(),
                    product.getProductQty(),
                    product.getProductCat(),
                    product.getProductDesc()
            });
        }
    }

    public void refreshCashierHomeTable(@NotNull JTable table) {

        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (Product product : getProducts()) {
            tableModel.addRow(new Object[]{
                    product.getProductSlug(),
                    product.getProductPrice(),
                    product.getProductQty(),
                    product.getProductDesc()
            });
        }
    }

    public String[] getProductCat() {
        List<String> categoriesList = new ArrayList<>();
        try {
            String getCatQuery = "select * from product_categories";
            PreparedStatement getCatStatement = getConnection().prepareStatement(getCatQuery);
            ResultSet getCatResult = getCatStatement.executeQuery();

            while (getCatResult.next()){
                categoriesList.add(getCatResult.getString("category"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoriesList.toArray(new String[0]);
    }
}
