package com.example.tddpractice.playlist_details

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PlaylistDetailsModule {

    @Singleton
    @Provides
    fun providePlaylistDetailsAPI(retrofit: Retrofit): PlaylistDetailsAPI {
        return retrofit.create(PlaylistDetailsAPI::class.java)
    }
}
