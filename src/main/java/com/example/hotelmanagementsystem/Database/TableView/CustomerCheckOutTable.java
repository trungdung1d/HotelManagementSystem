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

    public void setID(String id) {
        this.ID = id;
    }

    public String getRoom() {
        return Room;
    }

    public void setRoom(String room) {
        this.Room = room;
    }

    public String getCheckedInDate() {
        return Checkindate;
    }

    public void setCheckedInDate(String checkedInDate) {
        this.Checkindate = checkedInDate;
    }

    public String getCheckedOutDate() {
        return Checkoutdate;
    }

    public void setCheckedOutDate(String checkedOutDate) {
        this.Checkoutdate = checkedOutDate;
    }

    public String getPriceDay() {
        return Priceday;
    }

    public void setPriceDay(String priceDay) {
        this.Priceday = priceDay;
    }

    public String getTotalPrice() {
        return Totalprice;
    }

    public void setTotalPrice(String totalPrice) {
        this.Totalprice = totalPrice;
    }
}
