package com.mohammad_fathi.store.model.room.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity
public class Products {

    @ColumnInfo
    @PrimaryKey(autoGenerate = true)
    int product_ID;
    @ColumnInfo
    String product_Name_fa;
    @ColumnInfo
    String product_Name_en;
    @ColumnInfo
    String product_Category;
    @ColumnInfo
    int product_Price;
    @ColumnInfo
    String product_BrandName;
    @ColumnInfo
    String product_img_url;

    @Ignore
    public Products(String product_Name_fa, String product_Name_en, String product_Category, int product_Price, String product_BrandName, String product_img_url) {
        this.product_Name_fa = product_Name_fa;
        this.product_Name_en = product_Name_en;
        this.product_Category = product_Category;
        this.product_Price = product_Price;
        this.product_BrandName = product_BrandName;
        this.product_img_url = product_img_url;
    }

    public Products(int product_ID, String product_Name_fa, String product_Name_en, String product_Category, int product_Price, String product_BrandName, String product_img_url) {
        this.product_ID = product_ID;
        this.product_Name_fa = product_Name_fa;
        this.product_Name_en = product_Name_en;
        this.product_Category = product_Category;
        this.product_Price = product_Price;
        this.product_BrandName = product_BrandName;
        this.product_img_url = product_img_url;
    }

    public int getProduct_ID() {
        return product_ID;
    }

    public void setProduct_ID(int product_ID) {
        this.product_ID = product_ID;
    }

    public String getProduct_Name_fa() {
        return product_Name_fa;
    }

    public void setProduct_Name_fa(String product_Name_fa) {
        this.product_Name_fa = product_Name_fa;
    }

    public String getProduct_Name_en() {
        return product_Name_en;
    }

    public void setProduct_Name_en(String product_Name_en) {
        this.product_Name_en = product_Name_en;
    }

    public String getProduct_Category() {
        return product_Category;
    }

    public void setProduct_Category(String product_Category) {
        this.product_Category = product_Category;
    }

    public int getProduct_Price() {
        return product_Price;
    }

    public void setProduct_Price(int product_Price) {
        this.product_Price = product_Price;
    }

    public String getProduct_BrandName() {
        return product_BrandName;
    }

    public void setProduct_BrandName(String product_BrandName) {
        this.product_BrandName = product_BrandName;
    }

    public String getProduct_img_url() {
        return product_img_url;
    }

    public void setProduct_img_url(String product_img_url) {
        this.product_img_url = product_img_url;
    }
}
