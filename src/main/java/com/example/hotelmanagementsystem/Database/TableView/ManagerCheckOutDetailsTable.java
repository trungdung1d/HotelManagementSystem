package com.example.hotelmanagementsystem.Database.TableView;

public class ManagerCheckOutDetailsTable {
    String ID, Room,  Type,  Capacity,  Priceday,  Totalprice,  Checkedin,  Checkedout;

    public ManagerCheckOutDetailsTable( String ID,String Room, String Type, String Capacity, String Priceday, String Totalprice, String Checkedin, String Checkedout){
        this.ID = ID;
        this.Room = Room;
        this.Type = Type;
        this.Capacity = Capacity;
        this.Priceday = Priceday;
        this.Totalprice = Totalprice;
        this.Checkedin = Checkedin;
        this.Checkedout = Checkedout;
    }

    public String getID() {
        return ID;
    }

    public void setID(String id) {
        this.ID = id;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        this.Room = room;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        this.Capacity = capacity;
    }

    public String getPriceDay() {
        return Priceday;
    }

    public void setPriceDay(String priceday) {
        this.Priceday = priceday;
    }

    public String getTotalPrice() {
        return Totalprice;
    }

    public void setTotalPrice(String totalprice) {
        this.Totalprice = totalprice;
    }

    public String getCheckedIn() {
        return Checkedin;
    }

    public void setCheckedIn(String checkedin) {
        this.Checkedin = checkedin;
    }

    public String getCheckedOut() {
        return Checkedout;
    }

    public void setCheckedOut(String checkedout) {
        this.Checkedout = checkedout;
    }
}
