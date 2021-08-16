package com.dev.webnativebridge.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class Profile(val name: String, val location: String, val age: String) {

    fun encodeData(): String {
        return Json.encodeToString(this)
    }

    companion object {

        fun decodeFromJson(jsonString: String): Profile {
            return Json.decodeFromString(jsonString)
        }
    }
}