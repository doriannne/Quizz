package com.app.quizz.quizz

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class QuizViewModel : ViewModel() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://quiz3456590.api4glisk.space/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    private val service: QuizzAPI = retrofit.create(QuizzAPI::class.java)

    fun getList() : MutableLiveData<List<QuizzData>?> {
        val liveData = MutableLiveData<List<QuizzData>?>()
        service.listRepos().enqueue(object : Callback<List<QuizzData>?> {
            override fun onResponse(
                call: Call<List<QuizzData>?>,
                response: Response<List<QuizzData>?>
            ) {
                if (response.isSuccessful) {
                    liveData.value = response.body()
                }
            }

            override fun onFailure(call: Call<List<QuizzData>?>, t: Throwable) {
                println(t.localizedMessage)
            }

        })

        return liveData
    }

}