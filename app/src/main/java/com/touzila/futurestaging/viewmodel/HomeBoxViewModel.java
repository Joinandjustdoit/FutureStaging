package com.touzila.futurestaging.viewmodel;

import android.databinding.ObservableField;

import com.touzila.futurestaging.entity.BaseEntity;


import javax.inject.Inject;



public class HomeBoxViewModel {

    public final ObservableField<String> name = new ObservableField<>();
    public final ObservableField<String> tag = new ObservableField<>();

    @Inject
    public HomeBoxViewModel() {
    }

    public HomeBoxViewModel(BaseEntity entity) {
    }
}
