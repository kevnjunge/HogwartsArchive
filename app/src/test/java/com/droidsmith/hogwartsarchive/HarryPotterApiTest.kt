package com.droidsmith.hogwartsarchive

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.droidsmith.hogwartsarchive.network.HarryPotterApi
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HarryPotterApiTest {

    private lateinit var api: HarryPotterApi

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        val retrofit = Retrofit.Builder()
            .baseUrl(HarryPotterApi.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        api = retrofit.create(HarryPotterApi::class.java)
    }

    @Test
    fun `getCharacter should return a list of characters`() = runBlocking {
        val response = api.getCharacter()

        assertNotNull(response)

    }
}