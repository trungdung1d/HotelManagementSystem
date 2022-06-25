package com.example.hotelmanagementsystem.Database.TableView;

public class CustomerRoomTable {
    public String Room;
    public String Type;
    public String Capacity;
    public String  Status;
    public String Priceday;

    public CustomerRoomTable(String Room,String Type,String Capacity,String Priceday,String Status){
        this.Room = Room;
        this.Type = Type;
        this.Capacity = Capacity;
        this.Priceday = Priceday;
        this.Status = Status;
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

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getPriceday() {
        return Priceday;
    }

    public void setPriceday(String priceday) {
        Priceday = priceday;
    }
}
