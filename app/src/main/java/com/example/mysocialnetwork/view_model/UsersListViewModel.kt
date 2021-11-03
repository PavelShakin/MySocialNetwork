package com.example.mysocialnetwork.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.user.User
import com.example.mysocialnetwork.user.UsersData

class UsersListViewModel(application: Application) : AndroidViewModel(application) {
    private var usersData: UsersData = UsersData()

    private val _userLiveData = MutableLiveData<List<User>>()
    val userLiveData: LiveData<List<User>> = _userLiveData

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun fillUpDatabase() {
        if (database.isEmpty() == null) {
            for (user in usersData.usersList) {
                database.insert(user)
            }
        }
    }

    fun loadUsersData() {
        _userLiveData.value = database.getAll()
    }
}