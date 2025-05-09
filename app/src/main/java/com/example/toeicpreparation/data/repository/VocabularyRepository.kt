package com.example.toeicpreparation.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.toeicpreparation.data.api.RetrofitClient
import com.example.toeicpreparation.data.remote.Vocabulary
import com.example.toeicpreparation.data.remote.VocabularySubTopic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VocabularyRepository {
    fun findAllBySubTopic(id: Int) : LiveData<Result<List<Vocabulary>>> {
        val result = MutableLiveData<Result<List<Vocabulary>>>()
        RetrofitClient.apiService.findAllVocabularyBySubTopic(id).enqueue(object : Callback<List<Vocabulary>> {
            override fun onResponse(
                call: Call<List<Vocabulary>>,
                response: Response<List<Vocabulary>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    result.value = Result.success(response.body()!!)
                } else {
                    result.value = Result.failure(Exception("Lỗi: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<Vocabulary>>, t: Throwable) {
                result.value = Result.failure(t)
            }
        })

        return result
    }
}