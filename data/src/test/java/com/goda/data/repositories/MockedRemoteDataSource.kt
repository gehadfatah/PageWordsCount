package com.goda.data.repositories

import com.goda.data.getDataResponse
import com.goda.data.network.models.WordsResponse
import com.goda.data.network.request.RemoteDataSource

class MockedRemoteDataSource() : RemoteDataSource {

    override fun fetchHtmlResponse(): WordsResponse {
        val dataResponse = WordsResponse()
        dataResponse.response = getDataResponse().response
        return dataResponse
    }
}