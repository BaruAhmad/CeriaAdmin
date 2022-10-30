package com.skripsi.ceriaadmin.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.skripsi.ceriaadmin.adapter.ListPuzzleAdapter
import com.skripsi.ceriaadmin.databinding.FragmentPuzzleBinding
import com.skripsi.ceriaadmin.model.ListPuzzle

class PuzzleFragment : Fragment() {

    private var _binding: FragmentPuzzleBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: ListPuzzleAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var listPuzzle: ArrayList<ListPuzzle>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPuzzleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        List_Kuis()
        recyclerView = binding.listPuzzle
        recyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        recyclerView.setHasFixedSize(true)
        adapter = ListPuzzleAdapter(listPuzzle)
        recyclerView.adapter = adapter
    }

    private fun List_Kuis() {
        listPuzzle = arrayListOf(
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
            ListPuzzle("Judul Cerita"),
        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}