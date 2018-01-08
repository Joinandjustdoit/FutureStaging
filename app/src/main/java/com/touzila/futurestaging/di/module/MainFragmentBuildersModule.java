package com.touzila.futurestaging.di.module;

import com.touzila.futurestaging.view.home.HomeFragment;
import com.touzila.futurestaging.view.my.MyFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by liu on 2017/12/28.
 */

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract HomeFragment contributeHomeFragment();

    @ContributesAndroidInjector
    abstract MyFragment contributeMyFragment();
}
