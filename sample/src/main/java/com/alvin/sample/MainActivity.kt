package com.alvin.sample

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.alvin.sample.custom.CustomActivity
import com.alvin.sample.custom.CustomFragmentActivity
import com.alvin.sample.def.DefaultActivity
import com.alvin.sample.def.DefaultFragmentActivity
import com.alvin.sample.local.LocalActivity
import com.alvin.sample.local.LocalFragmentActivity
import com.alvin.sample.sample.SampleActivity
import com.alvin.sample.sample.SampleFragmentActivity
import com.alvin.sample.vm.SampleVMActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val btnDefaultActivity = findViewById<Button>(R.id.btn_default_activity)
        val btnDefaultFragment = findViewById<Button>(R.id.btn_default_fragment)
        val btnCustomActivity = findViewById<Button>(R.id.btn_custom_activity)
        val btnCustomFragment = findViewById<Button>(R.id.btn_custom_fragment)
        val btnLocalActivity = findViewById<Button>(R.id.btn_local_activity)
        val btnLocalFragment = findViewById<Button>(R.id.btn_local_fragment)
        val btnSampleActivity = findViewById<Button>(R.id.btn_sample_activity)
        val btnSampleFragment = findViewById<Button>(R.id.btn_sample_fragment)
        val btnSampleVMActivity = findViewById<Button>(R.id.btn_sample_vm_activity)
        btnDefaultActivity.setOnClickListener {
            startActivity(Intent(this, DefaultActivity::class.java))
        }
        btnDefaultFragment.setOnClickListener {
            startActivity(Intent(this, DefaultFragmentActivity::class.java))
        }
        btnCustomActivity.setOnClickListener {
            startActivity(Intent(this, CustomActivity::class.java))
        }
        btnCustomFragment.setOnClickListener {
            startActivity(Intent(this, CustomFragmentActivity::class.java))
        }
        btnLocalActivity.setOnClickListener {
            startActivity(Intent(this, LocalActivity::class.java))
        }
        btnLocalFragment.setOnClickListener {
            startActivity(Intent(this, LocalFragmentActivity::class.java))
        }
        btnSampleActivity.setOnClickListener {
            startActivity(Intent(this, SampleActivity::class.java))
        }
        btnSampleFragment.setOnClickListener {
            startActivity(Intent(this, SampleFragmentActivity::class.java))
        }
        btnSampleVMActivity.setOnClickListener {
            startActivity(Intent(this, SampleVMActivity::class.java))
        }
    }
}