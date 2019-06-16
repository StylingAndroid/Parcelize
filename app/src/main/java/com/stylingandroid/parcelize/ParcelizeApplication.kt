package com.stylingandroid.parcelize

import android.app.Application
import timber.log.Timber

class ParcelizeApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
