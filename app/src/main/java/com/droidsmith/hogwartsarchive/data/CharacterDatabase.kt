package com.droidsmith.hogwartsarchive.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.droidsmith.hogwartsarchive.data.Character
import com.droidsmith.hogwartsarchive.data.CharacterDao

@Database(entities = [Character::class], version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun characterDao(): CharacterDao

}