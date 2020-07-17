package com.mohammad_fathi.store.di.component;

import com.mohammad_fathi.store.di.module.Adapter_Products_module;
import com.mohammad_fathi.store.ui.Category_Fragment;
import com.mohammad_fathi.store.ui.adapter.Adapter_recyclerView_Products_Category;

import dagger.Component;

@Component(modules = Adapter_Products_module.class)
public interface Adapter_Product_component {

    Adapter_recyclerView_Products_Category getAdapter_Product_component();

    void inject(Category_Fragment category_fragment);
}
