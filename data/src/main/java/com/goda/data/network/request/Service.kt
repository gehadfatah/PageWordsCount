package com.goda.data.network.request

import com.goda.data.network.mappers.replaceSpaceSpecialCharacters
import com.goda.data.network.mappers.replacecSpaceSpecialCharacters
import com.goda.data.network.models.WordsResponse
import java.io.IOException
import java.net.URL


class Service(private val requestUrl: String) : RemoteDataSource {

    override fun fetchHtmlResponse(): WordsResponse {
        val content = StringBuilder()
        val dataResponse = WordsResponse()

        try {
          content.append(URL(requestUrl).readText())
            dataResponse.response =
                content.toString().replacecSpaceSpecialCharacters().replaceSpaceSpecialCharacters()
        } catch (e: StackOverflowError) {
            e.printStackTrace()
            throw IOException("Something wrong")
        } catch (e: Exception) {
            e.printStackTrace()
            throw IOException("500")
        } catch (e: Error) {
            e.printStackTrace()
            throw IOException("Something wrong")
        }

        return dataResponse
    }
}