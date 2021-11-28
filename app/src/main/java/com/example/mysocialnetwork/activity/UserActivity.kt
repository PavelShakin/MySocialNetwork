package com.example.mysocialnetwork.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.view_model.UserViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.user_activity)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val id = intent.extras?.getInt("id")
        viewModel.loadUserData(id!!)

        viewModel.userLiveData.observe(this, {
            val imgViewIcon = findViewById<ImageView>(
                resources.getIdentifier("imgProfileIcon", "id", packageName)
            )
            imgViewIcon.setImageDrawable(
                getDrawable(
                    resources.getIdentifier(
                        it.profilePhoto,
                        null,
                        packageName
                    )
                )
            )

            val txtViewName = findViewById<TextView>(
                resources.getIdentifier("txtUserName", "id", packageName)
            )
            txtViewName.text = it.name

            val txtViewWasOnline = findViewById<TextView>(
                resources.getIdentifier("txtWasOnline", "id", packageName)
            )
            txtViewWasOnline.text = it.wasOnline

            val txtViewStatus = findViewById<TextView>(
                resources.getIdentifier("txtStatus", "id", packageName)
            )
            txtViewStatus.text = "Status: " + it.status

            val txtViewHobie = findViewById<TextView>(
                resources.getIdentifier("txtHobby", "id", packageName)
            )
            txtViewHobie.text = "Hobby: " + it.hobby

            val txtViewEmail = findViewById<TextView>(
                resources.getIdentifier("txtEmail", "id", packageName)
            )
            txtViewEmail.text = it.email
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_edit_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = intent.extras?.getInt("id")

        return when (item.itemId) {
            R.id.editItem -> {
                val intent = Intent(this, EditUserActivity::class.java)
                intent.putExtra("id", id)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}