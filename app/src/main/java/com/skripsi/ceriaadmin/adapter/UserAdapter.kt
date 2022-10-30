package com.skripsi.ceriaadmin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.ceriaadmin.databinding.ItemUserBinding
import com.skripsi.ceriaadmin.model.User

class UserAdapter(val userList: List<User>) : RecyclerView.Adapter<UserAdapter.MyViewHolder>(){

    inner class MyViewHolder(val binding: ItemUserBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(user: User) {
            binding.imgUser.setImageResource(user.img_user)
            binding.namaUser.text = user.nama_user
            binding.nisUser.text = user.nis_user
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(userList.get(position))
    }

    override fun getItemCount(): Int {
        return userList.size
    }

}