package com.goda.pageword.core.app.di

import com.goda.pageword.domain.usecases.GetWordsUseCase
import com.goda.pageword.presentation.viewmodels.WordsFragmentViewModelFactory

class PresentationDI(dataContainer: DataDi) {

    private val useCase = GetWordsUseCase(dataContainer.repository)

    val viewModelFactory = WordsFragmentViewModelFactory(useCase)
}