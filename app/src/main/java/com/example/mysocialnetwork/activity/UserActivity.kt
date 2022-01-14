package com.example.mysocialnetwork.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.databinding.UserActivityBinding
import com.example.mysocialnetwork.view_model.UserViewModel

class UserActivity : AppCompatActivity() {
    private lateinit var binding: UserActivityBinding
    private lateinit var viewModel: UserViewModel

    @SuppressLint("UseCompatLoadingForDrawables", "SetTextI18n", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        val id = intent.extras?.getInt("id")
        viewModel.loadUserData(id!!)

        viewModel.userLiveData.observe(this, {
            val imgViewIcon = binding.imgProfileIcon
            imgViewIcon.setImageDrawable(
                getDrawable(
                    resources.getIdentifier(
                        it.profilePhoto,
                        null,
                        packageName
                    )
                )
            )

            val txtViewName = binding.txtUserName
            txtViewName.text = it.name

            val txtViewWasOnline = binding.txtWasOnline
            txtViewWasOnline.text = it.wasOnline

            val txtViewStatus = binding.txtStatus
            txtViewStatus.text = "Status: " + it.status

            val txtViewHobie = binding.txtEmail
            txtViewHobie.text = "Hobby: " + it.hobby

            val txtViewEmail = binding.txtEmail
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

    override fun onBackPressed() {
        val setIntent = Intent(this, BaseActivity::class.java)
        startActivity(setIntent)
    }
}