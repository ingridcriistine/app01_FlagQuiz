package com.example.flagquiz.controller

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.flagquiz.R
import com.example.flagquiz.model.Flag

class QuizActivity : AppCompatActivity() {

    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private lateinit var selectedFlags: List<Flag>
    private lateinit var playerName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)

        playerName = intent.getStringExtra("name") ?: ""

        val flags = listOf(
            Flag("Argentina", R.drawable.flag_argentina),
            Flag("Egito", R.drawable.flag_egito),
            Flag("França", R.drawable.flag_franca),
            Flag("Alemanha", R.drawable.flag_alemanha),
            Flag("India", R.drawable.flag_india),
            Flag("Italia", R.drawable.flag_italia),
            Flag("Japao", R.drawable.flag_japao),
            Flag("México", R.drawable.flag_mexico),
            Flag("Portugal", R.drawable.flag_portugal),
            Flag("Russia", R.drawable.flag_russia),
            Flag("Venezuela", R.drawable.flag_venezuela),
            Flag("Paraguai", R.drawable.flag_paraguai),
            Flag("Tailandia", R.drawable.flag_tailandia),
            Flag("Uruguai", R.drawable.flag_uruguai),
            Flag("Espanha", R.drawable.flag_espanha),
            Flag("Irlanda", R.drawable.flag_irlanda),
            Flag("Madagascar", R.drawable.flag_madagascar),
            Flag("Peru", R.drawable.flag_peru),
            Flag("Polonia", R.drawable.flag_polonia)
        )

        selectedFlags = flags.shuffled().take(5)

        showNextFlag()
    }

    fun showNextFlag(view: View? = null) {
        if (currentQuestionIndex < selectedFlags.size) {

            val currentFlag = selectedFlags[currentQuestionIndex]

            findViewById<ImageView>(R.id.imgFlag)
                .setImageResource(currentFlag.imgFlag)

            findViewById<TextView>(R.id.countTextView)
                .text = "${currentQuestionIndex + 1} / ${selectedFlags.size}"

            findViewById<EditText>(R.id.answerEditText).setText("")
            findViewById<TextView>(R.id.statusTextView).text = ""
        }
    }

    fun checkAnswer(view: View) {

        val answer = findViewById<EditText>(R.id.answerEditText)
            .text.toString().trim()

        val status = findViewById<TextView>(R.id.statusTextView)
        val correctFlag = selectedFlags[currentQuestionIndex]

        if (answer.equals(correctFlag.name, true)) {
            correctAnswers++
            status.setTextColor(Color.GREEN)
            status.text = "Correto!"
        } else {
            status.setTextColor(Color.RED)
            status.text = "Errado! A resposta é ${correctFlag.name}"
        }

        currentQuestionIndex++

        if (currentQuestionIndex >= selectedFlags.size) {
            val intent = Intent(this, ResultActivity::class.java)
            intent.putExtra("score", correctAnswers * 20)
            intent.putExtra("name", playerName)
            startActivity(intent)
            finish()
        }
    }
}