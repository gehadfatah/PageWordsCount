package com.goda.pageword.core.app.di

import android.content.Context
import com.goda.data.local.WordsLocalDatasource
import com.goda.data.local.IWorkLocalDatasource
import com.goda.data.network.request.RemoteDataSource
import com.goda.data.network.request.Service
import com.goda.data.network.state_provider.NetworkProvider
import com.goda.data.network.state_provider.NetworkStateProvider
import com.goda.data.repositories.WordsRepository

class DataDi(context: Context) {

    private val networkProvider: NetworkProvider = NetworkStateProvider(context)

    private val apiService: RemoteDataSource = Service("https://instabug.com")

    private val localDatasourceDataSourceI: IWorkLocalDatasource = WordsLocalDatasource(context)

    val repository: WordsRepository =
        WordsRepository(apiService, localDatasourceDataSourceI, networkProvider)
}