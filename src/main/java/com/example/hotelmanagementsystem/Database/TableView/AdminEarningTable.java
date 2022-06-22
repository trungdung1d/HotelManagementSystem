package com.example.hotelmanagementsystem.Database.TableView;

public class AdminEarningTable {

    String ID, Room, Type, Checkedin, Checkedout, Priceday, Totalprice;

    public AdminEarningTable(String ID, String Room, String Type, String Checkedin, String Checkedout, String Priceday, String Totalprice){
        this.ID = ID;
        this.Room = Room;
        this.Type = Type;
        this.Checkedin = Checkedin;
        this.Checkedout = Checkedout;
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

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public String getCheckedin() {
        return Checkedin;
    }

    public void setCheckedin(String checkedin) {
        this.Checkedin = checkedin;
    }

    public String getCheckedout() {
        return Checkedout;
    }

    public void setCheckedout(String checkedout) {
        this.Checkedout = checkedout;
    }

    public String getPriceday() {
        return Priceday;
    }

    public void setPriceday(String priceday) {
        this.Priceday = priceday;
    }

    public String getTotalprice() {
        return Totalprice;
    }

    public void setTotalprice(String totalprice) {
        this.Totalprice = totalprice;
    }
}
