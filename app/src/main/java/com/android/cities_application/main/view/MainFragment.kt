package com.android.cities_application.main.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.cities_application.R
import com.android.cities_application.databinding.FragmentMainBinding
import com.android.cities_application.main.viewModel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: CityViewModel by viewModels()
    private lateinit var binding : FragmentMainBinding
    private lateinit var adapter : CityAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        adapter = CityAdapter(requireActivity())
        val layoutManager = LinearLayoutManager(context)
        binding.citiesRv.layoutManager = layoutManager
        binding.citiesRv.adapter = adapter

        val dividerItemDecoration = DividerItemDecoration(binding.citiesRv.context, (binding.citiesRv.layoutManager as LinearLayoutManager).orientation)
        binding.citiesRv.addItemDecoration(dividerItemDecoration)

        getData()

        return binding.root
    }

    private fun getData(){
        viewModel.fetchData()
        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            if(cities != null && !cities.isNullOrEmpty()) {
                adapter.submitList(cities)
            }
        }
    }


}