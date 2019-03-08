package com.jason.manongapp.more.bean;

public class UserItemBean {

    public int itemImage;
    public int itemText;

    public UserItemBean(int itemImage, int itemText) {
        this.itemImage = itemImage;
        this.itemText = itemText;
    }

    @Override
    public String toString() {
        return "ItemBean{" +
                "itemImage=" + itemImage +
                ", itemText='" + itemText + '\'' +
                '}';
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }

    public int getItemText() {
        return itemText;
    }

    public void setItemText(int itemText) {
        this.itemText = itemText;
    }
}
