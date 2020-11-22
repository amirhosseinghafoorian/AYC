package com.a.ayc.home.ui

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.a.ayc.R
import kotlinx.android.synthetic.main.fragment_home_tab1.*

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
            if (name != null) tv_home_2.text = name
        })

        homeViewModel.getUserInfo()

        btn_home_1.setOnClickListener {
            showLogoutDialog()
        }

    }

    private fun showLogoutDialog() {
        val builder = AlertDialog.Builder(requireContext())
        builder.setMessage("Are you sure you want to logout ? ")
            .setPositiveButton("Yes") { _, _ ->
                homeViewModel.logout()
                findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToAuthentication())
            }
            .setNegativeButton("No") { dialog, _ -> // User cancelled the dialog
                dialog.dismiss()
            }
        val alertDialog = builder.create()
        alertDialog.show()

    }
}