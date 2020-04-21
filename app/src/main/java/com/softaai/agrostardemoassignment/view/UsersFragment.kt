package com.softaai.agrostardemoassignment.view

import android.location.Location
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.softaai.agrostardemoassignment.R
import com.softaai.agrostardemoassignment.adapter.UserRecyclerViewAdapter
import com.softaai.agrostardemoassignment.databinding.FragmentUsersBinding
import com.softaai.agrostardemoassignment.viewmodel.UserViewModel


class UsersFragment : Fragment() {

    lateinit var userViewModel: UserViewModel
    lateinit var binding: FragmentUsersBinding
    lateinit var userRecyclerViewAdapter: UserRecyclerViewAdapter

    lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_users, container, false)
        return binding.root
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        userViewModel = ViewModelProviders.of(this)[UserViewModel::class.java]
        userRecyclerViewAdapter =
            UserRecyclerViewAdapter(this.requireActivity(), userViewModel.userArrayList)

        val layoutManager = LinearLayoutManager(context)

        binding.rvUser.layoutManager = layoutManager
        binding.rvUser.hasFixedSize()
        binding.rvUser.adapter = userRecyclerViewAdapter
        binding.rvUser.addItemDecoration(DividerItemDecoration(context, layoutManager.orientation))

        binding.simpleSwipeRefreshLayout.setOnRefreshListener {
            userRecyclerViewAdapter =
                UserRecyclerViewAdapter(this.requireActivity(), userViewModel.populateList())
            binding.rvUser.adapter = userRecyclerViewAdapter

            binding.simpleSwipeRefreshLayout.isRefreshing = false
        }

        binding.viewModel = userViewModel
    }

    override fun onResume() {
        super.onResume()
        updateLastknownLocation()
    }


    fun updateLastknownLocation() {
        fusedLocationClient =
            LocationServices.getFusedLocationProviderClient(this.requireActivity())
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location.let {
                    binding.llLocation.visibility = View.VISIBLE
                    binding.tvLocation.text = "" + location?.latitude + ", " + location?.longitude
                }
            }
    }
}