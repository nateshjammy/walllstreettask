package com.wallstreet.main.home.view

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.wallstreet.Application
import com.wallstreet.R
import com.wallstreet.data.api.ApiHelper
import com.wallstreet.data.api.RetrofitBuilder
import com.wallstreet.databinding.FragmentHomeBinding
import com.wallstreet.main.base.ViewModelFactory
import com.wallstreet.main.home.adapter.UniversityAdapter
import com.wallstreet.main.home.viewmodel.HomeViewModel


/**
 * Home Fragment
 */
class HomeFragment : Fragment() {


    private lateinit var viewModel: HomeViewModel

    private lateinit var adapter: UniversityAdapter

    private lateinit var  binding: FragmentHomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_home, container, false)

        setupViewModel()

        setupUI()


        return binding.root

    }


    private fun setupViewModel() {
        viewModel = ViewModelProviders.of(
            this,
            ViewModelFactory(ApiHelper(RetrofitBuilder.apiService))
        ).get(HomeViewModel::class.java)
    }

    private fun setupUI() {
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        adapter = UniversityAdapter()
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(
                requireContext(),
                (binding.recyclerView.layoutManager as LinearLayoutManager).orientation
            )
        )
        binding.recyclerView.adapter = adapter
        setupObservers()

        binding.SearchEditText.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(s: Editable) {}

            override fun beforeTextChanged(s: CharSequence, start: Int,
                                           count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence, start: Int,
                                       before: Int, count: Int) {
                viewModel.searchString.postValue(s.toString())

            }
        })
    }


    private fun setupObservers() {
        viewModel.universityList?.observe(viewLifecycleOwner, Observer {

            adapter.submitList(it)

        })

        viewModel.mShowProgress.observe(viewLifecycleOwner, {
            if(it && adapter.itemCount == 0){
                binding.progressBar.visibility = View.VISIBLE
            }else{
                binding.progressBar.visibility = View.GONE
            }
        })

        viewModel.searchString.observe(viewLifecycleOwner, Observer {
            viewModel.refresh()
        })
    }





}