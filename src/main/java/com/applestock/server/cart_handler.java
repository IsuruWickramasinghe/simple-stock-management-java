package com.applestock.server;

import com.applestock.db.db_connection;
import com.applestock.lib.Cart;
import org.jetbrains.annotations.NotNull;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class cart_handler extends db_connection {

    List<Cart> cartList = new ArrayList<>();

    int invoiceCnt = 0;

    public void setCartList(String product_slug, int product_qty) {
        try {
            String getItemQuery = "select * from product where product_slug=?";
            PreparedStatement getItemStatement = getConnection().prepareStatement(getItemQuery);
            getItemStatement.setString(1, product_slug);
            ResultSet getItemResult = getItemStatement.executeQuery();

            if (getItemResult.next()) {
                String name = getItemResult.getString("product_slug");
                double price = Double.parseDouble(getItemResult.getString("product_price"));
                int stock_qty = Integer.parseInt(getItemResult.getString("product_qty"));

                int new_stock_qty = stock_qty - product_qty;

                //! -------------
                Cart cartItem = null;
                for (Cart cart : cartList) {
                    if (cart.getProduct_name().equals(name)) {
                        int newQty = cart.getProduct_qty() + product_qty;
                        cart.setProduct_qty(newQty);
                        cartItem = cart;
                        break;
                    }
                }
                if (cartItem == null) {
                    cartItem = new Cart(name, price, product_qty);
                    cartList.add(cartItem);
                }
                //! -------------

                try {
                    String updateQtyQuery = "update product set product_qty=? where product_slug=?";
                    PreparedStatement updateQtyStatement = getConnection().prepareStatement(updateQtyQuery);
                    updateQtyStatement.setInt(1, new_stock_qty);
                    updateQtyStatement.setString(2, product_slug);
                    updateQtyStatement.executeUpdate();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void refreshCart(@NotNull JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (Cart item : cartList) {
            tableModel.addRow(new Object[]{
                    item.getProduct_name(),
                    item.getProduct_price(),
                    item.getProduct_qty()
            });
        }
    }

    public void updateRemovedItem(String name, int qty) {

        try {
            String selectQuery = "select * from product where product_slug=?";
            PreparedStatement selectStatement = getConnection().prepareStatement(selectQuery);
            selectStatement.setString(1, name);
            ResultSet selectResult = selectStatement.executeQuery();

            int new_qty;
            if (selectResult.next()) {
                int stock_qty = selectResult.getInt("product_qty");
                new_qty = stock_qty + qty;

                try {
                    String updateQuery = "update product set product_qty=? where product_slug=?";
                    PreparedStatement updateStatement = getConnection().prepareStatement(updateQuery);
                    updateStatement.setInt(1, new_qty);
                    updateStatement.setString(2, name);
                    updateStatement.executeUpdate();
                    cartList.removeIf(cart -> cart.getProduct_name().equals(name));
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public double getSubTotal() {
        double amount = 0;
        for (Cart item : cartList) {
            amount = amount + (item.getProduct_qty() * item.getProduct_price());
        }
        return amount;
    }

    public void invoiceGenerator() {

        int width = 400;
        int height = 300 + (cartList.size()*20);

        BufferedImage invoiceImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = invoiceImage.createGraphics();

        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, width, height);

        g2d.setFont(new Font("Arial", Font.BOLD, 24));
        g2d.setColor(Color.decode("#176B87"));

        String invoiceTitle = "APPLE STOCK";
        g2d.drawString(invoiceTitle, 30, 30);

        g2d.setFont(new Font("Arial", Font.PLAIN, 12));
        g2d.setColor(Color.BLACK);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yy");
        String date = dateFormat.format(new Date());
        String invoiceNumber = "Invoice No: "+ String.format("%04d",++invoiceCnt);
        g2d.drawString(invoiceNumber, 30, 70);
        g2d.drawString("Date: " + date, 300, 70);

        g2d.drawLine(30, 100, 370, 100);
        g2d.drawString("Description", 50, 120);
        g2d.drawString("Qty", 250, 120);
        g2d.drawString("Amount", 300, 120);
        g2d.drawLine(30, 140, 370, 140);

        int tableY = 160;
        for (Cart item : cartList) {
            tableY += 20;
            g2d.drawString(item.getProduct_name(), 50 , tableY);
            g2d.drawString(String.valueOf(item.getProduct_qty()), 250 , tableY);
            g2d.drawString(String.valueOf(item.getProduct_qty()*item.getProduct_price()), 300 , tableY);
        }

        g2d.drawLine(30, tableY + 20, 370, tableY + 20);
        g2d.drawString("Total", 250, tableY + 40);
        g2d.drawString(String.valueOf(getSubTotal()), 300, tableY + 40);

        g2d.drawString("Contact No: 076-9146712", 30, tableY + 80);
        g2d.drawString("Email: applestock@gmail.com", 30, tableY + 100);

        g2d.dispose();

        try {
            String desktopPath = System.getProperty("user.home") + File.separator + "Desktop" + File.separator + "apple-stock-invoice";
            File folder = new File(desktopPath);
            if (!folder.exists()) {
                folder.mkdirs();
            }
            String filePath = desktopPath + File.separator + date + "-invoice-" + invoiceCnt + ".png";
            File output = new File(filePath);
            ImageIO.write(invoiceImage, "png", output);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}
