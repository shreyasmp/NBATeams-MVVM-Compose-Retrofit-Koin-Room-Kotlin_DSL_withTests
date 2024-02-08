package com.shreyasmp.nbateams.di

import android.content.Context
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.shreyasmp.nbateams.database.NBATeamsDao
import com.shreyasmp.nbateams.database.NBATeamsDatabase
import com.shreyasmp.nbateams.repository.NBATeamsRepository
import com.shreyasmp.nbateams.repository.NBATeamsRepositoryImpl
import com.shreyasmp.nbateams.service.NBATeamService
import com.shreyasmp.nbateams.viewmodel.NBATeamsViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val apiModule = module {

    fun provideNBATeamsApi(retrofit: Retrofit): NBATeamService {
        return retrofit.create(NBATeamService::class.java)
    }
    single { provideNBATeamsApi(get()) }
}

val networkModule = module {

    val NBA_TEAMS_SERVICE_BASE_URL = "https://raw.githubusercontent.com"
    val timeOut = 60

    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    }

    fun provideOkHttpClient(
        httpLoggingInterceptor: HttpLoggingInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .connectTimeout(timeOut.toLong(), TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
    }

    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(NBA_TEAMS_SERVICE_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(okHttpClient)
            .build()
    }

    single { provideHttpLoggingInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
}

val repositoryModule = module {
    fun provideNBATeamsRepository(
        service: NBATeamService,
        teamDao: NBATeamsDao,
        context: Context
    ): NBATeamsRepository {
        return NBATeamsRepositoryImpl(service, teamDao, context)
    }
    single { provideNBATeamsRepository(get(), get(), androidContext()) }
}

val viewModelModule = module {
    viewModel {
        NBATeamsViewModel(repository = get())
    }
}

val roomDatabaseModule = module {
    single { Room.databaseBuilder(get(), NBATeamsDatabase::class.java, "NBATeamDB").build() }
    single { get<NBATeamsDatabase>().getNBATeamsDao() }
}
