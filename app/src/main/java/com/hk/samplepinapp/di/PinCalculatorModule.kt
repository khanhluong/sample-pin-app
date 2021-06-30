package com.hk.samplepinapp.di

import com.hk.samplepinapp.repository.PinCalculatorRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@InstallIn(ViewModelComponent::class)
@Module
object PinCalculatorModule {

    @Provides
    @ViewModelScoped
    fun providePinCalculatorRepository(): PinCalculatorRepository {
        return PinCalculatorRepository()
    }
}