package com.example.mysocialnetwork.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.databinding.AddUserActivityBinding
import com.example.mysocialnetwork.user.User
import com.example.mysocialnetwork.view_model.AddUserViewModel
import kotlinx.coroutines.launch

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: AddUserActivityBinding
    private lateinit var viewModel: AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddUserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        binding.btnSaveUser.setOnClickListener {
            saveNewUser()
            val intent = Intent(this, BaseActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveNewUser() {

        var userId = 0
        viewModel.viewModelScope.launch {
            userId = UserDatabase.getInstance(applicationContext).userDatabaseDao.getAll().size
        }

        userId++

        viewModel.insertNewUser(
            User(
                binding.txtSetName.text.toString(),
                "@drawable/ic_launcher_background",
                binding.txtSetWasOnline.text.toString(),
                binding.txtSetHobby.text.toString(),
                binding.txtSetEmail.text.toString(),
                binding.txtSetStatus.text.toString()
            )
        )
    }

    override fun onBackPressed() {
        val setIntent = Intent(this, BaseActivity::class.java)
        startActivity(setIntent)
    }
}