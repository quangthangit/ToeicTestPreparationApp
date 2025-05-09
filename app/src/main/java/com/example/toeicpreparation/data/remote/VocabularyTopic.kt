package com.example.toeicpreparation.data.remote

data class VocabularyTopic (
    val vocabularyTopicId : Int,
    val name : String,
    val des : String,
    val images : String,
    val totalWord : Int,
    val totalLesson : Int
)
