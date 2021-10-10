package com.goda.pageword.domain.usecases

import com.goda.data.models.WordPresentationModel
import com.goda.data.repositories.IWordsRepository

class GetWordsUseCase(private val WordsRepository: IWordsRepository) {

    fun perform(): WordPresentationModel {
        return WordsRepository.fetchData()
    }
}