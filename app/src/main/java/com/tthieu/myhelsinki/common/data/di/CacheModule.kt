package com.tthieu.myhelsinki.common.data.di

import android.content.Context
import androidx.room.Room
import com.tthieu.myhelsinki.common.data.cache.Cache
import com.tthieu.myhelsinki.common.data.cache.EventSearchDatabase
import com.tthieu.myhelsinki.common.data.cache.RoomCache
import com.tthieu.myhelsinki.common.data.cache.daos.EventsDao
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CacheModule {

    @Binds
    abstract fun bindCache(cache: RoomCache): Cache

    companion object {

        @Provides
        @Singleton
        fun provideDatabase(
            @ApplicationContext context: Context
        ): EventSearchDatabase {
            return Room.databaseBuilder(
                context,
                EventSearchDatabase::class.java,
                "eventsearch_3.db"
            )
                .build()
        }

        @Provides
        fun provideEventsDao(
            eventSearchDatabase: EventSearchDatabase
        ): EventsDao = eventSearchDatabase.eventsDao()
    }
}