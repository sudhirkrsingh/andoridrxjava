package com.mega.programthree.view.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.mega.programthree.databinding.FragmentHomeBinding
import com.mega.programthree.model.response.PostModel
import com.mega.programthree.remote.GetPosts
import com.mega.programthree.repo.Repository
import com.mega.programthree.view.adapter.PostAdapter
import com.mega.programthree.viewmodel.HomeViewModel
import com.mega.programthree.viewmodel.ViewModelFactory


class HomeFragment : Fragment() {


    lateinit var binding: FragmentHomeBinding
    lateinit var viewModel: HomeViewModel
    private val service:GetPosts = GetPosts.getPosts()!!.create(GetPosts::class.java)


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        binding= FragmentHomeBinding.inflate(inflater, container, false)

        binding.recview.layoutManager= LinearLayoutManager(context)

        viewModel =
            ViewModelProvider(this, ViewModelFactory(Repository(service))).get(
                HomeViewModel::class.java
            )

        viewModel.getAllPosts()

        viewModel.postList.observe(viewLifecycleOwner, Observer {


            if(it != null) {
                binding.recview.adapter = context?.let { it1 -> PostAdapter(it1, it) }
            }
            else{
                Toast.makeText(context, "Something went wrong", Toast.LENGTH_SHORT).show()
            }
        })


        return binding.root
    }


}