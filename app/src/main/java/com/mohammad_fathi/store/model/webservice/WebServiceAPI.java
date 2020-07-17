package com.mohammad_fathi.store.model.webservice;

import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.room.entity.Products;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface WebServiceAPI {

    String address_DiscountItems = "/v3/62fabdd3-85e7-4b82-a5c5-f62d15709aa3";
    String address_allProducts = "/v3/e6116a28-e381-4447-8060-aaea01191737";
    String address_post = "/";

    @GET(address_DiscountItems)
    Call<ArrayList<Discount_Items_entity>> getDiscountItems();

    @GET(address_allProducts)
    Call<ArrayList<Products>> getAllProducts();

    // باید برای ارسال یک دسته از آیتم هایی جهت خرید انتخاب شده به روز شود
    @POST(address_post)
    @FormUrlEncoded
    Call<Cart_item_entity> send(@Field("id") int id, @Field("item_name") String item_name, @Field("item_quantity") int item_quantity);


}


//Product JSON: https://run.mocky.io/v3/e6116a28-e381-4447-8060-aaea01191737
//Discount JSON: https://run.mocky.io/v3/62fabdd3-85e7-4b82-a5c5-f62d15709aa3