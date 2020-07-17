package com.mohammad_fathi.store.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.snackbar.Snackbar;
import com.mohammad_fathi.store.R;
import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.room.entity.Products;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProductDetails extends AppCompatActivity {

    @BindView(R.id.img_favorite)
    ImageView img_favorite;
    @BindView(R.id.img_detail)
    ImageView img_detail;
    @BindView(R.id.tv_brand_detail)
    TextView tv_brand_detail;
    @BindView(R.id.tv_name_detail)
    TextView tv_name_detail;
    @BindView(R.id.tv_price_detail)
    TextView tv_price_detail;

    Items_ViewModel items_viewModel;
    Discount_Items_entity item;
    Products product;

    int flag = 0, itemId_Discount = 0, itemId_product = 0;
    String str_class_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_details);
        ButterKnife.bind(this);
        items_viewModel = ViewModelProviders.of(this).get(Items_ViewModel.class);


        itemId_Discount = getIntent().getExtras().getInt("item_id_discount");
        itemId_product = getIntent().getExtras().getInt("item_id_product");
        str_class_name = getIntent().getStringExtra("class_name");


        if (itemId_Discount != 0) {
            item = selectDiscountItemBy_ID(itemId_Discount);
        }
        if (itemId_product != 0) {
            product = selectProductBy_ID(itemId_product);
        }


    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("className", str_class_name);
        startActivity(intent);

        this.finish();
    }

    public void btn_close(View view) {
        onBackPressed();
    }

    public void btn_favorite(View view) {

        if (flag == 0) {
            img_favorite.setImageResource(R.drawable.ic_baseline_favorite_red_24);
            flag = 1;
        } else {
            flag = 0;
            img_favorite.setImageResource(R.drawable.ic_outline_favorite_border_24);
        }

    }

    public void addToCart(View view) {
        // ---- Add item into Cart Table -----------------------------------
        try {

            if (itemId_Discount != 0) {
                items_viewModel.insert(new Cart_item_entity(item.getItem_id(), item.getItem_name(), "", item.getItem_price(), 1, 0, item.getItem_pic()));
                Snackbar.make(view, getString(R.string.AddedToCart), Snackbar.LENGTH_SHORT).show();
            }
            if (itemId_product != 0) {
                items_viewModel.insert(new Cart_item_entity(product.getProduct_ID(), product.getProduct_Name_fa(), product.getProduct_Category(), product.getProduct_Price(), 1, 0, product.getProduct_img_url()));
                Snackbar.make(view, getString(R.string.AddedToCart), Snackbar.LENGTH_SHORT).show();
            }

        } catch (Exception e) {
            Snackbar.make(view, R.string.error_duplicate_Insert, Snackbar.LENGTH_SHORT).show();
        }

    }

    public Discount_Items_entity selectDiscountItemBy_ID(int pId) {
        Discount_Items_entity item = items_viewModel.selectDiscountItemBy_ID(pId);
        Picasso.with(this).load(item.getItem_pic()).into(img_detail);
        //img_detail.setImageResource(item.getItem_pic());
        tv_name_detail.setText(item.getItem_name());
        tv_price_detail.setText(String.valueOf(item.getItem_price()));
        return item;
    }

    public Products selectProductBy_ID(int pId) {
        Products item = items_viewModel.selectProduct_ByID(pId);
        Picasso.with(getApplicationContext()).load(item.getProduct_img_url()).into(img_detail);
        tv_name_detail.setText(item.getProduct_Name_fa());
        tv_brand_detail.setText(item.getProduct_BrandName());
        tv_price_detail.setText(String.valueOf(item.getProduct_Price()));
        return item;
    }



}