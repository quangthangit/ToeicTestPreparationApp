package com.example.toeicpreparation.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.toeicpreparation.data.api.RetrofitClient
import com.example.toeicpreparation.data.remote.VocabularySubTopic
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class VocabularySubTopicRepository {

    fun findAllByTopic(id: Int) : LiveData<Result<List<VocabularySubTopic>>> {
        val result = MutableLiveData<Result<List<VocabularySubTopic>>>()
        RetrofitClient.apiService.findAllSubTopicByTopic(id).enqueue(object : Callback<List<VocabularySubTopic>> {
            override fun onResponse(
                call: Call<List<VocabularySubTopic>>,
                response: Response<List<VocabularySubTopic>>
            ) {
                if (response.isSuccessful && response.body() != null) {
                    result.value = Result.success(response.body()!!)
                } else {
                    result.value = Result.failure(Exception("Lỗi: ${response.code()}"))
                }
            }

            override fun onFailure(call: Call<List<VocabularySubTopic>>, t: Throwable) {
                result.value = Result.failure(t)
            }
        })

        return result
    }

}