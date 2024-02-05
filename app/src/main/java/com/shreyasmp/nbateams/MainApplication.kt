package com.shreyasmp.nbateams

import android.app.Application
import com.shreyasmp.nbateams.di.apiModule
import com.shreyasmp.nbateams.di.networkModule
import com.shreyasmp.nbateams.di.repositoryModule
import com.shreyasmp.nbateams.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MainApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        // Start Dependency Injection using koin
        startKoin {
            androidLogger()
            androidContext(this@MainApplication)
            modules(
                apiModule,
                networkModule,
                repositoryModule,
                viewModelModule
            )
        }
    }
}