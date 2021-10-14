package com.example.mysocialnetwork.view_model

import android.content.Context
import android.content.Intent
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mysocialnetwork.activity.UserActivity
import com.example.mysocialnetwork.user.UsersData

class UserViewModel : ViewModel() {
    private val usersData: UsersData = UsersData()

    private val _userLiveData = MutableLiveData<UsersData>()
    val userLiveData: LiveData<UsersData> = _userLiveData

    fun loadUserData() {
        _userLiveData.value = usersData
    }

    fun setListener(layout: ConstraintLayout, id: Int, context: Context) {
        layout.setOnClickListener {
            val intent = Intent(context, UserActivity::class.java)
            intent.putExtra("id", id)
            context.startActivity(intent)
        }
    }
}