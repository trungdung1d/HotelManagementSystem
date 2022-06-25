package com.example.hotelmanagementsystem.Database.TableView;

public class ManagerCheckInDetailsTable {
    String ID, Name, Room, Priceday, Checkedin, Address;

    public ManagerCheckInDetailsTable(String ID, String Name, String Room, String Priceday, String Checkedin, String Address) {
        this.ID = ID;
        this.Name = Name;
        this.Room = Room;
        this.Priceday = Priceday;
        this.Checkedin = Checkedin;
        this.Address = Address;
    }

    public String getID() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getPriceday() {
        return Priceday;
    }

    public void setPriceday(String priceday) {
        Priceday = priceday;
    }

    public String getCheckedin() {
        return Checkedin;
    }

    public void setCheckedin(String checkedin) {
        Checkedin = checkedin;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }
}
