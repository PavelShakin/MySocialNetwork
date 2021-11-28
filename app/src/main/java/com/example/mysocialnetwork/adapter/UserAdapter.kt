package com.example.mysocialnetwork.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mysocialnetwork.databinding.UserListItemBinding
import com.example.mysocialnetwork.user.User

interface OnUserClick {
    fun onClick(userId: Int)
}

class UserAdapter(val userClick: OnUserClick) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var currentList: List<User> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val binding =
            UserListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        return UserViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is UserViewHolder -> holder.bind(currentList[position])
        }
    }

    override fun getItemCount(): Int = currentList.size

    inner class UserViewHolder(private val binding: UserListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("UseCompatLoadingForDrawables")
        fun bind(item: User) {
            with(binding) {
                txtUsername.text = item.name
                imgUserIcon.setImageDrawable(
                    itemView.resources.getDrawable(
                        itemView.resources.getIdentifier(
                            item.profilePhoto,
                            null,
                            itemView.context.packageName
                        )
                    )
                )
                txtWasOnline.text = item.wasOnline
            }

            binding.userItem.setOnClickListener {
                userClick.onClick(item.userId)
            }
        }
    }
}