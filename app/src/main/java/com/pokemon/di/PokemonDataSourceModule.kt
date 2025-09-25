package com.pokemon.di

import android.content.Context
import com.pokemon.data.datasource.PokemonLocalSource
import com.pokemon.data.datasource.PokemonRemoteSource
import com.pokemon.datasource.api.datasource.PokemonDao
import com.pokemon.datasource.api.datasource.PokemonLocalDataSource
import com.pokemon.datasource.api.datasource.PokemonRemoteDataSource
import com.pokemon.datasource.api.mapper.PokemonApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonDetailModelApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDataMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelApiToDatabaseMapper
import com.pokemon.datasource.api.mapper.PokemonInfoModelDatabaseToDataMapper
import com.pokemon.datasource.api.service.PokemonApiService
import com.pokemon.util.NetworkConnectionInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class PokemonDataSourceModule {

    @Provides
    fun providerPokemonRemoteDataSource(
        pokemonApiService: PokemonApiService,
        pokemonInfoModelApiToDataMapper: PokemonInfoModelApiToDataMapper,
        pokemonDetailModelApiToDataMapper: PokemonDetailModelApiToDataMapper,
        pokemonInfoModelApiToDatabaseMapper: PokemonInfoModelApiToDatabaseMapper,
        pokemonDao: PokemonDao
    ): PokemonRemoteSource = PokemonRemoteDataSource(
        pokemonApiService,
        pokemonInfoModelApiToDataMapper,
        pokemonDetailModelApiToDataMapper,
        pokemonInfoModelApiToDatabaseMapper,
        pokemonDao
    )

    @Provides
    fun providerPokemonLocalDataSource(
        pokemonInfoModelDatabaseToDataMapper: PokemonInfoModelDatabaseToDataMapper,
        pokemonDao: PokemonDao
    ): PokemonLocalSource = PokemonLocalDataSource(
        pokemonInfoModelDatabaseToDataMapper,
        pokemonDao
    )

    @Provides
    fun providerPokemonInfoModelApiToDataMapper() = PokemonInfoModelApiToDataMapper()

    @Provides
    fun providerPokemonDetailModelApiToDataMapper() = PokemonDetailModelApiToDataMapper()

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(PokemonApiService.BASE_URL)
            .client(client)
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()

    }

    @Singleton
    @Provides
    fun provideService(retrofit: Retrofit): PokemonApiService {
        return retrofit.create(PokemonApiService::class.java)
    }

    @Singleton
    @Provides
    fun providesOkHttpClient(@ApplicationContext context: Context): OkHttpClient {
        val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        return OkHttpClient.Builder()
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(3, TimeUnit.MINUTES)
            .addInterceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Content-Type", "application/json")
                    .build()
                chain.proceed(newRequest)
            }
            .addInterceptor(NetworkConnectionInterceptor(context))
            .addInterceptor(interceptor)
            .build()
    }
}
