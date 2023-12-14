package com.app.quizz.quizz

import java.io.Serializable

data class QuizzData(
    val id: Int,
    val question: String,
    val value1: String,
    val value2: String,
    val value3: String,
    val value4: String,
    val trueanswear : Int,
    var userAnswear : Int = -1
) : Serializable