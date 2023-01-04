package com.example.myapplication.view

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.myapplication.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)
        var temp = intent.getStringExtra("mINPUT")
        var bundle = intent.extras
        var result = bundle?.get("mINPUT").toString()
        binding.tvOutput.text = if(result.isNullOrBlank()) "null" else result + "   " + temp

        binding.btnRun.setOnClickListener {
            var intent = Intent()
            intent.putExtra("result",123456)
            setResult(Activity.RESULT_OK, intent)
            finish()
        }

    }
}