package com.skripsi.ceriaadmin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.ceriaadmin.databinding.ItemExtraBinding
import com.skripsi.ceriaadmin.model.ListPuzzle

class ListPuzzleAdapter(private val listPuzzle: ArrayList<ListPuzzle>) : RecyclerView.Adapter<ListPuzzleAdapter.MyViewHolder>() {
    inner class MyViewHolder(val binding: ItemExtraBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(listPuzzle: ListPuzzle) {
            binding.judulCerita.text = listPuzzle.judul_cerita
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemExtraBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(listPuzzle.get(position))
    }

    override fun getItemCount(): Int {
        return listPuzzle.size
    }
}