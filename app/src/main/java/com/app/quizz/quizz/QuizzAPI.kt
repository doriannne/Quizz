package com.app.quizz.quizz

import retrofit2.Call
import retrofit2.http.GET

interface QuizzAPI {
    @GET("getListOfQuestions.php")
    fun listRepos(): Call<List<QuizzData>?>
}