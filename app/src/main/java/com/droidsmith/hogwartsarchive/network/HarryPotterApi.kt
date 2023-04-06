package com.droidsmith.hogwartsarchive.network

import com.droidsmith.hogwartsarchive.data.Character
import retrofit2.http.GET

interface HarryPotterApi {

    companion object {
        const val BASE_URL = "https://hp-api.onrender.com/"
    }

    @GET("api/characters")
    suspend fun getCharacter(): List<Character>

}