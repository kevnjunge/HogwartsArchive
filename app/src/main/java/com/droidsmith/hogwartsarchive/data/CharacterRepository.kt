package com.droidsmith.hogwartsarchive.data

import androidx.room.withTransaction
import com.droidsmith.hogwartsarchive.network.HarryPotterApi
import kev.njunge.hogwartsarchive.util.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class CharacterRepository @Inject constructor(
    private val api: HarryPotterApi,
    private val db: CharacterDatabase
) {
    private val characterDao = db.characterDao()

    fun getCharacters() = networkBoundResource(
        query = {
            characterDao.getAllCharacters()
        },
        fetch = {
            delay(2000)
            api.getCharacter()
        },
        saveFetchResult = { characters ->
            db.withTransaction {
                characterDao.deleteAllCharacters()
                characterDao.insertCharacters(characters)
            }
        }
    )


}
