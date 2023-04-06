package com.droidsmith.hogwartsarchive.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import com.droidsmith.hogwartsarchive.data.CharacterDatabase
import com.droidsmith.hogwartsarchive.network.HarryPotterApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit =
        Retrofit.Builder()
            .baseUrl(HarryPotterApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    @Provides
    @Singleton
    fun provideCharacterApi(retrofit: Retrofit): HarryPotterApi =
        retrofit.create(HarryPotterApi::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): CharacterDatabase =
        Room.databaseBuilder(app, CharacterDatabase::class.java, "characters_database")
            .build()

}