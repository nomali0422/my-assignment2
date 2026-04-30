package com.example.lifehackapp

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class QuizActivity : AppCompatActivity() {
    var index = 0
    var score = 0
    lateinit var questionText: TextView
    lateinit var feedbackText: TextView
    val questions = arrayOf(
        "Goldfish have a 3-second memory",
        "Lightning never strikes the same place twice",
        "Humans and dinosaurs lived at the same time",
        "You should wait 24 hours before reporting a missing person",
        "Cracking your knuckles causes arthritis",
        "Shaving hair makes it grow back thicker",
        "Mount Everest is the tallest mountain from base to peak",
        "Sugar makes kids hyperactive",
        "Bulls get angry when they see the color red",
        "Different parts of your tongue detect different tastes"
    )

    val answers = arrayOf(false, false, false, false, false, false, false, false, false, false)

    val explanations = arrayOf(
        "Myth: Goldfish can remember things for months. The 3-second memory is false.",
        "Myth: The Empire State Building gets hit ~25 times per year. Tall objects are struck repeatedly.",
        "Myth: Dinosaurs went extinct 65 million years ago. Humans appeared ~300,000 years ago.",
        "Myth: You can report a missing person immediately. Waiting wastes critical time.",
        "Myth: Studies show no link between knuckle cracking and arthritis. It’s just gas bubbles.",
        "Myth: Shaving cuts hair at a blunt tip, so it feels coarse. It doesn’t change growth rate.",
        "Myth: Everest is highest above sea level. Mauna Kea is tallest from base to peak.",
        "Myth: Multiple studies debunk this. Parents expect hyperactivity, so they see it.",
        "Myth: Bulls are colorblind to red. They react to the cape’s movement, not color.",
        "Myth: The tongue map was debunked. All taste buds detect sweet, salty, sour, bitter, umami."
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_quiz)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        questionText = findViewById(R.id.questionText)
        feedbackText = findViewById(R.id.feedbackText)

        val hackButton = findViewById<Button>(R.id.hackButton)
        val mythButton = findViewById<Button>(R.id.mythButton)
        val nextButton = findViewById<Button>(R.id.nextButton)

        loadQuestion()

        hackButton.setOnClickListener { checkAnswer(true) }
        mythButton.setOnClickListener { checkAnswer(false) }

        nextButton.setOnClickListener {
            index++

            if (index < questions.size) {
                loadQuestion()
                feedbackText.text = ""
            } else {
                val intent = Intent(this, ScoreActivity::class.java)
                intent.putExtra("score", score)
                intent.putExtra("total", questions.size)
                startActivity(intent)
                finish()
            }
        }
    }

    fun loadQuestion() {
        questionText.text = questions[index]
    }

    fun checkAnswer(userAnswer: Boolean) {
        if (userAnswer == answers[index]) {
            feedbackText.text = "Correct! 🎉\n${explanations[index]}"
            score++
        } else {
            feedbackText.text = "Wrong! ❌\n${explanations[index]}"
        }
    }
}