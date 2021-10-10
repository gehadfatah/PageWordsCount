package com.goda.data.repositories

import com.goda.data.getResponsewordPresentationModel
import com.goda.data.local.IWorkLocalDatasource
import com.goda.data.local.models.DataResponse
import com.goda.data.local.models.Word
import com.goda.data.mappers.toWordsList


class MockedLocalDataSource(
) : IWorkLocalDatasource {


    override fun addWords(words: List<Word>) {
    }

    override fun getWords(): DataResponse {
        return  DataResponse(getResponsewordPresentationModel().data.toWordsList())
    }

    override fun isTableEmpty(): Boolean {
    return false
    }
}