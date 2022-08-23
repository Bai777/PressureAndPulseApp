package com.example.pressureandpulseapp.ui

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.pressureandpulseapp.databinding.FragmentMainBinding
import com.example.pressureandpulseapp.databinding.NewItemDialogLayoutBinding
import com.example.pressureandpulseapp.domain.Item
import java.text.SimpleDateFormat
import java.util.*


class MainFragment : Fragment() {

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private val adapter = MainRecyclerAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecycler()
        initFabListener()
    }

    private fun initFabListener() {
        binding.fab.setOnClickListener { showNewItemDialog() }
    }

    private fun initRecycler() {
        val observer = Observer<List<Item>> {
            adapter.setData(it)
        }
        binding.recycler.adapter = adapter
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.requestPosts()
    }

    private fun showNewItemDialog() {
        val dialog = Dialog(requireContext())
        val filterBinding = NewItemDialogLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(filterBinding.root)
        dialog.show()

        filterBinding.btn.setOnClickListener {
            @SuppressLint("SimpleDateFormat")
            val timeFormat = SimpleDateFormat("hh:mm")

            @SuppressLint("SimpleDateFormat")
            val dateFormat = SimpleDateFormat("dd MMM")

            val newItem = Item(
                0,
                dateFormat.format(Date()).toString(),
                timeFormat.format(Date()).toString(),
                filterBinding.pressure1.text.toString().toInt(),
                filterBinding.pressure2.text.toString().toInt(),
                filterBinding.pulse.text.toString().toInt()
            )

            viewModel.saveNewItem(newItem)
            dialog.cancel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}