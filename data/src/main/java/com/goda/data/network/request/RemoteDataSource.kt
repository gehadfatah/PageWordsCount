package com.goda.data.network.request
import com.goda.data.network.models.WordsResponse

interface RemoteDataSource {
    fun fetchHtmlResponse(): WordsResponse
}