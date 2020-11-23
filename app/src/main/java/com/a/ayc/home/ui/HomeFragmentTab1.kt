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

    //      directs can be brought by isInDirect in chatViewModel

    private val homeViewModel: HomeViewModel by viewModels()
    private var myAdapter: ChatListAdapter? = null

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

            }
        })

        directs_progress_bar.visibility = View.VISIBLE
        homeViewModel.getUserInfo() // this function's structure should change due to our own use (id , username , name)

    }
}