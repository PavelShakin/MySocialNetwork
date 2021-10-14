package com.example.mysocialnetwork.activity

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.view_model.UserViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.loadUserData()
        val id = intent.extras?.getInt("id")

        viewModel.userLiveData.observe(this, Observer {
            val imgViewIcon = findViewById<ImageView>(
                resources.getIdentifier("imgProfileIcon", "id", packageName)
            )
            imgViewIcon.setImageDrawable(
                getDrawable(
                    resources.getIdentifier(
                        it.usersList[id!!].profilePhoto,
                        null,
                        packageName
                    )
                )
            )

            val txtViewName = findViewById<TextView>(
                resources.getIdentifier("txtUserName", "id", packageName)
            )
            txtViewName.text = it.usersList[id].name

            val txtViewWasOnline = findViewById<TextView>(
                resources.getIdentifier("txtWasOnline", "id", packageName)
            )
            txtViewWasOnline.text = it.usersList[id].wasOnline

            val txtViewStatus = findViewById<TextView>(
                resources.getIdentifier("txtStatus", "id", packageName)
            )
            txtViewStatus.text = "Status: " + it.usersList[id].status

            val txtViewHobie = findViewById<TextView>(
                resources.getIdentifier("txtHobby", "id", packageName)
            )
            txtViewHobie.text = "Hobby: " + it.usersList[id].hobby

            val txtViewEmail = findViewById<TextView>(
                resources.getIdentifier("txtEmail", "id", packageName)
            )
            txtViewEmail.text = it.usersList[id].email
        })
    }
}