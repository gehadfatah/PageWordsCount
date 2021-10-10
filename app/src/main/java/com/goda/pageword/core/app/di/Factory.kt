package com.goda.pageword.core.app.di

interface Factory<T> {

    fun create(): T
}