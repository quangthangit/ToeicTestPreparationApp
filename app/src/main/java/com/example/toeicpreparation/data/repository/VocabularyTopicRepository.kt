package com.example.toeicpreparation.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.toeicpreparation.data.api.RetrofitClient
import com.example.toeicpreparation.data.remote.VocabularyTopic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VocabularyTopicRepository() {

    fun findAllTopic(): LiveData<Result<List<VocabularyTopic>>> {
        val result = MutableLiveData<Result<List<VocabularyTopic>>>()
        RetrofitClient.apiService.findAllTopic().enqueue(object : Callback<List<VocabularyTopic>> {
            override fun onResponse(
                call: Call<List<VocabularyTopic>>,
                response: Response<List<VocabularyTopic>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    result.value = Result.success(response.body()!!)
                } else {
                    result.value = Result.failure(Exception("Lỗi: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<VocabularyTopic>>, t: Throwable) {
                result.value = Result.failure(t)
            }
        })

        return result
    }
}
