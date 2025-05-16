package com.example.toeicpreparation.ui.activity

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.toeicpreparation.R
import com.example.toeicpreparation.data.remote.VocabularySubTopic
import com.example.toeicpreparation.ui.adapter.SubTopicAdapter
import com.example.toeicpreparation.ui.viewmodel.VocabularySubTopicViewModel
import com.example.toeicpreparation.ui.viewmodel.VocabularyTopicViewModel
import org.w3c.dom.Text


class TopicActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: SubTopicAdapter
    private lateinit var btnClose : ImageView

    private lateinit var viewModel: VocabularySubTopicViewModel
    private lateinit var progressBar: ProgressBar

    private lateinit var emptyView : View

    private lateinit var name : TextView
    private lateinit var totalWord : TextView
    private lateinit var totalLesson : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_topic)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val topicId = intent.getIntExtra("vocabularyTopicId", -1)

        name = findViewById(R.id.name)

        name.text = intent.getStringExtra("nameTopic")

        totalWord = findViewById<TextView>(R.id.totalWord)
        totalWord.text = intent.getIntExtra("totalWord", -1).toString()

        totalLesson = findViewById<TextView>(R.id.totalLesson)
        totalLesson.text = intent.getIntExtra("totalLesson", -1).toString()

        btnClose = findViewById<ImageView>(R.id.btn_close_topic)
        btnClose.setOnClickListener {
            super.onBackPressed()
        }
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.listSubTopic)

        emptyView = findViewById<View>(R.id.emptyView)

        viewModel = ViewModelProvider(this)[VocabularySubTopicViewModel::class.java]

        adapter = SubTopicAdapter(emptyList())

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        viewModel.loading.observe(this) { isLoading ->
            progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        }

        viewModel.subTopics.observe(this) { result ->
            result.onSuccess { list ->
                if (list.isEmpty()) {
//                    recyclerView.visibility = View.GONE
                    emptyView.visibility = View.VISIBLE
                } else {
                    adapter.updateData(list)
                    recyclerView.visibility = View.VISIBLE
                    emptyView.visibility = View.GONE
                }
            }.onFailure {
//                recyclerView.visibility = View.GONE
                emptyView.visibility = View.VISIBLE
            }
        }
        viewModel.loadData(topicId)
    }
}