package com.goda.data.network.state_provider

interface NetworkProvider {
    fun isConnected(): Boolean
}