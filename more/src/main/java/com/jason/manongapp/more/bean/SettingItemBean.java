package com.jason.manongapp.more.bean;

public class SettingItemBean {

    public int itemImage;
    public int itemText;

    public SettingItemBean(int itemImage, int itemText) {
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
