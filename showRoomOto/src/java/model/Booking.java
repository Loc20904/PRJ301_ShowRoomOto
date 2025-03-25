/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package model;


import java.time.LocalDate;

public class Booking {
    private int bookingDetailID;
    private int bookingID;
    private Customer customer;
    private LocalDate bookingDate;
    private String status;
    private Car car;
    private Employee employee;
    private LocalDate startDate;
    private LocalDate endDate;
    private int slot;

    // Constructor
    public Booking() {}

    public Booking(int bookingID, Customer customer, LocalDate bookingDate, String status,
            Car car, Employee employee, LocalDate startDate, LocalDate endDate, int slot) {
        this.bookingID = bookingID;
        this.customer = customer;
        this.bookingDate = bookingDate;
        this.status = status;
        this.car = car;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.slot = slot;
    }

    public Booking(int bookingDetailID, int bookingID, Customer customer, LocalDate bookingDate, String status, Car car, Employee employee, LocalDate startDate, LocalDate endDate, int slot) {
        this.bookingDetailID = bookingDetailID;
        this.bookingID = bookingID;
        this.customer = customer;
        this.bookingDate = bookingDate;
        this.status = status;
        this.car = car;
        this.employee = employee;
        this.startDate = startDate;
        this.endDate = endDate;
        this.slot = slot;
    }

    public int getBookingDetailID() {
        return bookingDetailID;
    }

    public void setBookingDetailID(int bookingDetailID) {
        this.bookingDetailID = bookingDetailID;
    }

    
    
    // Getters and Setters
    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public LocalDate getBookingDate() {
        return bookingDate;
    }

    public void setBookingDate(LocalDate bookingDate) {
        this.bookingDate = bookingDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public int getSlot() {
        return slot;
    }

    public void setSlot(int slot) {
        this.slot = slot;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "bookingID=" + bookingID +
                ", customer=" + customer.getFullName() +
                ", bookingDate=" + bookingDate +
                ", status='" + status + '\'' +
                ", car=" + car.getCarName() +
                ", employee=" + employee.getFullName() +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", slot=" + slot +
                '}';
    }
}
