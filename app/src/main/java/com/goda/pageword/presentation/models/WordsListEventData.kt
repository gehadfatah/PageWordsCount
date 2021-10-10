package com.goda.pageword.presentation.models

import com.goda.data.models.WordPresentationModel

sealed class WordsListEventData {
    object LoadingState : WordsListEventData()

    data class SuccessState(val data: WordPresentationModel) : WordsListEventData()

    data class ErrorState(val throwable: Throwable) : WordsListEventData()
}