package com.tthieu.myhelsinki.common.data.di

import com.tthieu.myhelsinki.common.data.preferences.MyHelsinkiPreferences
import com.tthieu.myhelsinki.common.data.preferences.Preferences
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PreferencesModule {

    @Binds
    abstract fun providePreferences(preferences: MyHelsinkiPreferences): Preferences
}