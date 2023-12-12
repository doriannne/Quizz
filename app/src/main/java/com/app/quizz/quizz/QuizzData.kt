package com.app.quizz.quizz

data class QuizzData(
    val id: Int,
    val question: String,
    val value1: String,
    val value2: String,
    val value3: String,
    val value4: String,
    val trueanswear : String
)