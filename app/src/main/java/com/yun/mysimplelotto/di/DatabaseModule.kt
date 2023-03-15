package com.yun.mysimplelotto.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.yun.mysimplelotto.db.DB
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {
    var INSTANCE: DB? = null
    val NAME = DB::class.java.simpleName

    @Singleton
    @Provides
    fun getInstance(context: Context): DB {
        if (INSTANCE == null) {
            synchronized(DB::class) {
                INSTANCE =
                    Room.databaseBuilder(context.applicationContext, DB::class.java, "$NAME.db")
                        .fallbackToDestructiveMigration()
                        .build()
            }
        }
        return INSTANCE!!
    }

    @Singleton
    @Provides
    fun provideContext(application: Application): Context =
        application.applicationContext
}