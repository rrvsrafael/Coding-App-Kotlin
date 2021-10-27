package com.mr.codingapp2.ui.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.mr.codingapp2.R
import com.mr.codingapp2.base.BaseFragment
import com.mr.codingapp2.databinding.FragmentRepoBinding

class RepoFragment : BaseFragment() {
    private val args: RepoFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setTitle(args.repo.name)
        return inflater.inflate(R.layout.fragment_repo, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentRepoBinding.bind(view)

        binding.webView.loadUrl(args.repo.url)
    }
}
