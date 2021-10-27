package com.mr.codingapp2.ui.home

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.mr.codingapp2.R
import com.mr.codingapp2.base.BaseFragment
import com.mr.codingapp2.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment(R.layout.fragment_home) {
    private val vm by lazy {
        getViewModel(HomeViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentHomeBinding.bind(view)

        val adapter = ReposAdapter { repo ->
            findNavController().navigate(HomeFragmentDirections.actionHomeToRepo(repo))
        }
        binding.recyclerView.adapter = adapter
        binding.recyclerView.visibility = View.INVISIBLE
        binding.progressBar.visibility = View.VISIBLE

        vm.reposLiveData.observe(viewLifecycleOwner, Observer { repos ->
            adapter.submitList(repos)
            binding.recyclerView.visibility = View.VISIBLE
            binding.progressBar.visibility = View.INVISIBLE
        })
    }
}
