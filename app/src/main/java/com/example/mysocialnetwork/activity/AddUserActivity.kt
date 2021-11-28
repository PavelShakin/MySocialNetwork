package com.example.mysocialnetwork.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.databinding.AddUserActivityBinding
import com.example.mysocialnetwork.user.User
import com.example.mysocialnetwork.user.UsersData
import com.example.mysocialnetwork.view_model.AddUserViewModel

class AddUserActivity : AppCompatActivity() {

    private lateinit var binding: AddUserActivityBinding
    private lateinit var viewModel: AddUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = AddUserActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e("", UsersData().getUsersData().size.toString())

        viewModel = ViewModelProvider(this).get(AddUserViewModel::class.java)
        binding.btnSaveUser.setOnClickListener {
            saveNewUser(viewModel)
            Log.e("", UsersData().getUsersData().size.toString())
            val intent = Intent(this, UserListActivity::class.java)
            startActivity(intent)
        }
    }

    private fun saveNewUser(viewModel: AddUserViewModel) {
        var userId = UsersData().getUsersData().size
        userId++

        viewModel.insertNewUser(
            User(
                userId,
                binding.txtSetName.text.toString(),
                "@drawable/ic_launcher_background",
                binding.txtSetWasOnline.text.toString(),
                binding.txtSetHobby.text.toString(),
                binding.txtSetEmail.text.toString(),
                binding.txtSetStatus.text.toString()
            )
        )

        UsersData().addNewUser(
            User(
                userId,
                binding.txtSetName.text.toString(),
                "@drawable/ic_launcher_background",
                binding.txtSetWasOnline.text.toString(),
                binding.txtSetHobby.text.toString(),
                binding.txtSetEmail.text.toString(),
                binding.txtSetStatus.text.toString()
            )
        )
    }
}