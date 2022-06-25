package com.example.hotelmanagementsystem.Database.TableView;

public class CustomerCheckOutTable {
    String ID, Room, Checkindate, Checkoutdate,Priceday, Totalprice;

    public CustomerCheckOutTable(String ID,String Room,String Checkindate,String Checkoutdate,String Priceday,String Totalprice){
        this.ID = ID;
        this.Room = Room;
        this.Checkindate = Checkindate;
        this.Checkoutdate = Checkoutdate;
        this.Priceday = Priceday;
        this.Totalprice = Totalprice;
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

    public String getCheckindate() {
        return Checkindate;
    }

    public void setCheckindate(String checkindate) {
        Checkindate = checkindate;
    }

    public String getCheckoutdate() {
        return Checkoutdate;
    }

    public void setCheckoutdate(String checkoutdate) {
        Checkoutdate = checkoutdate;
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
}
