package com.example.toeicpreparation.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.toeicpreparation.data.remote.Vocabulary
import com.example.toeicpreparation.data.repository.VocabularyRepository

class VocabularyViewModel : ViewModel() {
    private val repository = VocabularyRepository()

    private val _loading = MutableLiveData<Boolean>()
    val loading: LiveData<Boolean> = _loading

    private val _vocabulary = MutableLiveData<Result<List<Vocabulary>>>()
    val vocabulary: LiveData<Result<List<Vocabulary>>> = _vocabulary

    private var isLoaded = false

    fun loadData(id: Int) {
        if (isLoaded) return
        isLoaded = true

        _loading.value = true
        repository.findAllBySubTopic(id).observeForever { result ->
            _vocabulary.value = result
            _loading.value = false
        }
    }
}
