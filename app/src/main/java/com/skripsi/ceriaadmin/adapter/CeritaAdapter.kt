package com.skripsi.ceriaadmin.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.ceriaadmin.databinding.ItemBookBinding
import com.skripsi.ceriaadmin.model.Cerita

class CeritaAdapter(val ceritaList: List<Cerita>) : RecyclerView.Adapter<CeritaAdapter.MyViewHolder>(){

    inner class MyViewHolder(val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bindItem(cerita: Cerita) {
            binding.coverCerita.setImageResource(cerita.cover_cerita)
            binding.judulCerita.text = cerita.judul_cerita
            binding.asalCerita.text = cerita.asal_cerita
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindItem(ceritaList.get(position))
    }

    override fun getItemCount(): Int {
        return ceritaList.size
    }
}