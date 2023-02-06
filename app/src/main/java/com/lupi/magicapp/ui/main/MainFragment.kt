package com.lupi.magicapp.ui.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.lupi.magicapp.R
import com.lupi.magicapp.databinding.FragmentMainBinding
import com.lupi.magicapp.utils.viewBinding

class MainFragment : Fragment(R.layout.fragment_main) {

    private val mainFragmentViewBinding : FragmentMainBinding by viewBinding(FragmentMainBinding::bind)

    //En principio nuestro MainFragment no va a requirir de logica, pero lo dejo montado por si acaso.
    private val mainFragmentViewModel : MainViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mainFragmentViewBinding.btnContinuar.setOnClickListener {
            findNavController().navigate(R.id.action_mainFragment_to_masterFragment)
        }
        
    }

}