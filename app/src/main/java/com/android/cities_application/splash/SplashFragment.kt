package com.android.cities_application.splash

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.cities_application.R
import com.android.cities_application.databinding.FragmentSplashBinding



class SplashFragment : Fragment() {

    private lateinit var binding : FragmentSplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentSplashBinding.inflate(layoutInflater)

        binding.lottieAnimationView.setAnimation("splash_animation.json")
        binding.lottieAnimationView.playAnimation()

        binding.lottieAnimationView.animate().translationX(1500F).setDuration(1000).setStartDelay(4000)
        Handler(Looper.getMainLooper()).postDelayed({
            val navController = Navigation.findNavController(context as Activity, R.id.nav_host_fragment)
            navController.navigate(R.id.action_splashFragment_to_mainFragment)
        }, 10000) // 10 seconds delay
        return binding.root
    }

}