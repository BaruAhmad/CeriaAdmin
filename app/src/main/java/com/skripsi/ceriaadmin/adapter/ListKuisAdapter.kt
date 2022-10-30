package com.skripsi.ceriaadmin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.ceriaadmin.databinding.ItemExtraBinding
import com.skripsi.ceriaadmin.model.ListKuis

class ListKuisAdapter(private val listKuis: ArrayList<ListKuis>) : RecyclerView.Adapter<ListKuisAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemExtraBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(listKuis: ListKuis) {
            binding.judulCerita.text = listKuis.judul_cerita
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemExtraBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(listKuis.get(position))
    }

    override fun getItemCount(): Int {
        return listKuis.size
    }
}