package com.example.mysocialnetwork.activity

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.mysocialnetwork.R
import com.example.mysocialnetwork.view_model.UserViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: UserViewModel

    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        viewModel.loadUserData()

        viewModel.userLiveData.observe(this, Observer { it ->
            for (id in 0..8) {
                val layout = findViewById<ConstraintLayout>(
                    resources.getIdentifier("user$id", "id", packageName)
                )

                val txtViewName = findViewById<TextView>(
                    resources.getIdentifier("usrName$id", "id", packageName)
                )
                txtViewName.text = it.usersList[id].name

                val txtViewTime = findViewById<TextView>(
                    resources.getIdentifier("usrTime$id", "id", packageName)
                )
                txtViewTime.text = it.usersList[id].wasOnline

                val imgViewIcon = findViewById<ImageView>(
                    resources.getIdentifier("profileIcon$id", "id", packageName)
                )
                imgViewIcon.setImageDrawable(
                    getDrawable(
                        resources.getIdentifier(
                            it.usersList[id].profilePhoto,
                            null,
                            packageName)
                    )
                )

                setOnClickListener(layout, id)
            }
        })
    }

    private fun setOnClickListener(layout: ConstraintLayout, id: Int) {
        viewModel.setListener(layout, id, this)
    }
}
