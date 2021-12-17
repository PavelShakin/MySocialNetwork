package com.example.mysocialnetwork.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.user.User
import kotlinx.coroutines.launch

class EditUserViewModel(application: Application) : AndroidViewModel(application) {

    private val _userLiveData = MutableLiveData<User>()
    val userLiveData: LiveData<User> = _userLiveData

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun loadUserData(id: Int) {
        viewModelScope.launch {
            _userLiveData.value = database.get(id)
        }
    }

    fun updateUserInfo(user: User) {
        viewModelScope.launch {
            database.update(user)
        }
    }
}