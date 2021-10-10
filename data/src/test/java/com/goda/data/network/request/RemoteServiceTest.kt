package com.goda.data.network.request

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class RemoteServiceTest {



    private var remoteSource: RemoteDataSource? = null

    @Before
    fun setUp() {
        remoteSource = Service("")
    }

    @After
    fun tearDown() {
        remoteSource = null
    }


}