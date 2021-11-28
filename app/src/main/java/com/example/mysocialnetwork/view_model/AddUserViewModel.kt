package com.example.mysocialnetwork.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.user.User

class AddUserViewModel(application: Application) : AndroidViewModel(application) {

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun insertNewUser(user: User) {
        database.insert(user)
    }
}