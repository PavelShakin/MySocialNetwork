package com.example.mysocialnetwork.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.user.User
import com.example.mysocialnetwork.view_model.EditUserViewModel
import com.example.mysocialnetwork.view_model.UserDetailsViewModel

class EditUserActivity : AppCompatActivity() {
    private lateinit var viewModel: EditUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_user_activity)
        viewModel = ViewModelProvider(this).get(EditUserViewModel::class.java)
        val id = intent.extras?.getInt("id")
        viewModel.loadUserData(id!!)

        viewModel.userLiveData.observe(this, {
            val txtViewName = findViewById<TextView>(
                resources.getIdentifier("txtEditUserName", "id", packageName)
            )
            txtViewName.text = it.name

            val txtViewHobie = findViewById<TextView>(
                resources.getIdentifier("txtEditHobby", "id", packageName)
            )
            txtViewHobie.text = it.hobby

            val txtViewEmail = findViewById<TextView>(
                resources.getIdentifier("txtEditEmail", "id", packageName)
            )
            txtViewEmail.text = it.email

            val txtViewStatus = findViewById<TextView>(
                resources.getIdentifier("txtEditStatus", "id", packageName)
            )
            txtViewStatus.text = it.status
        })

        val updateButton = findViewById<Button>(R.id.btnUpdate)

        updateButton.setOnClickListener {
            updateUserInfo()
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }

    private fun updateUserInfo() {
        val newUserName = findViewById<EditText>(R.id.txtEditUserName).text.toString()
        val newHobby = findViewById<EditText>(R.id.txtEditHobby).text.toString()
        val newEmail = findViewById<EditText>(R.id.txtEditEmail).text.toString()
        val newStatus = findViewById<EditText>(R.id.txtEditStatus).text.toString()
        val id = intent.extras?.getInt("id")

        val user = User(
            id!!,
            newUserName,
            viewModel.userLiveData.value!!.profilePhoto,
            viewModel.userLiveData.value!!.wasOnline,
            newHobby,
            newEmail,
            newStatus
        )

        viewModel.updateUserInfo(user)
    }
}