package com.example.toeicpreparation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toeicpreparation.data.remote.VocabularySubTopic
import com.example.toeicpreparation.data.repository.VocabularySubTopicRepository

class VocabularySubTopicViewModel : ViewModel() {
    private var repository = VocabularySubTopicRepository()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _subTopics = MutableLiveData<Result<List<VocabularySubTopic>>>()
    val subTopics: LiveData<Result<List<VocabularySubTopic>>> = _subTopics

    fun loadData(id: Int) {
        if (_subTopics.value?.getOrNull().isNullOrEmpty()) {
            _loading.value = true
            repository.findAllByTopic(id).observeForever { result ->
                _subTopics.value = result
                _loading.value = false
            }
        }
    }
}