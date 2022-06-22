package com.example.hotelmanagementsystem.Database.TableView;

public class ManagerCustomerTable {
    private  String ID;
    private String Name;
    private String Email;
    private String Phone;
    private String Address;

    public ManagerCustomerTable(String ID, String Name, String Email, String Phone, String Address){
        this.ID = ID;
        this.Name = Name;
        this.Email = Email;
        this.Phone = Phone;
        this.Address = Address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String NID) {
        this.ID = NID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
