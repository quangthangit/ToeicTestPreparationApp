package com.example.toeicpreparation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toeicpreparation.data.remote.VocabularyTopic
import com.example.toeicpreparation.data.repository.VocabularyTopicRepository

class VocabularyTopicViewModel : ViewModel() {
    private var repository = VocabularyTopicRepository()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _topics = MutableLiveData<Result<List<VocabularyTopic>>>()
    val topics: LiveData<Result<List<VocabularyTopic>>> = _topics

    fun loadTopicsIfNeeded() {
        if (_topics.value?.getOrNull().isNullOrEmpty()) {
            _loading.value = true
            repository.findAllTopic().observeForever { result ->
                _topics.value = result
                _loading.value = false
            }
        }
    }
}
