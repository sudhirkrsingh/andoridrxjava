package com.mega.programthree.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.mega.programthree.databinding.FragmentSecondBinding


class SecondFragment : Fragment() {


    lateinit var binding:FragmentSecondBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding= FragmentSecondBinding.inflate(inflater, container, false)


        val bundle = this.arguments
        if (bundle != null) {
            var name:String=bundle.get("firstName").toString()
            var lastName:String=bundle.get("lastName").toString()
            var birthDate:String=bundle.get("birthDate").toString()
            var mobileNo:String=bundle.get("mobileNo").toString()
            var emailId:String= bundle.get("emailId").toString()

            binding.name.text=name
            binding.lastName.text=lastName
            binding.date.text=birthDate
            binding.phoneNo.text=mobileNo
            binding.emailId.text=emailId

        }





        return binding.root
    }


}