package com.mohammad_fathi.store.di.component;

import com.mohammad_fathi.store.di.module.ItemRepository_module;
import com.mohammad_fathi.store.model.repository.Item_repository;

import dagger.Component;

@Component(modules = ItemRepository_module.class)
public interface ItemRepository_component {


    Item_repository getItem_repository();
}
