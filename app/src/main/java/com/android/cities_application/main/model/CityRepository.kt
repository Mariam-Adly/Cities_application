package com.android.cities_application.main.model

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class CityRepository @Inject constructor(
    @ApplicationContext private val context: Context
) {

    fun getCities(): List<City> {
        val jsonString = ReadJsonFromAsset.readJsonFromAssets(context,"cities.json")
        return jsonString?.let {
            parseCitiesJson(it)
        } ?: emptyList()
    }

    private fun parseCitiesJson(jsonString: String): List<City> {
        val gson = Gson()
        val cityListType = object : TypeToken<List<City>>() {}.type
        return gson.fromJson(jsonString, cityListType)
    }
}