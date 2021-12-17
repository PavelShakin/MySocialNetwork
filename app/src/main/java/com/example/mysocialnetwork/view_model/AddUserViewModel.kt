package com.example.mysocialnetwork.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.user.User
import kotlinx.coroutines.launch

class AddUserViewModel(application: Application) : AndroidViewModel(application) {

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun insertNewUser(user: User) {
        viewModelScope.launch {
            database.insert(user)
        }
    }
}