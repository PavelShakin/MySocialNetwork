package com.example.mysocialnetwork.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.user.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun loadUserData(id: Int) {
        _userLiveData.value = database.get(id)
    }
}
