package com.example.test

import android.os.Bundle
import android.widget.TextView
import android.widget.Button
import androidx.activity.ComponentActivity

class SecondActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val textViewResult = findViewById<TextView>(R.id.textView)
        val backButton = findViewById<Button>(R.id.backButton)

        val receivedText = intent.getStringExtra("EXTRA_TEXT")
        textViewResult.text = receivedText

        backButton.setOnClickListener {
            finish()
        }

    }
}