package com.goda.data.mappers

import com.goda.data.local.models.Word
import com.goda.data.models.WordPresentationModel
import com.goda.data.network.mappers.toStringList
import com.goda.data.network.models.WordsResponse

internal fun Map<String, Int>.toWordsList( ):List<Word> =

    this.toList().map { pair: Pair<String, Int> ->  Word(pair.first,pair.second)  }

internal fun WordsResponse.toPresentationWordModel(): WordPresentationModel {
    val data = HashMap<String, Int>()
    this.response.toStringList().filter {
        it.length > 1
    }.forEach {
        var count = data[it] ?: 0
        data[it] = ++count
    }

    return WordPresentationModel(data)
}
internal fun List<Word>.toDisplayedDataModel(): WordPresentationModel {
    val dataMap = HashMap<String, Int>()

    this.forEach {
        dataMap += it.content to it.repeatedNo
    }

    return WordPresentationModel(dataMap)
}