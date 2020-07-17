package com.mohammad_fathi.store.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;
//import androidx.appcompat.widget.Toolbar;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.button.MaterialButton;
import com.mohammad_fathi.store.R;
import com.mohammad_fathi.store.model.room.entity.Discount_Items_entity;
import com.mohammad_fathi.store.model.webservice.WebServiceAPI;
import com.mohammad_fathi.store.ui.Cart_Fragment;
import com.mohammad_fathi.store.ui.Category_Fragment;
import com.mohammad_fathi.store.ui.Home_Fragment;
import com.mohammad_fathi.store.ui.MyProfile_Fragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigationView;

    int height, width;
    Context context = this;

    String str_fragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        //getDisplayDimensions();

        // ------------------------------------ فرگمنت شروع برنامه ----------------------------------
        //----------------------------------- Start Fragment ---------------------------------
        load_Fragment(new Home_Fragment(height, width, context));


        //---------------------------- برگشت به فرگمنت قبلی از اکتیویتی ProductDetails -----------------------
        //-----------------------Back to specific Fragment from ProductDetails Activity-----------

        str_fragment = getIntent().getStringExtra("className");

        if (str_fragment != null) {

            switch (str_fragment) {
                case "Category_Fragment":
                    load_Fragment(new Category_Fragment());
                    break;
                case "Home_Fragment":
                    load_Fragment(new Home_Fragment());
                    break;
                case "Cart_Fragment":
                    load_Fragment(new Cart_Fragment());
                    break;
                default:
                    break;
            }
        }


// ----------------On Click on Bottom Navigation Items ---------------------------------
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.bottomNavigationMyProfileMenuId:
                        load_Fragment(new MyProfile_Fragment());
                        //Toast.makeText(MainActivity.this, "My Profile", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bottomNavigationCartMenuId:
                        load_Fragment(new Cart_Fragment());
                        //Toast.makeText(MainActivity.this, "Cart", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bottomNavigationCategoryMenuId:
                        load_Fragment(new Category_Fragment());
                        // Toast.makeText(MainActivity.this, "Category", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.bottomNavigationHomeMenuId:
                        load_Fragment(new Home_Fragment());
                        //Toast.makeText(MainActivity.this, "Home", Toast.LENGTH_SHORT).show();
                        return true;

                }
                return false;
            }
        });

    }

    private void load_Fragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();

    }

    private void getDisplayDimensions() {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        height = displayMetrics.heightPixels;
        width = displayMetrics.widthPixels;
    }

    private void webService_GET() {
        String BASE_URL = "http://www.mocky.io";

        //1) Create Instance
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        //2) Create API
        WebServiceAPI api = retrofit.create(WebServiceAPI.class);

        //3) Create Requests
        Call<ArrayList<Discount_Items_entity>> list_DiscountItems_request = api.getDiscountItems();
        // request in Queue Methods
        list_DiscountItems_request.enqueue(new Callback<ArrayList<Discount_Items_entity>>() {

            // در صورت موفقیت وارد این متد می شود
            @Override
            public void onResponse(Call<ArrayList<Discount_Items_entity>> call, Response<ArrayList<Discount_Items_entity>> response) {
                ArrayList<Discount_Items_entity> list_body_discount = response.body();


                String str = "";
                for (Discount_Items_entity discount_items_entity : list_body_discount) {
                    str = str + "id: " + discount_items_entity.getItem_id() + " Item Name: " + discount_items_entity.getItem_name() + " \n";
                    Log.d("discount", str);

                }

            }

            // در صورت بروز مشکل وارد این متد می شود
            @Override
            public void onFailure(Call<ArrayList<Discount_Items_entity>> call, Throwable t) {
                Toast.makeText(context, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}