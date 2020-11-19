package com.a.ayc

import com.a.ayc.user.data.ValidateInput
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent
import javax.inject.Singleton

@InstallIn(ApplicationComponent::class)
@Module
object MyModule {

    @Singleton
    @Provides
    fun provideValidation() : ValidateInput {
        return ValidateInput()
    }
}