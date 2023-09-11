package com.cherlan.quote.di

import com.cherlan.quote.data.remote.ApiCall
import com.cherlan.quote.data.remote.ApiDetails
import com.cherlan.quote.data.repository.Repository
import com.cherlan.quote.data.repository.RepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiCall(retrofit: Retrofit): ApiCall{
        return retrofit.create(ApiCall::class.java)

    }

    @Provides
    @Singleton
    fun provideOkHTTPClient(): OkHttpClient{
        return OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            })
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofitInstance(okHttpClient: OkHttpClient): Retrofit{
        return Retrofit.Builder()
            .baseUrl(ApiDetails.BaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()

    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth{
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideRepository(
        apiCall: ApiCall,
        firebaseAuth: FirebaseAuth
    ): Repository{

        return RepositoryImpl(apiCall, firebaseAuth)

    }

}
