package com.goda.pageword.domain.usecases

import com.goda.data.repositories.IWordsRepository
import com.goda.pageword.getResponsewordPresentationModel
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class GetWordsUseCaseTest{

    @Mock
    private lateinit var repository: IWordsRepository

    private var useCase: GetWordsUseCase? = null

    @Before
    fun setUp() {
        useCase = GetWordsUseCase(repository)
    }

    @After
    fun tearDown() {
        useCase = null
    }

    @Test
    fun testExecuteRepoData() {
        Mockito.`when`(repository.fetchData()).thenReturn(getResponsewordPresentationModel())

        val receivedData = useCase?.perform()

        assertEquals(receivedData,getResponsewordPresentationModel())

        Mockito.verify(repository).fetchData()
        Mockito.verifyNoMoreInteractions(repository)
    }
}