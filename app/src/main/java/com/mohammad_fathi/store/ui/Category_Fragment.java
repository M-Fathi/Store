package com.mohammad_fathi.store.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.material.snackbar.Snackbar;
import com.mohammad_fathi.store.R;
import com.mohammad_fathi.store.di.component.DaggerAdapter_Product_component;
import com.mohammad_fathi.store.di.module.Adapter_Products_module;
import com.mohammad_fathi.store.model.room.entity.Products;
import com.mohammad_fathi.store.ui.adapter.Adapter_recyclerView_Products_Category;
import com.mohammad_fathi.store.ui.adapter.Adapter_recyclerView_discountItems_FragHome;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Category_Fragment extends Fragment {

    @BindView(R.id.rv_Cat_1)
    RecyclerView rv_Cat_1;

    @BindView(R.id.rv_Cat_2)
    RecyclerView rv_Cat_2;

    @BindView(R.id.rv_Cat_3)
    RecyclerView rv_Cat_3;

    @BindView(R.id.img_gardoone)
    ImageView img_gardoone;

    Items_ViewModel items_viewModel;
    @Inject
    Adapter_recyclerView_Products_Category myAdapter1, myAdapter2, myAdapter3;

    View view;


    public Category_Fragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        items_viewModel = ViewModelProviders.of(this).get(Items_ViewModel.class);


        recyclerView_initialize(rv_Cat_1, "Laptop", myAdapter1);
        recyclerView_initialize(rv_Cat_2, "Tutorial", myAdapter2);
        recyclerView_initialize(rv_Cat_3, "Mobile", myAdapter3);


        String url_img_gardoone = "https://www.digikala.com/mag/wp-content/uploads/2017/05/main2.gif";
        Picasso.with(getActivity()).load(url_img_gardoone).into(img_gardoone);


        return view;
    }

    public void recyclerView_initialize(RecyclerView recyclerView, String category, Adapter_recyclerView_Products_Category myAdapter) {
        //-----------RecyclerView initialize ------------------------------------------------------
        //---------- using Daager instead of ( myAdapter = new Adapter_recyclerView_Products_Category(items_viewModel, getActivity());  )


       // DaggerAdapter_Product_component.builder().adapter_Products_module(new Adapter_Products_module(items_viewModel, getActivity())).build().inject(this);
        myAdapter = new Adapter_recyclerView_Products_Category(items_viewModel, getActivity());
        recyclerView.setAdapter(myAdapter);
        setProductDataToAdapter(category, myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

    }


    private void setProductDataToAdapter(String category, Adapter_recyclerView_Products_Category myAdapter) {

        LiveData<List<Products>> DataList_ProductByCategory = items_viewModel.selectProduct_ByCategory(category);
        if (DataList_ProductByCategory != null) {
            DataList_ProductByCategory.observe(this, new Observer<List<Products>>() {
                @Override
                public void onChanged(List<Products> products) {

                    myAdapter.setList(products);

                    for (Products product : products) {
                        Log.d("category", "onChanged: " + product.getProduct_BrandName() + " / " + product.getProduct_Category());
                    }

                }
            });
        } else {
            Snackbar.make(view, R.string.connection_problem, Snackbar.LENGTH_LONG);
        }
    }
}