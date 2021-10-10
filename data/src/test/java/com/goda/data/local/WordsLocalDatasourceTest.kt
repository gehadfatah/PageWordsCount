package com.goda.data.local

import android.content.Context
import com.goda.data.getResponsewordPresentationModel
import com.goda.data.getwordsList
import com.goda.data.mappers.toDisplayedDataModel
import com.goda.data.mappers.toWordsList
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)

class WordsLocalDatasourceTest{
    @Mock
    lateinit var context: Context
    private var localSource: IWorkLocalDatasource? = null
    @Before
    fun setUp() {
        localSource = WordsLocalDatasource(context)
    }

    @After
    fun tearDown() {
        localSource = null
    }
    @Test
    fun testConvertingDisplayedDataToWordsList() {
        assertEquals(getResponsewordPresentationModel().data.toWordsList(), getwordsList())
    }

    @Test
    fun testConvertingWordsListToDisplayedData() {
        assertEquals(getwordsList().toDisplayedDataModel(), getResponsewordPresentationModel())
    }



}