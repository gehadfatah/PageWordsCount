package com.goda.pageword.core.app

import android.app.Application
import com.goda.pageword.core.app.di.AppDi

class PageCountWordsApplication : Application() {

    lateinit var appDi: AppDi

    override fun onCreate() {
        super.onCreate()

        appDi = AppDi(this)
    }
}