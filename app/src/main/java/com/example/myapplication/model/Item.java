package com.example.myapplication.model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "items")
public class Item {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;
    @ColumnInfo(name = "name")
    private String nameItem;
    @ColumnInfo(name = "price")
    private double price;
    @ColumnInfo(name = "quantity")
    private int quantityInStock;
    @Ignore
    private String customerBuy;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNameItem() {
        return nameItem;
    }

    public void setNameItem(String nameItem) {
        this.nameItem = nameItem;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public void setQuantityInStock(int quantityInStock) {
        this.quantityInStock = quantityInStock;
    }

    public String getCustomerBuy() {
        return customerBuy;
    }

    public void setCustomerBuy(String customerBuy) {
        this.customerBuy = customerBuy;
    }
}
