package com.dev.webnativebridge.ui.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dev.webnativebridge.model.Profile

class ProfileViewModel : ViewModel() {

    private val _profile = MutableLiveData(Profile("Michael", "London", "32"))

    val profile: LiveData<Profile>
        get() = _profile

    fun setProfileDataFromWeb(jsonData: String) {
        _profile.postValue(Profile.decodeFromJson(jsonData))
    }
}