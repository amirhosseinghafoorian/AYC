package com.a.ayc.home.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.a.ayc.R
import kotlinx.android.synthetic.main.fragment_home_tab2.*

class HomeFragmentTab2 : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_tab2, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val users = mutableListOf<String>()
        repeat(100){
            users.add("user : $it")
        }

        val myAdapter = UserListAdapter(users)

        home_page_user_list_recycler.apply {
            adapter = myAdapter
        }
    }

}