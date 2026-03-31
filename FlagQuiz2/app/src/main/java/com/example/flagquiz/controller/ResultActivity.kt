package com.example.flagquiz.controller

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquiz.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name = intent.getStringExtra("name") ?: ""
        val score = intent.getIntExtra("score", 0)

        val nameText = findViewById<TextView>(R.id.userNameText)
        val scoreText = findViewById<TextView>(R.id.scoreText)
        val button = findViewById<Button>(R.id.startQuizButton)

        nameText.text = "Nome: $name"
        scoreText.text = "Pontuação: $score"

        button.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}