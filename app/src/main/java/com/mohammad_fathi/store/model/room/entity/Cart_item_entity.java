package com.mohammad_fathi.store.model.room.entity;


import android.graphics.drawable.Drawable;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Cart_item_entity {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    private int item_id;
    @ColumnInfo
    private String item_Title;
    @ColumnInfo
    private String item_category;
    @ColumnInfo
    private int item_price;
    @ColumnInfo
    private int item_quantity;
    @ColumnInfo
    private int item_pic;
    @ColumnInfo
    private String item_picUrl;

    public Cart_item_entity(int item_id, String item_Title, String item_category, int item_price, int item_quantity, int item_pic,String item_picUrl) {
        this.item_id = item_id;
        this.item_Title = item_Title;
        this.item_category = item_category;
        this.item_price = item_price;
        this.item_quantity = item_quantity;
        this.item_pic = item_pic;
        this.item_picUrl=item_picUrl;
    }

    @Ignore
    public Cart_item_entity(int item_id) {
        this.item_id = item_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    public String getItem_Title() {
        return item_Title;
    }

    public void setItem_Title(String item_Title) {
        this.item_Title = item_Title;
    }

    public String getItem_category() {
        return item_category;
    }

    public void setItem_category(String item_category) {
        this.item_category = item_category;
    }

    public int getItem_price() {
        return item_price;
    }

    public void setItem_price(int item_price) {
        this.item_price = item_price;
    }

    public int getItem_quantity() {
        return item_quantity;
    }

    public void setItem_quantity(int item_quantity) {
        this.item_quantity = item_quantity;
    }

    public int getItem_pic() {
        return item_pic;
    }

    public void setItem_pic(int item_pic) {
        this.item_pic = item_pic;
    }

    public String getItem_picUrl() {
        return item_picUrl;
    }

    public void setItem_picUrl(String item_picUrl) {
        this.item_picUrl = item_picUrl;
    }
}
