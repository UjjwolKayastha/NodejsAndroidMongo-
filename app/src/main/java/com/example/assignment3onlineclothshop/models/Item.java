package com.example.assignment3onlineclothshop.models;

public class Item {
    String _id, itemName, itemDescription, itemImageName, itemPrice;



    public Item( String itemName, String itemDescription, String itemImageName, String itemPrice) {

        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemImageName = itemImageName;
        this.itemPrice = itemPrice;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemImageName() {
        return itemImageName;
    }

    public void setItemImageName(String itemImageName) {
        this.itemImageName = itemImageName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }


    @Override
    public String toString() {
        return "Item{" +
                "_id='" + _id + '\'' +
                ", itemName='" + itemName + '\'' +
                ", itemDescription='" + itemDescription + '\'' +
                ", itemImageName='" + itemImageName + '\'' +
                ", itemPrice='" + itemPrice + '\'' +
                '}';
    }
}
