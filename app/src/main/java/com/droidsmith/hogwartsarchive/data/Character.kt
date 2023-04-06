package com.droidsmith.hogwartsarchive.data

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

@Entity(tableName = "characters")
@TypeConverters(StringListConverter::class, WandTypeConverter::class)
data class Character(
    @PrimaryKey val actor: String,
    val alive: Boolean,
    val alternate_actors: List<String>,
    val alternate_names: List<String>,
    val ancestry: String,
    val dateOfBirth: String?,
    val eyeColour: String,
    val gender: String,
    val hairColour: String,
    val hogwartsStaff: Boolean,
    val hogwartsStudent: Boolean,
    val house: String,
    val id: String,
    val image: String,
    val name: String,
    val patronus: String,
    val species: String,
    @Embedded val wand: Wand,
    val wizard: Boolean,
    val yearOfBirth: Int

)

class StringListConverter {
    @TypeConverter
    fun fromString(value: String?): List<String>? {
        return value?.let { it.split(",") }
    }

    @TypeConverter
    fun toString(list: List<String>?): String? {
        return list?.joinToString(",")
    }
}

class WandTypeConverter {
    @TypeConverter
    fun fromString(value: String): Wand {
        val type = object : TypeToken<Wand>() {}.type
        return Gson().fromJson(value, type)
    }

    @TypeConverter
    fun fromWand(wand: Wand): String {
        return Gson().toJson(wand)
    }
}
