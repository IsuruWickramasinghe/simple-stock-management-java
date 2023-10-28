package com.applestock.model;

public class Employee {
    private final String emp_id;
    private final String emp_name;
    private final String emp_pass;
    private final boolean isManager;
    private final String emp_address;
    private final String emp_phone;
    private final String emp_email;

    public Employee(String emp_id, String emp_name, String emp_pass, boolean isManager, String emp_address, String emp_phone, String emp_email) {
        this.emp_id = emp_id;
        this.emp_name = emp_name;
        this.emp_pass = emp_pass;
        this.isManager = isManager;
        this.emp_address = emp_address;
        this.emp_phone = emp_phone;
        this.emp_email = emp_email;
    }
    public String getEmpId() {
        return emp_id;
    }
    public String getEmpName() {
        return emp_name;
    }
    public String getEmpPass() {
        return emp_pass;
    }
    public boolean isManager() {
        return isManager;
    }
    public String getEmpAddress() {
        return emp_address;
    }
    public String getEmpPhone() {
        return emp_phone;
    }
    public String getEmpEmail() {
        return emp_email;
    }
}
