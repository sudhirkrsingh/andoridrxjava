package com.mega.programthree.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mega.programthree.R
import com.mega.programthree.view.fragment.HomeFragment
import com.mega.programthree.view.fragment.StarBucksFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction()
            .replace(R.id.layout_1,StarBucksFragment())
            .commit()
    }
}