package com.a.ayc.home.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.a.ayc.R
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home_tab1.*

@AndroidEntryPoint
class HomeFragmentTab1 : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_tab1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.name.observe(viewLifecycleOwner, { name ->
            if (name != null) {
                directs_progress_bar.visibility = View.GONE
                tv_home_2.text = name
            }
        })
        directs_progress_bar.visibility = View.VISIBLE
        homeViewModel.getUserInfo()

    }
}