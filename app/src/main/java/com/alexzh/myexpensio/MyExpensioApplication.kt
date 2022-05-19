package com.alexzh.myexpensio

import android.app.Application
import com.alexzh.myexpensio.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level

class MyExpensioApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initDI()
    }

    private fun initDI() {
        startKoin {
            androidLogger(Level.ERROR)
            androidContext(this@MyExpensioApplication)
            modules(listOf(appModule))
        }
    }
}