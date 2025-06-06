
package com.example.yaroslavhorach.database.di

import android.content.Context
import androidx.room.Room
import com.example.yaroslavhorach.database.PomoDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal object DatabaseModule {

    @Provides
    @Singleton
    fun providesPomoDatabase(@ApplicationContext context: Context): PomoDatabase = Room.databaseBuilder(
        context,
        PomoDatabase::class.java,
        "pomo-database",
    ).build()
}
