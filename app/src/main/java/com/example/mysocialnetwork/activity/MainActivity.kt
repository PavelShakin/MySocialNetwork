package com.example.mysocialnetwork.activity

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.view_model.UsersListViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UsersListViewModel

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UsersListViewModel::class.java)
        viewModel.fillUpDatabase()
        viewModel.loadUsersData()

        viewModel.userLiveData.observe(this, {
            for (id in 0..8) {
                val layout = findViewById<ConstraintLayout>(
                    resources.getIdentifier("user$id", "id", packageName)
                )

                val txtViewName = findViewById<TextView>(
                    resources.getIdentifier("usrName$id", "id", packageName)
                )
                txtViewName.text = it[id].name

                val txtViewTime = findViewById<TextView>(
                    resources.getIdentifier("usrTime$id", "id", packageName)
                )
                txtViewTime.text = it[id].wasOnline

                val imgViewIcon = findViewById<ImageView>(
                    resources.getIdentifier("profileIcon$id", "id", packageName)
                )
                imgViewIcon.setImageDrawable(
                    getDrawable(
                        resources.getIdentifier(
                            it[id].profilePhoto,
                            null,
                            packageName
                        )
                    )
                )

                setOnClickListener(layout, id)
            }
        })
    }

    private fun setOnClickListener(layout: ConstraintLayout, id: Int) {
        layout.setOnClickListener {
            val intent = Intent(this, UserActivity::class.java)
            intent.putExtra("id", id)
            startActivity(intent)
        }
    }
}
