/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


import java.time.LocalDate;

public class Account {
    private int accId;
    private String username;
    private String password;
    private String email;
    private String role;
    private String authority;
    private LocalDate regisDate;
    private int customerId;
    private int employeeId;
    private Integer customerID;
    private Integer employeeID;

        public Account(int accId, String username, String password, String email, String role, String authority, java.sql.Date regisDate, Integer customerID, Integer employeeID) {
        this.accId = accId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.authority = authority;
        this.regisDate = regisDate.toLocalDate();
        this.customerID = customerID;
        this.employeeID = employeeID;
    }

    public Account(int accId, String username, String password, String email, String role, String authority, LocalDate regisDate, Integer customerID, Integer employeeID) {
        this.accId = accId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.authority = authority;
        this.regisDate = regisDate;
        this.customerID = customerID;
        this.employeeID = employeeID;
    }
    // Default constructor
    public Account() {
    }

    public Account(String username, String email) {
        this.username = username;
        this.email = email;
    }

    // Parameterized constructor
    public Account(int accId, String username, String password, String email, String role, String authority, LocalDate regisDate, int customerId) {
        this.accId = accId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.authority = authority;
        this.regisDate = regisDate;
        this.customerId = customerId;
    }

    public Account(int accId, String username, String password, String email, String role, String authority, LocalDate regisDate, int customerId, int employeeId) {
        this.accId = accId;
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
        this.authority = authority;
        this.regisDate = regisDate;
        this.customerId = customerId;
        this.employeeId = employeeId;
    }

    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Integer getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(Integer employeeID) {
        this.employeeID = employeeID;
    }

    // Getters and Setters
    public int getAccId() {
        return accId;
    }

    public void setAccId(int accId) {
        this.accId = accId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }

    public LocalDate getRegisDate() {
        return regisDate;
    }

    public void setRegisDate(LocalDate regisDate) {
        this.regisDate = regisDate;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    @Override
    public String toString() {
        return "Account{" + "accId=" + accId + ", username=" + username + ", password=" + password + ", email=" + email + ", role=" + role + ", authority=" + authority + ", regisDate=" + regisDate + ", customerId=" + customerId + '}';
    }
    
    
}
