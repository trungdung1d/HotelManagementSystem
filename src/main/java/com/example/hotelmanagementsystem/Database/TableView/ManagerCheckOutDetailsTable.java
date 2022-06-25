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

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        Room = room;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getCapacity() {
        return Capacity;
    }

    public void setCapacity(String capacity) {
        Capacity = capacity;
    }

    public String getPriceday() {
        return Priceday;
    }

    public void setPriceday(String priceday) {
        Priceday = priceday;
    }

    public String getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(String totalprice) {
        Totalprice = totalprice;
    }

    public String getCheckedin() {
        return Checkedin;
    }

    public void setCheckedin(String checkedin) {
        Checkedin = checkedin;
    }

    public String getCheckedout() {
        return Checkedout;
    }

    public void setCheckedout(String checkedout) {
        Checkedout = checkedout;
    }
}
