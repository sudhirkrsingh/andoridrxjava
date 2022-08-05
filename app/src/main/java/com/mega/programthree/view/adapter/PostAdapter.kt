package com.mega.programthree.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mega.programthree.databinding.SinglepostBinding
import com.mega.programthree.model.response.PostModel


class PostAdapter() : RecyclerView.Adapter<PostAdapter.PostViewHolder>() {


    var list: List<PostModel>? = null
    lateinit var context: Context


    constructor(context: Context, list: List<PostModel>) : this() {
        this.list = list
        this.context=context
        notifyDataSetChanged()

    }
    class PostViewHolder(binding: SinglepostBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var id: TextView = binding.id
        var title: TextView = binding.title
        var body: TextView = binding.body

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {

        val binding = SinglepostBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PostViewHolder(binding)
    }

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val postCard: PostModel = list!![position]


        holder.id.text= postCard.id.toString()
        holder.title.text=postCard.title
        holder.body.text=postCard.body


    }

    override fun getItemCount(): Int {
        return list!!.size-1
    }

}