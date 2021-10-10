package com.goda.data.repositories

import com.goda.data.local.IWorkLocalDatasource
import com.goda.data.local.models.Word
import com.goda.data.mappers.toDisplayedDataModel
import com.goda.data.mappers.toPresentationWordModel
import com.goda.data.mappers.toWordsList
import com.goda.data.models.WordPresentationModel
import com.goda.data.network.models.WordsResponse
import com.goda.data.network.request.RemoteDataSource
import com.goda.data.network.state_provider.NetworkProvider

interface IWordsRepository {

    fun fetchData(): WordPresentationModel
}

class WordsRepository(
    private val remoteDataSource: RemoteDataSource,
    private val localDatasourceDataSourceI: IWorkLocalDatasource,
    private val networkStateProvider: NetworkProvider
) : IWordsRepository {

    override fun fetchData(): WordPresentationModel {
        val rawData: WordsResponse
        var data: WordPresentationModel? = null



        when (networkStateProvider.isConnected()) {
            true -> {
                rawData = remoteDataSource.fetchHtmlResponse()
                data = rawData.toPresentationWordModel()

                localDatasourceDataSourceI.addWords(data.data.toWordsList())
            }
            false -> {
                data = localDatasourceDataSourceI.getWords().words.toDisplayedDataModel()
            }

        }

        val sortedDataByNameOfWord= data.data.map { it.key to it.value }.sortedBy { it.first  }.toMap()

        return WordPresentationModel(sortedDataByNameOfWord)
    }
}
