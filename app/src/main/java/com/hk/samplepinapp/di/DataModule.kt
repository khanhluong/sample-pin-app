package com.hk.samplepinapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import com.hk.samplepinapp.utils.PreferenceManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApplication(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("KEY_NAME", MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideSharePreferences(sharedPreferences: SharedPreferences) =
        PreferenceManager(sharedPreferences)


}