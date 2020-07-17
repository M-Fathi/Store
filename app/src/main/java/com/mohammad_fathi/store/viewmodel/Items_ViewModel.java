package com.mohammad_fathi.store.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mohammad_fathi.store.di.component.DaggerItemRepository_component;
import com.mohammad_fathi.store.di.component.ItemRepository_component;
import com.mohammad_fathi.store.di.module.ItemRepository_module;
import com.mohammad_fathi.store.model.repository.Item_repository;
import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.room.entity.Products;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class Items_ViewModel extends AndroidViewModel {

    Item_repository item_repository;
    private LiveData<List<Cart_item_entity>> listLiveData_items;
    private LiveData<List<Discount_Items_entity>> Discount_Items_list;
    private LiveData<List<Products>> listLiveData_Products;
    private Call<ArrayList<Discount_Items_entity>> ListCall_discountItem;


    // در اینجا Application  همون Context است
    public Items_ViewModel(@NonNull Application application) {
        super(application);

        // ---- using Dagger instead of ( item_repository = new Item_repository(application); )
        ItemRepository_component component = DaggerItemRepository_component.builder().itemRepository_module(new ItemRepository_module(application)).build();
        item_repository = component.getItem_repository();

        listLiveData_Products = item_repository.selectAll_Products();
        listLiveData_items = item_repository.selectAll();
    }


    // تمام متد های داخل ریپازیتوری رو مجددا اینجا می نویسیم

    // ------------- Cart Table Methods -----------------------------------------------------------
    public void insert(Cart_item_entity cart_item_entity) {
        item_repository.insert(cart_item_entity);
    }

    public void update(Cart_item_entity cart_item_entity) {
        item_repository.update(cart_item_entity);
    }

    public void delete(Cart_item_entity cart_item_entity) {
        item_repository.delete(cart_item_entity);
    }

    public LiveData<List<Cart_item_entity>> selectAll() {
       // item_repository.selectAll();
        return listLiveData_items;
    }

    public List<Cart_item_entity> selectAll_CartItems() {
        return item_repository.selectAll_CartItems();
    }

    //---------------------- Discount Items Methods in DAO ---------------------------------------
    public void insert_discountItems(Discount_Items_entity discount_items_entity) {
        item_repository.insert_discountItems(discount_items_entity);
    }

    public void update_discountItems(Discount_Items_entity discount_items_entity) {
        item_repository.update_discountItems(discount_items_entity);
    }

    public void delete_discountItems(Discount_Items_entity discount_items_entity) {
        item_repository.delete_discountItems(discount_items_entity);
    }

    public LiveData<List<Discount_Items_entity>> selectAll_discountItems() {
        return item_repository.selectAll_discountItems();
        //return Discount_Items_list;
    }

    public List<Discount_Items_entity> selectAll_discountItems4Test() {
        return item_repository.selectAll_discountItems4Test();
    }

    public Discount_Items_entity selectDiscountItemBy_ID(int pId) {
        return item_repository.selectDiscountItemBy_ID(pId);
    }

    //-----------Get All data from LOCAL (product table) or WebService -------------------
    //--------------------WebService Methods --------------------------------------------
    public Call<ArrayList<Discount_Items_entity>> getDiscountItems() {
        item_repository.getDiscountItems();
        return ListCall_discountItem;
    }

    //--------------------WebService Methods ---(product table) ----------------------
    public Call<ArrayList<Products>> getAllProducts() {
        return item_repository.getAllProducts();
    }

    //-------------------INSERT into Local DB ----------------------------------------
    public long insertInto_productsTable_local(Products products) {
        return item_repository.insertInto_productsTable_local(products);
    }


    //-------------------LOCAL DB (Product Table) ----------------------------------
    public LiveData<List<Products>> selectAll_Products() {
        return item_repository.selectAll_Products();
    }

    public LiveData<List<Products>> selectProduct_ByCategory(String category) {
        return item_repository.selectProduct_ByCategory(category);
    }

    public Products selectProduct_ByID(int pId) {
        return item_repository.selectProduct_ByID(pId);
    }



}
