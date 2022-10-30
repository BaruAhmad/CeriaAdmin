package com.skripsi.ceriaadmin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.ceriaadmin.adapter.ListKuisAdapter
import com.skripsi.ceriaadmin.databinding.FragmentKuisBinding
import com.skripsi.ceriaadmin.model.ListKuis

class KuisFragment : Fragment() {

    private var _binding: FragmentKuisBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListKuisAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listKuis: ArrayList<ListKuis>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentKuisBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        List_Kuis()
        recyclerView = binding.listKuis
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        adapter = ListKuisAdapter(listKuis)
        recyclerView.adapter = adapter
    }

    private fun List_Kuis() {
        listKuis = arrayListOf(
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
            ListKuis("Judul Cerita"),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}