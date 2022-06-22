package com.example.hotelmanagementsystem.Database.TableView;

public class ManagerCheckInDetailsTable {
    String ID, Name, Room, Priceday, Checkedin, Address;

    public ManagerCheckInDetailsTable(String ID,String Name,String Room,String Priceday,String Checkedin,String Address){
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

    public void setID(String id) {
        this.ID = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        this.Name = name;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        this.Room = Room;
    }

    public String getPriceDay() {
        return Priceday;
    }

    public void setPriceDay(String priceday) {
        this.Priceday = priceday;
    }

    public String getCheckedIn() {
        return Checkedin;
    }

    public void setCheckedIn(String checkedin) {
        this.Checkedin = checkedin;
    }

    public String getAddress() { return Address;}

    public void setAddress(String address) {
        this.Address = address;
    }
}
