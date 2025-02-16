package com.android.cities_application.map

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.cities_application.R
import com.android.cities_application.databinding.FragmentMapBinding
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class MapFragment : Fragment() , OnMapReadyCallback {

   private lateinit var binding: FragmentMapBinding
    private lateinit var googleMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMapBinding.inflate(inflater, container, false)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        return binding.root
    }

    override fun onMapReady(map: GoogleMap) {
        googleMap = map

        val lat = arguments?.getFloat("lat") ?: 0f
        val lon = arguments?.getFloat("lon") ?: 0f
        val cityName = arguments?.getString("cityName") ?: ""
        val location = LatLng(lat.toDouble(), lon.toDouble())

        googleMap.addMarker(MarkerOptions().position(location).title(cityName))
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }


}