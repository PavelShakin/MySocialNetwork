package com.example.mysocialnetwork.activity

import android.annotation.SuppressLint
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.adapter.OnUserClick
import com.example.mysocialnetwork.adapter.UserAdapter
import com.example.mysocialnetwork.database.UserDatabase
import com.example.mysocialnetwork.databinding.UserListActivityBinding
import com.example.mysocialnetwork.user.User
import com.example.mysocialnetwork.view_model.UsersListViewModel

class UserListActivity : AppCompatActivity(), OnUserClick {
    private lateinit var viewModel: UsersListViewModel
    private lateinit var binding: UserListActivityBinding
    private var userAdapter = UserAdapter(this as OnUserClick)

    @SuppressLint("UseCompatLoadingForDrawables", "ResourceType")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = UserListActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(UsersListViewModel::class.java)
        viewModel.fillUpDatabase()
        viewModel.loadUsersData()

        initAdapter()
        userAdapter.currentList = viewModel.getUsersData().value!!

        viewModel.getUsersData().observe(this, {
            userAdapter.currentList
        })

    }

    private fun initAdapter() {
        binding.recyclerView.apply {
            adapter = userAdapter
        }
    }

    override fun onClick(userId: Int) {
        val intent = Intent(this, UserActivity::class.java)
        intent.putExtra("id", userId)
        startActivity(intent)
    }

    override fun onLongPress(user: User) {
        val builder = android.app.AlertDialog.Builder(this)
        builder.setMessage("Delete user?")
            .setPositiveButton("Yes") { dialog, _ ->
                dialogYesClick(user, dialog)
            }
            .setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }

        builder.show()
    }

    private fun dialogYesClick(user: User, dialogInterface: DialogInterface) {
        UserDatabase.getInstance(this).userDatabaseDao.delete(user)
        dialogInterface.dismiss()
        val intent = Intent(this, UserListActivity::class.java)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_add_user, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.addItem-> {
                val intent = Intent(this, AddUserActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
