package com.android.cities_application.main.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.widget.SearchView
import com.android.cities_application.databinding.FragmentMainBinding
import com.android.cities_application.main.model.City
import com.android.cities_application.main.viewModel.CityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class MainFragment : Fragment() {

    private val viewModel: CityViewModel by viewModels()
    private lateinit var binding : FragmentMainBinding
    private lateinit var cityAdapter : CityAdapter
    private var citiesList: List<City> = emptyList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMainBinding.inflate(layoutInflater)
        setupRecyclerView()
        getData()
        setupSearchView()

        return binding.root
    }

    private fun setupRecyclerView(){
        cityAdapter = CityAdapter(requireActivity())
        val layoutManager = LinearLayoutManager(context)
        binding.citiesRv.layoutManager = layoutManager
        binding.citiesRv.adapter = cityAdapter

        val dividerItemDecoration = DividerItemDecoration(binding.citiesRv.context, (binding.citiesRv.layoutManager as LinearLayoutManager).orientation)
        binding.citiesRv.addItemDecoration(dividerItemDecoration)
    }

    private fun setupSearchView() {
        binding.filterCity.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCities(newText)
                return true
            }
        })
    }

    private fun getData(){
        viewModel.fetchData()
        viewModel.cities.observe(viewLifecycleOwner) { cities ->
            if(cities != null && !cities.isNullOrEmpty()) {
                citiesList = cities
                cityAdapter.submitList(citiesList)
            }
        }
    }

    private fun filterCities(query: String?) {
        CoroutineScope(Dispatchers.Default).launch {
            val filteredList = if (!query.isNullOrEmpty()) {
                citiesList.filter {
                    it.name.startsWith(query, ignoreCase = true)
                }
            } else {
                citiesList
            }

            withContext(Dispatchers.Main) {
                Log.d("MainFragment", "Filtered List: ${filteredList.size}")
                cityAdapter.submitList(filteredList)
                cityAdapter.notifyDataSetChanged()
            }
        }
    }


}