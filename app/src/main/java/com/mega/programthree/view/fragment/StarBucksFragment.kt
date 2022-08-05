package com.mega.programthree.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.mega.programthree.R
import com.mega.programthree.databinding.ActivityMainBinding.inflate
import com.mega.programthree.databinding.FragmentStarBucksBinding
import com.mega.programthree.view.activity.MainActivity
import com.mega.programthree.viewmodel.StarBucksViewModel


class StarBucksFragment : Fragment() {

    lateinit var binding:FragmentStarBucksBinding

    lateinit var viewModel:StarBucksViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentStarBucksBinding.inflate(inflater, container, false)

        viewModel= ViewModelProvider(this).get(StarBucksViewModel::class.java)
        binding.viewModel=viewModel


        context?.let { viewModel.setContext(it) }
        context?.let { viewModel.setActivityContext(requireContext()) }

        binding.lifecycleOwner=this





        return binding.root
    }


}