package com.example.hotelmanagementsystem.Database.TableView;

public class ManagerRoomTable {
    String Room, Type, Capacity, Priceday, Status;

    public ManagerRoomTable(String Room, String Type, String Capacity, String Priceday, String Status){
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

    public void setCapacity(String Capacity) {
        this.Capacity = Capacity;
    }

    public String getPriceday() {
        return Priceday;
    }

    public void setPriceday(String Priceday) {
        this.Priceday = Priceday;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = Status;
    }
}
