package com.example.flagquiz.controller

import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.flagquiz.R
import com.example.flagquiz.model.Flag

class QuizActivity : AppCompatActivity() {
    private var currentQuestionIndex = 0
    private var correctAnswers = 0
    private lateinit var flags: List<Flag>
    private lateinit var selectedFlags: List<Flag>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)

        val mainView = findViewById<View>(R.id.main)
        ViewCompat.setOnApplyWindowInsetsListener(mainView) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets

        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU){
            intent.getSerializableExtra("name")
        } else {
            intent.getSerializableExtra("name") as? String
        }

        flags = listOf(
            Flag("Argentina", R.drawable.flag_argentina),
            Flag("Austrália", R.drawable.flag_australia),
            Flag("Brasil", R.drawable.flag_brasil),
            Flag("Canadá", R.drawable.flag_canada),
            Flag("China", R.drawable.flag_china),
            Flag("Egito", R.drawable.flag_egito),
            Flag("França", R.drawable.flag_franca),
            Flag("Alemanha", R.drawable.flag_alemanha),
            Flag("Índia", R.drawable.flag_india),
            Flag("Itália", R.drawable.flag_italia),
            Flag("Japão", R.drawable.flag_japao),
            Flag("México", R.drawable.flag_mexico),
            Flag("Portugal", R.drawable.flag_portugal),
            Flag("Rússia", R.drawable.flag_russia),
            Flag("Estados Unidos", R.drawable.flag_estados_unidos),
            Flag("Nova Zelândia", R.drawable.flag_nova_zelandia),
            Flag("Venezuela", R.drawable.flag_venezuela),
            Flag("Paraguai", R.drawable.flag_paraguai),
            Flag("Tailândia", R.drawable.flag_tailandia),
            Flag("Uruguai", R.drawable.flag_uruguai),
            Flag("Espanha", R.drawable.flag_espanha),
            Flag("Indonésia", R.drawable.flag_indonesia),
            Flag("Irlanda", R.drawable.flag_irlanda),
            Flag("Madagascar", R.drawable.flag_madagascar),
            Flag("Peru", R.drawable.flag_peru),
            Flag("Polônia", R.drawable.flag_polonia),
            Flag("Reino Unido", R.drawable.flag_reino_unido),
            Flag("Chile", R.drawable.flag_chile),
            Flag("Colômbia", R.drawable.flag_colombia)
        )

        selectedFlags = flags.shuffled().take(5)

        showNextFlag()
    }

    fun showNextFlag(){
        if(currentQuestionIndex < selectedFlags.size) {
            val imgFlag: ImageView = findViewById(R.id.imgFlag)
            val currentFlag = selectedFlags[currentQuestionIndex]
            imgFlag.setImageResource(currentFlag.imgFlag)

            val countTextView: TextView = findViewById(R.id.countTextView)
            countTextView.text = "${currentQuestionIndex + 1} / ${selectedFlags.size}"

        }
    }
    fun checkAnswer(view: View) {
        val editText = findViewById<TextView>(R.id.answerEditText)
        val answer = editText.text.toString().trim()
        val statusTextView = findViewById<TextView>(R.id.statusTextView)
        val correctFlag = selectedFlags[currentQuestionIndex]

        if(answer.equals(correctFlag.name, ignoreCase = true)){
            correctAnswers++

            statusTextView.setTextColor(android.graphics.Color.GREEN)
            statusTextView.text = "Correto!"
        } else {
            statusTextView.setTextColor(android.graphics.Color.RED)
            statusTextView.text = "Errado! A resposta é ${correctFlag.name}!"
        }

        editText.text = ""
        currentQuestionIndex++
    }
}