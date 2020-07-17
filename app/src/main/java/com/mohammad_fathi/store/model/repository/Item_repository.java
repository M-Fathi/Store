package com.mohammad_fathi.store.model.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;

import com.mohammad_fathi.store.model.room.AppDatabase;
import com.mohammad_fathi.store.model.room.dao.Database_DAO;
import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.room.entity.Products;
import com.mohammad_fathi.store.model.webservice.WebServiceAPI;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Item_repository {

    private Database_DAO database_dao;
    private LiveData<List<Cart_item_entity>> cart_item_list;
    private LiveData<List<Discount_Items_entity>> Discount_Items_list;

    private WebServiceAPI webServiceAPI;
    private Call<ArrayList<Discount_Items_entity>> ListCall_discountItem;

    private LiveData<List<Products>> liveDataProduct;


    public Item_repository(Context context) {
        database_dao = AppDatabase.getInstance(context).getDatabaseDAO();

    }

    // Create public method for each method in DAO class:  Cart Items DAO
    // از تمام متدهای کلاس DAO یک کپی اینجا ایجاد می کنیم
    public void insert(Cart_item_entity cart_item_entity) {
        database_dao.insert(cart_item_entity);
    }

    public void update(Cart_item_entity cart_item_entity) {
        database_dao.update(cart_item_entity);
    }

    public void delete(Cart_item_entity cart_item_entity) {
        database_dao.delete(cart_item_entity);
    }

    public LiveData<List<Cart_item_entity>> selectAll() {
        return database_dao.selectAll();
    }

    public List<Cart_item_entity> selectAll_CartItems(){
        return database_dao.selectAll_CartItems();
    }

    // Create public method for each method in DAO class: Discount Items DAO
    public void insert_discountItems(Discount_Items_entity discount_items_entity) {
        database_dao.insert_discountItems(discount_items_entity);
    }

    public void update_discountItems(Discount_Items_entity discount_items_entity) {
        database_dao.update_discountItems(discount_items_entity);
    }

    public void delete_discountItems(Discount_Items_entity discount_items_entity) {
        database_dao.delete_discountItems(discount_items_entity);
    }

    public LiveData<List<Discount_Items_entity>> selectAll_discountItems() {
        return  database_dao.selectAll_discountItems();
        //return Discount_Items_list;
    }
    public List<Discount_Items_entity> selectAll_discountItems4Test(){
        return database_dao.selectAll_discountItems4Test();
    }

    public Discount_Items_entity selectDiscountItemBy_ID(int pId){
        return database_dao.selectDiscountItemBy_ID(pId);
    }


    //---Get All data from LOCAL (product table) or WebService
    //WebService Methods ---(should be edited)
    public Call<ArrayList<Discount_Items_entity>> getDiscountItems() {
        return webServiceAPI.getDiscountItems();
    }

    //WebService Methods ---(product table)
    public Call<ArrayList<Products>> getAllProducts() {
        return webServiceAPI.getAllProducts();
    }

    //INSERT into Local DB
    public long insertInto_productsTable_local(Products products){
        return database_dao.insertInto_productsTable_local(products);
    }

    //LOCAL (product table)
    public LiveData<List<Products>> selectAll_Products() {
        return database_dao.selectAll_Products();
    }

    public LiveData<List<Products>> selectProduct_ByCategory(String category) {
        return database_dao.selectProduct_ByCategory(category);
    }

    public Products selectProduct_ByID(int pId){
        return database_dao.selectProduct_ByID(pId);
    }


}
