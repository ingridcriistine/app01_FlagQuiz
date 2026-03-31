package com.example.flagquiz.controller

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquiz.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startQuiz(view: View) {
        val name = findViewById<EditText>(R.id.nameEditText).text.toString().trim()

        if (name.isEmpty()) {
            Toast.makeText(this, "Por favor, insira seu nome", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, QuizActivity::class.java)
        intent.putExtra("name", name)
        startActivity(intent)
    }
}