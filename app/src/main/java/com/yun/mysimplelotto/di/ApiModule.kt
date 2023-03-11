package com.yun.mysimplelotto.di

import com.yun.mysimplelotto.data.Api
import com.yun.mysimplelotto.data.repository.ApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)

object ApiModule {

    @Singleton
    @Provides
    fun providerApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }

    @Singleton
    @Provides
    fun providerRepository(api: Api) = ApiRepository(api)
}