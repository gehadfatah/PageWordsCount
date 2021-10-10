package com.goda.data.repositories

import com.goda.data.getContent
import com.goda.data.getDataResponse
import com.goda.data.getResponsewordPresentationModel
import com.goda.data.network.mappers.replaceSpaceSpecialCharacters
import org.junit.Assert.*
import org.junit.Test

class WordsRepositoryTest  {


    private var remoteDataSource =
        MockedRemoteDataSource()
    private var localDataSource =
        MockedLocalDataSource()

    private lateinit var networkProvider: MockedNetworkProvider

    private var repository: IWordsRepository? = null


    @Test
    fun testIgnoreSpecialCharacters() {
        assertEquals(
            getContent().replaceSpaceSpecialCharacters(),
            getDataResponse().response
        )
    }

    @Test
    fun testLocalResponse() {
        networkProvider =
            MockedNetworkProvider(false)
        repository = WordsRepository(remoteDataSource, localDataSource, networkProvider)
        val data = repository?.fetchData()

        assertEquals(data, getResponsewordPresentationModel())
    }

    @Test
    fun testFetchRemoteData() {
        networkProvider =
            MockedNetworkProvider(true)
        repository = WordsRepository(remoteDataSource, localDataSource, networkProvider)
        val data = repository?.fetchData()
        assertEquals(data, getResponsewordPresentationModel())
    }
}

