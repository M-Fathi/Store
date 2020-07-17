package com.mohammad_fathi.store.di.module;

import android.content.Context;

import com.mohammad_fathi.store.ui.adapter.Adapter_recyclerView_Products_Category;
import com.mohammad_fathi.store.viewmodel.Items_ViewModel;

import dagger.Module;
import dagger.Provides;

@Module
public class Adapter_Products_module {

    private Items_ViewModel items_viewModel;
    private Context context;


    public Adapter_Products_module(Items_ViewModel items_viewModel, Context context) {
        this.items_viewModel = items_viewModel;
        this.context = context;
    }

    @Provides
    public Adapter_recyclerView_Products_Category provide_Adapter_Product(){

        return new Adapter_recyclerView_Products_Category(items_viewModel,context);

    }

}
