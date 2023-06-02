package com.kalex.bookyouu_app.di

import android.content.Context
import com.google.firebase.auth.FirebaseAuth
import com.kalex.bookyouu_app.MainApplication
import com.kalex.bookyouu_app.common.Constants
import com.kalex.bookyouu_app.data.remote.UserRetroApi
import com.kalex.bookyouu_app.data.repository.AuthenticationRepositoryImp
import com.kalex.bookyouu_app.data.repository.UserRepositoryImpl
import com.kalex.bookyouu_app.domain.repository.AuthenticationRepository
import com.kalex.bookyouu_app.domain.repository.UserRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideSpApi(): UserRetroApi {
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(UserRetroApi::class.java)
    }

    @Provides
    @Singleton
    fun provideUserRepository(api: UserRetroApi): UserRepository {
        return UserRepositoryImpl(api)
    }

    @Singleton
    @Provides
    fun provideApplication(@ApplicationContext app: Context): MainApplication {
        return app as MainApplication
    }

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun providesAuthRepository(impl: AuthenticationRepositoryImp): AuthenticationRepository = impl
}
