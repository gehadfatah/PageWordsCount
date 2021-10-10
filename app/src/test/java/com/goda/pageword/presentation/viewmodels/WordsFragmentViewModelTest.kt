package com.goda.pageword.presentation.viewmodels

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import com.goda.data.models.WordPresentationModel
import com.goda.data.repositories.IWordsRepository
import com.goda.pageword.domain.usecases.GetWordsUseCase
import com.goda.pageword.getResponsewordPresentationModel
import com.goda.pageword.presentation.models.WordsListEventData
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import java.util.concurrent.CompletableFuture
import java.util.concurrent.Executors

@RunWith(MockitoJUnitRunner::class)
class WordsFragmentViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    @Mock
    private lateinit var context: Context
    @Mock
    private lateinit var repository: IWordsRepository

    @Mock
    lateinit var observer: Observer<WordsListEventData>

    private var useCase: GetWordsUseCase? = null
    private var viewModel: WordsFragmentViewModel? = null

    @Before
    fun setUp() {
        useCase = GetWordsUseCase(repository)
        viewModel = WordsFragmentViewModel(useCase!!)
        viewModel?.receivedData?.observeForever(observer)
    }

    @After
    fun tearDown() {
        viewModel = null
    }

    @Test
    fun testViewModelFetchDataSuccessfully() {
        Mockito.`when`(useCase?.perform()).thenReturn(getResponsewordPresentationModel())

        viewModel?.fetchMappedResponse()

        Mockito.verify(observer).onChanged(WordsListEventData.LoadingState)

        val data = CompletableFuture<WordPresentationModel>()
        val liveData = CompletableFuture<WordsListEventData>()

        Executors.newSingleThreadExecutor().submit {
            data.complete(useCase?.perform())
            liveData.complete(WordsListEventData.SuccessState(data.get()))
        }

        assertEquals(data.get(), getResponsewordPresentationModel())
        assertEquals(liveData.get(), WordsListEventData.SuccessState(
            getResponsewordPresentationModel()))
    }

}