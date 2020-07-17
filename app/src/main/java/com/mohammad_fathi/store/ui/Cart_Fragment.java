package com.mohammad_fathi.store.ui;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;


import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mohammad_fathi.store.R;
import com.mohammad_fathi.store.model.room.entity.Cart_item_entity;
import com.mohammad_fathi.store.ui.adapter.Adapter_RecyclerView;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Cart_Fragment extends Fragment {
    @BindView(R.id.rv_Cart_Page)
    RecyclerView recyclerViewCart;
    @BindView(R.id.btn_continue_cart)
    MaterialButton btn_continue_cart;
    @BindView(R.id.tv_totalPrice)
    TextView tv_totalPrice;

    View view;
    Context context = getContext();
    List<Cart_item_entity> list;

    Items_ViewModel items_viewModel;
    Adapter_RecyclerView myAdapter;

    int total_price = 0;

    public Cart_Fragment() {
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_cart, container, false);
        ButterKnife.bind(this, view);
        // ----------ViewModel---------------------------------------------------------------------
        items_viewModel = ViewModelProviders.of(this).get(Items_ViewModel.class);

        //-----------RecyclerView initialize ------------------------------------------------------
        recyclerView_initialize();

        //----------Button Continue to buy (Should Be implemented) ------------------------------------------------------
        btn_continue_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        // ---------------------------------------------------------  محاسبه قیمت تمام محصولات داخل سبد خرید ------------------------
        items_viewModel.selectAll().observe(this, new Observer<List<Cart_item_entity>>() {
            @Override
            public void onChanged(List<Cart_item_entity> cart_item_entities_list) {
                for (Cart_item_entity entity : cart_item_entities_list) {
                    total_price = total_price + entity.getItem_price();
                }
                tv_totalPrice.setText(String.valueOf(total_price));
                Log.d("price", "onChanged: " + total_price);
                total_price=0;
            }
        });

        return view;

    }


    //-----------RecyclerView initialize ------------------------------------------------------
    private void recyclerView_initialize() {
        myAdapter = new Adapter_RecyclerView(context, items_viewModel);
        recyclerViewCart.setAdapter(myAdapter);
        getAllData_2();
        recyclerViewCart.setLayoutManager(new GridLayoutManager(context, 1));
        recyclerViewCart.setItemAnimator(new DefaultItemAnimator());
    }


    // ---------- this Method was created only for Test ----------------------------------------
    private List<Cart_item_entity> getData() {
        //--- Get all data from local database by DAO -------------------------------------------
        //List<Cart_item_entity> result = AppDatabase.getInstance(this).getProjectsDAO().selectAll();

        list = new ArrayList<>();
        list.add(new Cart_item_entity(1, "title1", "Shoes", 87000000, 1, R.drawable.pic1, ""));
        list.add(new Cart_item_entity(2, "title2", "Shoes", 250000000, 2, R.drawable.pic2, ""));
        list.add(new Cart_item_entity(3, "title3", "Shoes", 35000000, 3, R.drawable.pic3, ""));
        list.add(new Cart_item_entity(4, "title4", "Shoes", 38000000, 7, R.drawable.pic4, ""));
        list.add(new Cart_item_entity(5, "title5", "Shoes", 37500000, 5, R.drawable.pic5, ""));
        list.add(new Cart_item_entity(6, "title6", "Shoes", 87000000, 6, R.drawable.pic6, ""));
        list.add(new Cart_item_entity(7, "title7", "Shoes", 250000000, 7, R.drawable.pic7, ""));
        list.add(new Cart_item_entity(8, "title8", "Shoes", 35000000, 8, R.drawable.pic8, ""));
        list.add(new Cart_item_entity(9, "title9", "Shoes", 38000000, 9, R.drawable.pic9, ""));
        list.add(new Cart_item_entity(10, "title10", "Shoes", 37500000, 10, R.drawable.pic10, ""));
        myAdapter.setList(list);
        return list;
    }


    //----------- استفاده از live date و برای اینکه لیست محصولات بصورت اتوماتیک اپدیت شود. ----------------------------
    private void getAllData() {
        //LiveData<List<Cart_item_entity>> listCart = items_viewModel.selectAll();
        items_viewModel.selectAll().observe(this, new Observer<List<Cart_item_entity>>() {
            @Override
            public void onChanged(List<Cart_item_entity> cart_item_entities_list) {
                myAdapter.setList((List<Cart_item_entity>) cart_item_entities_list);
            }
        });
    }

    private void getAllData_2() {
        List<Cart_item_entity> listCart = items_viewModel.selectAll_CartItems();
        myAdapter.setList((List<Cart_item_entity>) listCart);

    }



}
