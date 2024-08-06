package com.android.cities_application.main.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.cities_application.main.model.CityRepository
import com.android.cities_application.main.model.City
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val repository: CityRepository) : ViewModel() {

    private val _cities = MutableLiveData<List<City>>()
    val cities: LiveData<List<City>> get() = _cities



    init {
        fetchData()
    }

     fun fetchData() {
        _cities.value = repository.getCities()
    }
}