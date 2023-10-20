package com.applestock.server;

import com.applestock.db.db_connection;
import com.applestock.lib.Employee;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class manager_handler extends db_connection {

    private final String emp_id;
    private String emp_name;
    private String emp_pass;
    private boolean isManager;
    private String emp_address;
    private String emp_phone;
    private String emp_email;


    public manager_handler(String emp_id, String emp_name, boolean isManager) throws HeadlessException {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.isManager = isManager;
        setProfile();
    }

    private void setProfile(){
        try {
            String getDataQuery = "select * from employee where emp_id = ? and emp_name = ?";
            PreparedStatement getUserData = getConnection().prepareStatement(getDataQuery);
            getUserData.setString(1, emp_id);
            getUserData.setString(2, emp_name);
            ResultSet userData = getUserData.executeQuery();
            if (userData.next()){
                this.emp_pass = userData.getString("emp_pass");
                this.emp_address = userData.getString("emp_address");
                this.emp_phone = userData.getString("emp_phone");
                this.emp_email = userData.getString("emp_email");
            }
        }
        catch (Exception e){
            e.printStackTrace();
            System.out.println("user nto found");
        }
    }

    public String[] getProfile() {
        return new String[]{emp_id, emp_name, emp_pass, emp_address, emp_phone, emp_email};
    }

    public boolean getIsManager(){
        return isManager;
    }

    public int generateId() {
        int count = 0;
        try{

            String empCountQuery = "select * from employee";
            PreparedStatement empCountStatement = getConnection().prepareStatement(empCountQuery);
            ResultSet empResult = empCountStatement.executeQuery();
            while (empResult.next()){
                count++;
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return count+1;
    }

    public boolean updateProfile(String emp_name,String emp_pass, boolean isManager, String emp_address, String emp_phone, String emp_email){
        this.emp_name = emp_name;
        this.emp_pass = emp_pass;
        this.isManager = isManager;
        this.emp_address = emp_address;
        this.emp_phone = emp_phone;
        this.emp_email = emp_email;
        try{
            String updateQuery = "update employee set emp_name=?, emp_pass=?, isManager=?, emp_address=?, emp_phone=?, emp_email=? where emp_id=?";
            PreparedStatement updateStatement = getConnection().prepareStatement(updateQuery);
            updateStatement.setString(1, emp_name);
            updateStatement.setString(2, emp_pass);
            updateStatement.setBoolean(3, isManager);
            updateStatement.setString(4, emp_address);
            updateStatement.setString(5, emp_phone);
            updateStatement.setString(6, emp_email);
            updateStatement.setString(7, emp_id);
            int rowsAffected = updateStatement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addNewUser(String emp_id, String emp_name, String emp_pass, boolean isManager, String emp_address, String emp_phone, String emp_email) {
        try {
            String checkIdQuery = "select * from employee where emp_id = ?";
            PreparedStatement checkIdStatement = getConnection().prepareStatement(checkIdQuery);
            checkIdStatement.setString(1, emp_id);
            ResultSet checkIdResult = checkIdStatement.executeQuery();
            if (!checkIdResult.next()){
                String addUserQuery = "insert into employee (emp_id, emp_name, emp_pass, isManager, emp_address, emp_phone, emp_email) values (?,?,?,?,?,?,?)";
                PreparedStatement addUserStatement = getConnection().prepareStatement(addUserQuery);
                addUserStatement.setString(1, emp_id);
                addUserStatement.setString(2, emp_name);
                addUserStatement.setString(3, emp_pass);
                addUserStatement.setBoolean(4, isManager);
                addUserStatement.setString(5, emp_address);
                addUserStatement.setString(6, emp_phone);
                addUserStatement.setString(7, emp_email);

                int insetResult = addUserStatement.executeUpdate();
                return insetResult > 0;
            }
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public boolean updateUser(String emp_id, String emp_name, String emp_pass, boolean isManager, String emp_address, String emp_phone, String emp_email){
        try {
            String updateUserQuery = "update employee set emp_name=?, emp_pass=?, isManager=?, emp_address=?, emp_phone=?, emp_email=? where emp_id=?";
            PreparedStatement updateUserStatement = getConnection().prepareStatement(updateUserQuery);
            updateUserStatement.setString(1, emp_name);
            updateUserStatement.setString(2, emp_pass);
            updateUserStatement.setBoolean(3, isManager);
            updateUserStatement.setString(4, emp_address);
            updateUserStatement.setString(5, emp_phone);
            updateUserStatement.setString(6, emp_email);
            updateUserStatement.setString(7, emp_id);
            int rowsAffected = updateUserStatement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteUser(String emp_id, String emp_name){
        try {
            String deleteUserQuery = "delete from employee where emp_id=? and emp_name=?";
            PreparedStatement deleteUserStatement = getConnection().prepareStatement(deleteUserQuery);
            deleteUserStatement.setString(1, emp_id);
            deleteUserStatement.setString(2, emp_name);
            int rowsAffected = deleteUserStatement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean addNewProduct(String product_slug, String product_name, double product_price, int product_qty, String product_desc, String product_cat) {
        try {
            String addProductQuery = "insert into product (product_slug, product_name, product_price, product_qty, product_desc, product_cat) values (?,?,?,?,?,?)";
            PreparedStatement addProductStatement = getConnection().prepareStatement(addProductQuery);
            addProductStatement.setString(1, product_slug);
            addProductStatement.setString(2, product_name);
            addProductStatement.setDouble(3, product_price);
            addProductStatement.setInt(4, product_qty);
            addProductStatement.setString(5, product_desc);
            addProductStatement.setString(6, product_cat);
            int addProductResult = addProductStatement.executeUpdate();
            return addProductResult > 0;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateProducts(String product_slug, String product_name, double product_price, int product_qty, String product_desc, String product_cat){
        try {
            String updateUserQuery = "update product set product_slug=?, product_name=?, product_price=?, product_qty=?, product_desc=?, product_cat=? where product_slug=?";
            PreparedStatement updateUserStatement = getConnection().prepareStatement(updateUserQuery);
            updateUserStatement.setString(1,product_cat+" | "+product_name);
            updateUserStatement.setString(2, product_name);
            updateUserStatement.setDouble(3, product_price);
            updateUserStatement.setInt(4, product_qty);
            updateUserStatement.setString(5, product_desc);
            updateUserStatement.setString(6, product_cat);
            updateUserStatement.setString(7, product_slug);
            int rowsAffected = updateUserStatement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteProduct(String product_slug, String product_name){
        try {
            String deleteProductQuery = "delete from product where product_slug=? and product_name=?";
            PreparedStatement deleteProductStatement = getConnection().prepareStatement(deleteProductQuery);
            deleteProductStatement.setString(1, product_slug);
            deleteProductStatement.setString(2, product_name);
            int rowsAffected = deleteProductStatement.executeUpdate();
            return rowsAffected > 0;
        }
        catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    public List<Employee> getUsers(){

        List<Employee> employeeList = new ArrayList<>();
        try {
            String getUsersQuery = "select * from employee";
            PreparedStatement getUsersStatement = getConnection().prepareStatement(getUsersQuery);
            ResultSet getUsersResult = getUsersStatement.executeQuery();

            while (getUsersResult.next()) {
                String emp_id = getUsersResult.getString("emp_id");
                String emp_name = getUsersResult.getString("emp_name");
                String emp_pass = getUsersResult.getString("emp_pass");
                boolean isManager = getUsersResult.getBoolean("isManager");
                String emp_address = getUsersResult.getString("emp_address");
                String emp_phone = getUsersResult.getString("emp_phone");
                String emp_email = getUsersResult.getString("emp_email");

                employeeList.add(new Employee(emp_id,emp_name,emp_pass,isManager,emp_address,emp_phone,emp_email));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeList;
    }

    public void refreshUserTable(@NotNull JTable table) {
        DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
        tableModel.setRowCount(0);

        for (Employee employee : getUsers()) {
            tableModel.addRow(new Object[]{
                    employee.getEmpId(),
                    employee.getEmpName(),
                    employee.getEmpPass(),
                    employee.isManager(),
                    employee.getEmpAddress(),
                    employee.getEmpPhone(),
                    employee.getEmpEmail()
            });
        }
    }




}
