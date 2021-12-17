package com.example.mysocialnetwork.view_model

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.user.User
import com.example.mysocialnetwork.user.UsersData
import kotlinx.coroutines.launch

class UsersListViewModel(application: Application) : AndroidViewModel(application) {
    private var usersData: UsersData = UsersData()

    private val _userLiveData = MutableLiveData<List<User>>()
    private val userLiveData: LiveData<List<User>> = _userLiveData

    private val database = UserDatabase.getInstance(application).userDatabaseDao

    fun fillUpDatabase() {
        viewModelScope.launch {
            if (database.isEmpty() == null) {
                for (user in usersData.getUsersData()) {
                    database.insert(user)
                }
            }
        }
    }

    fun loadUsersData() {
        viewModelScope.launch {
            _userLiveData.value = database.getAll()
        }
    }

    fun getUsersData(): LiveData<List<User>> {
        return userLiveData
    }
}