package com.goda.data.repositories

import com.goda.data.network.state_provider.NetworkProvider

class MockedNetworkProvider(private val shouldPass: Boolean) :
    NetworkProvider {

    override fun isConnected(): Boolean {
        return shouldPass
    }
}