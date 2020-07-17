package com.mohammad_fathi.store.model.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Discount_Items_entity {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    private int item_id;
    @ColumnInfo
    private String item_name;
    @ColumnInfo
    private String discount_percent;
    @ColumnInfo
    private int item_price;
    @ColumnInfo
    private String item_pic;

    @Ignore
    public Discount_Items_entity(int item_id, String item_name, String discount_percent, int item_price, String item_pic) {
        this.item_id = item_id;
        this.item_name = item_name;
        this.discount_percent = discount_percent;
        this.item_price = item_price;
        this.item_pic = item_pic;
    }

    public Discount_Items_entity(String item_name, String discount_percent, int item_price, String item_pic) {
        this.item_name = item_name;
        this.discount_percent = discount_percent;
        this.item_price = item_price;
        this.item_pic = item_pic;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getDiscount_percent() {
        return discount_percent;
    }

    public void setDiscount_percent(String discount_percent) {
        this.discount_percent = discount_percent;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public String getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(String item_pic) {
        this.item_pic = item_pic;
    }
}
