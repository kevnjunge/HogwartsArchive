package com.droidsmith.hogwartsarchive.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import com.droidsmith.hogwartsarchive.data.CharacterRepository
import javax.inject.Inject

@HiltViewModel
class CharacterViewModel @Inject constructor(
    repository: CharacterRepository
) : ViewModel() {

    val characters = repository.getCharacters().asLiveData()


}