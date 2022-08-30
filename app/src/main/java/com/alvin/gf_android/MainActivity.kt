package com.alvin.gf_android

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.alvin.gf_android.databinding.ActivityMainBinding
import com.alvin.gf_android.ui.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.btnGlobalActivity.setOnClickListener(onClickListener)
        binding.btnLocalActivity.setOnClickListener(onClickListener)
        binding.btnListActivity.setOnClickListener(onClickListener)
        binding.btnExpandActivity.setOnClickListener(onClickListener)
        binding.btnTikTokActivity.setOnClickListener(onClickListener)
        binding.btnDefVideo.setOnClickListener(onClickListener)
    }

    private val onClickListener = View.OnClickListener {
        when (it) {
            binding.btnGlobalActivity -> {
                startActivity(GlobalActivity.newIntent(this))
            }
            binding.btnLocalActivity -> {
                startActivity(LocalActivity.newIntent(this))
            }
            binding.btnListActivity -> {
                startActivity(NormalListActivity.newIntent(this))
            }
            binding.btnExpandActivity -> {
                startActivity(ExpandActivity.newIntent(this))
            }
            binding.btnTikTokActivity -> {
                startActivity(TikTokActivity.newIntent(this))
            }
            binding.btnDefVideo -> {
                startActivity(DefVideoActivity.newIntent(this))
            }
        }
    }
}