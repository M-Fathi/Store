package com.mohammad_fathi.store.di.module;

import android.content.Context;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.mohammad_fathi.store.model.repository.Item_repository;

import dagger.Module;
import dagger.Provides;

@Module
public class ItemRepository_module {

    Context context;

    public ItemRepository_module(Context context) {
        this.context = context;
    }

    @Provides
    public Item_repository provide_item_repository(){

        return new Item_repository(context);
    }
}
