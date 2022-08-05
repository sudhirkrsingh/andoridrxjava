package com.mega.programthree.repo

import com.mega.programthree.remote.GetPosts

class Repository(private val posts: GetPosts) {
    fun getAllPosts() = posts.getData()
}