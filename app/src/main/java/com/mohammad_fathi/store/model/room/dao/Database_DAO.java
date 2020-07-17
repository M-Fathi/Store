package com.mohammad_fathi.store.model.room.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.room.entity.Products;

import java.util.List;

@Dao
public interface Database_DAO {

    // ----- Cart Items DAO --------------------------------------------------------
    @Insert
    void insert (Cart_item_entity cart_item_entity);

    @Update
    void update(Cart_item_entity cart_item_entity);

    @Delete
    void delete(Cart_item_entity cart_item_entity);

    @Query("select * from Cart_item_entity")
    LiveData<List<Cart_item_entity>> selectAll();

    @Query("select * from Cart_item_entity")
    List<Cart_item_entity> selectAll_CartItems();

    // --------------- Discount Items DAO ------------------------------------------
    @Insert
    void insert_discountItems (Discount_Items_entity discount_items_entity);

    @Update
    void update_discountItems(Discount_Items_entity discount_items_entity);

    @Delete
    void delete_discountItems(Discount_Items_entity discount_items_entity);

    @Query("select * from Discount_Items_entity")
    LiveData<List<Discount_Items_entity>> selectAll_discountItems();

    @Query("select * from Discount_Items_entity")
    List<Discount_Items_entity> selectAll_discountItems4Test();

    @Query("select * from Discount_Items_entity where item_id = :pId")
    Discount_Items_entity selectDiscountItemBy_ID(int pId);

    //------------------ Products Table (Entity) ---------------------------------

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long insertInto_productsTable_local (Products products);

    @Query("select * from Products")
    LiveData<List<Products>> selectAll_Products();

    @Query("select * from Products where product_Category Like :category")
    LiveData<List<Products>> selectProduct_ByCategory(String category);

    @Query("select * from Products where product_ID = :pId")
    Products selectProduct_ByID(int pId);




}
