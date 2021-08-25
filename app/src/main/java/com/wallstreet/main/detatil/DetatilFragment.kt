package com.wallstreet.main.detatil

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.wallstreet.R
import com.wallstreet.databinding.FragmentDetatilBinding
import com.wallstreet.main.home.viewmodel.HomeViewModel



class DetatilFragment : Fragment() {
    private lateinit var binding: FragmentDetatilBinding

    private lateinit var viewModel: HomeViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_detatil, container, false)

        var name : String? = requireArguments().getString("name")
        var domain : String? = requireArguments().getString("domain")
        var country : String? = requireArguments().getString("country")
        binding.textViewname.text = name
        binding.textViewCountry.text = country
        binding.textViewwebsite.text = domain

        return binding.root
    }
}