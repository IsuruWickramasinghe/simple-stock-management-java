package com.applestock.server;

import com.applestock.client.cashier_panel;
import com.applestock.client.manager_panel;
import com.applestock.db.db_connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login_handler extends db_connection {

    private final String emp_name;
    private final String emp_pass;

    public login_handler(String emp_name,String emp_pass) {
        this.emp_name = emp_name;
        this.emp_pass = emp_pass;
    }

    public boolean user_login(){
        try {
            String loginSearchQuery = "select * from employee where emp_name = ? and emp_pass = ?";
            PreparedStatement loginSearchStatement = getConnection().prepareStatement(loginSearchQuery);
            loginSearchStatement.setString(1, this.emp_name);
            loginSearchStatement.setString(2, this.emp_pass);
            ResultSet loginSearchResult =  loginSearchStatement.executeQuery();

            boolean isManager;
            String emp_id;

            if (loginSearchResult.next()) {
                isManager = loginSearchResult.getBoolean("isManager");
                emp_id = loginSearchResult.getString("emp_id");

                if (isManager){
                    new manager_panel(emp_id, this.emp_name, true);
                }
                else{
                    new cashier_panel(this.emp_name);
                }
                closeConnection();
                return true;
            }
            else {
                closeConnection();
                System.out.println("user not found");
                return false;
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
