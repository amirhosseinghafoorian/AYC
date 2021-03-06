package com.a.ayc.hiltModules

import com.a.domainmodule.inputValidation.ChatIdDecider
import com.a.domainmodule.inputValidation.ValidateInput
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
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
    fun provideValidation(): ValidateInput {
        return ValidateInput()
    }

    @Singleton
    @Provides
    fun provideChatIdDecider(): ChatIdDecider {
        return ChatIdDecider()
    }

    @Singleton
    @Provides
    fun provideAuth(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Singleton
    @Provides
    fun provideDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Singleton
    @Provides
    fun provideDatabaseReference(): DatabaseReference {
        return provideDatabase().reference
    }
}