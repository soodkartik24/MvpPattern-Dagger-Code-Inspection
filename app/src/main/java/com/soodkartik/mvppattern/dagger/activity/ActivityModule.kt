package com.soodkartik.mvppattern.dagger.activity

import androidx.appcompat.app.AppCompatActivity
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val pActivity: AppCompatActivity?) {

    @Provides
    fun provideActivity(): AppCompatActivity? = pActivity


}